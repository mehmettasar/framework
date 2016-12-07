/**
 *
 */
package com.vaadin.tests;

import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.v7.ui.LegacyVerticalLayout;

/**
 * @author marc
 *
 */
public class TestComponentAddAndRecursion extends CustomComponent {
    Panel p;
    LegacyVerticalLayout pl;
    Panel p2;
    LegacyVerticalLayout p2l;
    Label l;
    Label l2;
    Panel p3;
    LegacyVerticalLayout p3l;

    public TestComponentAddAndRecursion() {

        LegacyVerticalLayout main = new LegacyVerticalLayout();
        setCompositionRoot(main);

        l = new Label("A");
        l2 = new Label("B");
        pl = new LegacyVerticalLayout();
        pl.setMargin(true);
        p = new Panel("p", pl);
        pl.addComponent(l);
        pl.addComponent(l2);
        main.addComponent(p);
        p2l = new LegacyVerticalLayout();
        p2l.setMargin(true);
        p2 = new Panel("p2", p2l);
        p2l.addComponent(l);
        main.addComponent(p2);
        p3l = new LegacyVerticalLayout();
        p3l.setMargin(true);
        p3 = new Panel("p3", p3l);
        p2l.addComponent(p3);

        Button b = new Button("use gridlayout", new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                p.setContent(new GridLayout());
                p2.setContent(new GridLayout());
                p3.setContent(new GridLayout());
            }

        });
        main.addComponent(b);
        b = new Button("use orderedlayout", new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                p.setContent(new LegacyVerticalLayout());
                p2.setContent(new LegacyVerticalLayout());
                p3.setContent(new LegacyVerticalLayout());
            }

        });
        main.addComponent(b);
        b = new Button("move B", new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                p2l.addComponent(l2);
            }

        });
        main.addComponent(b);
        b = new Button("move p", new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                p3l.addComponent(p);
            }

        });
        main.addComponent(b);
        b = new Button("add to both", new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                Label l = new Label("both");
                pl.addComponent(l);
                p2l.addComponent(l);
            }

        });
        main.addComponent(b);
        b = new Button("recurse", new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                try {
                    p3l.addComponent(p2);
                    new Notification("ERROR", "This should have failed",
                            Notification.TYPE_ERROR_MESSAGE)
                                    .show(Page.getCurrent());
                } catch (Exception e) {
                    new Notification("OK", "threw, as expected",
                            Notification.TYPE_ERROR_MESSAGE)
                                    .show(Page.getCurrent());
                }
            }

        });
        main.addComponent(b);
        b = new Button("recurse2", new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                LegacyVerticalLayout layout = new LegacyVerticalLayout();
                Panel p = new Panel("dynamic", layout);
                layout.addComponent(p2);
                try {
                    p3l.addComponent(p);
                    new Notification("ERROR", "This should have failed",
                            Notification.TYPE_ERROR_MESSAGE)
                                    .show(Page.getCurrent());
                } catch (Exception e) {
                    new Notification("OK", "threw, as expected",
                            Notification.TYPE_ERROR_MESSAGE)
                                    .show(Page.getCurrent());
                }
            }

        });
        main.addComponent(b);
        /*
         * And that's it! The framework will display the main window and its
         * contents when the application is accessed with the terminal.
         */
    }
}
