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
 *     Copyright (C) 2002-2021 Jahia Solutions Group. All rights reserved.
 *
 *     This file is part of a Jahia's Enterprise Distribution.
 *
 *     Jahia's Enterprise Distributions must be used in accordance with the terms
 *     contained in the Jahia Solutions Group Terms & Conditions as well as
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
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Segment;
import net.htmlparser.jericho.CharacterReference;
import net.htmlparser.jericho.Tag;
import net.htmlparser.jericho.EndTag;
import org.jahia.modules.pagebuildercomponents.exception.PageBuilderException;
import org.jahia.modules.pagebuildercomponents.handlers.Handlers;
import org.jahia.modules.pagebuildercomponents.model.HtmlElementType;
import org.jahia.modules.pagebuildercomponents.model.TemplateFragment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Iterator;
import java.util.Stack;


/**
 * This Java utility class contains a series of libraries that parses
 * and slices html source code in order for the JSP to render
 *
 * @author nonico
 */
public final class PageBuilderLib {
    public static final String JAHIA_ATTRIBUTE = "data-jahia-";
    private static final Logger log = LoggerFactory.getLogger(PageBuilderLib.class);

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
        Source source = new Source(htmlSource);
        Iterator<Segment> sourceSegNodeIterator = source.getNodeIterator();
        String ignoreEmbeddedElements = "";
        int ignoreCount = 0;
        Stack<String> tagStack = new Stack<>();

        try {
            while (sourceSegNodeIterator.hasNext()) {
                Segment sourceSeg = sourceSegNodeIterator.next();
                if (sourceSeg instanceof Tag) {
                    if (sourceSeg instanceof StartTag) {
                        // Add the start tag name to the list to check for valid HTML
                        tagStack.push(((StartTag) sourceSeg).getName());
                        if (!ignoreEmbeddedElements.isEmpty()) {
                            if (ignoreEmbeddedElements.equals(((StartTag) sourceSeg).getName()))
                                ignoreCount++;
                            continue;
                        }
                        Element element = ((StartTag) sourceSeg).getElement();
                        List<Attribute> attributes = collectJahiaAttributes(element.getAttributes());
                        // Assuming if there is per the list of attributes
                        if (attributes != null && !attributes.isEmpty()) {
                            TemplateFragment templateFragment = createTemplateAreaFragment(element.getStartTag());
                            applyAttributesToTemplateArea(templateFragment, attributes);
                            htmlElements.add(templateFragment);
                            ignoreEmbeddedElements = ((StartTag) sourceSeg).getName();
                            ignoreCount++;
                        } else {
                            htmlElements.add(createHtmlFragment(sourceSeg.toString())); // add back the information
                        }

                    } else if (sourceSeg instanceof EndTag) {
                        if (ignoreEmbeddedElements.equals(((EndTag) sourceSeg).getName())) {
                            if (ignoreCount == 1)
                                ignoreEmbeddedElements = ""; // reset
                            ignoreCount--;
                        } else if (ignoreEmbeddedElements.isEmpty()) {
                            htmlElements.add(createHtmlFragment(sourceSeg.toString())); // add back the information
                        }
                        // Break-out as malformed-HTML if tags doesn't match in order
                        if (tagStack.isEmpty() || tagStack.peek() == null || !tagStack.peek().equals(((EndTag) sourceSeg).getName())) {
                            throw new PageBuilderException(String.format("The html tag %s does not match starting tag in stack.", ((EndTag) sourceSeg).getName()));
                        }
                        tagStack.pop(); // Remove the start-tag
                    }
                } else if (!(sourceSeg instanceof Tag) && !(sourceSeg instanceof CharacterReference)) {
                    if (!sourceSeg.toString().isEmpty() && ignoreEmbeddedElements.isEmpty()) { // For text base
                        htmlElements.add(createHtmlFragment(sourceSeg.toString()));
                    }
                }
            }
            if(!tagStack.isEmpty()) throw new PageBuilderException("The html tags doesn't fully match malformed-html source.");
        } catch (PageBuilderException exception) {
            htmlElements.clear(); // Clear the existing HTML elements
            htmlElements.add(createHtmlFragment(exception.getMessage())); // Add the error message
        }
        return htmlElements;
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
