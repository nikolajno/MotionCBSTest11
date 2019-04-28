package com.motionCBSTest.client.logic;
//Hej Christian :)

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.motionCBSTest.client.rpc.MotionCBSTestServiceAsync;
import com.motionCBSTest.client.ui.ContentPanel;
import com.motionCBSTest.shared.FieldVerifier;
import com.motionCBSTest.shared.User;

public class MainController {
    private ContentPanel content;
    private MotionCBSTestServiceAsync motionCBSTestService;
    private UserController userController;
    private User currentUser;
    private AdminController adminController;

    public MainController(ContentPanel content, MotionCBSTestServiceAsync motionCBSTestService) {
        this.content = content;
        this.motionCBSTestService = motionCBSTestService;

        userController = new UserController(content, motionCBSTestService);

        adminController = new AdminController(content, motionCBSTestService);

        bindHandlers();
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

            if (!FieldVerifier.isValidMobileNo(mobileNr) || !FieldVerifier.isValidPassword(password)) {
                content.getLoginView().getMobilenrTxtBox().setStyleName("textBox-invalidEntry");
                content.getLoginView().getPasswordTxtBox().setStyleName("textBox-invalidEntry");
            } else {
                content.getLoginView().getMobilenrTxtBox().setStyleName("textInputStd");
                content.getLoginView().getPasswordTxtBox().setStyleName("textInputStd");


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
                        if (user.getIsApproved() != true) {
                            Window.alert("User not approved!");
                        } else if (user == null) {
                            Window.alert("Wrong password or mobile number");
                        } else



                            /*
                             * 1) User match in database,
                             * 2) Checks access level Admin != User
                             * 3) Change the view to either admin og user view
                             */

                            if (user.getType() == 1) {
                                adminController.loadUser(user);
                                content.changeView(content.getMainAdminView());
                            } else if (user.getType() == 2) {
                                userController.loadUser(user);
                                content.changeView(content.getMainUserView());
                            }

                        // Clearing the text fields (mobileNr & password) from
                        // the login screen
                        content.getLoginView().clearTextBoxFields();
                    }
                });
            }
        }
    }

    class RegisterBtnClickHandler implements ClickHandler {
        @Override
        public void onClick(ClickEvent event) {
            content.changeView(content.getRegisterView());
        }
    }


    class RegisterClickHandler implements ClickHandler {

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
            String teamtype = null;
            int teamtype_teamID = 0;

            if (content.getRegisterView().getNewCrossfitBtn().getValue() == true) {
                teamtype = "Crossfit";
                teamtype_teamID = 1;
            }
            if (content.getRegisterView().getNewSpinningBtn().getValue() == true) {
                teamtype = "Spinning";
                teamtype_teamID = 3;
            }
            if (content.getRegisterView().getNewHitBtn().getValue() == true) {
                teamtype = "H.I.T.";
                teamtype_teamID = 2;
            }
            if (content.getRegisterView().getNewStramopBtn().getValue() == true) {
                teamtype = "Stram op";
                teamtype_teamID = 4;
            }


            if (!FieldVerifier.isValidFname(fName)){content.getRegisterView().getNewtxtFname().setStyleName("textBox-invalidEntry");}
            else if (!FieldVerifier.isValidLname(lName)){content.getRegisterView().getNewtxtLname().setStyleName("textBox-invalidEntry");}
            else if (!FieldVerifier.isValidEmail(email)){content.getRegisterView().getNewtxtEmail().setStyleName("textBox-invalidEntry");}
            else if (!FieldVerifier.isValidAddress(address)){content.getRegisterView().getNewtxtAddress().setStyleName("textBox-invalidEntry");}
            else if (!FieldVerifier.isValidMobileNo(mobileno)){content.getRegisterView().getNewtxtMobileNo().setStyleName("textBox-invalidEntry");}
            else if (!FieldVerifier.isValidEducation(education)){content.getRegisterView().getNewtxtEducation().setStyleName("textBox-invalidEntry");}
            else if (!FieldVerifier.isValidExperience(experience)){content.getRegisterView().getNewtxtExperience().setStyleName("textBox-invalidEntry");}
            else if (!FieldVerifier.isValidHoursPrWeek(hoursPrWeek)){content.getRegisterView().getNewtxtHoursPrWeek().setStyleName("textBox-invalidEntry");}
            else if (!FieldVerifier.isValidPassword(password)){content.getRegisterView().getNewtxtPassword().setStyleName("textBox-invalidEntry");}
            else {


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
                            user.setTeamtype_teamID(teamtype_teamID);

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
                                        // userController.loadTables();
                                        Window.alert("Du er nu tilføjet, vent på at en administrator godkender dig");
                                    }
                                }


                            });
                        }
                    }
                }

    class GoBack implements ClickHandler {
        @Override
        public void onClick(ClickEvent event) {
            content.changeView(content.getLoginView());
        }
    }
}
