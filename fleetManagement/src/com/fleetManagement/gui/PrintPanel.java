package com.fleetManagement.gui;

import javax.swing.*;
import java.util.List;

//¥Ú”°

public class PrintPanel {

    public static<T> void print(List<T> objects, JPanel mPanel, JPanel pPanel) {
        JTextArea jTextArea = new JTextArea();
        StringBuilder sb = new StringBuilder();
        for (T object : objects) {
            String s = object.toString();
            sb.append(s).append("\r\n\n");
        }
        jTextArea.setText(sb.toString());
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 0, mPanel.getWidth()-20, mPanel.getHeight()-50);
        scrollPane.setViewportView(jTextArea);
        pPanel.add(scrollPane);
    }
}
