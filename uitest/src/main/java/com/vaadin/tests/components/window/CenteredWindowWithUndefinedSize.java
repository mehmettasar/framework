package com.vaadin.tests.components.window;

import com.vaadin.tests.components.TestBase;
import com.vaadin.ui.Label;
import com.vaadin.v7.ui.LegacyVerticalLayout;
import com.vaadin.ui.Window;

public class CenteredWindowWithUndefinedSize extends TestBase {

    @Override
    protected String getDescription() {
        return "The centered sub-window with undefined height and a undefined high layout should be rendered in the center of the screen and not in the top-left corner.";
    }

    @Override
    protected Integer getTicketNumber() {
        return 2702;
    }

    @Override
    protected void setup() {
        LegacyVerticalLayout layout = new LegacyVerticalLayout();
        layout.setMargin(true);
        Window centered = new Window("A window", layout);
        centered.setSizeUndefined();
        layout.setSizeUndefined();
        centered.center();

        Label l = new Label("This window should be centered");
        l.setSizeUndefined();
        layout.addComponent(l);

        getMainWindow().addWindow(centered);

    }
}
