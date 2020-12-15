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

import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.StartTag;
import org.jahia.modules.pagebuildercomponents.exception.PageBuilderException;
import org.jahia.modules.pagebuildercomponents.model.HtmlElement;
import org.jahia.modules.pagebuildercomponents.model.HtmlElementType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This Java utility class contains a series of libraries that parses
 * and slices html source code in order for the JSP to render
 *
 * @author nonico
 */
public class PageBuilderLib {
    public static final String JAHIA_ATTRIBUTE = "data-jahia-area";
    private static final Logger log = LoggerFactory.getLogger(PageBuilderLib.class);
    private static final String REGEX_PATTERNS = String.format("<(.+?)%s=(.+?)></(.+?)>", JAHIA_ATTRIBUTE);
    private static final Pattern PATTERN = Pattern.compile(REGEX_PATTERNS, Pattern.MULTILINE);

    /**
     * Adding an non-public constructor to prevent Java from implicitly creating one
     */
    private PageBuilderLib() {
    }

    /**
     * Retrieve the id specified by the tag
     *
     * @param tag a string containing the data-jahia-area attribute
     * @return the value of the data-jahia-area attribute
     */
    private static String getDataJahiaAreaId(String tag) {
        if (!tag.contains(JAHIA_ATTRIBUTE)) {
            throw new PageBuilderException("The tag \"" + tag + "\" does not contain \"" + JAHIA_ATTRIBUTE + "\"");
        }
        Source source = new Source(tag);
        if (source.getAllElements().size() != 1) {
            throw new PageBuilderException(String.format("Unable to parse the string '%s'.", tag));
        }
        String id = source.getAllElements().get(0).getAttributeValue(JAHIA_ATTRIBUTE);
        if (id == null || id.isEmpty()) {
            throw new PageBuilderException(JAHIA_ATTRIBUTE + " is not found in the tag");
        }
        return id;
    }

    /**
     * Slices the HTML code into a list of HtmlElement allowing the JSP to determine which strings
     * to render as html and which one not to.
     *
     * @param htmlSource a string that contains html looking code
     * @return a list of html element
     */
    public static List<HtmlElement> getHtmlElements(String htmlSource) {
        log.debug("Creating html chunks");
        List<HtmlElement> htmlElements = new ArrayList<>();
        Matcher matcher = PATTERN.matcher(htmlSource);
        int startIndex = 0;
        String value = "";
        while (matcher.find() && !matcher.group(0).isEmpty()) {
            Source source = new Source(matcher.group(0));
            if (source.getAllStartTags().size() != 1) {
                throw new PageBuilderException(
                        String.format("The html tag with the %s attribute needs to be an empty tag.", JAHIA_ATTRIBUTE));
            }
            value = htmlSource.substring(startIndex, matcher.start()).trim();
            if (!value.isEmpty()) {
                htmlElements.add(HtmlElement.builder()
                        .type(HtmlElementType.HTML_FRAGMENT)
                        .value(value).build());
            }
            StartTag startTag = source.getAllStartTags().get(0);
            value = getDataJahiaAreaId(matcher.group(0).trim());
            htmlElements.add(HtmlElement.builder()
                    .startTag(startTag)
                    .type(HtmlElementType.TEMPLATE_AREA)
                    .value(value).build());
            startIndex = matcher.end();
        }
        value = htmlSource.substring(startIndex).trim();
        if (!value.isEmpty()) {
            htmlElements.add(HtmlElement.builder().type(HtmlElementType.HTML_FRAGMENT).value(value).build());
        }
        return htmlElements;
    }
}
