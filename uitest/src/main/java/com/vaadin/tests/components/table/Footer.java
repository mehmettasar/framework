package com.vaadin.tests.components.table;

import com.vaadin.server.VaadinRequest;
import com.vaadin.tests.components.AbstractReindeerTestUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.v7.ui.LegacyHorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.v7.ui.LegacyVerticalLayout;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Item;
import com.vaadin.v7.data.util.IndexedContainer;
import com.vaadin.v7.ui.Table;

public class Footer extends AbstractReindeerTestUI {

    @Override
    protected void setup(VaadinRequest request) {
        LegacyHorizontalLayout layout = new LegacyHorizontalLayout();
        layout.setSpacing(true);

        final Table table = new Table();
        table.setWidth("400px");
        table.setHeight("400px");

        table.setContainerDataSource(createContainer());
        table.setImmediate(true);

        table.setColumnCollapsingAllowed(true);
        table.setColumnReorderingAllowed(true);

        table.setFooterVisible(true);

        table.setColumnFooter("col1", "Footer1");
        table.setColumnFooter("col2", "Footer2");
        table.setColumnFooter("col3", "Footer3");

        table.setColumnAlignment("col2", Table.ALIGN_CENTER);
        table.setColumnAlignment("col3", Table.ALIGN_RIGHT);

        layout.addComponent(table);

        // Add some options to play with
        LegacyVerticalLayout options = new LegacyVerticalLayout();
        options.setSpacing(true);

        final CheckBox visible = new CheckBox("Footers Visible", true);
        visible.addValueChangeListener(
                event -> table.setFooterVisible(visible.getValue()));

        options.addComponent(visible);

        final TextField footer1Value = new TextField(null, "Footer1");
        Button footer1Btn = new Button("Change", new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                table.setColumnFooter("col1", footer1Value.getValue() == null
                        ? "" : footer1Value.getValue());
            }
        });
        LegacyHorizontalLayout footer1 = new LegacyHorizontalLayout();
        footer1.addComponent(footer1Value);
        footer1.addComponent(footer1Btn);
        options.addComponent(footer1);

        final TextField footer2Value = new TextField(null, "Footer2");
        Button footer2Btn = new Button("Change", new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                table.setColumnFooter("col2", footer2Value.getValue() == null
                        ? "" : footer2Value.getValue());
            }
        });
        LegacyHorizontalLayout footer2 = new LegacyHorizontalLayout();
        footer2.addComponent(footer2Value);
        footer2.addComponent(footer2Btn);
        options.addComponent(footer2);

        final TextField footer3Value = new TextField(null, "Footer3");
        Button footer3Btn = new Button("Change", new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                table.setColumnFooter("col3", footer3Value.getValue() == null
                        ? "" : footer3Value.getValue());
            }
        });
        LegacyHorizontalLayout footer3 = new LegacyHorizontalLayout();
        footer3.addComponent(footer3Value);
        footer3.addComponent(footer3Btn);
        options.addComponent(footer3);

        layout.addComponent(options);

        addComponent(layout);
    }

    @Override
    protected String getTestDescription() {
        return "Table with footer";
    }

    @Override
    protected Integer getTicketNumber() {
        return 1553;
    }

    private Container createContainer() {
        IndexedContainer container = new IndexedContainer();
        container.addContainerProperty("col1", String.class, "");
        container.addContainerProperty("col2", String.class, "");
        container.addContainerProperty("col3", String.class, "");

        for (int i = 0; i < 100; i++) {
            Item item = container.addItem("item " + i);
            item.getItemProperty("col1").setValue("first" + i);
            item.getItemProperty("col2").setValue("middle" + i);
            item.getItemProperty("col3").setValue("last" + i);
        }

        return container;
    }

}
