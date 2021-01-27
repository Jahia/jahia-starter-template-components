/*
 * Copyright (C) 2002-2021 Jahia Solutions Group SA. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
import org.jahia.modules.pagebuildercomponents.model.VoidHtmlElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
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
                        String startTag = (((StartTag) sourceSeg).getName() != null) ? ((StartTag) sourceSeg).getName().toLowerCase(): null;
                        // Add the start tag name to the list to check for valid HTML
                        tagStack.push(startTag); // Reason to add always is to add backward compatibility to HTML 5- syntax
                        if (!ignoreEmbeddedElements.isEmpty()) {
                            if (ignoreEmbeddedElements.equals(startTag)) ignoreCount++;
                            continue;
                        }
                        Element element = ((StartTag) sourceSeg).getElement();
                        List<Attribute> attributes = collectJahiaAttributes(element.getAttributes());
                        // Assuming if there is per the list of attributes
                        if (attributes != null && !attributes.isEmpty()) {
                            TemplateFragment templateFragment = createTemplateAreaFragment(element.getStartTag());
                            applyAttributesToTemplateArea(templateFragment, attributes);
                            htmlElements.add(templateFragment);
                            ignoreEmbeddedElements = startTag;
                            ignoreCount++;
                        } else {
                            htmlElements.add(createHtmlFragment(sourceSeg.toString())); // add back the information
                        }

                    } else if (sourceSeg instanceof EndTag) {
                        String endTag = (((EndTag) sourceSeg).getName() != null) ? ((EndTag) sourceSeg).getName().toLowerCase() : null;
                        if (ignoreEmbeddedElements.equals(endTag)) {
                            if (ignoreCount == 1) ignoreEmbeddedElements = ""; // reset
                            ignoreCount--;
                        } else if (ignoreEmbeddedElements.isEmpty()) {
                            htmlElements.add(createHtmlFragment(sourceSeg.toString())); // add back the information
                        }

                        // Take out any tags that are void html element before comparison
                        while(!tagStack.isEmpty() && tagStack.peek() != null && !tagStack.peek().equals(endTag) && VoidHtmlElement.contains(tagStack.peek())) {tagStack.pop();}
                        // Break-out as malformed-HTML if tags doesn't match in order
                        if (tagStack.isEmpty() || tagStack.peek() == null) {
                            throw new PageBuilderException(String.format("Parsing error: found closing html tag &lt;&#47;%s&gt; without "
                                            + "open tag",
                                    endTag));
                        } else if (!VoidHtmlElement.contains(tagStack.peek()) && !tagStack.peek().equals(endTag)) {
                            throw new PageBuilderException(String.format("Parsing error: found closing html tag &lt;&#47;%s&gt; when expecting "
                                    + "closing tag &lt;&#47;%s&gt;", endTag, tagStack.peek()));
                        }
                        tagStack.pop(); // Remove the start-tag
                    }
                } else if (!(sourceSeg instanceof CharacterReference)) {
                    if (sourceSeg != null && !sourceSeg.toString().isEmpty() && ignoreEmbeddedElements.isEmpty()) { // For text base
                        htmlElements.add(createHtmlFragment(sourceSeg.toString()));
                    }
                }
            }
            // Remove all void HTML elements to check if there are still ones left out
            List<String> tagList = tagStack.stream().filter(tag -> !VoidHtmlElement.contains(tag)).collect(Collectors.toList());
            if(!tagList.isEmpty()) throw new PageBuilderException("Parsing error: found the html tags doesn't fully match "
                    + "malformed-html source");
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
