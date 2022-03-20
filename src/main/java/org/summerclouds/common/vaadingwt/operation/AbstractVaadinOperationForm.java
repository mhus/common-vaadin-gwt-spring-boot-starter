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

import org.summerclouds.common.core.form.DefRoot;
import org.summerclouds.common.core.form.MutableMForm;
import org.summerclouds.common.core.form.PropertiesDataSource;
import org.summerclouds.common.core.nls.MNlsBundle;
import org.summerclouds.common.core.node.IProperties;
import org.summerclouds.common.core.node.MProperties;
import org.summerclouds.common.vaadingwt.form.VaadinForm;

public abstract class AbstractVaadinOperationForm extends AbstractVaadinOperationEditor {

    private static final long serialVersionUID = 1L;
    protected AbstractVaadinOperation operation;
    protected VaadinForm model;
    private PropertiesDataSource dataSource;

    public AbstractVaadinOperationForm(AbstractVaadinOperation operation) {
        this.operation = operation;
    }

    @Override
    protected void initUI() {
        model = createForm();
        model.setShowInformation(true);
        if (model.getForm().getNlsBundle() == null && model.getForm() instanceof MutableMForm)
            ((MutableMForm) model.getForm()).setNlsBundle(MNlsBundle.lookup(this));
        //        model.doBuild(getActivator());
        try {
            model.doBuild();
            this.addComponent(model);
        } catch (Exception e) {
            log.e(e);
        }
    }

    protected VaadinForm createForm() {
        try {
            DefRoot formDef = operation.getDescription().getForm();
            VaadinForm form = new VaadinForm();
            form.setForm(new MutableMForm(formDef));
            dataSource = new PropertiesDataSource();
            initDataSource(dataSource);
            if (dataSource.getProperties() == null) dataSource.setProperties(new MProperties());
            ((MutableMForm) form.getForm()).setDataSource(dataSource);
            return form;
        } catch (Throwable t) {
            log.w(t);
        }
        return null;
    }

    protected abstract void initDataSource(PropertiesDataSource ds);

    @Override
    public void fillOperationParameters(IProperties param) {
        param.putAll(dataSource.getProperties());
    }
}
