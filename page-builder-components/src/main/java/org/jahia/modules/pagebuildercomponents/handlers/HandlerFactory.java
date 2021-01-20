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
