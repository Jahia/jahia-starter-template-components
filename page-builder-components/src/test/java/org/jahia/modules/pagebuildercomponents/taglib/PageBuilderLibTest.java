package org.jahia.modules.pagebuildercomponents.taglib;

import org.jahia.modules.pagebuildercomponents.exception.PageBuilderException;
import org.jahia.modules.pagebuildercomponents.model.HtmlElement;
import org.jahia.modules.pagebuildercomponents.model.HtmlElementType;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
public class PageBuilderLibTest {

    @Test(expected = PageBuilderException.class)
    public void testMalformedHtmlCode() {
        final String malformedHtmlCode = "<div data-jahia-area='area1'><div data-jahia-area='area2'></div></div>";
        PageBuilderLib.getHtmlElements(malformedHtmlCode);
    }

    @Test
    public void testValidHtmlCodes() {
        int expectedNumOfChunks = 1;
        final List<String> validHtmlCodes = new ArrayList<>();
        validHtmlCodes.add("<div data-jahia-area='area1'>This text will be erase</div>");
        validHtmlCodes.add("<div id='1'><p>Some random text</p></div>");
        validHtmlCodes.add("<div data-jahia-area='area'></div>");
        for(String validHtmlCode : validHtmlCodes) {
            final List<HtmlElement> actualHtmlChunks = PageBuilderLib.getHtmlElements(validHtmlCode);
            Assert.assertEquals("It should only have one slice", expectedNumOfChunks, actualHtmlChunks.size());
        }
    }

    @Test
    public void testGetHtmlElementsWithEmptyTag() {
        final String validHtmlTag = "<div id='1'>\n<div id='2' data-jahia-area='area1'></div>\n</div>";
        List<HtmlElement> actualHtmlChunks = PageBuilderLib.getHtmlElements(validHtmlTag);
        Assert.assertEquals("There should be 3 slices of html elements", 3, actualHtmlChunks.size());
    }

    @Test
    public void testTagRetainAttributes() {
        String expectedAttribute = "1";
        String tagWithAttributes = String.format("<div id='%s' data-jahia-area='area1'></div>", expectedAttribute);
        List<HtmlElement> actualHtmlChunks = PageBuilderLib.getHtmlElements(tagWithAttributes);
        Assert.assertEquals(String.format("The html element should have the id %s", expectedAttribute), expectedAttribute,
                actualHtmlChunks.get(0).getStartTag().getAttributeValue("id"));
    }

    @Test
    public void testGetHtmlElementsWithTwoJahiaAttribute() {
        int expectedSize = 2;
        String testHtmlCode = "<div data-jahia-area='area1'></div><div data-jahia-area='area2'></div>";
        int actualSize = PageBuilderLib.getHtmlElements(testHtmlCode).size();
        Assert.assertEquals("It should only have two slices", expectedSize, actualSize);
    }


    @Test
    public void testGetHtmlElementsWithThreeSlices() {
        String expectedHtmlElementValue = "area";
        int expectedNumOfChunks = 3;
        HtmlElement expectedFirstHtmlElement = HtmlElement.builder()
                .type(HtmlElementType.HTML_FRAGMENT)
                .value("<div id='1'><p>Some text</p>")
                .build();
        HtmlElement expectedSecondHtmlElement = HtmlElement.builder()
                .type(HtmlElementType.TEMPLATE_AREA)
                .value(expectedHtmlElementValue)
                .build();
        HtmlElement expectedThirdHtmlElement = HtmlElement.builder()
                .type(HtmlElementType.HTML_FRAGMENT)
                .value("</div>")
                .build();
        String input = String.format("%s\n<div data-jahia-area='area'></div>\n%s", expectedFirstHtmlElement.getValue(),
                expectedThirdHtmlElement.getValue());
        List<HtmlElement> actualHtmlChunks = PageBuilderLib.getHtmlElements(input);
        Assert.assertEquals(String.format("There should only be %s slices", expectedNumOfChunks),
                expectedNumOfChunks, actualHtmlChunks.size());
        Assert.assertEquals(String.format("The 1st slice should have %s", expectedFirstHtmlElement.getValue()),
                expectedFirstHtmlElement.getValue().trim(), actualHtmlChunks.get(0).getValue().trim());
        Assert.assertEquals(String.format("The 1st slice should be of type %s", expectedFirstHtmlElement.getType()),
                expectedFirstHtmlElement.getType(), actualHtmlChunks.get(0).getType());
        Assert.assertEquals(String.format("The 2nd slice should have %s", expectedSecondHtmlElement.getValue()),
                expectedSecondHtmlElement.getValue(), actualHtmlChunks.get(1).getValue());
        Assert.assertEquals(String.format("The 3rd slice should have %s", expectedThirdHtmlElement.getValue()),
                expectedThirdHtmlElement.getValue().trim(), actualHtmlChunks.get(2).getValue().trim());
        Assert.assertEquals(String.format("The 3rd slice should be of type %s", expectedThirdHtmlElement.getType()),
                expectedThirdHtmlElement.getType(), actualHtmlChunks.get(2).getType());

    }
}