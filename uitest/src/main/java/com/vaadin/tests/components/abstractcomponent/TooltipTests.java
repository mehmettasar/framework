package com.vaadin.tests.components.abstractcomponent;

import com.vaadin.data.HasValue;
import com.vaadin.tests.components.TestBase;
import com.vaadin.ui.CheckBox;
import com.vaadin.v7.ui.LegacyHorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.v7.ui.LegacyVerticalLayout;

public class TooltipTests extends TestBase {

    private Panel panel;
    private LegacyVerticalLayout layout;
    private Label label;

    @Override
    protected String getDescription() {
        return "Generic tooltip handling tests";
    }

    @Override
    protected Integer getTicketNumber() {
        return 8425;
    }

    @Override
    protected void setup() {
        LegacyHorizontalLayout topLayout = new LegacyHorizontalLayout();
        addComponent(topLayout);
        CheckBox panelCbox = new CheckBox("Panel");
        panelCbox.addValueChangeListener(panelListener);
        topLayout.addComponent(panelCbox);
        CheckBox layoutCbox = new CheckBox("Layout");
        layoutCbox.addValueChangeListener(layoutListener);
        topLayout.addComponent(layoutCbox);
        CheckBox labelCbox = new CheckBox("Label");
        topLayout.addComponent(labelCbox);
        labelCbox.addValueChangeListener(labelListener);

        panel = new Panel();
        panel.setCaption("Panel caption");
        panel.setId("panel");
        addComponent(panel);

        layout = new LegacyVerticalLayout();
        layout.setId("layout");
        layout.setMargin(true);
        layout.setSpacing(true);
        panel.setContent(layout);

        label = new Label("Hover me!");
        label.setId("label");
        layout.addComponent(label);
    }

    private final HasValue.ValueChangeListener<Boolean> panelListener = event -> {
        if (event.getValue()) {
            panel.setDescription("I'm panel!");
        } else {
            panel.setDescription("");
        }
    };

    private final HasValue.ValueChangeListener<Boolean> layoutListener = event -> {
        if (event.getValue()) {
            layout.setDescription("I'm layout!");
        } else {
            layout.setDescription("");
        }
    };

    private final HasValue.ValueChangeListener<Boolean> labelListener = event -> {
        if (event.getValue()) {
            label.setDescription("I'm label!");
        } else {
            label.setDescription("");
        }
    };

}
