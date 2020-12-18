package org.jahia.modules.sitebuilder.taglibs.functions;

import org.jahia.modules.sitebuilder.taglibs.PropConstants;
import org.jahia.modules.sitebuilder.taglibs.mocks.MockNode;
import org.jahia.modules.sitebuilder.taglibs.mocks.MockValue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.jcr.RepositoryException;


import static org.junit.Assert.*;

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
 */public class BannerFunctionsTest {

    MockNode ctxNode;

    @Before public void setUp() {
        ctxNode = new MockNode();
    }

    @Test public void testHasNoPageOverrides() {
        try {
            boolean hasOverrides = BannerFunctions.hasPageOverrides(ctxNode);
            Assert.assertFalse(hasOverrides);
        } catch (RepositoryException e) {
            Assert.fail("unexpected exception");
        }
    }

    @Test public void testHasEmptyPageOverrides() {
        try {
            ctxNode.setProperty(PropConstants.CSS_OVERRIDE_PROP, new MockValue[] {});
            boolean hasOverrides = BannerFunctions.hasPageOverrides(ctxNode);
            Assert.assertFalse(hasOverrides);
        } catch (RepositoryException e) {
            Assert.fail("unexpected exception");
        }
    }

    @Test public void testHasPageOverrides() {
        try {
            ctxNode.setProperty(PropConstants.CSS_OVERRIDE_PROP,
                    new MockValue[] { new MockValue() });
            boolean hasOverrides = BannerFunctions.hasPageOverrides(ctxNode);
            Assert.assertTrue(hasOverrides);
        } catch (RepositoryException e) {
            Assert.fail("unexpected exception");
        }
    }

}