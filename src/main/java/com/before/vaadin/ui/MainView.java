/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.before.vaadin.ui;

import com.before.vaadin.Customer;
import com.before.vaadin.ejb.CustomerFacade;
import com.vaadin.cdi.CDIView;
import com.vaadin.cdi.UIScoped;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.vaadin.maddon.fields.MTable;
import org.vaadin.maddon.fields.MValueChangeEvent;
import org.vaadin.maddon.fields.MValueChangeListener;
import org.vaadin.maddon.layouts.MVerticalLayout;

/**
 *
 * @author Matti Tahvonen <matti@vaadin.com>
 */
@CDIView
public class MainView extends MVerticalLayout implements View {

    @Inject
    CustomerFacade cf;
    @Inject
    CustomerForm form;

    MTable<Customer> table = new MTable<>(Customer.class).withProperties("customerId", "name", "city");

    @PostConstruct
    public void initComponent() {
        final List<Customer> findAll = cf.findAll();
        table.setBeans(findAll);
        table.addMValueChangeListener(new MValueChangeListener<Customer>() {

            public void valueChange(MValueChangeEvent<Customer> event) {
                form.setCustomer(event.getValue());
            }
        });
        // When changed to this, the @PostConstruct
//        table.addMValueChangeListener((MValueChangeEvent<Customer> event) -> {
//        });

        addComponents(table,form);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

}
