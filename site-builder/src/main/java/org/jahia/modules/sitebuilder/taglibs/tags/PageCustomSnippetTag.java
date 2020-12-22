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
package org.jahia.modules.sitebuilder.taglibs.tags;

import org.jahia.modules.sitebuilder.taglibs.PropConstants;
import org.jahia.services.content.JCRNodeWrapper;
import org.jahia.services.render.RenderContext;
import org.jahia.taglibs.AbstractJahiaTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;
import javax.servlet.jsp.JspException;
import java.io.IOException;

/**
 * Add page-level snippet into HEAD tag
 */
public class PageCustomSnippetTag extends AbstractJahiaTag {

    private static Logger logger = LoggerFactory.getLogger(PageCustomSnippetTag.class);

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
        JCRNodeWrapper ctxNode = getRenderContext().getMainResource().getNode();
        if (!ctxNode.hasProperty(PropConstants.PAGE_HEAD_OVERRIDE_PROP)) return;

        String snippet = ctxNode.getPropertyAsString(PropConstants.PAGE_HEAD_OVERRIDE_PROP);
        if (snippet != null && !snippet.trim().isEmpty()) {
            pageContext.getOut().print(snippet);
        }
    }
}
