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

public class UiLabel extends UiVaadin {

    private Object value;

    @Override
    protected void setValue(Object value) throws MException {
        //		DataSource ds = getForm().getDataSource();
        //		((Link)getComponentEditor()).setTargetName("_blank");
        //		((Link)getComponentEditor()).setResource(new ExternalResource( MCast.toString(value) ));
        //		((Link)getComponentEditor()).setCaption( ds.getString(this, "label",
        // getConfig().getString("label", "label=Link") ) );

        ((Label) getComponentEditor()).setCaptionAsHtml(getConfig().getBoolean("html", true));
        if (value == null) ((Label) getComponentEditor()).setCaption("");
        else {
            ((Label) getComponentEditor()).setCaption(String.valueOf(value));
        }
        this.value = value;
    }

    @Override
    public Component createEditor() {
        return new Label();
        //		return new Link();
    }

    @Override
    protected Object getValue() throws MException {
        return value;
        //		return ((Link)getComponentEditor()).getResource().toString();
    }

    @Override
    public void setEnabled(boolean enabled) throws MException {}

    public static class Adapter implements ComponentAdapter {

        @Override
        public UiComponent createAdapter(INode config) {
            return new UiLabel();
        }

        @Override
        public ComponentDefinition getDefinition() {
            // TODO Auto-generated method stub
            return null;
        }
    }
}
