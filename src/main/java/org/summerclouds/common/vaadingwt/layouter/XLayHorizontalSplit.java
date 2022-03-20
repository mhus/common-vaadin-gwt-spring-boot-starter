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

import org.summerclouds.common.core.error.MException;
import org.summerclouds.common.core.node.INode;

import com.vaadin.ui.HorizontalSplitPanel;

public class XLayHorizontalSplit extends HorizontalSplitPanel implements XLayElement {

    /** */
    private static final long serialVersionUID = 1L;

    @Override
    public void setConfig(INode config) throws MException {
        LayUtil.configure(this, config);
    }

    @Override
    public void doAppendChild(XLayElement child, INode cChild) {
        if (getFirstComponent() == null) setFirstComponent(child);
        else setSecondComponent(child);
    }
}
