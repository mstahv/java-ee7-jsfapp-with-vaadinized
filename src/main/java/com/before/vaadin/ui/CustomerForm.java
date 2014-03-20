/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.before.vaadin.ui;

import org.vaadin.maddon.form.AbstractForm;
import com.before.vaadin.Customer;
import com.before.vaadin.MicroMarket;
import com.before.vaadin.ejb.MicroMarketFacade;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import org.vaadin.maddon.fields.MTextField;
import org.vaadin.maddon.fields.TypedSelect;
import org.vaadin.maddon.layouts.MVerticalLayout;

/**
 *
 * @author Matti Tahvonen <matti@vaadin.com>
 */
@Dependent
public class CustomerForm extends AbstractForm<Customer> {

    @Inject
    MicroMarketFacade microMarketfacade;
    
    MTextField name = new MTextField("Name");
    MTextField email = new MTextField("Email");
    MTextField fax = new MTextField("Fax");
    MTextField phone = new MTextField("Phone");
    TypedSelect<MicroMarket> zip = new TypedSelect<>(MicroMarket.class).
            withSelectType(ComboBox.class).withCaption("Zip").
            setCaptionGenerator(MicroMarket::getZipCode);

    @Override
    protected Component createContent() {
        setVisible(false);
        zip.setOptions(microMarketfacade.findAll());
        return new MVerticalLayout(
                new FormLayout(name, email, phone, fax, zip),
                getToolbar()
        );
    }

}
