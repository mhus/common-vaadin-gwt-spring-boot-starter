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
package org.summerclouds.common.vaadingwt.layouter;

import java.util.HashMap;

import org.summerclouds.common.core.form.DefRoot;
import org.summerclouds.common.core.log.MLog;
import org.summerclouds.common.core.node.INode;
import org.summerclouds.common.vaadingwt.util.ElementActivator;

public class LayoutBuilder extends MLog {

    public static final String VERTICAL = "vertical";
    public static final String HORIZONTAL = "horizontal";
    public static final String VERTICAL_SPLIT = "split_vertical";
    public static final String HORIZONTAL_SPLIT = "split_horizontal";
    public static final String DATA = "data";
    public static final String FULL_SIZE = "full_size";
    public static final String WIDTH = "width";
    public static final String HEIGHT = "height";
    public static final String MARGIN = "margin";
    public static final String SPACING = "spacing";
    public static final String STYLE = "style";
    public static final String HIDDEN = "hidden";
    public static final String SPLIT_MIN = "split_min";
    public static final String SPLIT_MAX = "split_max";
    public static final String SPLIT_POS = "split_pos";
    public static final String EXPAND = "expand";

    private HashMap<String, XLayElement> elements;
    private LayModel model;
	private ElementActivator activator;

    public LayoutBuilder() {}

    public LayoutBuilder doBuild(INode layout) throws Exception {

        if (layout.getName().equals(DefRoot.ROOT))
            layout = layout.getObjectList("layout").iterator().next();

        XLayElement root = getActivator().createObject(layout.getName());
        root.setConfig(layout);
        elements = new HashMap<String, XLayElement>();
        model = new LayModel(root, elements);
        build(root, layout);

        return this;
    }

    public ElementActivator getActivator() {
        return activator;
    }

    protected void build(XLayElement parent, INode layout) throws Exception {
        INode layoutLayout = layout.getObject("layout");
        if (layoutLayout == null) return;

        for (INode cChild : layoutLayout.getObjects()) {
            XLayElement child = getActivator().createObject(cChild.getName());
            parent.doAppendChild(child, cChild);
            child.setConfig(cChild);

            // remember if have name
            String name = cChild.getExtracted("name");
            if (name != null) elements.put(name, child);

            // recursion
            build(child, cChild);
        }
    }

    public static void initDefaultActivator(ElementActivator activator) {
        activator.register2(VERTICAL, XLayVertical.class);
        activator.register2(HORIZONTAL, XLayHorizontal.class);
        activator.register2(VERTICAL_SPLIT, XLayVerticalSplit.class);
        activator.register2(HORIZONTAL_SPLIT, XLayHorizontalSplit.class);
    }

    public LayModel getModel() {
        return model;
    }

	public void setActivator(ElementActivator activator) {
		this.activator = activator;
	}
}
