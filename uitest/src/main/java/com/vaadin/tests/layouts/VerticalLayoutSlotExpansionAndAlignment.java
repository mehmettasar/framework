package com.vaadin.tests.layouts;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Alignment;
import com.vaadin.v7.ui.LegacyHorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.v7.ui.LegacyVerticalLayout;
import com.vaadin.v7.ui.themes.Reindeer;

@SuppressWarnings("serial")
public class VerticalLayoutSlotExpansionAndAlignment extends UI {

    @Override
    protected void init(VaadinRequest request) {

        LegacyVerticalLayout layout = new LegacyVerticalLayout();
        layout.setSizeFull();
        setContent(layout);

        LegacyHorizontalLayout header = new LegacyHorizontalLayout(new Label("HEADER"));
        header.setHeight("100px");
        header.setWidth("100%");
        header.setStyleName(Reindeer.LAYOUT_WHITE);
        layout.addComponent(header);

        LegacyHorizontalLayout content = new LegacyHorizontalLayout(new Label("CONTENT"));
        content.setSizeFull();
        content.setStyleName(Reindeer.LAYOUT_BLUE);
        layout.addComponent(content);

        LegacyHorizontalLayout footer = new LegacyHorizontalLayout(new Label("FOOTER"));
        footer.setHeight("150px");
        footer.setWidth("100%");
        footer.setStyleName(Reindeer.LAYOUT_BLACK);
        layout.addComponent(footer);

        // This break things
        layout.setComponentAlignment(footer, Alignment.BOTTOM_LEFT);
        layout.setExpandRatio(content, 1);

    }

}
