package com.vaadin.tests.components.uitest.components;

import com.vaadin.tests.components.uitest.TestSampler;
import com.vaadin.tests.util.Person;
import com.vaadin.v7.ui.LegacyHorizontalLayout;
import com.vaadin.ui.LoginForm;
import com.vaadin.v7.ui.LegacyVerticalLayout;
import com.vaadin.v7.data.fieldgroup.BeanFieldGroup;
import com.vaadin.v7.data.fieldgroup.FieldGroup;
import com.vaadin.v7.data.util.BeanItem;

public class FormsCssTest extends LegacyHorizontalLayout {

    private TestSampler parent;
    private int debugIdCounter = 0;

    public FormsCssTest(TestSampler parent) {
        this.parent = parent;
        setSpacing(true);
        setWidth("100%");

        LegacyVerticalLayout vl = new LegacyVerticalLayout();
        FieldGroup fg = new BeanFieldGroup<>(Person.class);
        fg.setItemDataSource(new BeanItem<>(new Person()));
        for (Object propId : fg.getUnboundPropertyIds()) {
            if (!"address".equals(propId)) {
                vl.addComponent(fg.buildAndBind(propId));
            }
        }

        addComponent(vl);

        LoginForm login = new LoginForm();
        login.setId("form" + debugIdCounter++);
        login.setHeight("150px");
        addComponent(login);

    }

    @Override
    public void addComponent(com.vaadin.ui.Component c) {
        parent.registerComponent(c);
        super.addComponent(c);
    }

}
