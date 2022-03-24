package org.summerclouds.common.vaadingwt.form;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import org.summerclouds.common.core.form.ComponentAdapter;
import org.summerclouds.common.core.form.UiComponent;
import org.summerclouds.common.core.form.UiWizard;
import org.summerclouds.common.core.node.INode;
import org.summerclouds.common.vaadingwt.layouter.XLayElement;
import org.summerclouds.common.vaadingwt.util.ElementActivator;

public class DefaultElementActivator implements ElementActivator {

	private HashMap<String,Class<? extends ComponentAdapter>> register = new HashMap<>();
	
	@Override
	public XLayElement createObject(String name) {
		try {
			return (XLayElement) register.get(name).getConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void register(String name, Class<? extends ComponentAdapter> clazz) {
		register.put(name, clazz);
		
	}

	@Override
	public ComponentAdapter get(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UiComponent createComponent(String id, INode config) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComponentAdapter getAdapter(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UiWizard createWizard(String obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void register2(String vertical, Class<? extends XLayElement> class1) {
		// TODO Auto-generated method stub
		
	}

}
