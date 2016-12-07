package com.vaadin.tests.performance;

import java.util.Iterator;

import com.vaadin.server.VaadinRequest;
import com.vaadin.tests.util.TestUtils;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.v7.ui.LegacyHorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.v7.ui.LegacyVerticalLayout;
import com.vaadin.v7.ui.TextArea;
import com.vaadin.v7.ui.themes.Reindeer;

public class BasicPerformanceTest extends UI {

    private int updateOneCount = 0;

    private final LegacyVerticalLayout contentLayout = new LegacyVerticalLayout();

    private int clientLimit;
    private int serverLimit;
    private boolean reportBootstap = true;
    private String performanceTopic;
    private final Button reportPerformanceButton = new Button(
            "Report some performance", new Button.ClickListener() {
                @Override
                public void buttonClick(ClickEvent event) {
                    TestUtils.reportPerformance(performanceTopic, serverLimit,
                            clientLimit, reportBootstap);
                    event.getButton().setEnabled(false);
                }
            });

    @Override
    public void init(VaadinRequest request) {
        setContent(buildMainLayout());
        updatePerformanceReporting("first load", 100, 100);
        reportBootstap = true;
    }

    private void updatePerformanceReporting(String performanceTopic,
            int serverLimit, int clientLimit) {
        this.performanceTopic = performanceTopic;
        this.serverLimit = serverLimit;
        this.clientLimit = clientLimit;
        reportPerformanceButton
                .setCaption("Report performance for " + performanceTopic);
        reportPerformanceButton.setEnabled(true);
        reportBootstap = false;
    }

    private ComponentContainer buildMainLayout() {
        contentLayout.addComponent(new Label("Content lives here"));

        Panel contentScroller = new Panel(contentLayout);
        contentScroller.setStyleName(Reindeer.PANEL_LIGHT);
        contentScroller.setSizeFull();

        TextArea performanceReportArea = new TextArea();
        performanceReportArea.setWidth("200px");
        TestUtils.installPerformanceReporting(performanceReportArea);

        LegacyVerticalLayout leftBar = new LegacyVerticalLayout();
        leftBar.setWidth("250px");
        leftBar.addComponent(new Label("This is the left bar"));
        leftBar.addComponent(performanceReportArea);
        leftBar.addComponent(reportPerformanceButton);

        leftBar.addComponent(new Button("Set 20 panels as content",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                        popupateContent(contentLayout, 20, true);
                        updatePerformanceReporting("20 panels", 100, 100);
                    }
                }));
        leftBar.addComponent(new Button("Set 40 panels as content",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                        popupateContent(contentLayout, 40, true);
                        updatePerformanceReporting("40 panels", 100, 100);
                    }
                }));
        leftBar.addComponent(new Button("Set 40 layouts as content",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                        popupateContent(contentLayout, 40, false);
                        updatePerformanceReporting("40 layouts", 100, 100);
                    }
                }));

        leftBar.addComponent(
                new Button("Update all labels", new Button.ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                        Iterator<Component> componentIterator = contentLayout
                                .getComponentIterator();
                        while (componentIterator.hasNext()) {

                            Iterator<Component> columHolderIterator;
                            Component child = componentIterator.next();
                            if (child instanceof Panel) {
                                columHolderIterator = ((ComponentContainer) ((Panel) child)
                                        .getContent()).getComponentIterator();
                            } else {
                                columHolderIterator = ((ComponentContainer) child)
                                        .getComponentIterator();
                            }
                            while (columHolderIterator.hasNext()) {
                                LegacyVerticalLayout column = (LegacyVerticalLayout) columHolderIterator
                                        .next();
                                Iterator<Component> columnIterator = column
                                        .getComponentIterator();
                                while (columnIterator.hasNext()) {
                                    Label label = (Label) columnIterator.next();
                                    label.setValue("New value");
                                }
                            }
                        }
                        updatePerformanceReporting("Update labels", 100, 100);
                    }
                }));

        leftBar.addComponent(
                new Button("Update one label", new Button.ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                        Component child = contentLayout.getComponent(0);
                        if (child instanceof Panel) {
                            Panel panel = (Panel) child;
                            child = panel.getContent();
                        }

                        AbstractOrderedLayout layout = (AbstractOrderedLayout) ((AbstractOrderedLayout) child)
                                .getComponent(0);
                        Label label = (Label) layout.getComponent(0);

                        label.setValue("New value " + updateOneCount++);

                        updatePerformanceReporting("Update one", 10, 10);
                    }
                }));

        leftBar.addComponent(
                new Button("Clear content", new Button.ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                        contentLayout.removeAllComponents();
                        contentLayout.addComponent(new Label("No content"));
                        updatePerformanceReporting("No content", 100, 100);
                    }
                }));

        LegacyHorizontalLayout intermediateLayout = new LegacyHorizontalLayout();
        intermediateLayout.setSizeFull();
        intermediateLayout.addComponent(leftBar);
        intermediateLayout.addComponent(contentScroller);
        intermediateLayout.setExpandRatio(contentScroller, 1);

        LegacyVerticalLayout mainLayout = new LegacyVerticalLayout();
        mainLayout.setSizeFull();
        mainLayout.addComponent(new Label("This is a header"));
        mainLayout.addComponent(intermediateLayout);
        mainLayout.setExpandRatio(intermediateLayout, 1);

        return mainLayout;
    }

    private void popupateContent(LegacyVerticalLayout contentLayout, int childCount,
                                 boolean wrapInPanel) {
        contentLayout.removeAllComponents();
        for (int i = 0; i < childCount; i++) {
            LegacyVerticalLayout left = new LegacyVerticalLayout();
            left.addComponent(new Label("Label 1"));
            left.addComponent(new Label("Label 2"));
            left.addComponent(new Label("Label 3"));

            LegacyVerticalLayout right = new LegacyVerticalLayout();
            right.addComponent(new Label("Label 4"));
            right.addComponent(new Label("Label 5"));
            right.addComponent(new Label("Label 6"));

            LegacyHorizontalLayout columns = new LegacyHorizontalLayout();
            columns.addComponent(left);
            columns.addComponent(right);
            columns.setHeight(null);
            columns.setWidth("100%");

            if (wrapInPanel) {
                Panel panel = new Panel("Data " + i, columns);
                panel.setWidth("100%");
                panel.setHeight(null);

                contentLayout.addComponent(panel);
            } else {
                contentLayout.addComponent(columns);
            }
        }
    }
}
