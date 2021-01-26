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
package org.jahia.modules.jahiastartertemplate.taglibs.tags;

import org.jahia.modules.jahiastartertemplate.taglibs.PropConstants;
import org.jahia.services.content.JCRNodeWrapper;
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
