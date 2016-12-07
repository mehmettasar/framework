package com.vaadin.tests.components.richtextarea;

import com.vaadin.tests.components.TestBase;
import com.vaadin.v7.ui.LegacyHorizontalLayout;
import com.vaadin.v7.ui.LegacyVerticalLayout;
import com.vaadin.ui.RichTextArea;

public class RichTextAreaSize extends TestBase {

    @Override
    protected String getDescription() {
        return "Test the size of a rich text area. The first area is 100px*100px wide, the second 100%*100% (of 200x200px), the third one has undefined width and height.";
    }

    @Override
    protected Integer getTicketNumber() {
        return 2573;
    }

    @Override
    protected void setup() {
        LegacyHorizontalLayout main = new LegacyHorizontalLayout();
        getMainWindow().setContent(main);

        RichTextArea first = new RichTextArea();
        RichTextArea second = new RichTextArea();
        RichTextArea third = new RichTextArea();

        first.setWidth("150px");
        first.setHeight("400px");
        second.setSizeFull();
        third.setSizeUndefined();

        LegacyVerticalLayout secondLayout = new LegacyVerticalLayout();
        secondLayout.setWidth("200px");
        secondLayout.setHeight("200px");
        secondLayout.addComponent(second);

        main.addComponent(first);
        main.addComponent(secondLayout);
        main.addComponent(third);
    }

}
