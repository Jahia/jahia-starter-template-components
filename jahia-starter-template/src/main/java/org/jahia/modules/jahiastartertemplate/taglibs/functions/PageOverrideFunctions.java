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
package org.jahia.modules.jahiastartertemplate.taglibs.functions;

import org.apache.commons.lang3.StringUtils;
import org.jahia.modules.jahiastartertemplate.taglibs.model.FileType;
import org.jahia.services.content.JCRNodeWrapper;
import org.jahia.services.content.JCRValueWrapper;

import javax.jcr.RepositoryException;
import java.util.ArrayList;
import java.util.List;

/**
 * Tag lib functions for page overrides in site builder template
 */
public final class PageOverrideFunctions {

    private PageOverrideFunctions() {}

    /**
     * @return List of file URLs associated with the given property in the page resource
     *
     * @see [sbmix:pageOverrides] mixin in definitions.cnd
     * @throws RepositoryException
     */
    public static List<String> getPageOverrides(JCRNodeWrapper ctxNode, String propName,
            FileType fileType) throws RepositoryException
    {
        List<String> urls = new ArrayList<String>();
        if (!ctxNode.hasProperty(propName)) return urls;

        JCRValueWrapper[] values = ctxNode.getProperty(propName).getValues();
        for (JCRValueWrapper v : values) {
            // reference still there but file no longer exists
            if (v == null || v.getNode() == null) continue;

            String url = v.getNode().getUrl();
            if (StringUtils.isNotEmpty(url) && isFileType(fileType, url)) {
                urls.add(url);
            }
        }
        return urls;
    }

    private static boolean isFileType(FileType fileType, String url) {
        switch (fileType) {
            case CSS: return url.endsWith(".css");
            case JAVASCRIPT: return url.endsWith(".js");
            default: return false;
        }
    }
}
