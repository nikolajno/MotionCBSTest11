package com.motionCBSTest.client.ui.admin.statisticsAdminView;

import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
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

public class StatisticsAdminView extends Composite {
    interface StatisticsAdminViewUiBinder extends UiBinder<HTMLPanel, StatisticsAdminView> {
    }

    private static StatisticsAdminViewUiBinder ourUiBinder = GWT.create(StatisticsAdminViewUiBinder.class);

    @UiField
    DataGrid<User> dataGridUp;
    @UiField
    SimplePager pagerUp;

    public StatisticsAdminView() {
        initWidget(ourUiBinder.createAndBindUi(this));

        // Setting the page size of the table
        dataGridUp.setPageSize(10);
        //dataGridDown.setPageSize(10);

        // Adding the pager to the datagrid
        pagerUp.setDisplay(dataGridUp);
        //pagerDown.setDisplay(dataGridDown);

        // Ensures the headers doesn't get refreshed every time the data is updated
        dataGridUp.setAutoHeaderRefreshDisabled(true);
        //dataGridDown.setAutoHeaderRefreshDisabled(true);
    }
    // This method is called from the logic so it is possible to load the table with data from the database
    public void initUsersTable(ListDataProvider<User> dataProvider) {

        // Attach a column sort handler to the ListDataProvider to sort the list
        ColumnSortEvent.ListHandler<User> sortHandler = new ColumnSortEvent.ListHandler<User>(dataProvider.getList());
        dataGridUp.addColumnSortHandler(sortHandler);

        // Creating all the necessary columns to the table
        initTableColumns(sortHandler);

        // Adding the data grid to the DataProvider. The DataProvider is containing a List with all the data
        dataProvider.addDataDisplay(dataGridUp);

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
        dataGridUp.addColumn(userIdColumn, "Trainer Id");

        // Setting the size of the column.
        dataGridUp.setColumnWidth(userIdColumn, 4, Style.Unit.PX);

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
        dataGridUp.addColumn(lnameColumn, "Last Name");

        // Setting the size of the column
        dataGridUp.setColumnWidth(lnameColumn, 8, Style.Unit.PX);


        // The user id column is created. Remark that when it is a cell it isn't a IntegerCell but a NumberCell.
        Column<User, Number> hoursColumn = new Column<User, Number>(new NumberCell()) {
            @Override
            public Number getValue(User object) {
                return object.getHoursPrWeek();
            }
        };

        // Setting the user id column to sortable
        userIdColumn.setSortable(true);
        sortHandler.setComparator(hoursColumn, new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
                return Integer.compare(u1.getHoursPrWeek(), u2.getHoursPrWeek());
            }
        });

        // Adding the column to the table. The "Trainer id" is the title of the column
        dataGridUp.addColumn(hoursColumn, "Hours pr. Week");

        // Setting the size of the column.
        dataGridUp.setColumnWidth(hoursColumn, 4, Style.Unit.PX);
    }
}