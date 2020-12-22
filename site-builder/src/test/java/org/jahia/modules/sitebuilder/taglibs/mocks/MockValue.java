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
package org.jahia.modules.sitebuilder.taglibs.mocks;

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

    MockNode node = new MockNode();

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
