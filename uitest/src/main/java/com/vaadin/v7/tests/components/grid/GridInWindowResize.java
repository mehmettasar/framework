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
import com.vaadin.tests.components.AbstractTestUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.v7.ui.LegacyVerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.v7.data.Item;
import com.vaadin.v7.data.util.IndexedContainer;
import com.vaadin.v7.ui.Grid;

public class GridInWindowResize extends AbstractTestUI {

    @Override
    protected void setup(VaadinRequest request) {
        Grid g = new Grid();
        IndexedContainer cont = new IndexedContainer();
        for (int j = 0; j < 3; j++) {
            cont.addContainerProperty("" + j, String.class, "");
        }

        for (int k = 0; k < 100; k++) {
            Item addItem = cont.addItem(k);
            for (int j = 0; j < 3; j++) {
                addItem.getItemProperty("" + j).setValue(1 + "");
            }
        }
        g.setContainerDataSource(cont);
        g.setSizeFull();

        LegacyVerticalLayout vl = new LegacyVerticalLayout(g);
        vl.setSizeFull();
        Button resize = new Button("resize");
        LegacyVerticalLayout vl2 = new LegacyVerticalLayout(vl, resize);
        vl2.setSizeFull();

        final Window w = new Window(null, vl2);
        addWindow(w);

        w.center();
        w.setModal(true);
        w.setWidth("600px");
        w.setHeight("400px");

        resize.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                w.setWidth("400px");
            }
        });

    }
}
