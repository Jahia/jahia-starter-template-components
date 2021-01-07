package org.jahia.modules.pagebuildercomponents.handlers;

import net.htmlparser.jericho.Attribute;
import org.jahia.modules.pagebuildercomponents.model.TemplateArea;

/**
 * Handler interface
 */
public interface Handler {
    /**
     * Process attribute and augment templateArea as needed
     *
     * @param templateArea TemplateArea
     * @param attribute Attribute
     */
    void handle(TemplateArea templateArea, Attribute attribute);
}
