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

import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class PasswordInputDialog extends ModalDialog {

    /** */
    private static final long serialVersionUID = 1L;

    private String message;
    private Listener listener;
    private Action confirm;
    private Action cancel;
    protected Label label;
    private PasswordField textField;
    private String txtInput;
    private Action result;

    public PasswordInputDialog(
            String title,
            String message,
            String txtInput,
            String txtConfirm,
            String txtCancel,
            Listener listener)
            throws Exception {

        this.message = message;
        this.listener = listener;
        this.txtInput = txtInput;
        confirm = new Action("confirm", txtConfirm);
        cancel = new Action("cancel", txtCancel);
        actions = new Action[] {confirm, cancel};
        initUI();
        setCaption(title);
    }

    public Label getLabel() {
        return label;
    }

    public PasswordField getTextField() {
        return textField;
    }

    @Override
    protected void initContent(VerticalLayout layout) throws Exception {
        label = new Label(message);
        label.setContentMode(ContentMode.HTML);
        layout.addComponent(label);

        textField = new PasswordField();
        textField.setValue(txtInput);
        textField.setWidth("100%");
        textField.focus();

        textField.addShortcutListener(
                new ShortcutListener("Confirm", ShortcutAction.KeyCode.ENTER, null) {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void handleAction(Object sender, Object target) {
                        confirm.doAction(PasswordInputDialog.this);
                    }
                });

        layout.addComponent(textField);
        txtInput = null;
    }

    @Override
    protected boolean doAction(Action action) {
        result = action;
        if (action.equals(confirm)) txtInput = (String) textField.getValue();
        if (listener != null) {
            if (action.equals(confirm) && !listener.validate(txtInput)) return false;
            listener.onClose(this);
        }
        return true;
    }

    public static void show(
            UI parent,
            String title,
            String txtInput,
            String message,
            String txtConfirm,
            String txtCancel,
            Listener listener) {
        try {
            //			if (parent == null) parent = UI.getCurrent();
            new PasswordInputDialog(title, message, txtInput, txtConfirm, txtCancel, listener)
                    .show(parent);
        } catch (Exception e) {
        }
    }

    public static interface Listener {

        public boolean validate(String txtInput);

        public void onClose(PasswordInputDialog dialog);
    }

    public boolean isConfirmed() {
        return result == confirm;
    }

    public boolean isCancel() {
        return result == cancel;
    }

    public String getInputText() {
        return txtInput;
    }
}
