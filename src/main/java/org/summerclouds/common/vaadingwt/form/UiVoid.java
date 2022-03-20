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

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;

public class UiVoid extends UiVaadin {

    @Override
    protected void setValue(Object value) throws MException {}

    @Override
    public Component createEditor() {
        return new Label();
    }

    @Override
    protected Object getValue() throws MException {
        return null;
    }

    @Override
    protected void setCaption(String value) throws MException {}

    @Override
    public void setError(String error) {}

    @Override
    public void clearError() {}

    @Override
    public void fieldValueChangedEvent() {}

    public static class Adapter implements ComponentAdapter {

        @Override
        public UiComponent createAdapter(INode config) {
            return new UiVoid();
        }

        @Override
        public ComponentDefinition getDefinition() {
            // TODO Auto-generated method stub
            return null;
        }
    }
}
