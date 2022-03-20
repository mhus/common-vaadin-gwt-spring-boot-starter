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

import java.util.Set;
import java.util.TreeSet;

import org.summerclouds.common.core.error.MException;
import org.summerclouds.common.core.form.ComponentAdapter;
import org.summerclouds.common.core.form.ComponentDefinition;
import org.summerclouds.common.core.form.DataSource;
import org.summerclouds.common.core.form.Item;
import org.summerclouds.common.core.form.UiComponent;
import org.summerclouds.common.core.nls.MNls;
import org.summerclouds.common.core.node.INode;
import org.summerclouds.common.core.tool.MCollection;

import com.vaadin.ui.Component;
import com.vaadin.v7.ui.TwinColSelect;

@SuppressWarnings("deprecation")
public class UiOptions extends UiVaadin {

    @Override
    public Component createEditor() {
        TwinColSelect ret = new TwinColSelect();
        ret.setMultiSelect(true);
        ret.setNullSelectionAllowed(true);

        ret.setLeftColumnCaption(MNls.find(getForm(), getName() + ".available=Available options"));
        ret.setRightColumnCaption(MNls.find(getForm(), getName() + ".selected=Selected options"));

        return ret;
    }

    @Override
    protected void setValue(Object value) throws MException {
        if (value == null) {
            ((TwinColSelect) getComponentEditor()).setValue(new TreeSet<String>());
            return;
        }
        if (!(value instanceof Set))
            value = MCollection.toTreeSet(String.valueOf(value).split(","));
        ((TwinColSelect) getComponentEditor()).setValue(value);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Object getValue() throws MException {
        Set<String> ret = (Set<String>) ((TwinColSelect) getComponentEditor()).getValue();
        if (ret == null) return null;
        return ret;
    }

    @Override
    public void doUpdateMetadata() throws MException {
        TwinColSelect cb = (TwinColSelect) getComponentEditor();
        cb.removeAllItems();
        String itemsName = getConfig().getString("itemdef", getName() + "." + DataSource.ITEMS);
        Item[] items = (Item[]) getForm().getDataSource().getObject(itemsName, null);
        if (items != null) for (Item item : items) cb.addItem(item);
    }

    public static class Adapter implements ComponentAdapter {

        @Override
        public UiComponent createAdapter(INode config) {
            return new UiOptions();
        }

        @Override
        public ComponentDefinition getDefinition() {
            // TODO Auto-generated method stub
            return null;
        }
    }
}
