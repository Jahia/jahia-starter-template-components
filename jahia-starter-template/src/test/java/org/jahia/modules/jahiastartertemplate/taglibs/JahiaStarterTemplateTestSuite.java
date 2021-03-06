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
package org.jahia.modules.jahiastartertemplate.taglibs;

import org.jahia.modules.jahiastartertemplate.taglibs.functions.BannerFunctionsTest;
import org.jahia.modules.jahiastartertemplate.taglibs.functions.PageOverrideFunctionsTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BannerFunctionsTest.class,
        PageOverrideFunctionsTest.class
})
public class JahiaStarterTemplateTestSuite {
}
