package org.summerclouds.common.vaadingwt.util;

import org.summerclouds.common.core.form.ComponentAdapter;
import org.summerclouds.common.core.form.ComponentAdapterProvider;
import org.summerclouds.common.vaadingwt.layouter.XLayElement;

public interface ElementActivator extends ComponentAdapterProvider {

	XLayElement createObject(String name);

	void register(String name, Class<? extends ComponentAdapter> clazz);

	ComponentAdapter get(String name);

	void register2(String vertical, Class<? extends XLayElement> class1);

}
