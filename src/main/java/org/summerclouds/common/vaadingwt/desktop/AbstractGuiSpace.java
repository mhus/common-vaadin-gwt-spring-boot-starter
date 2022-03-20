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

import java.util.Locale;

import org.summerclouds.common.core.error.MRuntimeException;
import org.summerclouds.common.core.error.RC;
import org.summerclouds.common.core.log.MLog;
import org.summerclouds.common.core.tool.MString;
import org.summerclouds.common.vaadingwt.AccessControl;
import org.summerclouds.common.vaadingwt.annotation.GuiSpaceDefinition;

import com.vaadin.ui.AbstractComponent;

public abstract class AbstractGuiSpace extends MLog implements GuiSpaceService {

    @Override
    public String getName() {
        GuiSpaceDefinition def = getClass().getAnnotation(GuiSpaceDefinition.class);
        if (def != null) return def.name();
        return getClass().getSimpleName();
    }

    @Override
    public String getDisplayName(Locale locale) {
        GuiSpaceDefinition def = getClass().getAnnotation(GuiSpaceDefinition.class);
        if (def != null) return def.description();
        return getClass().getSimpleName();
    }

    @Override
    public AbstractComponent createSpace() {
        GuiSpaceDefinition def = getClass().getAnnotation(GuiSpaceDefinition.class);
        if (def != null) {
            try {
                Class<? extends AbstractComponent> clazz = def.spaceClass();
                AbstractComponent impl = (AbstractComponent) clazz.getConstructor().newInstance();
                return impl;
            } catch (Exception e) {
                throw new MRuntimeException(RC.STATUS.ERROR,  e);
            }
        }
        return null;
    }

    @Override
    public boolean hasAccess(AccessControl control) {
        GuiSpaceDefinition def = getClass().getAnnotation(GuiSpaceDefinition.class);
        //XXX
//        if (def != null) {
//            Class<? extends AbstractComponent> clazz = def.spaceClass();
//            if (Aaa.isAnnotated(clazz)) {
//                try {
//                    Aaa.checkPermission(clazz);
//                } catch (AuthorizationException e) {
//                    return false;
//                }
//            }
//        }
        return true;
    }

    @Override
    public boolean isHiddenSpace() {
        GuiSpaceDefinition def = getClass().getAnnotation(GuiSpaceDefinition.class);
        if (def != null) {
            return def.hidden();
        }
        return false;
    }

    @Override
    public AbstractComponent createTile() {
        return null;
    }

    @Override
    public int getTileSize() {
        return 1;
    }

    @Override
    public boolean isHiddenInMenu() {
        GuiSpaceDefinition def = getClass().getAnnotation(GuiSpaceDefinition.class);
        if (def != null) {
            return def.hiddenInMenu();
        }
        return false;
    }

    @Override
    public HelpContext createHelpContext(Locale locale) {
        GuiSpaceDefinition def = getClass().getAnnotation(GuiSpaceDefinition.class);
        if (def != null && MString.isSet(def.helpUrl())) {
            return new IFrameHelpContext(def.helpUrl());
        }
        return null;
    }
}
