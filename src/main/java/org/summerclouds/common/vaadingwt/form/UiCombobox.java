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
import org.summerclouds.common.core.form.DataSource;
import org.summerclouds.common.core.form.Item;
import org.summerclouds.common.core.form.UiComponent;
import org.summerclouds.common.core.node.INode;
import org.summerclouds.common.core.tool.MCast;

import com.vaadin.ui.Component;
import com.vaadin.v7.ui.ComboBox;

@SuppressWarnings("deprecation")
public class UiCombobox extends UiVaadin {

    @Override
    public Component createEditor() {
        ComboBox ret = new ComboBox();
        ret.setNullSelectionAllowed(false);
        ret.setTextInputAllowed(false);
        return ret;
    }

    @Override
    protected void setValue(Object value) throws MException {
        ((ComboBox) getComponentEditor()).setValue(MCast.toString(value));
    }

    @Override
    protected Object getValue() throws MException {
        String ret = (String) ((ComboBox) getComponentEditor()).getValue();
        if (ret == null) return null;
        return ret;
    }

    @Override
    public void doUpdateMetadata() throws MException {
        ComboBox cb = (ComboBox) getComponentEditor();
        Object value = cb.getValue();
        cb.removeAllItems();
        String itemsDef = getConfig().getString("itemdef", getName() + "." + DataSource.ITEMS);
        Object itemsObj = getForm().getDataSource().getObject(itemsDef, null);
        if (itemsObj == null) itemsObj = getConfig().getString("items", null);
        if (itemsObj != null) {
            if (itemsObj instanceof Item[]) {
                Item[] items = (Item[]) itemsObj;
                for (Item item : items) {
                    cb.addItem(item.getKey());
                    cb.setItemCaption(item.getKey(), item.getCaption());
                }
            } else if (itemsObj instanceof String) {
                for (String item : ((String) itemsObj).split(";")) {
                    String[] parts = item.split("=", 2);
                    if (parts.length == 2) {
                        cb.addItem(parts[0]);
                        cb.setItemCaption(parts[0], parts[1]);
                    } else if (parts.length == 1) {
                        cb.addItem(parts[0]);
                        cb.setItemCaption(parts[0], parts[0]);
                    }
                }
            }
        }
        cb.setValue(value);
    }

    public static class Adapter implements ComponentAdapter {

        @Override
        public UiComponent createAdapter(INode config) {
            return new UiCombobox();
        }

        @Override
        public ComponentDefinition getDefinition() {
            // TODO Auto-generated method stub
            return null;
        }
    }
}
