package com.vaadin.tests.components.table;

import com.vaadin.server.VaadinRequest;
import com.vaadin.tests.components.AbstractReindeerTestUI;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.LegacyHorizontalLayout;
import com.vaadin.v7.ui.Table;

public class TableSetUndefinedSize extends AbstractReindeerTestUI {
    @Override
    protected void setup(VaadinRequest request) {
        getLayout().setSizeFull();

        Table table = createTable();
        table.setSizeFull();
        addComponent(table);

        LegacyHorizontalLayout widthButtons = createWidthButtons(table);
        addComponent(widthButtons);

        LegacyHorizontalLayout heightButtons = createHeigthButtons(table);
        addComponent(heightButtons);

        addComponent(createSizeUndefinedButton(table));
    }

    private Button createSizeUndefinedButton(final Table table) {
        return new Button("size undefined", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                table.setSizeUndefined();
            }
        });
    }

    private LegacyHorizontalLayout createWidthButtons(final Table table) {
        LegacyHorizontalLayout layout = new LegacyHorizontalLayout();

        layout.addComponent(
                new Button("width 500px", new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        table.setWidth("500px");
                    }
                }));
        layout.addComponent(
                new Button("width 100%", new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        table.setWidth("100%");
                    }
                }));
        layout.addComponent(
                new Button("undefined width", new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        table.setWidthUndefined();
                    }
                }));

        return layout;
    }

    private LegacyHorizontalLayout createHeigthButtons(final Table table) {
        LegacyHorizontalLayout layout = new LegacyHorizontalLayout();

        layout.addComponent(
                new Button("height 200px", new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        table.setHeight("200px");
                    }
                }));
        layout.addComponent(
                new Button("height 300px", new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        table.setHeight("300px");
                    }
                }));
        layout.addComponent(
                new Button("height 100%", new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        table.setHeight("100%");
                    }
                }));
        layout.addComponent(
                new Button("undefined height", new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        table.setHeightUndefined();
                    }
                }));

        return layout;
    }

    private Table createTable() {
        Table table = new Table("");

        table.addContainerProperty("column 1", String.class, "column 1 value");
        table.addContainerProperty("column 2", String.class, "column 2 value");
        table.addContainerProperty("column 3", String.class, "column 3 value");

        for (int i = 0; i < 5; i++) {
            table.addItem();
        }

        return table;
    }

    @Override
    protected String getTestDescription() {
        return "Table width and height changing to undefined doesn't update table size";
    }

    @Override
    protected Integer getTicketNumber() {
        return 15384;
    }
}
