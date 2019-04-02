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
        /*final Button button = new Button("Click me");
        final Label label = new Label();

        button.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                if (label.getText().equals("")) {
                    MotionCBSTestService.App.getInstance().getMessage("Hello, World!", new MyAsyncCallback(label));
                } else {
                    label.setText("");
                }
            }
        });

        // Assume that the host HTML has elements defined whose
        // IDs are "slot1", "slot2".  In a real app, you probably would not want
        // to hard-code IDs.  Instead, you could, for example, search for all
        // elements with a particular CSS class and replace them with widgets.
        //
        RootPanel.get("slot1").add(button);
        RootPanel.get("slot2").add(label);
    }

    private static class MyAsyncCallback implements AsyncCallback<String> {
        private Label label;

        public MyAsyncCallback(Label label) {
            this.label = label;
        }

        public void onSuccess(String result) {
            label.getElement().setInnerHTML(result);
        }

        public void onFailure(Throwable throwable) {
            label.setText("Failed to receive answer from server!");
        }
    }
    /*/


        ContentPanel content = new ContentPanel();

        RootLayoutPanel.get().add(content);

        MotionCBSTestServiceAsync motionCBSTestService = GWT.create(MotionCBSTestService.class);

        new MainController(content, motionCBSTestService);

    }


}
