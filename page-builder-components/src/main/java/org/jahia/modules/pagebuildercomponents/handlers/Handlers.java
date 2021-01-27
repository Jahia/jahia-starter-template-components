/*
 * Copyright (C) 2002-2021 Jahia Solutions Group SA. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
        RICH_TEXT("richtext"),
        IMAGE_REFERENCE("image"),
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
