package com.motionCBSTest.client.logic;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
    }

    class MenuClickHandler implements ClickHandler {

        @Override
        public void onClick(ClickEvent event) {
            if (event.getSource() == mainAdminView.getLogoutBtn()) {
                    content.changeView(content.getLoginView());
                    listProviderUsers.getList().clear();
                    currentUser = null;
            } else if (event.getSource() == mainAdminView.getDeleteTrainerBtn()){
                    mainAdminView.changeView(mainAdminView.getDeleteTrainerView());
            } else if (event.getSource() == mainAdminView.getShowInfoBtn()){
                mainAdminView.changeView(mainAdminView.getShowInfoAdminView());
            }
         }
        }

    }
