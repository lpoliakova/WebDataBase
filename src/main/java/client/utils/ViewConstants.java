package client.utils;

import java.awt.*;

public class ViewConstants {

    public static final int SMALL_WINDOW_WIDTH = 500;
    public static final int SMALL_WINDOW_HEIGHT = 200;

    public static final int STANDARD_WINDOW_WIDTH = 900;
    public static final int STANDARD_WINDOW_HEIGHT = 600;

    public static final int EXCEPTION_WINDOW_WIDTH = 500;
    public static final int EXCEPTION_WINDOW_HEIGHT = 100;

    public static Point getSmallWindowLocation() {
        return getWindowLocation(SMALL_WINDOW_WIDTH, SMALL_WINDOW_HEIGHT);
    }

    public static Point getStandardWindowLocation() {
        return getWindowLocation(STANDARD_WINDOW_WIDTH, STANDARD_WINDOW_HEIGHT);
    }

    public static Point getExceptionWindowLocation() {
        return getWindowLocation(EXCEPTION_WINDOW_WIDTH, EXCEPTION_WINDOW_HEIGHT);
    }

    private static Point getWindowLocation(int width, int height) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dimension.width - width) / 2;
        int y = (dimension.height - height) / 2;
        return new Point(x, y);
    }
}
