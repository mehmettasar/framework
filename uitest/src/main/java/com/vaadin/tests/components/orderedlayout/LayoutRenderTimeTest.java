package com.vaadin.tests.components.orderedlayout;

import com.vaadin.server.LegacyApplication;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Embedded;
import com.vaadin.v7.ui.LegacyHorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.LegacyWindow;
import com.vaadin.v7.ui.LegacyVerticalLayout;
import com.vaadin.v7.ui.themes.Reindeer;

public class LayoutRenderTimeTest extends LegacyApplication {

    @Override
    public void init() {
        LegacyWindow main = new LegacyWindow();
        setMainWindow(main);

        LegacyVerticalLayout root = new LegacyVerticalLayout();
        root.setWidth("100%");
        main.setContent(root);

        for (int i = 1; i <= 100; i++) {
            root.addComponent(getRow(i));
        }
    }

    private LegacyHorizontalLayout getRow(int i) {
        LegacyHorizontalLayout row = new LegacyHorizontalLayout();
        // row.setWidth("100%");
        // row.setSpacing(true);

        Embedded icon = new Embedded(null,
                new ThemeResource("../runo/icons/32/document.png"));
        // row.addComponent(icon);
        // row.setComponentAlignment(icon, Alignment.MIDDLE_LEFT);

        Label text = new Label("Row content #" + i
                + ". In pellentesque faucibus vestibulum. Nulla at nulla justo, eget luctus tortor. Nulla facilisi. Duis aliquet.");
        // row.addComponent(text);
        // row.setExpandRatio(text, 1);

        Button button = new Button("Edit");
        button.addStyleName(Reindeer.BUTTON_SMALL);
        row.addComponent(button);
        // row.setComponentAlignment(button, Alignment.MIDDLE_LEFT);

        button = new Button("Delete");
        button.addStyleName(Reindeer.BUTTON_SMALL);
        row.addComponent(button);
        // row.setComponentAlignment(button, Alignment.MIDDLE_LEFT);

        return row;
    }
}
