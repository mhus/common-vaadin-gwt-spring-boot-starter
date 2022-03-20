/**
 * Copyright (C) 2019 Mike Hummel (mh@mhus.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.summerclouds.common.vaadingwt.desktop;

public interface DesktopApi extends GuiApi {

    boolean openSpace(String spaceId, String subSpace, String search);

    boolean openSpace(
            String spaceId, String subSpace, String search, boolean history, boolean navLink);

    void rememberNavigation(SimpleGuiSpace space, String subSpace, String search, boolean navLink);

    void rememberNavigation(
            String caption, String space, String subSpace, String search, boolean navLink);

    boolean hasAccess(Class<? extends SimpleGuiSpace> space, String role);

    boolean hasAccess(SimpleGuiSpace space, String role);
}
