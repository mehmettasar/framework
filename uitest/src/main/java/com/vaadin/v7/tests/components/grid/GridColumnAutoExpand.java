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
package com.vaadin.v7.tests.components.grid;

import com.vaadin.server.VaadinRequest;
import com.vaadin.tests.components.AbstractReindeerTestUI;
import com.vaadin.v7.ui.LegacyVerticalLayout;
import com.vaadin.v7.ui.Grid;
import com.vaadin.v7.ui.Grid.Column;

public class GridColumnAutoExpand extends AbstractReindeerTestUI {

    @Override
    protected void setup(VaadinRequest request) {
        final LegacyVerticalLayout layout = new LegacyVerticalLayout();
        layout.setSizeFull();
        layout.setMargin(true);
        addComponent(layout);

        Grid grid = new Grid("Broken Grid with Caption");
        grid.setWidth("100%");
        grid.setHeight("100px");

        Column col1 = grid.addColumn("Col1");
        col1.setWidth(100);

        Column col2 = grid.addColumn("Col2");
        col2.setMinimumWidth(100);
        col2.setExpandRatio(1);

        layout.addComponent(grid);
    }
}
