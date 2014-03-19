/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.before.vaadin.ui;

import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import org.vaadin.maddon.BeanBinder;
import org.vaadin.maddon.button.MButton;
import org.vaadin.maddon.button.PrimaryButton;
import org.vaadin.maddon.layouts.MHorizontalLayout;

/**
 *
 * @param <T>
 */
public abstract class AbstractForm<T> extends CustomComponent {

    public interface SavedHandler<T> {

        void onSave(T entity);
    }

    public interface ResetHandler<T> {

        void onReset(T entity);
    }

    private T entity;
    private SavedHandler<T> savedHandler;
    private ResetHandler<T> resetHandler;

    public void setEntity(T entity) {
        this.entity = entity;
        if (entity != null) {
            BeanBinder.bind(entity, this);
            setVisible(true);
        } else {
            setVisible(false);
        }
    }

    public void setSavedHandler(SavedHandler<T> savedHandler) {
        this.savedHandler = savedHandler;
    }

    public void setResetHandler(ResetHandler<T> resetHandler) {
        this.resetHandler = resetHandler;
    }

    public HorizontalLayout getToolbar() {
        return new MHorizontalLayout(
                new PrimaryButton("Save", this::save),
                new MButton("Cancel", this::reset)
        );
    }

    protected void save(Button.ClickEvent e) {
        savedHandler.onSave(entity);
    }

    protected void reset(Button.ClickEvent e) {
        resetHandler.onReset(entity);
    }

    @Override
    public void attach() {
        setCompositionRoot(createContent());
        super.attach();
    }
    
    protected abstract  Component createContent();

}
