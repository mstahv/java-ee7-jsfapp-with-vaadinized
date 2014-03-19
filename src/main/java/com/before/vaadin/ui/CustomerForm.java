/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.before.vaadin.ui;

import com.before.vaadin.Customer;
import com.before.vaadin.ejb.MicroMarketFacade;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.cdi.UIScoped;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import org.vaadin.maddon.BeanBinder;
import org.vaadin.maddon.fields.MTextField;
import org.vaadin.maddon.fields.TypedSelect;
import org.vaadin.maddon.layouts.MVerticalLayout;

/**
 *
 * @author Matti Tahvonen <matti@vaadin.com>
 */
@Dependent
public class CustomerForm extends MVerticalLayout {
    
    @Inject
    MicroMarketFacade microMarketFacade;

    private MTextField name = new MTextField();
    private MTextField email = new MTextField();
    private MTextField fax = new MTextField();
    private MTextField phone = new MTextField();
    private TypedSelect zip = new TypedSelect(MicroMarket.class);

    @PostConstruct
    void init() {
        zip.setOptions(microMarketFacade.findAll());
        addComponents(name, email, phone, fax, zip);
    }

    public void setCustomer(Customer customer) {
        BeanBinder.bind(customer, this);
    }

}
