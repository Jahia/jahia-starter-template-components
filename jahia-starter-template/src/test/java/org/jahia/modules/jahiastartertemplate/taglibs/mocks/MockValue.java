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
package org.jahia.modules.jahiastartertemplate.taglibs.mocks;

import org.jahia.data.beans.CategoryBean;
import org.jahia.services.content.JCRNodeWrapper;
import org.jahia.services.content.JCRValueWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Binary;
import javax.jcr.RepositoryException;
import javax.jcr.ValueFormatException;
import javax.jcr.nodetype.PropertyDefinition;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * Short description of the class
 *
 * @author gflores
 */
public class MockValue implements JCRValueWrapper {


    private static Logger log = LoggerFactory.getLogger(MockValue.class);

    MockNode node = null;

    public MockValue() {
        this(new MockNode());
    }

    public MockValue(MockNode n) {
        this.node = n;
    }

    @Override public JCRNodeWrapper getNode() throws ValueFormatException, IllegalStateException, RepositoryException {
       return node;
    }

    public MockValue setNodeUrl(String url) {
        node.setUrl(url);
        return this;
    }

    /** unimplemented methods */


    @Override public CategoryBean getCategory() throws ValueFormatException, RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public PropertyDefinition getDefinition() throws RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public Date getTime() throws ValueFormatException, RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public String getString() throws ValueFormatException, IllegalStateException, RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public InputStream getStream() throws RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public Binary getBinary() throws RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public long getLong() throws ValueFormatException, RepositoryException {
        log.warn("Unimplemented methods");
        return 0;
    }

    @Override public double getDouble() throws ValueFormatException, RepositoryException {
        log.warn("Unimplemented methods");
        return 0;
    }

    @Override public BigDecimal getDecimal() throws ValueFormatException, RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public Calendar getDate() throws ValueFormatException, RepositoryException {
        log.warn("Unimplemented methods");
        return null;
    }

    @Override public boolean getBoolean() throws ValueFormatException, RepositoryException {
        log.warn("Unimplemented methods");
        return false;
    }

    @Override public int getType() {
        log.warn("Unimplemented methods");
        return 0;
    }
}
