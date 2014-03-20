/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.before.vaadin.ui;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import javax.annotation.PostConstruct;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MVerticalLayout;

/**
 *
 * @author Matti Tahvonen <matti@vaadin.com>
 */
@CDIView("anotherexample")
public class AnotherView extends MVerticalLayout implements View {

    @PostConstruct
    public void initComponent() {
        addComponents(
                new Header("Another view")
        );
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

}
