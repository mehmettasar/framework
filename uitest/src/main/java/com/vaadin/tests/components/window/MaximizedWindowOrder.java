package com.vaadin.tests.components.window;

import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.window.WindowMode;
import com.vaadin.tests.components.AbstractReindeerTestUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.v7.ui.LegacyVerticalLayout;
import com.vaadin.ui.Window;

public class MaximizedWindowOrder extends AbstractReindeerTestUI {

    @Override
    protected void setup(VaadinRequest request) {
        addButton("Open Maximized Window", new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                openWindow(true);
            }
        });
    }

    private void openWindow(boolean maximized) {
        Window window = new Window();
        LegacyVerticalLayout layout = new LegacyVerticalLayout();

        Label label = new Label(maximized ? "Maximized" : "Normal");

        layout.addComponent(label);
        Button button = new Button("Open Normal Window");
        button.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                openWindow(false);
            }

        });

        layout.addComponent(button);

        window.setContent(layout);
        window.setWindowMode(
                maximized ? WindowMode.MAXIMIZED : WindowMode.NORMAL);

        addWindow(window);
    }
}