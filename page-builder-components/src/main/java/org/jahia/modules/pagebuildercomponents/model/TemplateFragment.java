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
package org.jahia.modules.pagebuildercomponents.model;

import net.htmlparser.jericho.StartTag;

import java.util.List;

/**
 * Template area abstraction, helps to generate <template:area /> tags or contains raw html rawValue
 */
public class TemplateFragment {
    private StartTag startTag;
    private HtmlElementType type;
    private String rawValue;
    private String path;
    private String areaType;
    private int limit;
    private List types;

    /**
     * Constructor
     */
    public TemplateFragment() {
        areaType = "jnt:contentList";
    }

    public StartTag getStartTag() {
        return startTag;
    }

    public void setStartTag(StartTag startTag) {
        this.startTag = startTag;
    }

    public HtmlElementType getType() {
        return type;
    }

    public void setType(HtmlElementType type) {
        this.type = type;
    }

    public String getRawValue() {
        return rawValue;
    }

    public void setRawValue(String rawValue) {
        this.rawValue = rawValue;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String[] getTypes() {
        return types == null ? null : (String[]) types.toArray(new String[0]);
    }

    public void setTypes(List types) {
        this.types = types;
    }
}
