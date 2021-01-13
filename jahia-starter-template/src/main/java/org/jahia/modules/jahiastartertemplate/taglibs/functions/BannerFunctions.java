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
