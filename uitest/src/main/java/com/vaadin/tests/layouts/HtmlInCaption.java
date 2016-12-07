/*
 * Copyright 2000-2016 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.tests.layouts;

import com.vaadin.server.VaadinRequest;
import com.vaadin.tests.components.AbstractReindeerTestUI;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ColorPicker;
import com.vaadin.ui.ColorPickerArea;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.v7.ui.LegacyHorizontalLayout;
import com.vaadin.ui.Link;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Panel;
import com.vaadin.v7.ui.LegacyVerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.v7.ui.TextField;

/**
 *
 * @since
 * @author Vaadin Ltd
 */
public class HtmlInCaption extends AbstractReindeerTestUI {

    @Override
    protected void setup(VaadinRequest request) {
        LegacyHorizontalLayout main = new LegacyHorizontalLayout();
        addComponent(main);
        LegacyVerticalLayout components = new LegacyVerticalLayout();
        components.setId("components");
        LegacyVerticalLayout layouts = new LegacyVerticalLayout();
        layouts.setId("layouts");
        main.addComponent(layouts);
        main.addComponent(components);

        createComponents(components);
        createLayouts(layouts);

        Window w = new Window();
        w.setCaption(getTextCaption("Window"));
        w.setPositionX(600);
        addWindow(w);

        w = new Window();
        w.setCaptionAsHtml(true);
        w.setCaption(getHtmlCaption("Window"));
        w.setPositionX(600);
        w.setPositionY(100);
        addWindow(w);
    }

    private void createLayouts(LegacyVerticalLayout layouts) {
        LegacyVerticalLayout vl = new LegacyVerticalLayout(tf(false), tf(true));
        vl.setCaption("LegacyVerticalLayout");
        layouts.addComponent(vl);

        LegacyHorizontalLayout hl = new LegacyHorizontalLayout(tf(false), tf(true));
        hl.setCaption("LegacyHorizontalLayout");
        layouts.addComponent(hl);

        GridLayout gl = new GridLayout(2, 1);
        gl.setCaption("GridLayout");
        gl.addComponents(tf(false), tf(true));
        layouts.addComponent(gl);

        CssLayout cl = new CssLayout();
        cl.setCaption("CssLayout");
        cl.addComponents(tf(false), tf(true));
        layouts.addComponent(cl);

        AbsoluteLayout al = new AbsoluteLayout();
        al.setCaption("AbsoluteLayout");
        al.setWidth("300px");
        al.setHeight("200px");
        al.addComponent(tf(false), "top:30px");
        al.addComponent(tf(true), "top: 100px");
        layouts.addComponent(al);
    }

    private void createComponents(LegacyVerticalLayout components) {
        createComponent(components, Button.class);
        createComponent(components, NativeButton.class);
        createComponent(components, CheckBox.class);
        createComponent(components, Link.class);

        createComponent(components, Panel.class);
        createComponent(components, ColorPicker.class);
        createComponent(components, ColorPickerArea.class);

    }

    private void createComponent(LegacyVerticalLayout components,
            Class<? extends AbstractComponent> class1) {
        AbstractComponent ac;
        try {
            ac = class1.newInstance();
            ac.setCaption(getTextCaption(class1.getSimpleName()));
            components.addComponent(ac);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            ac = class1.newInstance();
            ac.setCaption(getHtmlCaption(class1.getSimpleName()));
            ac.setCaptionAsHtml(true);
            components.addComponent(ac);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Component tf(boolean htmlCaption) {
        TextField tf = new TextField();
        if (htmlCaption) {
            tf.setCaptionAsHtml(htmlCaption);
            tf.setCaption(getHtmlCaption(""));
        } else {
            tf.setCaption(getTextCaption(""));
        }
        return tf;
    }

    private String getTextCaption(String string) {
        return "<b>Plain text " + string + "</b>";
    }

    private String getHtmlCaption(String string) {
        return "<b><font color='red'>HTML " + string + "</font></b>";
    }

    @Override
    protected Integer getTicketNumber() {
        return 9426;
    }

}
