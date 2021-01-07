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
package org.jahia.modules.pagebuildercomponents.model;

/**
 * Html type
 */
public enum HtmlElementType {
    HTML_FRAGMENT("HTML_FRAGMENT"),
    TEMPLATE_AREA("TEMPLATE_AREA"),
    TEMPLATE_CONTENT("TEMPLATE_CONTENT"),
    ;

    private final String value;

    HtmlElementType(String val) {
        this.value = val;
    }

    @Override
    public String toString() {
        return value;
    }
}
