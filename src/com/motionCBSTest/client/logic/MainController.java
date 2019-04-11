package com.motionCBSTest.client.logic;
//Hej Christian :)

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.view.client.ListDataProvider;
import com.motionCBSTest.client.rpc.MotionCBSTestServiceAsync;
import com.motionCBSTest.client.ui.ContentPanel;
import com.motionCBSTest.shared.User;

public class MainController {
    private ContentPanel content;
    private MotionCBSTestServiceAsync motionCBSTestService;
    private UserController userController;
    private User currentUser;
    private AdminController adminController;


    private ListDataProvider<User> listProviderUsers;

    public MainController(ContentPanel content, MotionCBSTestServiceAsync motionCBSTestService){
        this.content = content;
        this.motionCBSTestService = motionCBSTestService;

        userController = new UserController(content, motionCBSTestService);
        bindHandlers();

        adminController = new AdminController(content, motionCBSTestService);
        bindHandlers();

        listProviderUsers = new ListDataProvider<>();


    }

    private void bindHandlers() {
        content.getLoginView().getLoginBtn().addClickHandler(new LoginClickHandler());
        content.getLoginView().getRegisterBtn().addClickHandler(new RegisterBtnClickHandler());
        content.getRegisterView().getRegisterBtn().addClickHandler(new RegisterClickHandler());
        content.getRegisterView().getGobackBtn().addClickHandler(new GoBack());
        content.getMainAdminView().addClickHandlers(new LoginClickHandler());
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
                            adminController.loadUser(user);
                            content.changeView(content.getMainAdminView());
                            content.getMainAdminView().changeView(content.getMainAdminView());
                        } else if (user.getType() == 2){
                            userController.loadUser(user);
                            content.changeView(content.getMainUserView());
                            content.getMainUserView().changeView(content.getMainUserView().getStatisticsUserView());
                        } else {
                            Window.alert("Test");
                        }

                        // Clearing the text fields (mobileNr & password) from
                        // the login screen
                        content.getLoginView().clearTextBoxFields();
                    }

                }
            });

        }
    }

    class RegisterBtnClickHandler implements ClickHandler {
        @Override
        public void onClick(ClickEvent event) {
            content.changeView(content.getRegisterView());
        }
    }

    class RegisterClickHandler implements ClickHandler{

        @Override
        public void onClick(ClickEvent event) {
            // Getting the text from the two text boxes on the login screen
            String fName = content.getRegisterView().getNewtxtFname().getText();
            String lName = content.getRegisterView().getNewtxtLname().getText();
            String email = content.getRegisterView().getNewtxtEmail().getText();
            String address = content.getRegisterView().getNewtxtAddress().getText();
            String mobileno = content.getRegisterView().getNewtxtMobileNo().getText();
            String education = content.getRegisterView().getNewtxtEducation().getText();
            String experience = content.getRegisterView().getNewtxtExperience().getText();
            Integer hoursPrWeek = content.getRegisterView().getNewtxtHoursPrWeek().getValue();
            String password = content.getRegisterView().getNewtxtPassword().getText();


                if (event.getSource()instanceof RadioButton) {
                    if (event.getSource() == content.getRegisterView().getNewCrossfitBtn()) {
                        teamtype = "Crossfit";
                    }
                    if (event.getSource() == content.getRegisterView().getNewSpinningBtn()) {
                        teamtype = "Spinning";
                    }
                    if (event.getSource() == content.getRegisterView().getNewHitBtn()) {
                        teamtype = "H.I.T.";
                    }
                    if (event.getSource() == content.getRegisterView().getNewStramopBtn()) {
                        teamtype = "Stram op";
                    }
                }


            User user = new User();
            user.setFname(fName);
            user.setLname(lName);
            user.setEmail(email);
            user.setAddress(address);
            user.setMobilenr(mobileno);
            user.setEducation(education);
            user.setExperience(experience);
            user.setHoursPrWeek(hoursPrWeek);
            user.setPassword(password);
            user.setType(2);
            user.setIsApproved(false);
            user.setTeamtype(teamtype);

            // check if all fields are valid
                            /*if (FieldVerifier.isValidFname(fName)
                                    && FieldVerifier.isValidLname(lName)
                                    && FieldVerifier.isValidEmail(email)
                                    && FieldVerifier.isValidAddress(address)
                                    && FieldVerifier.isValidMobileNo(mobileno)
                                    && FieldVerifier.isValidEducation(education)
                                    && FieldVerifier.isValidExperience(experience)
                                    && FieldVerifier.isValidHoursPrWeek(hoursPrWeek)
                                    && FieldVerifier.isValidPassword(password);
                            /*&& FieldVerifier.isValidTeamtype()*/

            // RPC authenticating user method
            motionCBSTestService.createUser(user, new AsyncCallback<Boolean>() {
                        /*
                         * Handles error from callback function
                         */
                        @Override
                        public void onFailure(Throwable caught) {
                            Window.alert("Something went wrong");

                        }

                        @Override
                        public void onSuccess(Boolean isCreated) {
                          if (!isCreated) {
                              Window.alert("Could not create user");
                          } else {
                              content.getRegisterView().clearTextBoxFields();
                              listProviderUsers.getList().add(user);
                          }
                        }


                    });
        }
    }

    class GoBack implements ClickHandler {
        @Override
        public void onClick(ClickEvent event) {
            content.changeView(content.getLoginView());
        }
    }

//hej
}
