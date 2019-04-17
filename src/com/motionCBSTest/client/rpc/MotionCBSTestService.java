package com.motionCBSTest.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.motionCBSTest.shared.User;

import java.util.ArrayList;

@RemoteServiceRelativePath("MotionCBSTestService")
public interface MotionCBSTestService extends RemoteService {

    // Sample interface method of remote interface

    User authorizeUser(String mobileNr, String password) throws Exception;

    ArrayList<User> getUsers(int trainerID) throws Exception;

    boolean createUser(User user) throws IllegalArgumentException;

    boolean changeUserInfo(User user) throws IllegalArgumentException;

    ArrayList<User> getUsersFullTime(int trainerID) throws Exception;

    ArrayList<User> getUsersPartTime(int trainerID) throws Exception;


    /**
     * Utility/Convenience class.
     * Use MotionCBSTestService.App.getInstance() to access static instance of MotionCBSTestServiceAsync
     */
    /*public static class App {
        private static MotionCBSTestServiceAsync ourInstance = GWT.create(MotionCBSTestService.class);

        public static synchronized MotionCBSTestServiceAsync getInstance() {
            return ourInstance;
        }
    }*/
}
