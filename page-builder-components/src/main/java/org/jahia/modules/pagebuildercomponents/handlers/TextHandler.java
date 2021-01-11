package org.jahia.modules.pagebuildercomponents.handlers;

import net.htmlparser.jericho.Attribute;
import org.jahia.modules.pagebuildercomponents.model.TemplateFragment;

/**
 * Text handler: data-jahia-text="textId"
 */
public class TextHandler implements Handler {
    @Override
    public void handle(TemplateFragment templateFragment, Attribute attribute) {
        templateFragment.setPath(attribute.getValue());
        templateFragment.setAreaType("jnt:text");
    }
}
