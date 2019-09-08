package baseController;

import javafx.util.Duration;

public abstract class Config {
    public static final int ANIMATION_DEFAULT_MILLIS = 1000;
    public static final double CELL_SIZE = 50;
    public static final int WINDOW_WIDTH = 1366;
    public static final int WINDOW_HEIGHT = 768;
    public static final int HEAP_DEFAULT_SIZE = 10;
    public static final int SLIDE_WIDTH = 768;
    public static final int SLIDE_HEIGHT = 282;


    public static final int FONT_SIZE = (int) CELL_SIZE * 3 / 8;
    public static final Duration ANIMATION_DURATION = Duration.millis(ANIMATION_DEFAULT_MILLIS);
    public static final double HALF_CELL_SIZE = CELL_SIZE / 2;
    public static final double CELL_SPACE = CELL_SIZE * 5 / 4;
    public static final double HALF_CELL_SPACE = CELL_SPACE / 2;
    public static final double TREE_VERTICAL_OFFSET = CELL_SPACE;
    public static final int RANDOM_RANGE = HEAP_DEFAULT_SIZE * 3 + 1;
}
