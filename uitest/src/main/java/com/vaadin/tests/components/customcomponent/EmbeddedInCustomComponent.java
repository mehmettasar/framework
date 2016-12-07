package com.vaadin.tests.components.customcomponent;

import java.util.Date;

import com.vaadin.server.ThemeResource;
import com.vaadin.tests.components.TestBase;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Embedded;
import com.vaadin.v7.ui.LegacyHorizontalLayout;

public class EmbeddedInCustomComponent extends TestBase {

    @Override
    protected void setup() {
        LegacyHorizontalLayout hl = new LegacyHorizontalLayout();

        hl.addComponent(wrap("An uncached image",
                "ok.png?random=" + new Date().getTime()));
        hl.addComponent(wrap("A cached image", "cancel.png"));
        addComponent(hl);
    }

    private CustomComponent wrap(String caption, String themeImage) {
        Embedded image = new Embedded(caption,
                new ThemeResource("../runo/icons/64/" + themeImage));
        CustomComponent cc = new CustomComponent(image);
        return cc;
    }

    @Override
    protected String getDescription() {
        return "Two embedded images are shown below, side-by-side. The first one has a random url so it is not cached, the second one is cached.";
    }

    @Override
    protected Integer getTicketNumber() {
        return 6304;
    }

}
