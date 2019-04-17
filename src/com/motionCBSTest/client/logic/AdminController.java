package com.motionCBSTest.client.logic;
import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.ListDataProvider;
import com.motionCBSTest.client.rpc.MotionCBSTestServiceAsync;
import com.motionCBSTest.client.ui.ContentPanel;
import com.motionCBSTest.client.ui.admin.MainAdminView.MainAdminView;
import com.motionCBSTest.shared.User;

import java.util.ArrayList;

public class AdminController {
    private ContentPanel content;
    private MotionCBSTestServiceAsync motionCBSTestServiceAsync;
    private User currentUser;
    private MainAdminView mainAdminView;

    // A List Data Provider which contains an ArrayList with users is used for the DataGrid
    private ListDataProvider<User> listProviderUsers;

    public AdminController(ContentPanel content, MotionCBSTestServiceAsync motionCBSTestServiceAsync) {
        this.content = content;
        this.mainAdminView = content.getMainAdminView();
        this.motionCBSTestServiceAsync = motionCBSTestServiceAsync;

        bindHandlers();

        listProviderUsers = new ListDataProvider<User>();
        mainAdminView.getTrainerStatusView().initUsersTable(listProviderUsers);
        mainAdminView.getShowInfoAdminView().initUsersTable(listProviderUsers);
        mainAdminView.getTabLayot().getSdeltid().initUsersTable(listProviderUsers);
        mainAdminView.getTabLayot().getSfuldtid().initUsersTable(listProviderUsers);
    }

    public void loadUser(User currentUser) {
        this.currentUser = currentUser;
        loadTables();
    }

    private void loadTables() {
        // The RPC to get all the users
        motionCBSTestServiceAsync.getUsers(currentUser.getId(), new AsyncCallback<ArrayList<User>>() {

            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Could not load users");
            }

            @Override
            public void onSuccess(ArrayList<User> users) {
                // Adding all the users to the DataProvider (ArrayList)
                listProviderUsers.getList().addAll(users);
            }
        });
    }

    private void bindHandlers() {

        mainAdminView.addClickHandlers(new MenuClickHandler());
        mainAdminView.getShowInfoAdminView().addClickHandler(new SelectInfoHandler());
        mainAdminView.getTabLayot().addSelectionHandler(new StatisticTypeHandler());
    }

    class SelectInfoHandler implements ActionCell.Delegate<User> {
        @Override
        public void execute(User user) {
            Window.alert("Here is all the information about " + user.getFname() + " " + user.getLname() +
                    "\n\nTrainer ID: " + user.getId() +
                    "\nFirst Name: " + user.getFname() +
                    "\nLast Name: " + user.getLname() +
                    "\nEmail: " + user.getEmail() +
                    "\nAddress: " + user.getAddress() +
                    "\nMobile: " + user.getMobilenr() +
                    "\nEducation: " + user.getEducation() +
                    "\nExperience: " + user.getExperience() +
                    "\nHours Pr. Week: " + user.getHoursPrWeek() +
                    "\nApproved?: " + user.getIsApproved() +
                    "\nPassword: " + user.getPassword() +
                    "\nTeam: " + user.getTeamName());
        }
    }


    class MenuClickHandler implements ClickHandler {

        @Override
        public void onClick(ClickEvent event) {
            if (event.getSource() == mainAdminView.getLogoutBtn()) {
                content.changeView(content.getLoginView());
                listProviderUsers.getList().clear();
                currentUser = null;
            } else if (event.getSource() == mainAdminView.getTrainerStatusBtn()) {
                mainAdminView.changeView(mainAdminView.getTrainerStatusView());
            } else if (event.getSource() == mainAdminView.getShowInfoBtn()) {
                mainAdminView.changeView(mainAdminView.getShowInfoAdminView());
            } else if (event.getSource() == mainAdminView.getStatisticBtn()) {
                mainAdminView.changeView(mainAdminView.getTabLayot());
            }
        }
    }

    /**
     * This is used for the TabLayoutPanel. The panel that can show either fuldtid or
     * deltid. If this isn't used it is likely that the DataGrids will show up empty.
     * Mark that this isn't ClickHandler but a SelectionHandler instead
     */
    class StatisticTypeHandler implements SelectionHandler<Integer> {

        @Override
        public void onSelection(SelectionEvent<Integer> event) {
            switch (event.getSelectedItem()) {
                case 0:
                    listProviderUsers.refresh();
                    break;

                case 1:
                    listProviderUsers.refresh();
                    break;
            }

        }

    }
}
