package org.jahia.modules.pagebuildercomponents.handlers;

import net.htmlparser.jericho.Attribute;
import org.jahia.modules.pagebuildercomponents.model.TemplateFragment;

/**
 * Area handler: data-jahia-area="areaId"
 */
public class AreaHandler implements Handler {
    @Override
    public void handle(TemplateFragment templateFragment, Attribute attribute) {
        templateFragment.setPath(attribute.getValue());
    }
}
