package client.view;

import client.utils.CommonComponents;
import client.utils.ViewConstants;

import javax.swing.*;
import java.awt.*;

public class ExceptionFrame extends JFrame {

    private static final String DEFAULT_MESSAGE = "Error!";
    private static String message = DEFAULT_MESSAGE;

    public static void showException(String message) {
        if (message == null) {
            ExceptionFrame.message = DEFAULT_MESSAGE;
        } else {
            ExceptionFrame.message = message;
        }
        EventQueue.invokeLater(ExceptionFrame::new);
    }

    private ExceptionFrame() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(ViewConstants.EXCEPTION_WINDOW_WIDTH, ViewConstants.EXCEPTION_WINDOW_HEIGHT);
        setTitle("Application Error");
        setResizable(false);
        setLocation(ViewConstants.getExceptionWindowLocation());

        add(createExceptionLabel(), BorderLayout.CENTER);

        setVisible(true);
    }

    private static JLabel createExceptionLabel() {
        JLabel label = CommonComponents.createLabel(message);
        label.setForeground(Color.red);
        return label;
    }

}
