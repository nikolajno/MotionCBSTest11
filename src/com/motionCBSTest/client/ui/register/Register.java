package com.motionCBSTest.client.ui.register;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

public class Register extends Composite {
    interface RegisterUiBinder extends UiBinder<HTMLPanel, Register> {
    }

    private static RegisterUiBinder ourUiBinder = GWT.create(RegisterUiBinder.class);

    public Register() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }
}