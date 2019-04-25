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

        listProviderUsers = new ListDataProvider<>();
        mainAdminView.getTrainerStatusView().initUsersTable(listProviderUsers);
        mainAdminView.getShowInfoAdminView().initUsersTable(listProviderUsers);
        mainAdminView.getTabLayot().getSdeltid().initUsersTable(listProviderUsers);
        mainAdminView.getTabLayot().getSfuldtid().initUsersTable(listProviderUsers);
        mainAdminView.getChangeInfoAdminView().initUsersTable(listProviderUsers);
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
        mainAdminView.getChangeInfoAdminView().addClickHandler(new GetInfoHandler());
        mainAdminView.getChangeInfoAdminView().addClickHandlers(new ChangeInfoHanlder());
        mainAdminView.getTrainerStatusView().addClickHandler(new DeleteUserHandler());

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
                listProviderUsers.getList().clear();
                loadTables();
                listProviderUsers.refresh();
            } else if (event.getSource() == mainAdminView.getShowInfoBtn()) {
                mainAdminView.changeView(mainAdminView.getShowInfoAdminView());
                listProviderUsers.getList().clear();
                loadTables();
                listProviderUsers.refresh();
            } else if (event.getSource() == mainAdminView.getStatisticBtn()) {
                mainAdminView.changeView(mainAdminView.getTabLayot());
            } else if (event.getSource() == mainAdminView.getChangeBtn()) {
                mainAdminView.changeView(mainAdminView.getChangeInfoAdminView());
                listProviderUsers.getList().clear();
                loadTables();
                listProviderUsers.refresh();
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
                    listProviderUsers.getList().clear();
                    motionCBSTestServiceAsync.getUsersPartTime(currentUser.getId(), new AsyncCallback<ArrayList<User>>() {

                        @Override
                        public void onFailure(Throwable caught) {
                            Window.alert("Could not load users");
                        }

                        @Override
                        public void onSuccess(ArrayList<User> users) {
                            // Adding all the users to the DataProvider (ArrayList)
                            listProviderUsers.getList().addAll(users);
                            listProviderUsers.refresh();

                        }
                    });
                    break;

                case 1:
                    listProviderUsers.getList().clear();
                    motionCBSTestServiceAsync.getUsersFullTime(currentUser.getId(), new AsyncCallback<ArrayList<User>>() {

                        @Override
                        public void onFailure(Throwable caught) {
                            Window.alert("Could not load users");
                        }

                        @Override
                        public void onSuccess(ArrayList<User> users) {
                            // Adding all the users to the DataProvider (ArrayList)
                            listProviderUsers.getList().addAll(users);
                            listProviderUsers.refresh();
                        }
                    });
                    break;
            }

        }

    }

    private User chosenUser;
    class GetInfoHandler implements ActionCell.Delegate<User> {

        @Override
        public void execute(User user) {
            chosenUser = user;
            mainAdminView.getChangeInfoAdminView().setProfileChanges(user);
        }
    }

    class ChangeInfoHanlder implements ClickHandler {

        @Override
        public void onClick(ClickEvent clickEvent) {
            /*
             * It firsts sets(change) all the user info with the info from
             * the text fields and radio button in the settings view
             */

            chosenUser.setFname(mainAdminView.getChangeInfoAdminView().getTxtFname().getText());
            chosenUser.setLname(mainAdminView.getChangeInfoAdminView().getTxtLname().getText());
            chosenUser.setEmail(mainAdminView.getChangeInfoAdminView().getTxtEmail().getText());
            chosenUser.setAddress(mainAdminView.getChangeInfoAdminView().getTxtAddress().getText());
            chosenUser.setMobilenr(mainAdminView.getChangeInfoAdminView().getTxtMobileNo().getText());
            chosenUser.setEducation(mainAdminView.getChangeInfoAdminView().getTxtEducation().getText());
            chosenUser.setExperience(mainAdminView.getChangeInfoAdminView().getTxtExperience().getText());
            chosenUser.setHoursPrWeek(Integer.valueOf(mainAdminView.getChangeInfoAdminView().getTxtHoursPrWeek().getText()));
            chosenUser.setPassword(mainAdminView.getChangeInfoAdminView().getTxtPassword().getText());

            int teamtype_teamID = 0;

            if (mainAdminView.getChangeInfoAdminView().getNewCrossfitBtn().isChecked()) {
                teamtype_teamID = 1;
            }
            if (mainAdminView.getChangeInfoAdminView().getNewSpinningBtn().isChecked()) {
                teamtype_teamID = 3;
            }
            if (mainAdminView.getChangeInfoAdminView().getNewHitBtn().isChecked()) {
                teamtype_teamID = 2;
            }
            if (mainAdminView.getChangeInfoAdminView().getNewStramopBtn().isChecked()) {
                teamtype_teamID = 4;
            }

            Window.alert(""+teamtype_teamID);
            chosenUser.setTeamtype_teamID(teamtype_teamID);


            // The RPC call which through the server updates the user info in the users table in the database
            motionCBSTestServiceAsync.changeUserInfo(chosenUser, new AsyncCallback<Boolean>() {

                @Override
                public void onFailure(Throwable caught) {
                    // TODO Auto-generated method stub
                }

                /*
                 * Confirmation if the info was updated
                 */
                @Override
                public void onSuccess(Boolean updated) {
                    if (updated) {
                        Window.alert("Change succes");
                        listProviderUsers.getList().clear();
                        loadTables();

                    } else {
                        Window.alert("Could not make changes");
                    }

                }
            });

        }
    }


    class DeleteUserHandler implements ActionCell.Delegate<User> {

        @Override
        public void execute(final User user) {

            //First that is executed is a confirmation window to delete the user
            boolean deleteUserConfirmed = Window.confirm("Are you sure you want to delete:\n" + user.getFname() + " " + user.getLname());
            //If its confirmed a RPC call will be made so the user is deleted in the database
            if (deleteUserConfirmed)
                motionCBSTestServiceAsync.deleteUser(user.getId(), new AsyncCallback<Boolean>() {

                    /*
                     * Handles error from callback function
                     */
                    @Override
                    public void onFailure(Throwable caught) {
                        Window.alert("Something went wrong");
                    }

                    /*
                     * Handles success response from callback
                     * The callback returns either true or false depending if the user
                     * were deleted or not
                     */
                    @Override
                    public void onSuccess(Boolean isDeleted) {
                        if (!isDeleted) {
                            Window.alert("Could not delete user");
                        } else {
                            // If the user is deleted the user will be removed from the list of users
                            listProviderUsers.getList().remove(user);
                        }

                    }
                });
        }
    }

}
