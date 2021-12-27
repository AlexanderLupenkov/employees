package com.altech.employees.views;

import com.altech.employees.domain.dto.EmployeeDto;
import com.altech.employees.domain.dto.OrganizationDto;
import com.altech.employees.domain.dto.StatusDto;
import com.altech.employees.domain.entity.Organization;
import com.altech.employees.domain.entity.Status;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class EmployeeForm extends FormLayout {
    private final IntegerField id = new IntegerField("Id");
    private final TextField firstName = new TextField("First name");
    private final TextField lastName = new TextField("Last name");
    private final TextField email = new TextField("Email");

    private final ComboBox<StatusDto> status = new ComboBox<>("Status");
    private final ComboBox<OrganizationDto> organization = new ComboBox<>("Organization");

    private final Button save = new Button("Save");
    private final Button delete = new Button("Delete");
    private final Button close = new Button("Close");

    private final Binder<EmployeeDto> binder = new BeanValidationBinder<>(EmployeeDto.class);

    private EmployeeDto employee;

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, employee)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(employee);
            fireEvent(new SaveEvent(this, employee));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    public EmployeeForm(List<OrganizationDto> organizations, List<StatusDto> statuses) {
        addClassName("contact-form");
        binder.bindInstanceFields(this);
        organization.setItems(organizations);
        organization.setItemLabelGenerator(OrganizationDto::getName);
        status.setItems(statuses);
        status.setItemLabelGenerator(StatusDto::getName);

        id.setReadOnly(true);

        add(id, firstName, lastName, email, organization, status, createButtonsLayout());
    }

    public void setEmployee(EmployeeDto employee) {
        this.employee = employee;
        binder.readBean(employee);
    }

    public static abstract class ContactFormEvent extends ComponentEvent<EmployeeForm> {
        private EmployeeDto employeeDto;

        protected ContactFormEvent(EmployeeForm form, EmployeeDto employee) {
            super(form, false);
            this.employeeDto = employee;
        }

        public EmployeeDto getEmployeeDto() {
            return this.employeeDto;
        }
    }

    public static class SaveEvent extends ContactFormEvent {
        SaveEvent(EmployeeForm source, EmployeeDto contact) {
            super(source, contact);
        }
    }

    public static class DeleteEvent extends ContactFormEvent {
        DeleteEvent(EmployeeForm source, EmployeeDto contact) {
            super(source, contact);
        }
    }

    public static class CloseEvent extends ContactFormEvent {
        CloseEvent(EmployeeForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
