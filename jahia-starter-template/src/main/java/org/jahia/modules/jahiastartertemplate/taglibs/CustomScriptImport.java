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
