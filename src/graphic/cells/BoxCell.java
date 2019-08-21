package graphic.cells;

import javafx.animation.FillTransition;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class BoxCell extends StackPane {
    private Rectangle rect;
    private Color color = Color.WHITE;
    private Text text;

    private void initializeCell() {
        text = new Text();
        text.setFont(Font.font(30.0));
        rect = new Rectangle(80.0, 80.0, color);
        rect.setStroke(Color.RED);
        rect.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                highlight();
            }
        });
    }

    public BoxCell() {
        initializeCell();
        super.getChildren().addAll(rect, text);
    }

    public BoxCell(int el) {
        initializeCell();
        this.setValue(el);
        super.getChildren().addAll(rect, text);
    }

    public void setValue(String text) {
        this.text.setText(text);
    }

    public void setValue(Integer value) {
        this.text.setText(value.toString());
    }

    public void setColor(Color color) {
        FillTransition ft = new FillTransition(Duration.millis(1000), rect, this.color, color);
        this.color = color;
        ft.play();
    }

    public void highlight() {
        FillTransition ft = new FillTransition(Duration.millis(1000), rect, this.color, Color.YELLOW);
        ft.setCycleCount(2);
        ft.setAutoReverse(true);
        ft.play();
    }

    public FillTransition getFillTransition(Color color, boolean reverse) {
        if (!reverse)
            return new FillTransition(Duration.millis(1000), rect, this.color, color);
        else
            return new FillTransition(Duration.millis(1000), rect, color, this.color);
    }
}
