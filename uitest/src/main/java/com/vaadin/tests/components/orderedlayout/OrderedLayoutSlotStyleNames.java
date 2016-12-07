package com.vaadin.tests.components.orderedlayout;

import com.vaadin.tests.components.TestBase;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.LegacyHorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.v7.ui.LegacyVerticalLayout;

public class OrderedLayoutSlotStyleNames extends TestBase {

    @Override
    protected void setup() {
        LegacyVerticalLayout vl = new LegacyVerticalLayout();

        Label lbl = new Label("A label");
        lbl.setStyleName("my-label");
        lbl.addStyleName("my-second-label");
        vl.addComponent(lbl);

        Button btn = new Button("A Button");
        btn.setStyleName("my-button");
        btn.addStyleName("my-second-button");
        vl.addComponent(btn);

        addComponent(vl);

        LegacyHorizontalLayout hl = new LegacyHorizontalLayout();

        lbl = new Label("A label");
        lbl.setStyleName("my-label");
        lbl.addStyleName("my-second-label");
        hl.addComponent(lbl);

        btn = new Button("A Button");
        btn.setStyleName("my-button");
        btn.addStyleName("my-second-button");
        hl.addComponent(btn);

        addComponent(hl);
    }

    @Override
    protected String getDescription() {
        return "Vertical/LegacyHorizontalLayout slots should get child dependant name";
    }

    @Override
    protected Integer getTicketNumber() {
        return 9051;
    }

}
