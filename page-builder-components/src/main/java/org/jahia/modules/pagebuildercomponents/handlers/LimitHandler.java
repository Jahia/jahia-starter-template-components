package org.jahia.modules.pagebuildercomponents.handlers;

import net.htmlparser.jericho.Attribute;
import org.jahia.modules.pagebuildercomponents.model.TemplateArea;

/**
 * Limit handler: data-jahia-area-limit="3"
 */
public class LimitHandler implements Handler {
    @Override
    public void handle(TemplateArea templateArea, Attribute attribute) {
        templateArea.setLimit(Integer.parseInt(attribute.getValue()));
    }
}
