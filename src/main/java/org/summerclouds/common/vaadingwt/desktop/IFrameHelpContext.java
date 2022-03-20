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
package org.summerclouds.common.vaadingwt.desktop;

import java.util.List;

import org.summerclouds.common.core.log.MLog;
import org.summerclouds.common.core.util.Pair;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

public class IFrameHelpContext extends MLog implements HelpContext {

    private HelpPanel panel;
    private String mainUrl;

    public IFrameHelpContext(String mainUrl) {
        this.mainUrl = mainUrl;
    }

    @Override
    public synchronized Component getComponent() {
        if (panel == null) {
            panel = new HelpPanel();
            showHelpTopic(null);
        }
        return panel;
    }

    @Override
    public void showHelpTopic(String topic) {
        panel.setContent(topic);
    }

    private class HelpPanel extends VerticalLayout {

        private static final long serialVersionUID = 1L;
        private BrowserFrame iframe;

        public HelpPanel() {
            iframe = new BrowserFrame();
            addComponent(iframe);
            iframe.setSizeFull();
            setSizeFull();
        }

        public void setContent(String url) {
            if (url == null) url = mainUrl;
            iframe.setSource(new ExternalResource(url));
        }
    }

    @Override
    public List<Pair<String, String>> searchTopics(String search) {
        return null;
    }

    @Override
    public List<Pair<String, String>> getIndex() {
        return null;
    }
}
