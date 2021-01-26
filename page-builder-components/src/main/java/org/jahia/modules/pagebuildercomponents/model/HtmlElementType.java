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
package org.jahia.modules.pagebuildercomponents.model;

/**
 * Html type
 */
public enum HtmlElementType {
    HTML_FRAGMENT("HTML_FRAGMENT"),
    TEMPLATE_AREA("TEMPLATE_AREA"),
    TEMPLATE_MODULE("TEMPLATE_MODULE"),
    ;

    private final String value;

    HtmlElementType(String val) {
        this.value = val;
    }

    @Override
    public String toString() {
        return value;
    }
}
