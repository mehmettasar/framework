package com.vaadin.tests.components.orderedlayout;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.LegacyHorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.v7.ui.LegacyVerticalLayout;

/**
 * LegacyHorizontalLayout and LegacyVerticalLayout should not leak .v-spacing elements via
 * listeners when removing components from a layout.
 *
 * @since 7.1.12
 * @author Vaadin Ltd
 */
public class SpacingLeak extends UI {

    private LegacyHorizontalLayout spacingLayout;

    @Override
    public void init(VaadinRequest req) {
        final LegacyVerticalLayout root = new LegacyVerticalLayout();
        setContent(root);
        root.setSizeUndefined();

        final Button spacingButton = new Button("Add layout with spacing");
        spacingButton.setId("addbutton");
        root.addComponent(spacingButton);
        spacingButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                spacingLayout = new LegacyHorizontalLayout();
                spacingLayout.setSpacing(true);
                spacingLayout.setWidth("100%");

                for (int i = 0; i < 100; ++i) {
                    spacingLayout.addComponent(new Button("" + i));
                }

                root.addComponent(spacingLayout);
            }
        });

        final Button removeButton = new Button("Remove layouts");
        removeButton.setId("removebutton");
        root.addComponent(removeButton);
        removeButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                root.removeComponent(spacingLayout);
            }
        });
    }
}
