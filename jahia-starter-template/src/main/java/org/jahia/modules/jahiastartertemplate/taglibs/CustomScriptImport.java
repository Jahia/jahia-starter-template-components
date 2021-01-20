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
package org.jahia.modules.jahiastartertemplate.taglibs;

import org.jahia.services.content.decorator.JCRSiteNode;
import org.jahia.services.render.RenderContext;
import org.jahia.taglibs.AbstractJahiaTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;
import javax.servlet.jsp.JspException;
import java.io.IOException;

/**
 * Imports custom script defined at site level
 */
public class CustomScriptImport extends AbstractJahiaTag {

    private static final long serialVersionUID = -549052631292368495L;
    private static Logger logger = LoggerFactory.getLogger(CustomScriptImport.class);

    private static final String CODE_PROPERTY = "headCode";

    @Override
    public int doEndTag() throws JspException {
        try {
            writeToContext();
        } catch (RepositoryException e) {
            logger.error("Failed to read jcr while loading custom code: {}", e.getMessage());
        } catch (IOException e) {
            logger.error("Failed to load resource for site: {}", e.getMessage());
        }
        return super.doEndTag();
    }

    private void writeToContext() throws RepositoryException, IOException {
        RenderContext ctx = getRenderContext();
        JCRSiteNode site = ctx.getSite();

        if (!site.hasProperty(CODE_PROPERTY)) {
            return;
        }

        pageContext.getOut().print(site.getPropertyAsString(CODE_PROPERTY));
    }
}
