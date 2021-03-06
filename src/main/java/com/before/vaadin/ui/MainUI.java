/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.before.vaadin.ui;

import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import javax.inject.Inject;

/**
 *
 * @author Matti Tahvonen <matti@vaadin.com>
 */
@CDIUI
public class MainUI extends UI {
    
    @Inject
    private CDIViewProvider viewProvider;
    
    @Inject
    ViewMenu menu;

    @Override
    public void init(VaadinRequest request) {
        
        VerticalLayout mainarea = new VerticalLayout();
        
        Navigator navigator = new Navigator(this, mainarea);
        navigator.addProvider(viewProvider);
        navigator.setErrorView(CustomerView.class);
        
        setContent(new VerticalLayout(menu.getBasicMenu(), mainarea));
    }    
}
