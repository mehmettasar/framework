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
package com.vaadin.tests.contextclick;

import java.util.Collections;

import com.vaadin.shared.ui.grid.GridConstants.Section;
import com.vaadin.tests.util.Person;
import com.vaadin.tests.util.PersonContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.GridContextClickEvent;
import com.vaadin.v7.ui.LegacyHorizontalLayout;

public class GridContextClick extends
        AbstractContextClickUI<Grid<Person>, GridContextClickEvent<Person>> {

    @Override
    protected Grid<Person> createTestComponent() {
        Grid<Person> grid = new Grid<>();
        grid.setItems(PersonContainer.createTestData());
        grid.addColumn("Address",
                person -> String.valueOf(person.getAddress()));
        grid.addColumn("Email", person -> String.valueOf(person.getEmail()));
        grid.addColumn("First Name",
                person -> String.valueOf(person.getFirstName()));
        grid.addColumn("Last Name",
                person -> String.valueOf(person.getLastName()));
        grid.addColumn("Phone Number",
                person -> String.valueOf(person.getPhoneNumber()));
        grid.addColumn("Street Address", person -> String
                .valueOf(person.getAddress().getStreetAddress()));
        grid.addColumn("City",
                person -> String.valueOf(person.getAddress().getCity()));

        // grid.setFooterVisible(true);
        // grid.appendFooterRow();

        // grid.setColumnOrder("address", "email", "firstName", "lastName",
        // "phoneNumber", "address.streetAddress", "address.postalCode",
        // "address.city");

        grid.setWidth("100%");
        grid.setHeight("400px");

        return grid;
    }

    @Override
    protected void handleContextClickEvent(
            GridContextClickEvent<Person> event) {
        String value = "";
        Person person = event.getItem();
        if (event.getItem() != null) {
            value = person.getFirstName() + " " + person.getLastName();
        } else if (event.getSection() == Section.HEADER) {
            value = event.getColumn().getCaption();
        } else if (event.getSection() == Section.FOOTER) {
            value = event.getColumn().getCaption();
        }

        if (event.getColumn() != null) {
            log("ContextClickEvent value: " + value + ", column: "
                    + event.getColumn().getCaption() + ", section: "
                    + event.getSection());
        } else {
            log("ContextClickEvent value: " + value + ", section: "
                    + event.getSection());

        }
    }

    @Override
    protected LegacyHorizontalLayout createContextClickControls() {
        LegacyHorizontalLayout controls = super.createContextClickControls();
        controls.addComponent(
                new Button("Remove all content", new Button.ClickListener() {

                    @Override
                    public void buttonClick(ClickEvent event) {
                        testComponent.setItems(Collections.emptyList());
                    }
                }));
        return controls;
    }
}
