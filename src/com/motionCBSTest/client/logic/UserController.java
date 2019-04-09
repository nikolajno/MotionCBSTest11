package com.motionCBSTest.client.logic;
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

        //bindHandlers();

        listProviderUsers = new ListDataProvider<>();
        mainUserView.getStatisticsUserView().initUsersTable(listProviderUsers);
    }


    public void loadUser(User currentUser) {
        this.currentUser = currentUser;
        loadTables();
    }

    /*private void bindHandlers() {
        mainUserView.addClickHandlers(new MenuClickHandler());
        mainUserView.getStatisticsUserView().addClickHandler(new ChooseRecieverHandler());
        mainUserView.getChangeUserInfoUserView().addClickHandlers(new ChangeSettingsClickHandler());
    }

    class MenuClickHandler implements ClickHandler {

        @Override
        public void onClick(ClickEvent event) {
            if (event.getSource() == mainUserView.getUsersBtn()) {
                mainUserView.changeView(mainUserView.getUsersView());
            } else if (event.getSource() == mainUserView.getMessageBtn()) {
                mainUserView.changeView(mainUserView.getNewMessageView());
            } else if (event.getSource() == mainUserView.getInboxBtn()) {
                listProviderNewMessages.refresh();
                mainUserView.changeView(mainUserView.getMessagesContainer());
            } else if (event.getSource() == mainUserView.getSettingsBtn()) {
                mainUserView.getSettingsView().setProfileSettings(currentUser);
                mainUserView.changeView(mainUserView.getSettingsView());
            } else if (event.getSource() == mainUserView.getLogoutBtn()) {
                /*
                 * When a user is logged out it:
                 * 1) Changing to the login view
                 * 2) Clearing the List of users in the DataProvider
                 * 3) Clearing the List of messages in the DataProvider
                 * 4) Clearing the current user by setting it to null
                 */
                /*content.changeView(content.getLoginView());
                listProviderUsers.getList().clear();
                listProviderNewMessages.getList().clear();
                listProviderOldMessages.getList().clear();
                currentUser = null;
            }
        }
    }*/



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
}
