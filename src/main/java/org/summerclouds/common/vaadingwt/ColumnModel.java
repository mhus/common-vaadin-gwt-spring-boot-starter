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
package org.summerclouds.common.vaadingwt;

import org.summerclouds.common.core.log.Log;
import org.summerclouds.common.vaadingwt.annotation.Column;
import org.summerclouds.common.vaadingwt.converter.ObjectConverter;

import com.vaadin.v7.data.util.converter.Converter;
import com.vaadin.v7.ui.Table;

@SuppressWarnings("deprecation")
public class ColumnModel {

    private Table table;
    private String colId;
    private boolean editable = true;
    private Class<?> converter;

    public ColumnModel(Table table, String colId) {
        this.table = table;
        this.colId = colId;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public String getPropertyId() {
        return colId;
    }

    public Table getTable() {
        return table;
    }

    public void setCollapsed(boolean collapsed) {
        table.setColumnCollapsed(colId, collapsed);
    }

    public Class<?> getConverter() {
        return converter;
    }

    public void setConverter(Class<?> converter) {
        if (converter == Object.class) this.converter = null;
        else this.converter = converter;
    }

    @SuppressWarnings("unchecked")
    public Converter<String, ?> generateConverter(Class<?> type) {
        try {
            if (converter != null) return (Converter<String, ?>) converter.newInstance();
            converter = MhuTable.findDefaultConverter(this, type);
            if (converter != null) return (Converter<String, ?>) converter.newInstance();
        } catch (Throwable t) {
            Log.getLog(ColumnModel.class).d(t);
        }
        return new ObjectConverter();
    }

    public void configureByAnnotation(Column columnDef, boolean canWrite) {
        setEditable(columnDef.editable() && canWrite);
        setConverter(columnDef.converter());
    }
}
