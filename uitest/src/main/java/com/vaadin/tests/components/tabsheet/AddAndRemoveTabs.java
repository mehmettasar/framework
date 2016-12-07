package com.vaadin.tests.components.tabsheet;

import com.vaadin.tests.components.TestBase;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.v7.ui.LegacyHorizontalLayout;
import com.vaadin.ui.TabSheet;

public class AddAndRemoveTabs extends TestBase {
    private TabSheet tabSheet;

    private int counter = 0;

    @Override
    public void setup() {
        tabSheet = new TabSheet();
        addTab();
        addComponent(tabSheet);

        Button addTabBtn = new Button("Add new tab",
                new Button.ClickListener() {

                    @Override
                    public void buttonClick(ClickEvent event) {
                        addTab();
                    }

                });
        addComponent(addTabBtn);
    }

    private void addTab() {
        final LegacyHorizontalLayout layout = new LegacyHorizontalLayout();
        layout.setCaption("Test " + counter);

        Button closeTab = new Button("Close tab", new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                tabSheet.removeComponent(layout);

            }

        });

        layout.addComponent(closeTab);

        tabSheet.addComponent(layout);
        counter++;
    }

    @Override
    protected String getDescription() {
        return "Removing all tabs and then adding new tabs should work properly and without javascript errors. All new tabs should be displayed and not only the first one";
    }

    @Override
    protected Integer getTicketNumber() {
        return 2861;
    }

}
