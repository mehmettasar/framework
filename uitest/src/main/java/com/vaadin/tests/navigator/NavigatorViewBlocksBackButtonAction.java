package com.vaadin.tests.navigator;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.tests.components.AbstractReindeerTestUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.v7.ui.LegacyVerticalLayout;
import com.vaadin.ui.Window;

public class NavigatorViewBlocksBackButtonAction extends AbstractReindeerTestUI {

    private Navigator navigator;

    protected static final String LABEL_MAINVIEW_ID = "LABEL_MAINVIEW_ID";
    protected static final String LABEL_PROMPTEDVIEW_ID = "LABEL_PROMPTEDVIEW_ID";

    @Override
    protected void setup(VaadinRequest request) {
        navigator = new Navigator(this, this);
        navigator.addView(MainView.NAME, new MainView());
        navigator.addView(ViewWithPromptedLeave.NAME,
                new ViewWithPromptedLeave());
        navigator.navigateTo(MainView.NAME);
    }

    class MainView extends LegacyVerticalLayout implements View {

        public static final String NAME = "mainview";

        public MainView() {
            Label label = new Label("MainView content");
            label.setId(LABEL_MAINVIEW_ID);
            addComponent(label);

            Button buttonNavToAnotherView = new Button(
                    "Navigate to another view", new ClickListener() {

                        @Override
                        public void buttonClick(ClickEvent event) {
                            navigator.navigateTo(ViewWithPromptedLeave.NAME);
                        }
                    });
            addComponent(buttonNavToAnotherView);
        }

        @Override
        public void enter(ViewChangeEvent event) {
        }

    }

    class ViewWithPromptedLeave extends LegacyVerticalLayout
            implements View, ViewChangeListener {

        public static final String NAME = "prompted";

        protected boolean okToLeave;

        public ViewWithPromptedLeave() {
            Label label = new Label("ViewWithPromptedLeave content");
            label.setId(LABEL_PROMPTEDVIEW_ID);
            addComponent(label);
            addComponent(new Label(
                    "Try to navigate back to first view with browser back button."));
        }

        @Override
        public void enter(ViewChangeEvent event) {
            event.getNavigator().addViewChangeListener(this);
        }

        @Override
        public boolean beforeViewChange(final ViewChangeEvent event) {
            if (okToLeave) {
                okToLeave = false;
                return true;
            } else {
                final Window confirmationWindow = new Window("Confirm");
                confirmationWindow.setModal(true);
                confirmationWindow.setClosable(true);

                LegacyVerticalLayout confirmationWindowLayout = new LegacyVerticalLayout();
                confirmationWindow.setContent(confirmationWindowLayout);
                confirmationWindowLayout.setMargin(true);
                confirmationWindowLayout.setSpacing(true);
                confirmationWindowLayout
                        .addComponent(new Label("Really exit this view?"));
                confirmationWindowLayout.addComponent(
                        new Button("Yeah, sure!", new Button.ClickListener() {

                            @Override
                            public void buttonClick(ClickEvent buttonEvent) {
                                okToLeave = true;
                                getUI().removeWindow(confirmationWindow);
                                event.getNavigator()
                                        .navigateTo(event.getViewName() + "/"
                                                + event.getParameters());
                            }
                        }));
                getUI().addWindow(confirmationWindow);
                return false;
            }
        }

        @Override
        public void afterViewChange(ViewChangeEvent event) {
            if (event.getNewView() != this) {
                event.getNavigator().removeViewChangeListener(this);
            }
        }
    }

    @Override
    protected String getTestDescription() {
        return "URL should not be changed when view blocks navigating away from view using the browser's Back-button";
    }

    @Override
    protected Integer getTicketNumber() {
        return 10901;
    }
}