package com.vaadin.tests.minitutorials.v7b9;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.v7.ui.LegacyVerticalLayout;

public class MainView__LastNavigatorExample extends Panel implements View {

    public static final String NAME = "";

    public MainView__LastNavigatorExample(final Navigator navigator) {

        Link lnk = new Link("Settings",
                new ExternalResource("#!" + SettingsView.NAME));
        LegacyVerticalLayout vl = new LegacyVerticalLayout();
        vl.addComponent(lnk);
        setContent(vl);

    }

    @Override
    public void enter(ViewChangeEvent event) {

    }
}
