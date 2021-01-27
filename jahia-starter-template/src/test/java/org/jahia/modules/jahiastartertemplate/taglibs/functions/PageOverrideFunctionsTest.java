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
