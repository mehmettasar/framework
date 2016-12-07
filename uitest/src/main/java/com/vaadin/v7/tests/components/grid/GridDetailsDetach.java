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
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.v7.ui.LegacyVerticalLayout;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.event.ItemClickEvent;
import com.vaadin.v7.event.ItemClickEvent.ItemClickListener;
import com.vaadin.v7.ui.Grid;
import com.vaadin.v7.ui.Grid.DetailsGenerator;
import com.vaadin.v7.ui.Grid.RowReference;
import com.vaadin.v7.ui.Grid.SelectionMode;

public class GridDetailsDetach extends AbstractReindeerTestUI {

    private Grid currentGrid;

    @Override
    protected void setup(VaadinRequest request) {
        LegacyVerticalLayout layout = new LegacyVerticalLayout();
        layout.setSizeFull();

        Button button = new Button("Test");
        layout.addComponent(button);
        layout.setExpandRatio(button, 0f);

        currentGrid = generateGrid();
        final LegacyVerticalLayout gridContainer = new LegacyVerticalLayout();
        gridContainer.addComponent(currentGrid);

        button.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                gridContainer.replaceComponent(currentGrid, new Label("Foo"));
            }
        });

        layout.addComponent(
                new Button("Reattach Grid", new Button.ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                        gridContainer.removeAllComponents();
                        gridContainer.addComponent(currentGrid);
                    }
                }));

        layout.addComponent(gridContainer);
        layout.setExpandRatio(gridContainer, 1f);

        addComponent(layout);
    }

    private Grid generateGrid() {
        BeanItemContainer<GridExampleBean> container = new BeanItemContainer<>(
                GridExampleBean.class);
        for (int i = 0; i < 1000; i++) {
            container.addItem(new GridExampleBean("Bean " + i, i * i, i / 10d));
        }

        final Grid grid = new Grid(container);
        grid.setColumnOrder("name", "amount", "count");
        grid.setSizeFull();
        grid.setSelectionMode(SelectionMode.NONE);

        grid.setDetailsGenerator(new DetailsGenerator() {
            @Override
            public Component getDetails(RowReference rowReference) {
                final GridExampleBean bean = (GridExampleBean) rowReference
                        .getItemId();
                LegacyVerticalLayout layout = new LegacyVerticalLayout(
                        new Label("Extra data for " + bean.getName()));
                layout.setMargin(true);
                return layout;
            }
        });

        grid.addItemClickListener(new ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                Object itemId = event.getItemId();
                grid.setDetailsVisible(itemId, !grid.isDetailsVisible(itemId));
            }
        });
        return grid;
    }

    public class GridExampleBean {

        private String name;

        private int count;

        private double amount;

        public GridExampleBean() {
        }

        public GridExampleBean(String name, int count, double amount) {
            this.name = name;
            this.count = count;
            this.amount = amount;
        }

        public String getName() {
            return name;
        }

        public int getCount() {
            return count;
        }

        public double getAmount() {
            return amount;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

    }

}
