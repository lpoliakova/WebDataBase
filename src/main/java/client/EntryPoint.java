package client;

import client.view.StartFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by oradchykova on 10/26/17.
 */
public class EntryPoint {

    public static void main(String[] args) {
        EventQueue.invokeLater(StartFrame::new);
    }
}
