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
