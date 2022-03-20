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

import org.summerclouds.common.core.error.MException;
import org.summerclouds.common.core.form.ComponentAdapter;
import org.summerclouds.common.core.form.ComponentDefinition;
import org.summerclouds.common.core.form.UiComponent;
import org.summerclouds.common.core.node.INode;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.TextField;

public class UiAction extends UiVaadin {

    @Override
    protected void setValue(Object value) throws MException {}

    @Override
    public Component createEditor() {
        Button b = new Button();
        b.addClickListener(
                new Button.ClickListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(ClickEvent event) {
                        getForm()
                                .getActionHandler()
                                .doAction(getForm(), getConfig().getString("action", null));
                    }
                });
        return b;
    }

    @Override
    protected Object getValue() throws MException {
        return ((TextField) getComponentEditor()).getValue();
    }

    @Override
    protected void setCaption(String value) throws MException {
        //		if (getComponentLabel() != null) getComponentLabel().setCaption("");
        if (getComponentEditor() != null) getComponentEditor().setCaption(value);
    }

    public static class Adapter implements ComponentAdapter {

        @Override
        public UiComponent createAdapter(INode config) {
            return new UiAction();
        }

        @Override
        public ComponentDefinition getDefinition() {
            // TODO Auto-generated method stub
            return null;
        }
    }
}
