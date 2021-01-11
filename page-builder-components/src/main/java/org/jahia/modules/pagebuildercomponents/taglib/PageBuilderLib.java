/*
 * ==========================================================================================
 * =                            JAHIA'S ENTERPRISE DISTRIBUTION                             =
 * ==========================================================================================
 *
 *                                  http://www.jahia.com
 *
 * JAHIA'S ENTERPRISE DISTRIBUTIONS LICENSING - IMPORTANT INFORMATION
 * ==========================================================================================
 *
 *     Copyright (C) 2002-2020 Jahia Solutions Group. All rights reserved.
 *
 *     This file is part of a Jahia's Enterprise Distribution.
 *
 *     Jahia's Enterprise Distributions must be used in accordance with the terms
 *     contained in the Jahia Solutions Group Terms &amp; Conditions as well as
 *     the Jahia Sustainable Enterprise License (JSEL).
 *
 *     For questions regarding licensing, support, production usage...
 *     please contact our team at sales@jahia.com or go to http://www.jahia.com/license.
 *
 * ==========================================================================================
 */
package org.jahia.modules.pagebuildercomponents.taglib;

import net.htmlparser.jericho.Attribute;
import net.htmlparser.jericho.Attributes;
import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.StartTag;
import org.jahia.modules.pagebuildercomponents.exception.PageBuilderException;
import org.jahia.modules.pagebuildercomponents.handlers.Handlers;
import org.jahia.modules.pagebuildercomponents.model.HtmlElementType;
import org.jahia.modules.pagebuildercomponents.model.TemplateFragment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * This Java utility class contains a series of libraries that parses
 * and slices html source code in order for the JSP to render
 *
 * @author nonico
 */
public final class PageBuilderLib {
    public static final String JAHIA_ATTRIBUTE = "data-jahia-";
    private static final Logger log = LoggerFactory.getLogger(PageBuilderLib.class);
    private static final String REGEX_PATTERNS = String.format("<(.+?)%s(.+?)=(.+?)></(.+?)>", JAHIA_ATTRIBUTE);
    private static final Pattern PATTERN = Pattern.compile(REGEX_PATTERNS, Pattern.MULTILINE);

    /**
     * Adding an non-public constructor to prevent Java from implicitly creating one
     */
    private PageBuilderLib() {
    }

    /**
     * Slices the HTML code into a list of HtmlElement allowing the JSP to determine which strings
     * to render as html and which one not to.
     *
     * @param htmlSource a string that contains html looking code
     * @return a list of html element
     */
    public static List<TemplateFragment> getTemplateFragments(String htmlSource) {
        log.debug("Creating html chunks");
        List<TemplateFragment> htmlElements = new ArrayList<>();
        Matcher matcher = PATTERN.matcher(htmlSource);
        int startIndex = 0;
        String value = "";
        while (matcher.find() && !matcher.group(0).isEmpty()) {
            log.debug("Retrieving only html snippet");
            value = htmlSource.substring(startIndex, matcher.start()).trim();
            if (!value.isEmpty()) {
                htmlElements.add(createHtmlFragment(value));
            }
            log.debug("Creating the HtmlElement of the tag with the jahia attribute only");
            Source source = new Source(matcher.group(0));
            if (source.getAllStartTags().size() != 1) {
                throw new PageBuilderException(
                        String.format("The html tag with the %s attribute needs to be an empty tag.", JAHIA_ATTRIBUTE));
            }
            StartTag startTag = source.getAllStartTags().get(0);
            List<Attribute> attributes = getAttributes(matcher.group(0).trim());
            TemplateFragment templateFragment = createTemplateAreaFragment(startTag);
            applyAttributesToTemplateArea(templateFragment, attributes);
            htmlElements.add(templateFragment);
            startIndex = matcher.end();
        }
        value = htmlSource.substring(startIndex).trim();
        if (!value.isEmpty()) {
            htmlElements.add(createHtmlFragment(value));
        }
        return htmlElements;
    }

    private static List<Attribute> getAttributes(String tag) {
        if (!tag.contains(JAHIA_ATTRIBUTE)) {
            throw new PageBuilderException(String.format("The tag '%s' does not contain %s", tag, JAHIA_ATTRIBUTE));
        }
        Source source = new Source(tag);
        if (source.getAllElements().size() != 1) {
            throw new PageBuilderException(String.format("Unable to parse the string '%s'.", tag));
        }
        Attributes attributes = source.getAllElements().get(0).getAttributes();
        if (attributes.isEmpty()) {
            throw new PageBuilderException(String.format("%s is not found in the tag",JAHIA_ATTRIBUTE));
        }

        return collectJahiaAttributes(attributes);
    }

    private static List<Attribute> collectJahiaAttributes(Attributes attributes) {
        return attributes.stream().filter(attr -> attr.getName().startsWith(JAHIA_ATTRIBUTE)).collect(Collectors.toList());
    }

    private static TemplateFragment createTemplateAreaFragment(StartTag startTag) {
        TemplateFragment templateFragment = new TemplateFragment();
        templateFragment.setStartTag(startTag);
        templateFragment.setType(HtmlElementType.TEMPLATE_AREA);
        return templateFragment;
    }

    private static void applyAttributesToTemplateArea(TemplateFragment templateFragment, List<Attribute> attributes) {
        attributes.forEach(attr -> Handlers.handle(templateFragment, attr));
    }

    private static TemplateFragment createHtmlFragment(String value) {
        TemplateFragment templateFragment = new TemplateFragment();
        templateFragment.setType(HtmlElementType.HTML_FRAGMENT);
        templateFragment.setRawValue(value);
        return templateFragment;
    }
}
