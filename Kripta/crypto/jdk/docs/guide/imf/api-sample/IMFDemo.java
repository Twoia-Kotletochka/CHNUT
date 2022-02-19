/*
 * @(#)IMFDemo.java	1.2 99/08/02
 * 
 * Copyright 1997-1999 by Sun Microsystems, Inc.,
 * 901 San Antonio Road, Palo Alto, California, 94303, U.S.A.
 * All rights reserved.
 */

import java.applet.Applet;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Locale;

public class IMFDemo extends Applet implements ActionListener {

    public void init() {
        addComponents();
    }
    
    public void start() {
    }
    
    public void stop() {
    }
    
    public void addComponents() {
        setLayout(new GridLayout(4, 1, 10, 10));
        add(new ActiveClient("Lightweight component, active client"));
        add(new LWTextComponent("Lightweight component, passive client", true));
        add(new LWTextComponent("Lightweight component, non-client", false));
        add(new PeeredTextArea());
    }

    public static void main(String argv[]) {
        final IMFDemo applet = new IMFDemo();
        applet.init();
        applet.start();
        makeFrame(applet, "Input Method Framework Demo");
    }

    public static void makeFrame(IMFDemo applet, String title) {
        Frame frame = new Frame(title);
        frame.pack(); // adds peer
        makeMenuBar(frame, applet);
        frame.add("Center", applet);
        frame.setSize(400, 400);
        WindowListener listener = new AppletWindowListener(applet);
        frame.addWindowListener(listener);
        frame.pack();
        frame.show();
    }
    
    private static void makeMenuBar(Frame frame, ActionListener listener) {
        MenuBar menuBar = new MenuBar();
        frame.setMenuBar(menuBar);
        Menu menu;

        menu = new Menu("Locale");
        menuBar.add(menu);
        addMenuItem(menu, "English", listener, "en");
        addMenuItem(menu, "French", listener, "fr");
        addMenuItem(menu, "German", listener, "de");
        addMenuItem(menu, "Japanese", listener, "ja");
        addMenuItem(menu, "Simplified Chinese", listener, "zh_CN");
        addMenuItem(menu, "Traditional Chinese", listener, "zh_TW");

        menu = new Menu("Size");
        menuBar.add(menu);
        addMenuItem(menu, "12", listener, "12");
        addMenuItem(menu, "24", listener, "24");
        addMenuItem(menu, "36", listener, "36");
    }
    
    private static void addMenuItem(Menu menu, String label, ActionListener listener, String command) {
        MenuItem item = new MenuItem(label);
        menu.add(item);
        item.setActionCommand(command);
        item.addActionListener(listener);
    }
    
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        if (Character.isDigit(command.charAt(0))) {
            int size = Integer.parseInt(command);
            for (int i = 0; i < 3; i++) {
                LWTextComponent component = (LWTextComponent) getComponent(i);
                component.setFontSize(size);
                component.invalidateTextLayout();
                component.repaint();
            }
            PeeredTextArea textArea = (PeeredTextArea) getComponent(3);
            textArea.setFontSize(size);
            textArea.repaint();
        } else {
            String language, country;
            if (command.length() == 2) {
                language = command;
                country = "";
            } else {
                language = command.substring(0, 2);
                country = command.substring(3);
            }
            if (!getInputContext().selectInputMethod(new Locale(language, country))) {
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }
}

class AppletWindowListener extends WindowAdapter {

    private Applet applet;

    AppletWindowListener(Applet applet) {
        this.applet = applet;
    }

    public void windowClosing(WindowEvent e) {
        e.getWindow().dispose();
        System.exit(0);
    }
}
