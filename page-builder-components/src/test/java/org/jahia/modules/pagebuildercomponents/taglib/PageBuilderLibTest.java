package org.jahia.modules.pagebuildercomponents.taglib;

import org.jahia.modules.pagebuildercomponents.exception.PageBuilderException;
import org.jahia.modules.pagebuildercomponents.model.HtmlElementType;
import org.jahia.modules.pagebuildercomponents.model.TemplateFragment;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
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
        PageBuilderLib.getTemplateFragments(malformedHtmlCode);
    }

    @Test
    public void testValidHtmlCodes() {
        int expectedNumOfChunks = 1;
        final List<String> validHtmlCodes = new ArrayList<>();
        validHtmlCodes.add("<div data-jahia-area='area1'>This text will be erase</div>");
        validHtmlCodes.add("<div id='1'><p>Some random text</p></div>");
        validHtmlCodes.add("<div data-jahia-area='area'></div>");
        for(String validHtmlCode : validHtmlCodes) {
            final List<TemplateFragment> actualHtmlChunks = PageBuilderLib.getTemplateFragments(validHtmlCode);
            Assert.assertEquals("It should only have one slice", expectedNumOfChunks, actualHtmlChunks.size());
        }
    }

    @Test
    public void testGetHtmlElementsWithEmptyTag() {
        final String validHtmlTag = "<div id='1'>\n<div id='2' data-jahia-area='area1'></div>\n</div>";
        List<TemplateFragment> actualHtmlChunks = PageBuilderLib.getTemplateFragments(validHtmlTag);
        Assert.assertEquals("There should be 3 slices of html elements", 3, actualHtmlChunks.size());
    }

    @Test
    public void testTagRetainAttributes() {
        String expectedAttribute = "1";
        String tagWithAttributes = String.format("<div id='%s' data-jahia-area='area1'></div>", expectedAttribute);
        List<TemplateFragment> actualHtmlChunks = PageBuilderLib.getTemplateFragments(tagWithAttributes);
        Assert.assertEquals(String.format("The html element should have the id %s", expectedAttribute), expectedAttribute,
                actualHtmlChunks.get(0).getStartTag().getAttributeValue("id"));
    }

    @Test
    public void testGetHtmlElementsWithTwoJahiaAttribute() {
        int expectedSize = 2;
        String testHtmlCode = "<div data-jahia-area='area1'></div><div data-jahia-area='area2'></div>";
        int actualSize = PageBuilderLib.getTemplateFragments(testHtmlCode).size();
        Assert.assertEquals("It should only have two slices", expectedSize, actualSize);
    }


    @Test
    public void testGetHtmlElementsWithThreeSlices() {
        String expectedHtmlElementValue = "area";
        int expectedNumOfChunks = 3;
        TemplateFragment expectedFirstHtmlElement = new TemplateFragment();
        expectedFirstHtmlElement.setType(HtmlElementType.HTML_FRAGMENT);
        expectedFirstHtmlElement.setRawValue("<div id='1'><p>Some text</p>");

        TemplateFragment expectedSecondHtmlElement = new TemplateFragment();
        expectedSecondHtmlElement.setType(HtmlElementType.TEMPLATE_AREA);
        expectedSecondHtmlElement.setPath(expectedHtmlElementValue);

        TemplateFragment expectedThirdHtmlElement = new TemplateFragment();
        expectedThirdHtmlElement.setType(HtmlElementType.HTML_FRAGMENT);
        expectedThirdHtmlElement.setRawValue("</div>");

        String input = String.format("%s\n<div data-jahia-area='area'></div>\n%s", expectedFirstHtmlElement.getRawValue(),
                expectedThirdHtmlElement.getRawValue());
        List<TemplateFragment> actualHtmlChunks = PageBuilderLib.getTemplateFragments(input);
        Assert.assertEquals(String.format("There should only be %s slices", expectedNumOfChunks),
                expectedNumOfChunks, actualHtmlChunks.size());
        Assert.assertEquals(String.format("The 1st slice should have %s", expectedFirstHtmlElement.getRawValue()),
                expectedFirstHtmlElement.getRawValue().trim(), actualHtmlChunks.get(0).getRawValue().trim());
        Assert.assertEquals(String.format("The 1st slice should be of type %s", expectedFirstHtmlElement.getType()),
                expectedFirstHtmlElement.getType(), actualHtmlChunks.get(0).getType());
        Assert.assertEquals(String.format("The 2nd slice should have %s", expectedSecondHtmlElement.getRawValue()),
                expectedSecondHtmlElement.getPath(), actualHtmlChunks.get(1).getPath());
        Assert.assertEquals(String.format("The 3rd slice should have %s", expectedThirdHtmlElement.getRawValue()),
                expectedThirdHtmlElement.getRawValue().trim(), actualHtmlChunks.get(2).getRawValue().trim());
        Assert.assertEquals(String.format("The 3rd slice should be of type %s", expectedThirdHtmlElement.getType()),
                expectedThirdHtmlElement.getType(), actualHtmlChunks.get(2).getType());

    }

    @Test(expected = PageBuilderException.class)
    public void testIllegalJahiaAttribute() {
        final String html = "<div data-jahia-illegal='value'></div>";
        PageBuilderLib.getTemplateFragments(html);
    }

    @Test
    public void testAreaHandler() {
        int expectedSize = 1;

        // default areaType is jnt:contentList
        TemplateFragment fragment = new TemplateFragment();
        fragment.setPath("mypath");

        String testHtmlCode = String.format("<div data-jahia-area='%s'></div>", fragment.getPath());
        List<TemplateFragment> actualHtmlChunks = PageBuilderLib.getTemplateFragments(testHtmlCode);
        Assert.assertEquals("It should only have one slice", expectedSize, actualHtmlChunks.size());
        Assert.assertEquals("Incorrect path for area", actualHtmlChunks.get(0).getPath(), fragment.getPath());
        Assert.assertEquals("Incorrect area type for area", actualHtmlChunks.get(0).getAreaType(), fragment.getAreaType());
    }

    @Test
    public void testTextHandler() {
        int expectedSize = 1;

        TemplateFragment fragment = new TemplateFragment();
        fragment.setPath("mypath");
        fragment.setAreaType("jnt:text");

        String testHtmlCode = String.format("<div data-jahia-text='%s'></div>", fragment.getPath());
        List<TemplateFragment> actualHtmlChunks = PageBuilderLib.getTemplateFragments(testHtmlCode);
        Assert.assertEquals("It should only have one slice", expectedSize, actualHtmlChunks.size());
        Assert.assertEquals("Incorrect path for simple text", actualHtmlChunks.get(0).getPath(), fragment.getPath());
        Assert.assertEquals("Incorrect area type for simple text", actualHtmlChunks.get(0).getAreaType(), fragment.getAreaType());
    }

    @Test
    public void testLimitHandlerOnArea() {
        int expectedSize = 1;

        TemplateFragment fragment = new TemplateFragment();
        fragment.setPath("mypath");
        fragment.setLimit(2);

        String testHtmlCode = String.format("<div data-jahia-area-limit='%s' data-jahia-area='%s'></div>", fragment.getLimit(), fragment.getPath());
        List<TemplateFragment> actualHtmlChunks = PageBuilderLib.getTemplateFragments(testHtmlCode);
        Assert.assertEquals("It should only have one slice", expectedSize, actualHtmlChunks.size());
        Assert.assertEquals("Incorrect path for area", actualHtmlChunks.get(0).getPath(), fragment.getPath());
        Assert.assertEquals("Incorrect limit for area", actualHtmlChunks.get(0).getLimit(), fragment.getLimit());
    }

    @Test
    public void testTypesHandlerOnArea() {
        int expectedSize = 1;

        TemplateFragment fragment = new TemplateFragment();
        fragment.setPath("mypath");
        fragment.setTypes(Arrays.asList("jnt:text", "jnt:bigText"));

        String types = String.join(" ", fragment.getTypes());
        String testHtmlCode = String.format("<div data-jahia-area-area='%s' data-jahia-area-types='%s'></div>", fragment.getPath(), types);
        List<TemplateFragment> actualHtmlChunks = PageBuilderLib.getTemplateFragments(testHtmlCode);
        Assert.assertEquals("It should only have one slice", expectedSize, actualHtmlChunks.size());
        Assert.assertEquals("Incorrect path for area", actualHtmlChunks.get(0).getPath(), fragment.getPath());
        Assert.assertEquals("Incorrect types for area", String.join(" ", actualHtmlChunks.get(0).getTypes()), types);
    }

    @Test
    public void testRichTextHandler() {
        int expectedSize = 1;

        TemplateFragment fragment = new TemplateFragment();
        fragment.setPath("mypath");
        fragment.setAreaType("jnt:bigText");

        String testHtmlCode = String.format("<div data-jahia-richtext='%s'></div>", fragment.getPath());
        List<TemplateFragment> actualHtmlChunks = PageBuilderLib.getTemplateFragments(testHtmlCode);
        Assert.assertEquals("It should only have one slice", expectedSize, actualHtmlChunks.size());
        Assert.assertEquals("Incorrect path for rich text", actualHtmlChunks.get(0).getPath(), fragment.getPath());
        Assert.assertEquals("Incorrect area type for rich text", actualHtmlChunks.get(0).getAreaType(), fragment.getAreaType());
    }

    @Test
    public void testImageHandler() {
        int expectedSize = 1;

        TemplateFragment fragment = new TemplateFragment();
        fragment.setPath("mypath");
        fragment.setAreaType("jnt:imageReferenceLink");

        String testHtmlCode = String.format("<div data-jahia-image='%s'></div>", fragment.getPath());
        List<TemplateFragment> actualHtmlChunks = PageBuilderLib.getTemplateFragments(testHtmlCode);
        Assert.assertEquals("It should only have one slice", expectedSize, actualHtmlChunks.size());
        Assert.assertEquals("Incorrect path for image reference", actualHtmlChunks.get(0).getPath(), fragment.getPath());
        Assert.assertEquals("Incorrect area type for image reference", actualHtmlChunks.get(0).getAreaType(), fragment.getAreaType());
    }
}
