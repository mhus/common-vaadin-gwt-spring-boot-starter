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
package org.summerclouds.common.vaadingwt.form;

import org.summerclouds.common.vaadingwt.util.ActivatorAdapterProvider;
import org.summerclouds.common.vaadingwt.util.ElementActivator;

/*
Default Config:
<de.mhus.lib.vaadin.form.DefaultAdapterProvider>
	<adapter name="text"        class="de.mhus.lib.vaadin.form.UiText.Adapter"/>
	<adapter name="checkbox"    class="de.mhus.lib.vaadin.form.UiCheckbox.Adapter"/>
	<adapter name="date"        class="de.mhus.lib.vaadin.form.UiDate.Adapter"/>
	<adapter name="password"    class="de.mhus.lib.vaadin.form.UiPassword.Adapter"/>
	<adapter name="number"      class="de.mhus.lib.vaadin.form.UiNumber.Adapter"/>
	<adapter name="textarea"    class="de.mhus.lib.vaadin.form.UiTextArea.Adapter"/>
	<adapter name="richtext"    class="de.mhus.lib.vaadin.form.UiRichTextArea.Adapter"/>
	<adapter name="combobox"    class="de.mhus.lib.vaadin.form.UiCombobox.Adapter"/>
	<adapter name="layout100"   class="de.mhus.lib.vaadin.form.UiLayout100.Adapter"/>
	<adapter name="layout50x50" class="de.mhus.lib.vaadin.form.UiLayout50x50.Adapter"/>
	<adapter name="layouttabs"  class="de.mhus.lib.vaadin.form.UiLayoutTabs.Adapter"/>
	<adapter name="100"         class="de.mhus.lib.vaadin.form.UiLayout100.Adapter"/>
	<adapter name="50x50"       class="de.mhus.lib.vaadin.form.UiLayout50x50.Adapter"/>
	<adapter name="tabs"        class="de.mhus.lib.vaadin.form.UiLayoutTabs.Adapter"/>
	<adapter name="options"     class="de.mhus.lib.vaadin.form.UiOptions.Adapter"/>
	<adapter name="layoutpanel" class="de.mhus.lib.vaadin.form.UiPanel.Adapter"/>
	<adapter name="panel"       class="de.mhus.lib.vaadin.form.UiPanel.Adapter"/>
	<adapter name="link"        class="de.mhus.lib.vaadin.form.UiLink.Adapter"/>
	<adapter name="label"       class="de.mhus.lib.vaadin.form.UiLabel.Adapter"/>
</de.mhus.lib.vaadin.form.DefaultAdapterProvider>
 */
public class DefaultAdapterProvider extends ActivatorAdapterProvider {

    public DefaultAdapterProvider() {
        super(null);
        ElementActivator a = new DefaultElementActivator();
        activator = a;

        // default config
        a.register("text", UiText.Adapter.class);
        a.register("checkbox", UiCheckbox.Adapter.class);
        a.register("date", UiDate.Adapter.class);
        a.register("password", UiPassword.Adapter.class);
        a.register("number", UiNumber.Adapter.class);
        a.register("textarea", UiTextArea.Adapter.class);
        a.register("richtext", UiRichTextArea.Adapter.class);
        a.register("combobox", UiCombobox.Adapter.class);
        a.register("layout100", UiLayout100.Adapter.class);
        a.register("layout50x50", UiLayout2x50.Adapter.class);
        a.register("layout33x33x33", UiLayout3x33.Adapter.class);
        a.register("layout25x25x25x25", UiLayout4x25.Adapter.class);
        a.register("layouttabs", UiLayoutTabs.Adapter.class);
        a.register("layoutwizard", UiLayoutWizard.Adapter.class);
        a.register("100", UiLayout100.Adapter.class);
        a.register("50x50", UiLayout2x50.Adapter.class);
        a.register("tabs", UiLayoutTabs.Adapter.class);
        a.register("options", UiOptions.Adapter.class);
        a.register("layoutpanel", UiPanel.Adapter.class);
        a.register("panel", UiPanel.Adapter.class);
        a.register("link", UiLink.Adapter.class);
        a.register("label", UiLabel.Adapter.class);
        a.register("action", UiAction.Adapter.class);
        a.register("void", UiVoid.Adapter.class);

    }
}
