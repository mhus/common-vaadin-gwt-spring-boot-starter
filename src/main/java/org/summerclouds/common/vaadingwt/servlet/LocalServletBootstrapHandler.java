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
package org.summerclouds.common.vaadingwt.servlet;

import com.vaadin.server.communication.ServletBootstrapHandler;

public class LocalServletBootstrapHandler extends ServletBootstrapHandler {

    private static final long serialVersionUID = 1L;
    private VaadinLocalServlet servlet;

    public LocalServletBootstrapHandler(VaadinLocalServlet servlet) {
        this.servlet = servlet;
    }

    @SuppressWarnings("deprecation")
    @Override
    protected String getServiceUrl(BootstrapContext context) {
        //        String pathInfo = context.getRequest().getPathInfo();
        //        if (pathInfo == null)
        //        	return super.getServiceUrl(context);
        return servlet.getServicePath();
    }
}
