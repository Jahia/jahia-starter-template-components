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
package org.jahia.modules.jahiastartertemplate.taglibs.functions;

import org.jahia.api.Constants;
import org.jahia.modules.jahiastartertemplate.taglibs.PropConstants;
import org.jahia.services.content.*;
import org.jahia.services.render.RenderContext;

import javax.jcr.RepositoryException;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public final class BannerFunctions {

    private BannerFunctions() {}

    public static boolean areAllAssetsPublished(RenderContext ctx) throws RepositoryException {
        JCRNodeWrapper nodeWrapper = ctx.getSite().getNode("files/assets");
        String sourceWorkSpace = ctx.getMainResource().getWorkspace();

        JCRPublicationService publicationService = JCRPublicationService.getInstance();
        List<PublicationInfo> infos = publicationService.getPublicationInfo(
                nodeWrapper.getIdentifier(), Collections.singleton(nodeWrapper.getLanguage()),
                false, true, true,
                sourceWorkSpace, Constants.LIVE_WORKSPACE
        );

        if(infos == null || infos.isEmpty()) {
            return true;
        }
        for(PublicationInfo info : infos) {
            Set<Integer> allStatus = info.getTreeStatus(nodeWrapper.getLanguage());
            if(!allStatus.isEmpty() && Collections.max(allStatus) > PublicationInfo.PUBLISHED) return false;
        }
        return true;
    }

    public static boolean hasPageOverrides(JCRNodeWrapper ctxNode) throws RepositoryException {
        // check file picker props if it has values
        String[] pageOverrideProps = new String[] {
                PropConstants.CSS_OVERRIDE_PROP, PropConstants.JS_HEAD_OVERRIDE_PROP, PropConstants.JS_BODY_OVERRIDE_PROP };
        for (String prop : pageOverrideProps) {
            if (ctxNode.hasProperty(prop) && ctxNode.getProperty(prop).getValues().length > 0) {
                return true;
            }
        }

        // check text area if it has value
        if (!ctxNode.hasProperty(PropConstants.PAGE_HEAD_OVERRIDE_PROP)) {
            String snippet = ctxNode.getPropertyAsString(PropConstants.PAGE_HEAD_OVERRIDE_PROP);
            return snippet != null && !snippet.trim().isEmpty();
        }

        return false;
    }

}
