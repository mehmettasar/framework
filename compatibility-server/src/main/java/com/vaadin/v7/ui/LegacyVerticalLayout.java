/*
 * Copyright 2000-2016 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.v7.ui;

import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

/**
 * Vertical layout
 *
 * <code>LegacyVerticalLayout</code> is a component container, which shows the
 * subcomponents in the order of their addition (vertically). A vertical layout
 * is by default 100% wide.
 *
 * @author Vaadin Ltd.
 * @since 5.3
 */
@SuppressWarnings("serial")
@Deprecated
public class LegacyVerticalLayout extends VerticalLayout {

    /**
     * Constructs an empty LegacyVerticalLayout.
     */
    public LegacyVerticalLayout() {
        super(false, false);
    }

    /**
     * Constructs a LegacyVerticalLayout with the given components. The components are
     * added in the given order.
     *
     * @see com.vaadin.ui.AbstractOrderedLayout#addComponents(Component...)
     *
     * @param children
     *            The components to add.
     */
    public LegacyVerticalLayout(Component... children) {
        this();
        addComponents(children);
    }

}
