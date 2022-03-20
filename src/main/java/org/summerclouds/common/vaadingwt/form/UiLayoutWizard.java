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

import java.util.LinkedList;

import org.summerclouds.common.core.error.MException;
import org.summerclouds.common.core.form.ComponentAdapter;
import org.summerclouds.common.core.form.ComponentDefinition;
import org.summerclouds.common.core.form.DataSource;
import org.summerclouds.common.core.form.UiComponent;
import org.summerclouds.common.core.node.INode;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

public class UiLayoutWizard extends UiLayout {

    private static final long serialVersionUID = 1L;
    private TabSheet layout;
    private LinkedList<UiVaadin> tabIndex = new LinkedList<>();

    public UiLayoutWizard() {
        this.layout = new TabSheet();
        //		layout.setSizeFull();
        layout.setWidth("100%");
    }

    @Override
    public void createRow(final UiVaadin c) {

        tabIndex.add(c);
        final int current = tabIndex.size();

        // String name = c.getName();
        Component editor = c.createEditor();
        DataSource ds = getForm().getDataSource();
        String caption = c.getCaption(ds);

        VerticalLayout iEditor = new VerticalLayout();
        iEditor.addComponent(editor);
        iEditor.setExpandRatio(editor, 1);

        HorizontalLayout toolBar = new HorizontalLayout();
        Button bBack = new Button(VaadinIcons.ARROW_LEFT);
        bBack.addClickListener(
                e -> {
                    if (current < 1) return;
                    layout.setSelectedTab(current - 2);
                });
        toolBar.addComponent(bBack);
        toolBar.setExpandRatio(bBack, 0);

        Label spacer = new Label();
        toolBar.addComponent(spacer);
        toolBar.setExpandRatio(spacer, 1);

        Button bNext = new Button(VaadinIcons.ARROW_RIGHT);
        bNext.addClickListener(
                e -> {
                    if (current >= tabIndex.size()) return;
                    layout.setSelectedTab(current);
                });
        toolBar.addComponent(bNext);
        toolBar.setExpandRatio(bNext, 0);

        toolBar.setWidth("100%");
        iEditor.addComponent(toolBar);
        iEditor.setExpandRatio(toolBar, 0);

        iEditor.setSizeFull();

        layout.addTab(iEditor, caption);
    }

    @Override
    public void doRevert() throws MException {

        for (UiVaadin entry : tabIndex)
            try {
                entry.doRevert();
            } catch (Throwable e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        super.doRevert();
    }

    @Override
    public Component getComponent() {
        return layout;
    }

    public static class Adapter implements ComponentAdapter {

        @Override
        public UiComponent createAdapter(INode config) {
            return new UiLayoutWizard();
        }

        @Override
        public ComponentDefinition getDefinition() {
            // TODO Auto-generated method stub
            return null;
        }
    }
}
