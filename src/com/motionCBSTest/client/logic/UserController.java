package com.motionCBSTest.client.logic;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.ListDataProvider;
import com.motionCBSTest.client.rpc.MotionCBSTestServiceAsync;
import com.motionCBSTest.client.ui.ContentPanel;
import com.motionCBSTest.client.ui.user.mainUserView.MainUserView;
import com.motionCBSTest.shared.User;

import java.util.ArrayList;

public class UserController {
    private ContentPanel content;
    private MotionCBSTestServiceAsync motionCBSTestServiceAsync;
    private User currentUser;
    private MainUserView mainUserView;

    // A List Data Provider which contains an ArrayList with users is used for the DataGrid
    private ListDataProvider<User> listProviderUsers;

    public UserController(ContentPanel content, MotionCBSTestServiceAsync motionCBSTestServiceAsync) {
        this.content = content;
        this.mainUserView = content.getMainUserView();
        this.motionCBSTestServiceAsync = motionCBSTestServiceAsync;

        bindHandlers();

        listProviderUsers = new ListDataProvider<User>();
        mainUserView.getTableUserView().initUsersTable(listProviderUsers);
    }

    // A method that can load the users with the current information

    public void loadUser(User currentUser) {
        this.currentUser = currentUser;
        loadTables();
    }

    // A method that can load the tables with the current information
    public void loadTables() {

        // The RPC to get all the users
        motionCBSTestServiceAsync.getUsers(currentUser.getId(), new AsyncCallback<ArrayList<User>>() {

            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Could not load users");
            }

            @Override
            public void onSuccess(ArrayList<User> users) {
                listProviderUsers.getList().clear();
                // Adding all the users to the DataProvider (ArrayList)
                listProviderUsers.getList().addAll(users);
            }
        });
    }

    private void bindHandlers() {
        mainUserView.addClickHandlers(new MenuClickHandler());
        mainUserView.getChangeInfoUserView().addClickHandlers(new ChangeSettingsClickHandler());
    }

    // This inner class finds out which button is pressed and then switches the view
    class MenuClickHandler implements ClickHandler {

        @Override
        public void onClick(ClickEvent event) {
            if (event.getSource() == mainUserView.getTrainersTable()) {
                mainUserView.changeView(mainUserView.getTableUserView());
                listProviderUsers.refresh();
            } else if (event.getSource() == mainUserView.getChangeBtn()) {
                mainUserView.getChangeInfoUserView().setProfileChanges(currentUser);
                mainUserView.changeView(mainUserView.getChangeInfoUserView());
            } else if (event.getSource() == mainUserView.getLogoutBtn()) {
                content.changeView(content.getLoginView());
                listProviderUsers.getList().clear();
                currentUser = null;
            }
        }
    }

    // In the next lines we are creating an actioncell that can take the current user and show all their information
    // and give the current user the ability to change their info.
    class ChangeSettingsClickHandler implements ClickHandler {

        @Override
        public void onClick(ClickEvent event) {
            // It firsts sets(change) all the user info with the info from the text fields and
            // radio button in the settings view
            currentUser.setFname(mainUserView.getChangeInfoUserView().getTxtFname().getText());
            currentUser.setLname(mainUserView.getChangeInfoUserView().getTxtLname().getText());
            currentUser.setEmail(mainUserView.getChangeInfoUserView().getTxtEmail().getText());
            currentUser.setAddress(mainUserView.getChangeInfoUserView().getTxtAddress().getText());
            currentUser.setMobilenr(mainUserView.getChangeInfoUserView().getTxtMobileNo().getText());
            currentUser.setEducation(mainUserView.getChangeInfoUserView().getTxtEducation().getText());
            currentUser.setExperience(mainUserView.getChangeInfoUserView().getTxtExperience().getText());
            currentUser.setHoursPrWeek(mainUserView.getChangeInfoUserView().getTxtHoursPrWeek().getTabIndex());
            currentUser.setPassword(mainUserView.getChangeInfoUserView().getTxtPassword().getText());

            // Here we check which of the radiobuttons the current user choose and then set it
            int teamtype_teamID = 0;

                if (content.getMainUserView().getChangeInfoUserView().getNewCrossfitBtn().isChecked()) {
                    teamtype_teamID = 1;
                }
                if (content.getMainUserView().getChangeInfoUserView().getNewSpinningBtn().isChecked()) {
                    teamtype_teamID = 3;
                }
                if (content.getMainUserView().getChangeInfoUserView().getNewHitBtn().isChecked()) {
                    teamtype_teamID = 2;
                }
                if (content.getMainUserView().getChangeInfoUserView().getNewStramopBtn().isChecked()) {
                    teamtype_teamID = 4;
                }

            currentUser.setTeamtype_teamID(teamtype_teamID);

            // The RPC call which through the server updates the user info in the users table in the database
            motionCBSTestServiceAsync.changeUserInfo(currentUser, new AsyncCallback<Boolean>() {

                @Override
                public void onFailure(Throwable caught) {
                    // TODO Auto-generated method stub
                }

                // Confirmation if the info was updated
                @Override
                public void onSuccess(Boolean updated) {
                    if (updated) {
                        Window.alert("Change succes");
                        loadTables();
                    } else {
                        Window.alert("Could not make changes");
                    }
                }
            });
        }
    }
}
