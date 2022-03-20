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

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class ConfirmDialog extends ModalDialog {

    /** */
    private static final long serialVersionUID = 1L;

    private Action confirm;
    private Action cancel;
    private String message;
    private Action result;
    private Listener listener;
    protected Label label;

    public ConfirmDialog(
            String title, String message, String txtConfirm, String txtCancel, Listener listener)
            throws Exception {

        this.message = message;
        this.listener = listener;
        confirm = new Action("confirm", txtConfirm);
        confirm.setDefaultAction(true);
        cancel = new Action("cancel", txtCancel);
        actions = new Action[] {confirm, cancel};
        setPack(true);
        initUI();
        setCaption(title);
    }

    public Label getLabel() {
        return label;
    }

    @Override
    protected void initContent(VerticalLayout layout) throws Exception {
        label = new Label(message);
        label.setContentMode(ContentMode.HTML);
        layout.addComponent(label);
    }

    @Override
    protected boolean doAction(Action action) {
        result = action;
        if (listener != null) listener.onClose(this);
        return true;
    }

    public static void show(
            UI ui,
            String title,
            String message,
            String txtConfirm,
            String txtCancel,
            Listener listener) {
        try {
            new ConfirmDialog(title, message, txtConfirm, txtCancel, listener).show(ui);
        } catch (Exception e) {
        }
    }

    public static interface Listener {

        public void onClose(ConfirmDialog dialog);
    }

    public boolean isConfirmed() {
        return result == confirm;
    }

    public boolean isCancel() {
        return result == cancel;
    }
}
