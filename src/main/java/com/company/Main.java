package com.company;

import UI.ConsoleUI;
import WEB.JettyServer;

public class Main {

    public static void main(String[] args) throws Exception {

        JettyServer jettyServer =new JettyServer();
        jettyServer.start();

        ConsoleUI.chooseAction();
    }
}
