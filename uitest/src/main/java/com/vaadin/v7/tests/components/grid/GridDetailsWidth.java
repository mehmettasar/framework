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
import com.vaadin.ui.Component;
import com.vaadin.v7.ui.LegacyHorizontalLayout;
import com.vaadin.v7.ui.LegacyVerticalLayout;
import com.vaadin.v7.event.ItemClickEvent;
import com.vaadin.v7.event.ItemClickEvent.ItemClickListener;
import com.vaadin.v7.ui.Grid;
import com.vaadin.v7.ui.Grid.Column;
import com.vaadin.v7.ui.Grid.DetailsGenerator;
import com.vaadin.v7.ui.Grid.RowReference;
import com.vaadin.v7.ui.TextArea;

public class GridDetailsWidth extends AbstractReindeerTestUI {

    @Override
    protected void setup(VaadinRequest request) {
        final LegacyVerticalLayout layout = new LegacyVerticalLayout();
        layout.setMargin(true);

        final Grid grid = new Grid();

        Column column = grid.addColumn("Hello", String.class);
        for (int i = 0; i < 3; i++) {
            grid.addRow("Hello " + i);
        }

        column.setWidth(600);
        grid.setWidth(400, Unit.PIXELS);

        grid.setDetailsGenerator(new DetailsGenerator() {

            @Override
            public Component getDetails(RowReference rowReference) {
                LegacyHorizontalLayout myLayout = new LegacyHorizontalLayout();
                TextArea textArea1 = new TextArea();
                TextArea textArea2 = new TextArea();
                textArea1.setSizeFull();
                textArea2.setSizeFull();
                myLayout.addComponent(textArea1);
                myLayout.addComponent(textArea2);
                myLayout.setWidth(100, Unit.PERCENTAGE);
                myLayout.setHeight(null);
                myLayout.setMargin(true);
                return myLayout;
            }
        });

        grid.addItemClickListener(new ItemClickListener() {

            @Override
            public void itemClick(ItemClickEvent event) {
                grid.setDetailsVisible(event.getItemId(),
                        !grid.isDetailsVisible(event.getItemId()));

            }
        });

        layout.addComponent(grid);

        addComponent(layout);
    }

    @Override
    protected Integer getTicketNumber() {
        return 18223;
    }

    @Override
    protected String getTestDescription() {
        return "Tests that Escalator will not set explicit widths to the TD element in a details row.";
    }

}
