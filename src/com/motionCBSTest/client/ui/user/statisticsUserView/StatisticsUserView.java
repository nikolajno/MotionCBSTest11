package com.motionCBSTest.client.ui.user.statisticsUserView;

import com.google.gwt.cell.client.ActionCell;
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


public class StatisticsUserView extends Composite {

    private static StatisticsUserViewUiBinder UiBinder = GWT.create(StatisticsUserViewUiBinder.class);

    @UiField
    DataGrid<User> dataGrid;
    @UiField
    SimplePager pager;

    private ActionCell.Delegate<User> actionCell;

    interface StatisticsUserViewUiBinder extends UiBinder<HTMLPanel, StatisticsUserView> {
    }


    public StatisticsUserView() {
        initWidget(UiBinder.createAndBindUi(this));

        // Setting the page size of the table
        dataGrid.setPageSize(25);
        // Adding the pager to the datagrid
        pager.setDisplay(dataGrid);
        // Ensures the headers doesn't get refreshed every time the data is
        // updated
        dataGrid.setAutoHeaderRefreshDisabled(true);
    }

    public void initUsersTable(ListDataProvider<User> dataProvider) {
        // Attach a column sort handler to the ListDataProvider to sort the list
        ColumnSortEvent.ListHandler<User> sortHandler = new ColumnSortEvent.ListHandler<User>(dataProvider.getList());
        dataGrid.addColumnSortHandler(sortHandler);

        // Creating all the necessary columns to the table
        initTableColumns(sortHandler);

        /*
         * Adding the data grid to the DataProvider The DataPrivider is
         * containing a List with all the data
         */
        dataProvider.addDataDisplay(dataGrid);

    }

    private void initTableColumns(ColumnSortEvent.ListHandler<User> sortHandler) {
        
        Column<User, String> firstnameColumn = new Column<User, String>(new TextCell()) {
            @Override
            public String getValue(User user) {
                return user.getFname();
            }
        };
        // Setting the firstname column to sortable
        firstnameColumn.setSortable(true);
        sortHandler.setComparator(firstnameColumn, new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
                return u1.getFname().compareTo(u2.getFname());
            }
        });
        // Adding the column to the table. The "firstname" is the title of the column
        dataGrid.addColumn(firstnameColumn, "First name");
        // Setting the size of the column. Unit.PX can also be Unit.PCT, Unit.EM etc.
        dataGrid.setColumnWidth(firstnameColumn, 7, Style.Unit.PX);


        Column<User, String> lastnameColumn = new Column<User, String>(new TextCell()) {
            @Override
            public String getValue(User user) {
                return user.getLname();
            }
        };
        // Setting the lastname column to sortable
        firstnameColumn.setSortable(true);
        sortHandler.setComparator(lastnameColumn, new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
                return u1.getLname().compareTo(u2.getLname());
            }
        });
        // Adding the column to the table. The "lastname" is the title of the column
        dataGrid.addColumn(lastnameColumn, "Last name");
        // Setting the size of the column. Unit.PX can also be Unit.PCT, Unit.EM etc.
        dataGrid.setColumnWidth(lastnameColumn, 7, Style.Unit.PX);



        Column<User, String> mobileColumn = new Column<User, String>(new TextCell()) {
            @Override
            public String getValue(User user) {
                return user.getMobilenr();
            }
        };
        // Setting the MobileNr column to sortable
        mobileColumn.setSortable(true);
        sortHandler.setComparator(mobileColumn, new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
                return u1.getMobilenr().compareTo(u2.getMobilenr());
            }
        });
        // Adding the column to the table. The "Mobile" is the title of the column
        dataGrid.addColumn(mobileColumn, "Mobile");
        // Setting the size of the column. Unit.PX can also be Unit.PCT, Unit.EM etc.
        dataGrid.setColumnWidth(mobileColumn, 7, Style.Unit.PX);


        Column<User, String> teamNameColumn = new Column<User, String>(new TextCell()) {
            @Override
            public String getValue(User user) {
                return user.getTeamName();
            }
        };
        // Setting the teamName column to sortable
        teamNameColumn.setSortable(true);
        sortHandler.setComparator(teamNameColumn, new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
                return u1.getTeamName().compareTo(u2.getTeamName());
            }
        });
        // Adding the column to the table. The "Team Name" is the title of the column
        dataGrid.addColumn(teamNameColumn, "Team name");
        // Setting the size of the column. Unit.PX can also be Unit.PCT, Unit.EM etc.
        dataGrid.setColumnWidth(teamNameColumn, 7, Style.Unit.PX);




    }
}