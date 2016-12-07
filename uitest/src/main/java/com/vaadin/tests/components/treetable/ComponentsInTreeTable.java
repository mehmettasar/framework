package com.vaadin.tests.components.treetable;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.tests.components.TestBase;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.v7.ui.LegacyHorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.v7.ui.LegacyVerticalLayout;
import com.vaadin.v7.ui.TreeTable;

public class ComponentsInTreeTable extends TestBase {

    @Override
    protected void setup() {
        TreeTable tt = new TreeTable();
        tt.setWidth("300px");
        tt.setHeight("300px");
        addComponent(tt);

        tt.addContainerProperty("component", Component.class, "");
        tt.addContainerProperty("type", String.class, "bar");

        Layout l = new LegacyHorizontalLayout();
        l.addComponent(new Label("bar"));
        l.addComponent(new Label("bar"));
        tt.addItem(new Object[] { l, "LegacyHorizontalLayout" }, 1);

        l = new LegacyVerticalLayout();
        l.addComponent(new Label("baz"));
        l.addComponent(new Label("baz"));
        tt.addItem(new Object[] { l, "LegacyVerticalLayout" }, 2);

        Label lbl = new Label("<b>foo</b><br/><i>bar</i>");
        lbl.setContentMode(ContentMode.HTML);
        tt.addItem(new Object[] { lbl, "Label" }, 3);

        tt.addItem(new Object[] { new Button("Test"), "Button" }, 4);
        tt.setParent(4, 3);
    }

    @Override
    protected String getDescription() {
        return "Components in TreeTable cells should be rendered inline with the expand/collapse arrow";
    }

    @Override
    protected Integer getTicketNumber() {
        return 7387;
    }
}
