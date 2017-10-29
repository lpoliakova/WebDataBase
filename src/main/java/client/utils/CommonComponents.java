package client.utils;

import javax.swing.*;

/**
 * Created by oradchykova on 10/29/17.
 */
public class CommonComponents {

    public static JLabel createLabel(String text) {
        return new JLabel(convertToProperLabel(text));
    }

    private static String convertToProperLabel(String text) {
        StringBuilder html = new StringBuilder();
        html.append("<html>").append("<div style = \"padding-left: 10px\">");
        String[] lines = text.split(System.lineSeparator());
        for (String line : lines) {
            html.append(line).append("<br>");
        }

        html.append("</div>").append("</html>");
        return html.toString();
    }
}
