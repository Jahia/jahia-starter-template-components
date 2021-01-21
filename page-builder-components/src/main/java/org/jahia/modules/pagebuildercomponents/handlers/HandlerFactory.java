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
package org.jahia.modules.pagebuildercomponents.handlers;

/**
 * Generates a handler
 */
public final class HandlerFactory {

    private HandlerFactory() {
    }

    /**
     * Generate a handler
     *
     * @param type String
     * @return Handler
     */
    public static Handler getHandler(Handlers.Type type) {
        switch (type) {
            case TEXT : return new TextHandler();
            case RICH_TEXT: return new RichTextHandler();
            case IMAGE_REFERENCE: return new ImageReferenceHandler();
            case LIMIT: return new LimitHandler();
            case TYPES: return new TypesHandler();
            case AREA:
            default:  return new AreaHandler();
        }
    }
}
