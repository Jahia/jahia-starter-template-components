package org.jahia.modules.pagebuildercomponents.handlers;

import net.htmlparser.jericho.Attribute;
import org.apache.commons.lang.StringUtils;
import org.jahia.modules.pagebuildercomponents.exception.PageBuilderException;
import org.jahia.modules.pagebuildercomponents.model.TemplateFragment;

import java.util.Arrays;
import java.util.Optional;

/**
 * Handler types and handle functionality
 */
public class Handlers {

    enum Type {
        TEXT("text"),
        LIMIT("limit"),
        TYPES("types"),
        AREA("area");

        private String type;

        Type(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

        /**
         * Find type from string
         *
         * @param str String
         * @return Optional<Type>
         */
        public static Optional<Type> fromString(String str) {
            return Optional.ofNullable(Arrays.stream(values())
                    .filter(t -> t.type.equalsIgnoreCase(str))
                    .findFirst()
                    .orElseThrow(() -> new PageBuilderException(String.format(
                            "Unknown handler argument: %s. Make sure to supply an attribute that has a handler or create a handler for %s", str, str))));
        }
    }

    /**
     * Handle an attribute for template area
     *
     * @param templateFragment TemplateFragment
     * @param attribute Attribute
     */
    public static void handle(TemplateFragment templateFragment, Attribute attribute) {
        Optional<Type> type = Type.fromString(StringUtils.substringAfterLast(attribute.getName(), "-"));

        if (type.isPresent()) {
            Handler handler = HandlerFactory.getHandler(type.get());
            handler.handle(templateFragment, attribute);
        }
    }
}
