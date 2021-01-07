package org.jahia.modules.pagebuildercomponents.handlers;

import net.htmlparser.jericho.Attribute;
import org.jahia.modules.pagebuildercomponents.model.TemplateArea;

import java.util.Arrays;

/**
 * Types handler: data-jahia-area-types="jnt:text,jnt:bigText"
 */
public class TypesHandler implements Handler {
    @Override
    public void handle(TemplateArea templateArea, Attribute attribute) {
        templateArea.setTypes(Arrays.asList(attribute.getValue().split(",")));
    }
}
