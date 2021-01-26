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
