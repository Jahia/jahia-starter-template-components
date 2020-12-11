package org.jahia.modules.pagebuildercomponents.taglib;

import org.jahia.modules.pagebuildercomponents.exception.PageBuilderException;
import org.jahia.services.content.JCRNodeWrapper;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import javax.jcr.RepositoryException;
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
    public void testGetAreaIdNotValidTag() {
        String invalidTag = "<div id=\"1\"></div>";
        PageBuilderLib.getAreaId(invalidTag);
    }

    @Test(expected = PageBuilderException.class)
    public void testGetAreaIdNoIdFound() {
        String emptyIdTag = "<template:area />";
        PageBuilderLib.getAreaId(emptyIdTag);
    }

    @Test
    public void testGetAreaIdValidId() {
        String expectedAreaId = "someRandomId";
        String validTemplateAreaTag = String.format("<%s %s='%s' />", PageBuilderLib.templatePlaceHolder(),
                PageBuilderLib.AREA_ID_ATTRIBUTE, expectedAreaId);
        String actualAreaId = PageBuilderLib.getAreaId(validTemplateAreaTag);
        Assert.assertEquals("The ids should be equal", expectedAreaId, actualAreaId);
    }

    @Test
    public void getHtmlSlicesWithOnlyOneSlice() {
        int expectedSize = 1;
        String testHtmlCode = "<div id='1'><p>Some random text</p></div>";
        int actualSize = PageBuilderLib.getHtmlSlices(testHtmlCode).size();
        Assert.assertEquals("It should only have one slice", expectedSize, actualSize);
    }

    @Test
    public void getHtmlSlicesWithThreeSlices() {
        String expectedAreaId = "someAreaId";
        String expectedFirstSlice = String.format("<div id='1'><p>Some random text</p><div %s='%s'>",
                PageBuilderLib.JAHIA_ATTRIBUTE, expectedAreaId);
        String expectedSecondSlice = String.format("<%s %s='%s' />", PageBuilderLib.TEMPLATE_PLACEHOLDER,
                PageBuilderLib.AREA_ID_ATTRIBUTE, expectedAreaId);
        String expectedThirdSlice = "</div></div>";
        int expectedSize = 3;
        String testHtmlCode = String.format("%s%s", expectedFirstSlice, expectedThirdSlice);
        List<String> actualHtmlSlices = PageBuilderLib.getHtmlSlices(testHtmlCode);
        int actualSize = actualHtmlSlices.size();
        Assert.assertEquals(String.format("It should only have %s slices", expectedSize), expectedSize, actualSize);
        Assert.assertEquals(String.format("The first slice should have %s", expectedFirstSlice), expectedFirstSlice,
                actualHtmlSlices.get(0));
        Assert.assertEquals(String.format("The second slice should have %s", expectedSecondSlice), expectedSecondSlice,
                actualHtmlSlices.get(1));
        Assert.assertEquals(String.format("The third slice should have %s", expectedThirdSlice), expectedThirdSlice,
                actualHtmlSlices.get(2));
    }

    @Test
    public void testUpdateHtmlSourceCode() throws RepositoryException {
        JCRNodeWrapper mockNode = Mockito.mock(JCRNodeWrapper.class);
        String mockHtml = "<div id='1'><p>Mock<p></div>";
        PageBuilderLib.updateHtmlSourceCode(mockNode, mockHtml);
        Mockito.verify(mockNode, Mockito.times(1))
                .setProperty(Mockito.anyString(), Mockito.anyString());
        Mockito.verify(mockNode, Mockito.times(1))
                .saveSession();
    }

    @Test
    public void testUpdateHtmlSourceCodeWhenAlreadyDefined() throws RepositoryException {
        JCRNodeWrapper mockNode = Mockito.mock(JCRNodeWrapper.class);
        String mockHtml = "<div id='1'><p>Mock<p></div>";
        Mockito.when(mockNode.hasProperty(Mockito.anyString())).thenReturn(Boolean.TRUE);
        PageBuilderLib.updateHtmlSourceCode(mockNode, mockHtml);
        Mockito.verify(mockNode, Mockito.times(0))
                .setProperty(Mockito.anyString(), Mockito.anyString());
        Mockito.verify(mockNode, Mockito.times(0))
                .saveSession();
    }
}