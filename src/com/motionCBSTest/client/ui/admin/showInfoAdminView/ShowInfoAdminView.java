package com.motionCBSTest.client.ui.admin.showInfoAdminView;

import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.motionCBSTest.shared.User;

import java.util.Comparator;

public class ShowInfoAdminView extends Composite {
    interface ShowInfoAdminViewUiBinder extends UiBinder<HTMLPanel, ShowInfoAdminView> {
    }

    private static ShowInfoAdminViewUiBinder ourUiBinder = GWT.create(ShowInfoAdminViewUiBinder.class);

    @UiField
    DataGrid<User> dataGrid;
    @UiField
    SimplePager pager;

    private ActionCell.Delegate<User> actionCell;

    public ShowInfoAdminView() {
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
        dataGrid.addColumn(userIdColumn, "Træner id");

        // Setting the size of the column.
        dataGrid.setColumnWidth(userIdColumn, 8, Unit.PX);

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
        dataGrid.addColumn(fnameColumn, "Fornavn");

        // Setting the size of the column
        dataGrid.setColumnWidth(fnameColumn,8, Unit.PX);

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
        dataGrid.addColumn(lnameColumn, "Efternavn");

        // Setting the size of the column
        dataGrid.setColumnWidth(lnameColumn,8, Unit.PX);


        // Teamtype will be explained in details
        Column<User, String> teamtypeColumn = new Column<User, String>(new TextCell()) {
            @Override
            public String getValue(User user) {
                return user.getTeamtype();
            }
        };

        //Setting the teamtype column to sortable
        teamtypeColumn.setSortable(true);
        sortHandler.setComparator(teamtypeColumn, new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
                return u1.getTeamtype().compareTo(u2.getTeamtype());
            }
        });

        // Adding the column to the table. The "Hold" is the title of the column
        dataGrid.addColumn(teamtypeColumn, "Hold");

        // Setting the size of the column
        dataGrid.setColumnWidth(teamtypeColumn,8, Unit.PX);


        // Mobilenumber will be explained in details
        Column<User, String> mobileColumn = new Column<User, String>(new TextCell()) {
            @Override
            public String getValue(User user) {
                return user.getMobilenr();
            }
        };

        //Setting the mobile column to sortable
        teamtypeColumn.setSortable(true);
        sortHandler.setComparator(mobileColumn, new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
                return u1.getMobilenr().compareTo(u2.getMobilenr());
            }
        });

        // Adding the column to the table. The "Hold" is the title of the column
        dataGrid.addColumn(mobileColumn, "Mobil nr.");

        // Setting the size of the column
        dataGrid.setColumnWidth(mobileColumn,8, Unit.PX);
    }

    public static ShowInfoAdminViewUiBinder getOurUiBinder() {
        return ourUiBinder;
    }

    public DataGrid<User> getDataGrid() {
        return dataGrid;
    }

    public SimplePager getPager() {
        return pager;
    }

    public ActionCell.Delegate<User> getActionCell() {
        return actionCell;
    }
}
