/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.before.vaadin.ui;

import com.before.vaadin.Customer;
import com.before.vaadin.ejb.CustomerFacade;
import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Notification;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.vaadin.maddon.fields.MTable;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.layouts.MVerticalLayout;

/**
 *
 * @author Matti Tahvonen <matti@vaadin.com>
 */
@CDIView
public class CustomerView extends MVerticalLayout implements View {

    @Inject
    CustomerFacade cf;
    @Inject
    CustomerForm form;

    MTable<Customer> table = new MTable<>(Customer.class).withProperties(
            "customerId", "name", "city");

    @PostConstruct
    public void initComponent() {
        form.setResetHandler(this::reset);
        form.setSavedHandler(this::save);

        table.addMValueChangeListener(e -> {
            form.setEntity(e.getValue());
        });
        listEntities();
        addComponents(
                new Header("Customer listing"),
                table, 
                form
        );
    }

    private void listEntities() {
        table.setBeans(cf.findAll());
    }

    public void save(Customer customer) {
        if (customer.getCustomerId() == null) {
            cf.create(customer);
        } else {
            cf.edit(customer);
        }
        listEntities();
        Notification.show("Saved!");
    }

    public void reset(Customer customer) {
        // just hide the form
        form.setEntity(null);
        listEntities();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

}
