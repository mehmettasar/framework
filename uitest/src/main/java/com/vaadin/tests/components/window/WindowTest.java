package com.vaadin.tests.components.window;

import com.vaadin.tests.components.panel.PanelTest;
import com.vaadin.v7.ui.LegacyVerticalLayout;
import com.vaadin.ui.Window;

public class WindowTest extends PanelTest<Window> {

    @Override
    protected Class<Window> getTestClass() {
        return Window.class;
    }

    @Override
    protected void addTestComponent(Window w) {
        LegacyVerticalLayout layout = new LegacyVerticalLayout();
        layout.setMargin(true);
        w.setContent(layout);
        addWindow(w);
        getTestComponents().add(w);
    }

}
