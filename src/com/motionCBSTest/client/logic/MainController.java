package com.motionCBSTest.client.logic;
//Hej Christian :)

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.motionCBSTest.client.rpc.MotionCBSTestServiceAsync;
import com.motionCBSTest.client.ui.ContentPanel;
import com.motionCBSTest.shared.User;

public class MainController {
    private ContentPanel content;
    private MotionCBSTestServiceAsync motionCBSTestService;

    private UserController userController;

    public MainController(ContentPanel content, MotionCBSTestServiceAsync motionCBSTestService){
        this.content = content;
        this.motionCBSTestService = motionCBSTestService;

        userController = new UserController(content, motionCBSTestService);
        bindHandlers();


    }

    private void bindHandlers() {
        content.getLoginView().addClickHandlers(new LoginClickHandler());
    }

    class LoginClickHandler implements ClickHandler {
        @Override
        public void onClick(ClickEvent event) {
            // Getting the text from the two text boxes on the login screen
            String mobileNr = content.getLoginView().getMobilenrTxtBox().getText();
            String password = content.getLoginView().getPasswordTxtBox().getText();

            // RPC authenticating user method
            motionCBSTestService.authorizeUser(mobileNr, password, new AsyncCallback<User>() {

                /*
                 * Handles error from callback function
                 */
                @Override
                public void onFailure(Throwable caught) {
                    Window.alert("Der skete en fejl");

                }

                /*
                 * Handles success response from callback
                 * The callback returns a user
                 */
                @Override
                public void onSuccess(User user) {
                    // Failed to match input with User in database
                    if (user == null) {
                        Window.alert("Wrong mobileNr or password");
                    } else {

                        /*
                         * 1) User match in database,
                         * 2) Checks access level Admin != User
                         * 3) Change the view to either admin og user view
                         */

                        if (user.getType() == 1) {
                            Window.alert("Vi er ikke noget til admin endnu");
                        } else if (user.getType() == 2){
                            userController.loadUser(user);
                            content.changeView(content.getMainUserView());
                            content.getMainUserView().changeView(content.getMainUserView().getChangeUserInfoUserView());
                        }

                        // Clearing the text fields (mobileNr & password) from
                        // the login screen
                        content.getLoginView().clearTextBoxFields();
                    }

                }
            });

        }
    }
//hej
}
