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

        listProviderUsers = new ListDataProvider<>();
        mainUserView.getTrainersTableUserView().initUsersTable(listProviderUsers);
    }


    public void loadUser(User currentUser) {
        this.currentUser = currentUser;
        loadTables();
    }

    private void bindHandlers() {
        mainUserView.addClickHandlers(new MenuClickHandler());
        //mainUserView.getTrainersTableUserView().addClickHandler(new ChooseRecieverHandler());
        mainUserView.getChangeInfoUserView().addClickHandlers(new ChangeSettingsClickHandler());
    }

    class MenuClickHandler implements ClickHandler {

        @Override
        public void onClick(ClickEvent event) {
            if (event.getSource() == mainUserView.getStatisticBtn()) {
                mainUserView.changeView(mainUserView.getTrainersTableUserView());
            } else if (event.getSource() == mainUserView.getChangeBtn()) {
                mainUserView.getChangeInfoUserView().setProfileChanges(currentUser);
                mainUserView.changeView(mainUserView.getChangeInfoUserView());
            } else if (event.getSource() == mainUserView.getLogoutBtn()) {
                /*
                 * When a user is logged out it:
                 * 1) Changing to the login view
                 * 2) Clearing the List of users in the DataProvider
                 * 3) Clearing the List of messages in the DataProvider
                 * 4) Clearing the current user by setting it to null
                 */
                content.changeView(content.getLoginView());
                listProviderUsers.getList().clear();
                currentUser = null;
            }
        }
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

    class ChangeSettingsClickHandler implements ClickHandler {

        @Override
        public void onClick(ClickEvent event) {
            /*
             * It firsts sets(change) all the user info with the info from
             * the text fields and radio button in the settings view
             */
            currentUser.setFname(mainUserView.getChangeInfoUserView().getTxtFname().getText());
            currentUser.setLname(mainUserView.getChangeInfoUserView().getTxtLname().getText());
            currentUser.setEmail(mainUserView.getChangeInfoUserView().getTxtEmail().getText());
            currentUser.setAddress(mainUserView.getChangeInfoUserView().getTxtAddress().getText());
            currentUser.setMobilenr(mainUserView.getChangeInfoUserView().getTxtMobileNo().getText());
            currentUser.setEducation(mainUserView.getChangeInfoUserView().getTxtEducation().getText());
            currentUser.setExperience(mainUserView.getChangeInfoUserView().getTxtExperience().getText());
            currentUser.setHoursPrWeek(mainUserView.getChangeInfoUserView().getTxtHoursPrWeek().getTabIndex());
            currentUser.setPassword(mainUserView.getChangeInfoUserView().getTxtPassword().getText());
            currentUser.setTeamtype(mainUserView.getChangeInfoUserView().getTxtTeamtype().getText());

            //Sådan skal det gøres hvis vi bruger radio buttons
            /*if (mainUserView.getChangeInfoUserView().getGenderMaleRBtn().getValue() == true) {
                currentUser.setGender('m');
            } else if (mainUserView.getChangeInfoUserView().getGenderFemaleRBtn().getValue() == true) {
                currentUser.setGender('f');
            }*/

            // The RPC call which through the server updates the user info in the users table in the database
            motionCBSTestServiceAsync.changeUserInfo(currentUser, new AsyncCallback<Boolean>() {

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
                    } else {
                        Window.alert("Could not make changes");
                    }

                }
            });
        }
    }
}
