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
