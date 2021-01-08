package org.jahia.modules.pagebuildercomponents.handlers;

import net.htmlparser.jericho.Attribute;
import org.jahia.modules.pagebuildercomponents.model.TemplateFragment;

/**
 * Limit handler: data-jahia-area-limit="3"
 */
public class LimitHandler implements Handler {
    @Override
    public void handle(TemplateFragment templateFragment, Attribute attribute) {
        templateFragment.setLimit(Integer.parseInt(attribute.getValue()));
    }
}
