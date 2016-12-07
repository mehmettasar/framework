package com.vaadin.tests.components.orderedlayout;

import com.vaadin.server.Page;
import com.vaadin.server.Page.Styles;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.v7.ui.LegacyHorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.v7.ui.LegacyVerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class OrderedLayoutInfiniteLayoutPasses extends UI {

    @Override
    protected void init(VaadinRequest request) {
        LegacyVerticalLayout layout = new LegacyVerticalLayout();
        layout.addComponent(createOpenWindowButton());
        setContent(layout);

        Styles styles = Page.getCurrent().getStyles();
        styles.add(
                ".my-separator {background-color: lightgray; min-height:2px;max-height:2px} ");
    }

    private Button createOpenWindowButton() {
        Button button = new Button("Open modal window");
        button.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                UI.getCurrent().addWindow(createWindow());
            }
        });
        return button;
    }

    private Window createWindow() {
        LegacyVerticalLayout contentHolder = new LegacyVerticalLayout();
        contentHolder.addComponent(new Label("window content"));

        Label separator = new Label();
        separator.setWidth(100, Unit.PERCENTAGE);
        separator.addStyleName("my-separator");

        LegacyHorizontalLayout buttons = new LegacyHorizontalLayout();
        buttons.addComponent(new Button("button 1"));
        buttons.addComponent(new Button("button 2"));

        LegacyVerticalLayout windowContent = new LegacyVerticalLayout();
        windowContent.setSizeFull();
        windowContent.addComponent(contentHolder);
        windowContent.addComponent(separator);
        windowContent.addComponent(buttons);
        windowContent.setExpandRatio(contentHolder, 1.0f);

        Window window = new Window();
        window.setModal(true);
        window.setWidth(680, Unit.PIXELS);
        window.setHeight(700, Unit.PIXELS);
        window.setContent(windowContent);

        return window;
    }

}