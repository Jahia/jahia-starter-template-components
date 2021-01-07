package org.jahia.modules.pagebuildercomponents.handlers;

import net.htmlparser.jericho.Attribute;
import org.jahia.modules.pagebuildercomponents.model.TemplateArea;

/**
 * Text handler: data-jahia-area-text="textId"
 */
public class TextHandler implements Handler {
    @Override
    public void handle(TemplateArea templateArea, Attribute attribute) {
        templateArea.setPath(attribute.getValue());
        templateArea.setAreaType("jnt:text");
    }
}
