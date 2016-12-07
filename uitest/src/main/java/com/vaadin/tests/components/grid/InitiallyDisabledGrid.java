package com.vaadin.tests.components.grid;

import java.util.ArrayList;
import java.util.Collection;

import com.vaadin.server.VaadinRequest;
import com.vaadin.tests.data.bean.Person;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.v7.ui.LegacyVerticalLayout;

public class InitiallyDisabledGrid extends UI {

    @Override
    protected void init(VaadinRequest request) {
        LegacyVerticalLayout layout = new LegacyVerticalLayout();
        setContent(layout);
        layout.setSizeFull();
        layout.setWidth("600px");
        layout.setHeight("600px");
        final Grid<Person> grid = createGrid();
        Button button = new Button("Enable/Disable",
                event -> grid.setEnabled(!grid.isEnabled()));

        layout.addComponent(button);
        LegacyVerticalLayout l = new LegacyVerticalLayout();
        l.setSizeFull();
        l.addComponent(grid);

        layout.addComponent(l);
        layout.setExpandRatio(l, 1.0f);
    }

    private Grid<Person> createGrid() {
        // Have some data
        Collection<Person> people = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Person person = new Person();
            person.setFirstName("First " + i);
            person.setLastName("Last " + i);
            people.add(person);
        }
        // Have a container of some type to contain the data

        // Create a grid bound to the container
        Grid<Person> grid = new Grid<>();
        grid.setSizeFull();
        grid.setItems(people);
        grid.addColumn("firstName", Person::getFirstName);
        grid.addColumn("lastNAme", Person::getLastName);

        grid.setEnabled(false);

        return grid;
    }

}