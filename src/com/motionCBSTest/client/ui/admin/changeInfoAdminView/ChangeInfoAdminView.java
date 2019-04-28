package com.motionCBSTest.client.ui.admin.changeInfoAdminView;

import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.ListDataProvider;
import com.motionCBSTest.shared.User;

import java.util.Comparator;

public class ChangeInfoAdminView extends Composite {
    interface ChangeInfoAdminViewUiBinder extends UiBinder<HTMLPanel, ChangeInfoAdminView> {}

    private static ChangeInfoAdminViewUiBinder ourUiBinder = GWT.create(ChangeInfoAdminViewUiBinder.class);

    @UiField TextBox txtFname;
    @UiField TextBox txtLname;
    @UiField TextBox txtEmail;
    @UiField TextBox txtAddress;
    @UiField TextBox txtMobileNo;
    @UiField TextBox txtEducation;
    @UiField TextBox txtExperience;
    @UiField IntegerBox txtHoursPrWeek;
    @UiField TextBox txtPassword;
    @UiField RadioButton newCrossfitBtn;
    @UiField RadioButton newHitBtn;
    @UiField RadioButton newStramopBtn;
    @UiField RadioButton newSpinningBtn;
    @UiField Button changeProfileBtn;
    @UiField DataGrid<User> dataGrid;
    @UiField SimplePager pager;

    private ActionCell.Delegate<User> actionCell;

    public ChangeInfoAdminView() {
        initWidget(ourUiBinder.createAndBindUi(this));

        // Setting the page size of the table
        dataGrid.setPageSize(20);

        // Adding the pager to the datagrid
        pager.setDisplay(dataGrid);

        // Ensures the headers doesn't get refreshed every time the data is updated
        dataGrid.setAutoHeaderRefreshDisabled(true);
    }

    // This method is called from the logic so it is possible to load the table with data from the database
    public void initUsersTable(ListDataProvider<User> dataProvider) {

        // Attach a column sort handler to the ListDataProvider to sort the list
        ColumnSortEvent.ListHandler<User> sortHandler = new ColumnSortEvent.ListHandler<User>(dataProvider.getList());
        dataGrid.addColumnSortHandler(sortHandler);

        // Creating all the necessary columns to the table
        initTableColumns(sortHandler);

        // Adding the data grid to the DataProvider. The DataPrivider is containing a List with all the data
        dataProvider.addDataDisplay(dataGrid);
    }

    // This method is creating all the columns. Each column wont be described since it is more or less the
    // same with small changes
    private void initTableColumns(ColumnSortEvent.ListHandler<User> sortHandler) {

        // In this next lines the user id column will be explained in details

        // The user id column is created. Remark that when it is a cell it isn't a IntegerCell but a NumberCell.
        Column<User, Number> userIdColumn = new Column<User, Number>(new NumberCell()) {
            @Override
            public Number getValue(User object) {
                return object.getId();
            }
        };

        // Setting the user id column to sortable
        userIdColumn.setSortable(true);
        sortHandler.setComparator(userIdColumn, new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
                return Integer.compare(u1.getId(), u2.getId());
            }
        });

        // Adding the column to the table. The "Træner id" is the title of the column
        dataGrid.addColumn(userIdColumn, "Trainer Id");

        // Setting the size of the column.
        dataGrid.setColumnWidth(userIdColumn, 5, Style.Unit.PX);

        // Firstname will be explained in details
        Column<User, String> fnameColumn = new Column<User, String>(new TextCell()) {
            @Override
            public String getValue(User user) {
                return user.getFname();
            }
        };

        // Setting the firstname column to sortable
        fnameColumn.setSortable(true);
        sortHandler.setComparator(fnameColumn, new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
                return u1.getFname().compareTo(u2.getFname());
            }
        });

        // Adding the column to the table. The "Fornavn" is the title of the column
        dataGrid.addColumn(fnameColumn, "First Name");

        // Setting the size of the column
        dataGrid.setColumnWidth(fnameColumn,7, Style.Unit.PX);

        // Lastname will be explained in details
        Column<User, String> lnameColumn = new Column<User, String>(new TextCell()) {
            @Override
            public String getValue(User user) {
                return user.getLname();
            }
        };

        //Setting the lastname column to sortable
        lnameColumn.setSortable(true);
        sortHandler.setComparator(lnameColumn, new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
                return u1.getLname().compareTo(u2.getLname());
            }
        });

        // Adding the column to the table. The "Efternavn" is the title of the column
        dataGrid.addColumn(lnameColumn, "Last Name");

        // Setting the size of the column
        dataGrid.setColumnWidth(lnameColumn,7, Style.Unit.PX);

        //Here we create a button to show all info about the specific user
        ActionCell<User> selectUserCell = new ActionCell<>("Change Info", actionCell);
        Column<User, User> joinColumn = new Column<User, User>(selectUserCell) {
            @Override
            public User getValue(User user) {
                return user;
            }
        };

        // Adding the column to the table. The "Efternavn" is the title of the column
        dataGrid.addColumn(joinColumn, "Info");

        // Setting the size of the column
        dataGrid.setColumnWidth(joinColumn, 6, Style.Unit.PX);
    }

    public void addClickHandler(ActionCell.Delegate<User> actionCell) {
        this.actionCell = actionCell;
    }

    public static ChangeInfoAdminView.ChangeInfoAdminViewUiBinder getOurUiBinder() {
        return ourUiBinder;
    }

    public DataGrid<User> getDataGrid() {
        return dataGrid;
    }

    public SimplePager getPager() {
        return pager;
    }

    // This method is adding the click handler to the change profile button
    public void addClickHandlers(ClickHandler clickHandler) {
        changeProfileBtn.addClickHandler(clickHandler);
    }

    // This method is used to set all the widgets with the current users information
    public void setProfileChanges(User user) {
        txtFname.setText(user.getFname());
        txtLname.setText(user.getLname());
        txtEmail.setText(user.getEmail());
        txtAddress.setText(user.getAddress());
        txtMobileNo.setText(user.getMobilenr());
        txtEducation.setText(user.getEducation());
        txtExperience.setText(user.getExperience());
        txtHoursPrWeek.setValue(user.getHoursPrWeek());
        txtPassword.setText(user.getPassword());

        if (user.getTeamtype_teamID() == 1) {
            newCrossfitBtn.setValue(true);
            newCrossfitBtn.setEnabled(true);
        } else if (user.getTeamtype_teamID() == 3) {
            newSpinningBtn.setValue(true);
            newSpinningBtn.setEnabled(true);
        } else if (user.getTeamtype_teamID() == 2) {
            newHitBtn.setValue(true);
            newHitBtn.setEnabled(true);
        } else if (user.getTeamtype_teamID() == 4) {
            newStramopBtn.setValue(true);
            newStramopBtn.setEnabled(true);
        }

    }

    // Getters
    public TextBox getTxtFname() { return txtFname; }

    public TextBox getTxtLname() { return txtLname; }

    public TextBox getTxtEmail() { return txtEmail; }

    public TextBox getTxtAddress() { return txtAddress; }

    public TextBox getTxtMobileNo() { return txtMobileNo; }

    public TextBox getTxtEducation() { return txtEducation; }

    public TextBox getTxtExperience() { return txtExperience; }

    public IntegerBox getTxtHoursPrWeek() { return txtHoursPrWeek; }

    public TextBox getTxtPassword() { return txtPassword; }

    public RadioButton getNewCrossfitBtn() {return newCrossfitBtn;}

    public RadioButton getNewHitBtn() {return newHitBtn;}

    public RadioButton getNewStramopBtn() {return newStramopBtn;}

    public RadioButton getNewSpinningBtn() {return newSpinningBtn;}

}