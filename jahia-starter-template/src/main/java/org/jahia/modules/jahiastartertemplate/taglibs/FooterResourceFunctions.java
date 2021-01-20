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

import org.jahia.modules.jahiastartertemplate.taglibs.model.Resource;
import org.jahia.services.content.JCRNodeWrapper;
import org.jahia.services.render.RenderContext;

import javax.jcr.RepositoryException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Loads resources into footer
 */
public class FooterResourceFunctions {


    private FooterResourceFunctions() {
    }

    /**
     * Gets js resources urls
     *
     * @param ctx render context
     * @return List<Resource>
     * @throws RepositoryException RepositoryException
     */
    public static List<Resource> getResources(RenderContext ctx) throws RepositoryException {
        String siteKey = ctx.getSite().getSiteKey();
        List<String> jsPaths = new ArrayList<>();

        // Add global paths
        jsPaths.add(String.format("/sites/%s/files/assets/global/jsFooter", siteKey));

        if (ctx.isPreviewMode() || ctx.isLiveMode()) {
            jsPaths.add(String.format("/sites/%s/files/assets/previewAndLive/jsFooter", siteKey));
        }

        List<JCRNodeWrapper> jsFiles = TaglibUtils.getFileNodes(jsPaths, ctx);

        if (ctx.isLiveMode()) {
            jsFiles = TaglibUtils.filterMinFileDuplicates(jsFiles);
        }

        jsFiles.sort(Comparator.comparing(JCRNodeWrapper::getName));

        List<Resource> resources = new ArrayList<>();

        jsFiles.forEach(file -> resources.add(new Resource("js", file.getUrl())));

        return resources;
    }
}
