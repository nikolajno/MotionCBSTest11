package com.motionCBSTest.client.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.motionCBSTest.shared.User;
import java.util.ArrayList;

public interface MotionCBSTestServiceAsync {
    void authorizeUser(String mobileNr, String password, AsyncCallback<User> async) throws IllegalArgumentException;

    void getUsers(int trainerID, AsyncCallback<ArrayList<User>> asyncCallback) throws IllegalArgumentException;

    void createUser(User user, AsyncCallback<Boolean> asyncCallback) throws IllegalArgumentException;

    void changeUserInfo(User user, AsyncCallback<Boolean> asyncCallback) throws IllegalArgumentException;

    void getUsersFullTime(int trainerID, AsyncCallback<ArrayList<User>> asyncCallback) throws IllegalArgumentException;

    void getUsersPartTime(int trainerID, AsyncCallback<ArrayList<User>> async);

    void deleteUser(int trainerID, AsyncCallback<Boolean> asyncCallback) throws IllegalArgumentException;
}
