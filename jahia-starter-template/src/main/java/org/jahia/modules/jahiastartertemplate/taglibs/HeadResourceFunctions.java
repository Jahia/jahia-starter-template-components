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
