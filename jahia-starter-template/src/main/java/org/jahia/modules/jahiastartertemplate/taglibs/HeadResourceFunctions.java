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
 * Resource tag lib function
 */
public final class HeadResourceFunctions {

    private HeadResourceFunctions() {
    }

    /**
     * Get global specific resources
     * @param ctx   [RenderContext]
     * @return List<Resource>
     * @throws RepositoryException RepositoryException
     */
    public static List<Resource> getGlobalResources(RenderContext ctx) throws RepositoryException {
        String siteKey = ctx.getSite().getSiteKey();
        List<String> cssPaths = new ArrayList<>();
        List<String> jsPaths = new ArrayList<>();

        // Add global paths
        cssPaths.add(String.format("/sites/%s/files/assets/global/css", siteKey));
        jsPaths.add(String.format("/sites/%s/files/assets/global/jsHead", siteKey));

        List<Resource> resources = new ArrayList<>();
        resources.addAll(getResourceByPath(cssPaths, ctx, "css", Comparator.comparing(JCRNodeWrapper::getName)));
        resources.addAll(getResourceByPath(jsPaths, ctx, "js", Comparator.comparing(JCRNodeWrapper::getName)));
        return resources;
    }

    /**
     * Get preview specific resources
     * @param ctx   [RenderContext]
     * @return List<Resource>
     * @throws RepositoryException RepositoryException
     */
    public static List<Resource> getPreviewResources(RenderContext ctx) throws RepositoryException {
        String siteKey = ctx.getSite().getSiteKey();
        List<String> cssPaths = new ArrayList<>();
        List<String> jsPaths = new ArrayList<>();

        if (ctx.isPreviewMode() || ctx.isLiveMode()) {
            cssPaths.add(String.format("/sites/%s/files/assets/previewAndLive/css", siteKey));
            jsPaths.add(String.format("/sites/%s/files/assets/previewAndLive/jsHead", siteKey));
        }
        List<Resource> resources = new ArrayList<>();
        resources.addAll(getResourceByPath(cssPaths, ctx, "css", Comparator.comparing(JCRNodeWrapper::getName)));
        resources.addAll(getResourceByPath(jsPaths, ctx, "js", Comparator.comparing(JCRNodeWrapper::getName)));
        return resources;
    }

    /**
     * Get page composer specific resources
     * @param ctx   [RenderContext]
     * @return List<Resource>
     * @throws RepositoryException RepositoryException
     */
    public static List<Resource> getPageComposerResources(RenderContext ctx) throws RepositoryException {
        String siteKey = ctx.getSite().getSiteKey();
        List<String> cssPaths = new ArrayList<>();
        if (ctx.isEditMode()) {
            cssPaths.add(String.format("/sites/%s/files/assets/pageComposerOnly/css", siteKey));
        }
        return getResourceByPath(cssPaths, ctx, "css", Comparator.comparing(JCRNodeWrapper::getName));
    }

    /**
     * Helper function get resource by path
     * @param paths         [List<String>]                      paths
     * @param ctx           [RenderContext]
     * @param type          [String]                            resource type tag
     * @param comparator    [Comparator<? super JCRNodeWrapper>]
     * @return A
     * @throws RepositoryException RepositoryException
     */
    public static List<Resource> getResourceByPath(List<String> paths, RenderContext ctx, String type,
            Comparator<? super JCRNodeWrapper> comparator) throws RepositoryException {
        List<Resource> resources = new ArrayList<>();
        if(paths == null || paths.isEmpty()) return resources;
        List<JCRNodeWrapper> fileNodes = TaglibUtils.getFileNodes(paths, ctx);
        if (ctx.isLiveMode()) fileNodes = TaglibUtils.filterMinFileDuplicates(fileNodes);
        if(comparator != null) fileNodes.sort(comparator);
        fileNodes.forEach(file -> resources.add(new Resource(type, file.getUrl())));
        return resources;
    }
}
