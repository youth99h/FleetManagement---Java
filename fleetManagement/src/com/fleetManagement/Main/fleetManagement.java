package com.fleetManagement.Main;

import com.fleetManagement.gui.Config;
import com.fleetManagement.gui.MainFrame;

public class fleetManagement {

	public static void main(String[] args) {
        Config.setWidth(900);
        Config.setHeight(600);
        MainFrame loginMain = new MainFrame(Config.getWidth(), Config.getHeight());
        loginMain.doShow();
    }

}
