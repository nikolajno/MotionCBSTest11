package com.motionCBSTest.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.motionCBSTest.client.logic.MainController;
import com.motionCBSTest.client.rpc.MotionCBSTestService;
import com.motionCBSTest.client.rpc.MotionCBSTestServiceAsync;
import com.motionCBSTest.client.ui.ContentPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class MotionCBSTest implements EntryPoint {


    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {

        ContentPanel content = new ContentPanel();

        RootLayoutPanel.get().add(content);

        MotionCBSTestServiceAsync motionCBSTestService = GWT.create(MotionCBSTestService.class);

        new MainController(content, motionCBSTestService);

    }


}
