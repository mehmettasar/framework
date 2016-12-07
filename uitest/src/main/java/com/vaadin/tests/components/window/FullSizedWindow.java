package com.vaadin.tests.components.window;

import com.vaadin.tests.components.TestBase;
import com.vaadin.ui.NativeButton;
import com.vaadin.v7.ui.LegacyVerticalLayout;
import com.vaadin.ui.Window;

public class FullSizedWindow extends TestBase {

    @Override
    protected void setup() {
        LegacyVerticalLayout layout = new LegacyVerticalLayout();
        layout.setMargin(true);
        Window w = new Window("full sized window", layout);
        w.setSizeFull();
        layout.setSizeFull();
        NativeButton b = new NativeButton("A large button");
        b.setSizeFull();
        layout.addComponent(b);
        getMainWindow().addWindow(w);
        setTheme("runo");
    }

    @Override
    protected String getDescription() {
        return "A 100%x100% sub window should not produce scrollbars in the main view or in the sub window. The button inside the sub window is 100%x100%, as is the layout";
    }

    @Override
    protected Integer getTicketNumber() {
        return 3407;
    }

}
