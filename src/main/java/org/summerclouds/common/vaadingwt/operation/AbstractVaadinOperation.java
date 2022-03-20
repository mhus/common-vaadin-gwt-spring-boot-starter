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

import org.summerclouds.common.core.node.INode;
import org.summerclouds.common.core.operation.AbstractOperation;
import org.summerclouds.common.core.operation.DefaultTaskContext;
import org.summerclouds.common.core.operation.OperationDescription;
import org.summerclouds.common.core.operation.OperationResult;
import org.summerclouds.common.core.operation.TaskContext;
import org.summerclouds.common.vaadingwt.DialogControl;

import com.vaadin.ui.Component;

public abstract class AbstractVaadinOperation extends AbstractOperation implements VaadinOperation {

    @Override
    public Component createEditor(INode editorProperties, DialogControl control) {
        AbstractVaadinOperationEditor editor = createEditor();
        if (editor == null) return null;
        editor.setSizeFull();
        // editor.setCaption(getDescription().getCaption());
        editor.initialize(editorProperties, control);
        return editor;
    }

    /**
     * Create the visible content editor for this operation.
     *
     * @return
     */
    protected abstract AbstractVaadinOperationEditor createEditor();

    @Override
    protected abstract OperationResult execute(TaskContext context) throws Exception;

    @Override
    public OperationResult doExecute(INode editorProperties, Component editor) throws Exception {
        if (editor != null && (editor instanceof AbstractVaadinOperationEditor))
            ((AbstractVaadinOperationEditor) editor).fillOperationParameters(editorProperties);

        DefaultTaskContext context = new DefaultTaskContext(this.getClass());
        context.setParameters(editorProperties);
        return execute(context);
    }

    @Override
    protected abstract OperationDescription createDescription();
}
