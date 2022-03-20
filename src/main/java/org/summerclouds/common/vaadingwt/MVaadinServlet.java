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

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletException;

import org.summerclouds.common.core.M;
import org.summerclouds.common.core.log.Log;
import org.summerclouds.common.core.node.INode;
import org.summerclouds.common.core.node.INodeFactory;
import org.summerclouds.common.core.node.MNode;
import org.summerclouds.common.core.tool.MSystem;

import com.vaadin.server.VaadinServlet;

public class MVaadinServlet extends VaadinServlet {

    /** */
    private static final long serialVersionUID = 1L;

    private static Log log = Log.getLog(MVaadinServlet.class);

    private INode config;

    @Override
    public void init() throws ServletException {
        super.init();

        // Load an config file
        String mhusConfigPath = getServletConfig().getInitParameter("mhus.config");
        URL mhusConfigUrl = null;
        if (mhusConfigPath != null) {
            try {
                mhusConfigUrl = new File(mhusConfigPath).toURI().toURL();
            } catch (MalformedURLException e) {
                log.i(mhusConfigPath, e);
            }
        }
        if (mhusConfigUrl == null)
            try {
                mhusConfigUrl = MSystem.locateResource(this, getApplicationConfigName());
            } catch (IOException e) {
                log.i(getApplicationConfigName(), e);
            }
        if (mhusConfigUrl != null)
            try {
                config = M.l(INodeFactory.class).read(mhusConfigUrl.toURI().toURL());
            } catch (Exception e) {
                log.i(mhusConfigPath, e);
            }
        else config = new MNode();

        doInit();
    }

    /** Use this method to initialize extended classes. */
    protected void doInit() {}

    protected String getApplicationConfigName() {
        return "config.xml";
    }

    public INode getConfig() {
        return config;
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    //	public void setConfig(IConfig config) {
    //		this.config = config;
    //	}

}
