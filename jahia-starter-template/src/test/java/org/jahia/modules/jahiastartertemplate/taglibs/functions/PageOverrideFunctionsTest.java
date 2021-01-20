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
package org.jahia.modules.jahiastartertemplate.taglibs.functions;

import org.jahia.modules.jahiastartertemplate.taglibs.mocks.MockNode;
import org.jahia.modules.jahiastartertemplate.taglibs.mocks.MockValue;
import org.jahia.modules.jahiastartertemplate.taglibs.model.FileType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.jcr.RepositoryException;

import java.util.List;

public class PageOverrideFunctionsTest {

    private MockNode ctxNode;

    @Before public void setUp() throws RepositoryException {
        ctxNode = new MockNode();
        ctxNode.setProperty("testProp", new MockValue[] {
                new MockValue().setNodeUrl("/cssfile1.css"),
                new MockValue().setNodeUrl("/jsfile1.js")
        });
    }


    @Test public void testPageOverrideCss() {
        try {
            List<String> urls = PageOverrideFunctions.getPageOverrides(
                    ctxNode, "testProp", FileType.CSS);
            Assert.assertEquals(urls.size(), 1);
        } catch (RepositoryException e) {
            Assert.fail("unexpected exception");
        }
    }

    @Test public void testPageOverrideJs() {
        try {
            List<String> urls = PageOverrideFunctions.getPageOverrides(
                    ctxNode, "testProp", FileType.JAVASCRIPT);
            Assert.assertEquals(urls.size(), 1);
        } catch (RepositoryException e) {
            Assert.fail("unexpected exception");
        }
    }

    @Test public void testPageOverrideNoProp() {
        try {
            List<String> urls = PageOverrideFunctions.getPageOverrides(
                    ctxNode, "noProp", FileType.JAVASCRIPT);
            Assert.assertEquals(urls.size(), 0);
        } catch (RepositoryException e) {
            Assert.fail("unexpected exception");
        }
    }

    /**
     * Happens when a file has been deleted but the prop reference is still there.
     */
    @Test public void testPageOverrideNullValueNode() {
        try {
            ctxNode.setProperty("nullNodeProp", new MockValue[] { new MockValue(null) });
            List<String> urls = PageOverrideFunctions.getPageOverrides(
                    ctxNode, "nullNodeProp", FileType.JAVASCRIPT);
            Assert.assertEquals(urls.size(), 0);
        } catch (RepositoryException e) {
            Assert.fail("unexpected exception");
        }
    }

}
