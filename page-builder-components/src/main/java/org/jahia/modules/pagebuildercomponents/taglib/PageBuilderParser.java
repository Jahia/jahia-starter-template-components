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

import net.htmlparser.jericho.*;
import org.jahia.modules.pagebuildercomponents.exception.PageBuilderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Short description of the class
 *
 * @author nonico
 */
public class PageBuilderParser {
    public static final Logger log = LoggerFactory.getLogger(PageBuilderParser.class);
    private static final String JAHIA_ATTRIBUTE = "data-jahia-area";
    public static final String TEMPLATE_PLACEHOLDER = "template:area";
    private static final String AREA_ID_ATTRIBUTE = "areaId";
    private static final String REGEX_PATTERN = String.format("<%s(.+?)/>", TEMPLATE_PLACEHOLDER);
    private static final Pattern PATTERN =   Pattern.compile(REGEX_PATTERN, Pattern.MULTILINE);
    private Source source;
    private OutputDocument outputDocument;

    public PageBuilderParser() {}

    public PageBuilderParser(String htmlSource) {
        this.source = new Source(htmlSource);
        this.outputDocument = new OutputDocument(source);
    }

    public void setHtmlSource(String htmlSource) {
        this.source = new Source(htmlSource);
        this.outputDocument = new OutputDocument(this.source);
    }

    public static String parseHTML(String htmlSource) {
        log.debug("Start parsing htmlSource");
        PageBuilderParser htmlParser = new PageBuilderParser();
        htmlParser.setHtmlSource(htmlSource);
        return htmlParser.parseHTML();
    }

    /**
     * Slices the HTML code into chunks
     * @return
     */
    public static List<String> getHTMLSlices(String htmlSource) {
        log.debug("Slicing html");
        List<String> slicedHTML = new ArrayList<>();
        PageBuilderParser htmlParser = new PageBuilderParser();
        htmlParser.setHtmlSource(htmlSource);
        String parsedHTML = htmlParser.parseHTML();
        Matcher matcher =   PATTERN.matcher(parsedHTML);
        int startIndex = 0;
        while(matcher.find()) {
            slicedHTML.add(parsedHTML.substring(startIndex, matcher.start()));
            slicedHTML.add(matcher.group(0));
            startIndex = matcher.end();
        }
        slicedHTML.add(parsedHTML.substring(startIndex));
        return slicedHTML;
    }

    public String parseHTML() {
        List<Element> elementList = source.getAllElements();
        for (Element element : elementList) {
            StartTag startTag = element.getStartTag();
            String jahiaAttributeValue = element.getAttributeValue(JAHIA_ATTRIBUTE);
            if (jahiaAttributeValue != null) {
                String injectedData = insertTemplateAreaJSPTag(startTag, jahiaAttributeValue);
                outputDocument.replace(startTag, injectedData);
            }
        }
        log.debug("Parsing finished");
        return outputDocument.toString().trim();
    }

    public String insertTemplateAreaJSPTag(StartTag startTag, String areaId) {
        StringBuilder sb = new StringBuilder();
        String injectedTemplateArea = String.format("<%s %s='%s' />",TEMPLATE_PLACEHOLDER, AREA_ID_ATTRIBUTE, areaId);
        return sb.append(startTag).append(injectedTemplateArea).toString();
    }

    public static String templatePlaceHolder() {
        return TEMPLATE_PLACEHOLDER;
    }

    public static String getAreaId(String template) {
        Source source = new Source(template);
        Element element = source.getAllElements(TEMPLATE_PLACEHOLDER).get(0);
        Attribute id = element.getAttributes().get(AREA_ID_ATTRIBUTE);
        if (id.hasValue()) {
            return id.getValue();
        }
        throw new PageBuilderException("ID was not added to the template");
    }
}
