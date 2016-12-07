package com.vaadin.tests.components.textarea;

import com.vaadin.tests.components.TestBase;
import com.vaadin.tests.util.LoremIpsum;
import com.vaadin.ui.CheckBox;
import com.vaadin.v7.ui.LegacyHorizontalLayout;
import com.vaadin.ui.TextArea;

public class Wordwrap extends TestBase {

    @Override
    public void setup() {
        LegacyHorizontalLayout layout = new LegacyHorizontalLayout();

        TextArea area1 = new TextArea("Wrapping");
        area1.setWordWrap(true); // The default
        area1.setValue(LoremIpsum.get(50) + "\n" + "Another row");

        final TextArea area2 = new TextArea("Nonwrapping");
        area2.setWordWrap(false);
        area2.setValue(LoremIpsum.get(50) + "\n" + "Another row");

        layout.addComponent(area1);
        layout.addComponent(area2);
        layout.setSpacing(true);

        addComponent(layout);

        CheckBox onoff = new CheckBox("Wrap state for the right field");
        onoff.setValue(false);
        onoff.addValueChangeListener(event -> {
            boolean wrap = event.getValue();
            area2.setWordWrap(wrap);
            if (wrap) {
                area2.setCaption("Wrapping");
            } else {
                area2.setCaption("Nonwrapping");
            }
        });

        addComponent(onoff);
    }

    @Override
    protected String getDescription() {
        return "";
    }

    @Override
    protected Integer getTicketNumber() {
        return 6003;
    }
}
