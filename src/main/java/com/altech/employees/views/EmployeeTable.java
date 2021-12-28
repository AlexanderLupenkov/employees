package com.altech.employees.views;

import com.altech.employees.domain.dto.EmployeeDto;
import com.altech.employees.service.CRMService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Employees")
@AllArgsConstructor
public class EmployeeTable extends VerticalLayout {
    private final Grid<EmployeeDto> grid;
    private final TextField filterText;
    private EmployeeForm form;
    private final CRMService crmService;

    @Autowired
    public EmployeeTable(CRMService crmService) {
        this.crmService = crmService;

        this.grid = new Grid<>(EmployeeDto.class);
        this.filterText = new TextField();

        addClassName("list-view");

        setSizeFull();
        configureGrid();
        configureForm();

        add(getToolbar(), getContent());
        updateList();
        closeEditor();
    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.setColumns("id", "firstName", "lastName", "email");
        grid.addColumn(employee -> employee.getStatus().getName()).setHeader("Status");
        grid.addColumn(employee -> employee.getOrganization().getName()).setHeader("Organization");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                editContact(event.getValue()));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e ->
                grid.setItems(crmService.findEmployees(filterText.getValue()))
        );

        com.vaadin.flow.component.button.Button addContactButton = new Button("Add contact");
        addContactButton.addClickListener(click -> addContact());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addContactButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void editContact(EmployeeDto employee) {
        if (employee == null) {
            closeEditor();
        } else {
            form.setEmployee(employee);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setEmployee(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void addContact() {
        grid.asSingleSelect().clear();
        editContact(new EmployeeDto());
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void updateList() {
        grid.setItems(crmService.findEmployees(filterText.getValue()));
    }

    private void configureForm() {
        form = new EmployeeForm(crmService.findAllOrganizations(), crmService.findAllStatuses());
        form.setWidth("25em");

        form.addListener(EmployeeForm.SaveEvent.class, this::saveContact);
        form.addListener(EmployeeForm.DeleteEvent.class, this::deleteContact);
        form.addListener(EmployeeForm.CloseEvent.class, e -> closeEditor());
    }

    private void saveContact(EmployeeForm.SaveEvent e) {
        crmService.saveEmployee(e.getEmployeeDto());
        updateList();
        closeEditor();
    }

    private void deleteContact(EmployeeForm.DeleteEvent e) {
        crmService.deleteEmployee(e.getEmployeeDto());
        updateList();
        closeEditor();
    }
}
