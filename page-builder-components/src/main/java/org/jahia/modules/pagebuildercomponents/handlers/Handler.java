package org.jahia.modules.pagebuildercomponents.handlers;

import net.htmlparser.jericho.Attribute;
import org.jahia.modules.pagebuildercomponents.model.TemplateFragment;

/**
 * Handler interface
 */
public interface Handler {
    /**
     * Process attribute and augment templateFragment as needed
     *
     * @param templateFragment TemplateFragment
     * @param attribute Attribute
     */
    void handle(TemplateFragment templateFragment, Attribute attribute);
}
