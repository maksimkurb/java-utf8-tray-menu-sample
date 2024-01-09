package org.example;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.RenderingHints;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class TrayAppSampleUtf implements ActionListener {

    // TrayIcon to be used in system tray
    private TrayIcon trayIcon = null;

    // dimension of the TrayIcon
    private Dimension iconSize = null;

    // popupMenu to be used in connection with the TrayIcon
    private final PopupMenu menu = this.createMenu();


    /**
     * main method
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        // create new TrayIcon if a SystemTray is supported by the OS
        if (SystemTray.isSupported()) {
            new TrayAppSampleUtf();
        }
    }

    /**
     * TimeTray Constructor
     */
    public TrayAppSampleUtf() {
        // retrieve iconSize of SystemTray
        SystemTray systemTray = SystemTray.getSystemTray();
        iconSize = systemTray.getTrayIconSize();

        // create TrayIcon according to iconSize
        trayIcon = new TrayIcon(getTrayImage(), "Пример приложения на UTF", menu);
        try {
            systemTray.add(trayIcon);
        } catch (AWTException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * create the image for the TrayIcon
     *
     * @returns BufferedImage the image for the TrayIcon
     */
    private BufferedImage getTrayImage() {
        BufferedImage image = new BufferedImage(
                this.iconSize.width,
                this.iconSize.height,
                BufferedImage.TYPE_INT_ARGB);

        // draw background image
        Graphics2D g2 = image.createGraphics();
        g2.setColor(Color.CYAN);
        g2.fillRect(0, 0, iconSize.width, iconSize.height);

        // draw number
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.BLACK);

        return image;
    }

    /**
     * ActionListener for the TrayIcon
     *
     * @param ev the event triggering the ActionListener
     */
    public void actionPerformed(ActionEvent ev) {
        // user chose to exit TimeTray
        if (Objects.equals(ev.getActionCommand(), "quit")) {
            System.exit(0);
        }
        if (Objects.equals(ev.getActionCommand(), "settings")) {
            // do nothing
        }
    }

    /**
     * creates a PopUp menu for the TrayIcon
     *
     * @return PopUp menu for the TrayIcon
     */
    private PopupMenu createMenu() {
        PopupMenu menu = new PopupMenu("Пример текста UTF-8");

        // about item
        MenuItem menuSettings = new MenuItem("Настройки");
        menuSettings.setActionCommand("settings");
        menu.add(menuSettings);

        // exit item
        MenuItem menuExit = new MenuItem("Выход");
        menuExit.setActionCommand("quit");
        menu.add(menuExit);

        menu.addActionListener(this);

        return menu;
    }
}