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
import org.summerclouds.common.core.tool.MCast;

import com.vaadin.ui.Component;
import com.vaadin.ui.TextField;

public class UiText extends UiVaadin {

    @Override
    protected void setValue(Object value) throws MException {
        ((TextField) getComponentEditor()).setValue(MCast.toString(value));
    }

    @Override
    public Component createEditor() {
        return new TextField();
    }

    @Override
    protected Object getValue() throws MException {
        return ((TextField) getComponentEditor()).getValue();
    }

    public static class Adapter implements ComponentAdapter {

        @Override
        public UiComponent createAdapter(INode config) {
            return new UiText();
        }

        @Override
        public ComponentDefinition getDefinition() {
            // TODO Auto-generated method stub
            return null;
        }
    }
}
