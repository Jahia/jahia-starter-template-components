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

/**
 * This enum is used to describe the Void HTML elements
 */
public enum VoidHtmlElement {

    /**
     * See below for the list of void elements and non-closing-tags for references
     * http://xahlee.info/js/html5_non-closing_tag.html
     * https://html.spec.whatwg.org/multipage/syntax.html#void-elements
     */
    AREA("area"),
    BASE("base"),
    BR("br"),
    COL("col"),
    EMBED("embed"),
    HR("hr"),
    IMG("img"),
    INPUT("input"),
    LINK("link"),
    META("meta"),
    PARAM("param"),
    SOURCE("source"),
    TRACK("track"),
    WBR("wbr")
    ;

    private final String tagName;
    VoidHtmlElement(String tagName) {
        this.tagName = tagName;
    }


    /**
     * Simple method to check if the void element exists
     * @param tagName   [String]
     * @return
     */
    public static boolean contains(String tagName) {
        for(VoidHtmlElement element : VoidHtmlElement.values()) {
            if(element.getTagName().equals(tagName)) return true;
        }
        return false;
    }

    public String getTagName() { return this.tagName;}
}

