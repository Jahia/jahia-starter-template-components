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
package org.jahia.modules.sitebuilder.taglibs.functions;

import org.jahia.services.content.JCRNodeWrapper;
import org.jahia.services.render.RenderContext;

import javax.jcr.RepositoryException;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;

import static org.jahia.modules.sitebuilder.taglibs.PropConstants.*;

public final class BannerFunctions {

    private BannerFunctions() {}

    private static final String PUBLISHED_QUERY = "SELECT [j:id] FROM [jnt:file] as node "
            + "WHERE ISDESCENDANTNODE(node, '%s') AND ([j:published] = false OR [j:published] is null)";

    public static boolean areAllAssetsPublished(RenderContext ctx) throws RepositoryException {
        String path = String.format("/sites/%s/files/assets", ctx.getSite().getSiteKey()); ;
        String query = String.format(PUBLISHED_QUERY, path);

        QueryManager queryMgr = ctx.getMainResource().getNode().getSession().getWorkspace().getQueryManager();
        QueryResult result = queryMgr.createQuery(query, Query.JCR_SQL2).execute();

        // All assets published if empty (no hasNext)
        return !result.getNodes().hasNext();
    }

    public static boolean hasPageOverrides(JCRNodeWrapper ctxNode) throws RepositoryException {
        // check file picker props if it has values
        String[] pageOverrideProps = new String[] {
                CSS_OVERRIDE_PROP, JS_HEAD_OVERRIDE_PROP, JS_BODY_OVERRIDE_PROP };
        for (String prop : pageOverrideProps) {
            if (ctxNode.hasProperty(prop) && ctxNode.getProperty(prop).getValues().length > 0) {
                return true;
            }
        }

        // check text area if it has value
        if (!ctxNode.hasProperty(PAGE_HEAD_OVERRIDE_PROP)) {
            String snippet = ctxNode.getPropertyAsString(PAGE_HEAD_OVERRIDE_PROP);
            return snippet != null && !snippet.trim().isEmpty();
        }

        return false;
    }

}
