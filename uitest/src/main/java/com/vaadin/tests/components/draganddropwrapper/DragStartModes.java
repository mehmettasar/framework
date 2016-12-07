package com.vaadin.tests.components.draganddropwrapper;

import com.vaadin.tests.components.TestBase;
import com.vaadin.tests.util.TestUtils;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DragAndDropWrapper;
import com.vaadin.ui.DragAndDropWrapper.DragStartMode;
import com.vaadin.ui.Label;
import com.vaadin.v7.ui.LegacyVerticalLayout;

public class DragStartModes extends TestBase {

    @Override
    protected void setup() {

        TestUtils.injectCSS(getMainWindow(),
                ".v-ddwrapper { background: #ACF; } .extra{ background: #FFA500; }");

        addComponent(makeWrapper(DragStartMode.NONE));
        addComponent(makeWrapper(DragStartMode.COMPONENT));
        addComponent(makeWrapper(DragStartMode.WRAPPER));
        addComponent(makeWrapper(DragStartMode.HTML5));
        addComponent(makeOtherComponentWrapper(DragStartMode.COMPONENT_OTHER));

        addComponent(new Label("Drop here"));
    }

    private Component makeOtherComponentWrapper(DragStartMode componentOther) {
        LegacyVerticalLayout parent = new LegacyVerticalLayout();
        parent.setWidth("200px");
        parent.setSpacing(true);

        CssLayout header = new CssLayout();
        Label dragStartModeLabel = new Label(
                "Drag start mode : COMPONENT_OTHER");
        dragStartModeLabel.setWidth("100%");
        header.addComponent(dragStartModeLabel);
        header.setSizeUndefined();

        DragAndDropWrapper wrapper = new DragAndDropWrapper(header);
        wrapper.setDragStartMode(DragStartMode.COMPONENT_OTHER);
        wrapper.setDragImageComponent(parent);
        wrapper.setId("label" + "COMPONENT_OTHER");
        parent.addComponent(wrapper);

        Label extra = new Label(
                "Extra label that is not part of the wrapper. This should be dragged along with COMPONENT_OTHER.");
        extra.setWidth("100%");
        extra.addStyleName("extra");
        parent.addComponent(extra);

        return parent;
    }

    private Component makeWrapper(DragStartMode mode) {
        Label label = new Label("Drag start mode: " + mode);
        label.setId("label" + mode);
        DragAndDropWrapper wrapper = new DragAndDropWrapper(label);
        wrapper.setHTML5DataFlavor("Text", "HTML5!");
        wrapper.setDragStartMode(mode);
        wrapper.setWidth("200px");
        return wrapper;
    }

    @Override
    protected String getDescription() {
        return "Different drag start modes should show correct drag images";
    }

    @Override
    protected Integer getTicketNumber() {
        return 8949;
    }

}
