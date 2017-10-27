package client;

import client.view.StartFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by oradchykova on 10/26/17.
 */
public class EntryPoint {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            StartFrame frame = new StartFrame();
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
