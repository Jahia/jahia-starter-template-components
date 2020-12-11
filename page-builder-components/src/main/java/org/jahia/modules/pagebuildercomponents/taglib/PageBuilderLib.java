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
import org.jahia.services.content.JCRNodeWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;
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
    public static final String TEMPLATE_PLACEHOLDER = "template:area";
    public static final String AREA_ID_ATTRIBUTE = "areaId";
    public static final String HTML_SOURCE_CODE_PROPERTY = "htmlSourceCode";
    private static final Logger log = LoggerFactory.getLogger(PageBuilderLib.class);
    private static final String REGEX_PATTERN = String.format("<%s(.+?)/>", TEMPLATE_PLACEHOLDER);
    private static final Pattern PATTERN =   Pattern.compile(REGEX_PATTERN, Pattern.MULTILINE);

    /**
     * Adding an non-public constructor to prevent Java from implicitly creating one
     */
    private PageBuilderLib() {}

    /**
     * Retrieve the template placeholder constant
     * @return the placeholder string
     */
    public static String templatePlaceHolder() {
        return TEMPLATE_PLACEHOLDER;
    }

    /**
     * Retrieve the id specified by the template:area
     * @param templateAreaTag a string containing <template:area> tag
     * @return the id of the template:area
     */
    public static String getAreaId(String templateAreaTag) {
        if (!templateAreaTag.contains(TEMPLATE_PLACEHOLDER)) {
            throw new PageBuilderException("The tag \""+ templateAreaTag +"\" does not contain \"" + TEMPLATE_PLACEHOLDER + "\"");
        }
        Source source = new Source(templateAreaTag);
        Element element = source.getAllElements(TEMPLATE_PLACEHOLDER).get(0);
        String id = element.getAttributeValue(AREA_ID_ATTRIBUTE);
        if (id == null || id.isEmpty()) {
            throw new PageBuilderException( AREA_ID_ATTRIBUTE + " is not found in the tag");
        }
        return id;
    }

    /**
     * Update the htmlSourceCode property of the node to be the same as the htmlSourceFile
     * @param node
     * @param updatedHtmlSourceCode
     * @throws RepositoryException
     */
    public static void updateHtmlSourceCode(JCRNodeWrapper node, String updatedHtmlSourceCode) throws RepositoryException {
        if (!node.hasProperty(HTML_SOURCE_CODE_PROPERTY)) {
            node.setProperty(HTML_SOURCE_CODE_PROPERTY, updatedHtmlSourceCode);
            node.saveSession();
        }
    }

    /**
     * Slices the HTML code into a list of strings allowing the JSP to determine which strings
     * to render as html and which one not to.
     * @param htmlSource a string that contains html looking code
     * @return a list of strings
     */
    public static List<String> getHtmlSlices(String htmlSource) {
        log.debug("Slicing html");
        List<String> slicedHTML = new ArrayList<>();
        String parsedHTML = parseHtml(htmlSource);
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

    /**
     * Inserts a template placeholder
     * @param startTag The beginning tag of an html element with the custom Jahia attribute
     * @param areaId The id specified in the tag. Eg. <div data-jahia-area="id"></div>
     * @return a string with the template area tags inserted
     */
    private static String insertTemplateAreaJSPTag(StartTag startTag, String areaId) {
        StringBuilder sb = new StringBuilder();
        String injectedTemplateArea = String.format("<%s %s='%s' />",TEMPLATE_PLACEHOLDER, AREA_ID_ATTRIBUTE, areaId);
        return sb.append(startTag).append(injectedTemplateArea).toString();
    }

    /**
     * Parses through the html code and inject <template:area> tags for every
     * tags that contains the attribute "data-jahia-area"
     * @param htmlSource a string that contains html source code
     * @return a string with injected <template:area> tag
     */
    private static String parseHtml(String htmlSource) {
        Source source = new Source(htmlSource);
        OutputDocument outputDocument = new OutputDocument(source);
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
}
