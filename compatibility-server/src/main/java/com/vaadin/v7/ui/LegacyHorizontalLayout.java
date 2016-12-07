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

import com.vaadin.shared.ui.orderedlayout.HorizontalLayoutState;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;

/**
 * Horizontal layout
 *
 * <code>LegacyHorizontalLayout</code> is a component container, which shows the
 * subcomponents in the order of their addition (horizontally).
 *
 * @author Vaadin Ltd.
 * @since 5.3
 */
@SuppressWarnings("serial")
@Deprecated
public class LegacyHorizontalLayout extends HorizontalLayout {

    /**
     * Constructs an empty LegacyHorizontalLayout.
     */
    public LegacyHorizontalLayout() {
        super(false,false);
    }

    /**
     * Constructs a LegacyHorizontalLayout with the given components. The components
     * are added in the given order.
     *
     * @see AbstractOrderedLayout#addComponents(Component...)
     *
     * @param children
     *            The components to add.
     */
    public LegacyHorizontalLayout(Component... children) {
        this();
        addComponents(children);
    }
}
