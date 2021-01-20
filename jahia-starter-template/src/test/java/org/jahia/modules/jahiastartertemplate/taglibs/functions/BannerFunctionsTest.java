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

import org.jahia.modules.jahiastartertemplate.taglibs.PropConstants;
import org.jahia.modules.jahiastartertemplate.taglibs.mocks.MockNode;
import org.jahia.modules.jahiastartertemplate.taglibs.mocks.MockValue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.jcr.RepositoryException;

public class BannerFunctionsTest {

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
