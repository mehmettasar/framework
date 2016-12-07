package com.vaadin.tests.tooltip;

import com.vaadin.server.VaadinRequest;
import com.vaadin.tests.components.AbstractReindeerTestUI;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.LegacyVerticalLayout;

public class StationaryTooltip extends AbstractReindeerTestUI {

    @Override
    protected void setup(VaadinRequest request) {
        LegacyVerticalLayout vl = new LegacyVerticalLayout();
        Button button = new Button("Button");
        button.setDescription("description");

        button.setWidth("200px");
        vl.addComponent(button);

        addComponent(vl);
    }

    @Override
    protected String getTestDescription() {
        return null;
    }

    @Override
    protected Integer getTicketNumber() {
        return null;
    }
}
