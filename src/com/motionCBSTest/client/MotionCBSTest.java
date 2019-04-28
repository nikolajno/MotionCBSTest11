package com.motionCBSTest.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.motionCBSTest.client.logic.MainController;
import com.motionCBSTest.client.rpc.MotionCBSTestService;
import com.motionCBSTest.client.rpc.MotionCBSTestServiceAsync;
import com.motionCBSTest.client.ui.ContentPanel;

public class MotionCBSTest implements EntryPoint {

    // This is the entry point method.
    public void onModuleLoad() {

        // Instantiate the panel
        ContentPanel content = new ContentPanel();

        // Adding the panel to the RootLayoutPanel
        RootLayoutPanel.get().add(content);

        //Instantiate the RPC service
        MotionCBSTestServiceAsync motionCBSTestService = GWT.create(MotionCBSTestService.class);

        //Passes the panel and the RPC service to the MainController
        new MainController(content, motionCBSTestService);
    }
}
