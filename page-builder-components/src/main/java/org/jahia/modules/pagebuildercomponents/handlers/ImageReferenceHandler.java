package org.jahia.modules.pagebuildercomponents.handlers;

import net.htmlparser.jericho.Attribute;
import org.jahia.modules.pagebuildercomponents.model.HtmlElementType;
import org.jahia.modules.pagebuildercomponents.model.TemplateFragment;

/**
 * Text handler: data-jahia-image="imageId"
 */
public class ImageReferenceHandler implements Handler {
    @Override
    public void handle(TemplateFragment templateFragment, Attribute attribute) {
        templateFragment.setType(HtmlElementType.TEMPLATE_MODULE);
        templateFragment.setPath(attribute.getValue());
        templateFragment.setAreaType("jnt:imageReferenceLink");
    }
}
