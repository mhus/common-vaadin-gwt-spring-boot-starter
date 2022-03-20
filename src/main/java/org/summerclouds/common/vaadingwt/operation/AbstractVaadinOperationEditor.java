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
package org.summerclouds.common.vaadingwt.operation;

import org.summerclouds.common.core.log.Log;
import org.summerclouds.common.core.node.IProperties;
import org.summerclouds.common.vaadingwt.DialogControl;

import com.vaadin.ui.VerticalLayout;

public abstract class AbstractVaadinOperationEditor extends VerticalLayout {

    private static final long serialVersionUID = 1L;
    protected DialogControl control;
    protected IProperties editorProperties;
    public static final Log log = Log.getLog(AbstractVaadinOperationEditor.class);

    protected final void initialize(IProperties editorProperties, DialogControl control) {
        this.editorProperties = editorProperties;
        this.control = control;
        initUI();
    }

    protected abstract void initUI();

    public abstract void fillOperationParameters(IProperties param);
}
