package client;

import client.view.StartFrame;

import java.awt.*;

public class EntryPoint {

    public static void main(String[] args) {
        EventQueue.invokeLater(StartFrame::new);
    }
}
