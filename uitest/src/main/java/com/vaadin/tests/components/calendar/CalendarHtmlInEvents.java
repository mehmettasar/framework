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
package com.vaadin.tests.components.calendar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vaadin.server.VaadinRequest;
import com.vaadin.tests.components.AbstractReindeerTestUIWithLog;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CheckBox;
import com.vaadin.v7.ui.LegacyHorizontalLayout;
import com.vaadin.v7.data.Property.ValueChangeEvent;
import com.vaadin.v7.data.Property.ValueChangeListener;
import com.vaadin.v7.ui.Calendar;
import com.vaadin.v7.ui.NativeSelect;
import com.vaadin.v7.ui.components.calendar.event.BasicEvent;
import com.vaadin.v7.ui.components.calendar.event.CalendarEvent;
import com.vaadin.v7.ui.components.calendar.event.CalendarEventProvider;

public class CalendarHtmlInEvents extends AbstractReindeerTestUIWithLog {

    private Calendar calendar = new Calendar();

    @Override
    protected void setup(VaadinRequest request) {
        final NativeSelect ns = new NativeSelect("Period");
        ns.addItems("Day", "Week", "Month");
        ns.addValueChangeListener(new ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                if ("Day".equals(ns.getValue())) {
                    calendar.setStartDate(new Date(2014 - 1900, 1 - 1, 1));
                    calendar.setEndDate(new Date(2014 - 1900, 1 - 1, 1));
                } else if ("Week".equals(ns.getValue())) {
                    calendar.setStartDate(new Date(2014 - 1900, 1 - 1, 1));
                    calendar.setEndDate(new Date(2014 - 1900, 1 - 1, 7));
                } else if ("Month".equals(ns.getValue())) {
                    calendar.setStartDate(new Date(2014 - 1900, 1 - 1, 1));
                    calendar.setEndDate(new Date(2014 - 1900, 2 - 1, 1));
                }
            }
        });
        ns.setValue("Month");
        final CheckBox allowHtml = new CheckBox("Allow HTML in event caption");
        allowHtml.setValue(calendar.isEventCaptionAsHtml());
        allowHtml.addValueChangeListener(event -> {
            calendar.setEventCaptionAsHtml(event.getValue());
            log("HTML in event caption: " + event.getValue());
        });
        LegacyHorizontalLayout hl = new LegacyHorizontalLayout();
        hl.setDefaultComponentAlignment(Alignment.BOTTOM_LEFT);
        hl.addComponents(ns, allowHtml);
        hl.setSpacing(true);
        hl.setMargin(true);
        calendar.setEventProvider(new CalendarEventProvider() {

            @Override
            public List<CalendarEvent> getEvents(Date startDate, Date endDate) {
                Date d = startDate;
                ArrayList<CalendarEvent> events = new ArrayList<>();
                while (d.before(endDate)) {
                    BasicEvent ce = new BasicEvent();
                    ce.setAllDay(false);
                    ce.setCaption("<b>Hello</b> <u>world</u>!");
                    ce.setDescription("Nothing really important");
                    Date start = new Date(d.getTime());
                    start.setHours(d.getDay());
                    Date end = new Date(d.getTime());
                    end.setHours(d.getDay() + 3);
                    ce.setStart(start);
                    ce.setEnd(end);
                    events.add(ce);
                    d.setTime(d.getTime() + 1000 * 60 * 60 * 24);
                }

                return events;
            }

        });
        addComponent(hl);
        addComponent(calendar);
    }
}
