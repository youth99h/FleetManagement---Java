package com.fleetManagement.util;

import java.awt.*;

//电脑屏幕工具类

public class ScreenUtils {

    private static int width;
    private static int height;

    static {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.width;
        height = screenSize.height;
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public static int getHalfOfWidth() {
        return width / 2;
    }

    public static int getHalfOfHeight() {
        return height / 2;
    }

    /**
     * 界面间距 用于界面基于屏幕水平居中
     *
     * @param w
     * @return
     */
    public static int getMarginWidth(int w) {
        return (width - w) / 2;
    }

     /**
     * 界面间距 用于界面基于屏幕垂直居中
     *
     * @param h
     * @return
     */
    public static int getMarginHeight(int h) {
        return (height - h) / 2;
    }

    public static int getMarginX(int w, int cw) {
        return (w - cw) / 2;
    }

    public static int getMarginY(int h, int ch) {
        return (h - ch) / 2;
    }
}
