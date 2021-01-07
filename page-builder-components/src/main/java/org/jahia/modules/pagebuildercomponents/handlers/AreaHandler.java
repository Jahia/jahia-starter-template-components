package org.jahia.modules.pagebuildercomponents.handlers;

import net.htmlparser.jericho.Attribute;
import org.jahia.modules.pagebuildercomponents.model.TemplateArea;

/**
 * Area handler: data-jahia-area="areaId"
 */
public class AreaHandler implements Handler {
    @Override
    public void handle(TemplateArea templateArea, Attribute attribute) {
        templateArea.setPath(attribute.getValue());
    }
}
