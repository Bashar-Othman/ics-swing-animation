package com.demo;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Stroke;

/**
 *
 * @author BasharOthman
 */
public class DrawingUtil {

    public static final float[] DASH_SS = {10.0f, 5.0f, 3.0f};
    public static final int WINDOW_WIDTH = 600;
    public static final int WINDOW_HEIGHT = 600;

    public static Stroke generateStroke(final float width) {
        Stroke dashed = new BasicStroke(width, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER, 10.0f, DrawingUtil.DASH_SS, 0.0f);

        return dashed;

    }

    public static Dimension generateDimension(final int WINDOW_WIDTH, final int WINDOW_HEIGHT) {

        return new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
    }

}
