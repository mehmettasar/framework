package com.vaadin.tests.components.tabsheet;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.v7.ui.LegacyVerticalLayout;
import com.vaadin.v7.ui.themes.Runo;

@Theme("runo")
public class ExtraScrollbarsInTabSheet extends UI {

    @Override
    public void init(VaadinRequest request) {

        LegacyVerticalLayout vl = new LegacyVerticalLayout();
        vl.setSizeFull();

        HorizontalSplitPanel horizontalSplit = new HorizontalSplitPanel();

        TabSheet ts = new TabSheet();

        LegacyVerticalLayout tabContent = new LegacyVerticalLayout();
        tabContent.setSizeFull();

        Panel p = new Panel();
        p.addStyleName(Runo.PANEL_LIGHT);
        p.setHeight("400px");
        tabContent.addComponent(p);

        ts.addTab(tabContent);
        horizontalSplit.setSecondComponent(ts);

        vl.addComponent(horizontalSplit);

        setContent(vl);

    }

}
