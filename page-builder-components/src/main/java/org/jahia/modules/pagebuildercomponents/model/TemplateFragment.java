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
