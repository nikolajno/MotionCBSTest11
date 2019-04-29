package com.motionCBSTest.client.ui.admin.trainerStatusView;

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


public class TrainerStatusView extends Composite {


    private static TrainerStatusView.DeleteTrainerViewUiBinder UiBinder = GWT.create(TrainerStatusView.DeleteTrainerViewUiBinder.class);

    @UiField
    DataGrid<User> dataGrid;
    @UiField
    SimplePager pager;


    interface DeleteTrainerViewUiBinder extends UiBinder<HTMLPanel, TrainerStatusView> {
    }

    private ActionCell.Delegate<User> approveActionCell;
    private ActionCell.Delegate<User> deleteActionCell;


    public TrainerStatusView() {
        initWidget(UiBinder.createAndBindUi(this));

        // Setting the page size of the table
        dataGrid.setPageSize(20);

        // Adding the pager to the datagrid
        pager.setDisplay(dataGrid);

        // Ensures the headers doesn't get refreshed every time the data is updated
        dataGrid.setAutoHeaderRefreshDisabled(true);
    }

    public void initUsersTable(ListDataProvider<User> dataProvider) {
        // Attach a column sort handler to the ListDataProvider to sort the list
        ColumnSortEvent.ListHandler<User> sortHandler = new ColumnSortEvent.ListHandler<User>(dataProvider.getList());
        dataGrid.addColumnSortHandler(sortHandler);

        // Creating all the necessary columns to the table
        initTableColumns(sortHandler);

        // Adding the data grid to the DataProvider
        dataProvider.addDataDisplay(dataGrid);
    }

    private void initTableColumns(ColumnSortEvent.ListHandler<User> sortHandler) {


        //First name
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
        dataGrid.setColumnWidth(firstnameColumn, 5, Style.Unit.PX);


        //Last name
        Column<User, String> lastnameColumn = new Column<User, String>(new TextCell()) {
            @Override
            public String getValue(User user) {
                return user.getLname();
            }
        };
        // Setting the lastname column to sortable
        lastnameColumn.setSortable(true);
        sortHandler.setComparator(lastnameColumn, new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
                return u1.getLname().compareTo(u2.getLname());
            }
        });
        // Adding the column to the table. The "lastname" is the title of the column
        dataGrid.addColumn(lastnameColumn, "Last name");

        // Setting the size of the column. Unit.PX can also be Unit.PCT, Unit.EM etc.
        dataGrid.setColumnWidth(lastnameColumn, 5, Style.Unit.PX);


        //Last name
        Column<User, String> isApprovedColumn = new Column<User, String>(new TextCell()) {
            @Override
            public String getValue(User user) {
                return user.getIsApproved() ? "Approved" : "Not Approved";

            }
        };

        isApprovedColumn.setSortable(true);
        sortHandler.setComparator(isApprovedColumn, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                boolean _o1 = o1.getIsApproved();
                boolean _o2 = o2.getIsApproved();

                if (_o1 && _o2 || !_o1 && !_o2) {
                    return 0;
                } else if (_o1 && !_o2) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });

        // Adding the column to the table. The "lastname" is the title of the column
        dataGrid.addColumn(isApprovedColumn, "Status");

        // Setting the size of the column. Unit.PX can also be Unit.PCT, Unit.EM etc.
        dataGrid.setColumnWidth(isApprovedColumn, 5, Style.Unit.PX);





        //Here we create a row with a containing actionButton to approve trainers
            ActionCell<User> approveUserCell = new ActionCell<>("Approve", approveActionCell);
            Column<User, User> approveColumn = new Column<User, User>(approveUserCell) {
                @Override
                public User getValue(User user) {
                    return user;
                }
            };

        // Adding the column to the table. The "lastname" is the title of the column
        dataGrid.addColumn(approveColumn, "Status");

        // Setting the size of the column. Unit.PX can also be Unit.PCT, Unit.EM etc.
        dataGrid.setColumnWidth(approveColumn, 5, Style.Unit.PX);





        //Here we create a row with a containing DeleteButton to delete trainers
        ActionCell<User> deleteUserCell = new ActionCell<>("Delete", deleteActionCell);
        Column<User, User> deleteColumn = new Column<User, User>(deleteUserCell) {
            @Override
            public User getValue(User user) {
                return user;
            }
        };

        // Adding the column to the table. The "lastname" is the title of the column
        dataGrid.addColumn(deleteColumn, "Delete Trainers");

        // Setting the size of the column. Unit.PX can also be Unit.PCT, Unit.EM etc.
        dataGrid.setColumnWidth(deleteColumn, 5, Style.Unit.PX);
    }

    public void addDeleteClickHandler (ActionCell.Delegate<User> deleteActionCell){this.deleteActionCell = deleteActionCell;}
    public void addApproveClickHandler (ActionCell.Delegate<User> approveActionCell){this.approveActionCell = approveActionCell;}
}



