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
 *     contained in the Jahia Solutions Group Terms &amp; Conditions as well as
 *     the Jahia Sustainable Enterprise License (JSEL).
 *
 *     For questions regarding licensing, support, production usage...
 *     please contact our team at sales@jahia.com or go to http://www.jahia.com/license.
 *
 * ==========================================================================================
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

