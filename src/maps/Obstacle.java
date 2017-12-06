package maps;


public class Obstacle {
    private int layoutX;
    private int layoutY;
    private int width;
    private int height;
    private int transparency;

    public Obstacle(String layoutX, String layoutY, String width, String height) {
        this.layoutX = Integer.parseInt(layoutX);
        this.layoutY = Integer.parseInt(layoutY);
        this.width = Integer.parseInt(width);
        this.height = Integer.parseInt(height);

    }


    public int getLayoutX() {
        return layoutX;
    }

    public void setLayoutX(int layoutX) {
        this.layoutX = layoutX;
    }

    public int getLayoutY() {
        return layoutY;
    }
    public void setLayoutY(int layoutY) {
        this.layoutY = layoutY;
    }

    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }

    public int getTransparency() {
        return transparency;
    }
    public void setTransparency(int transparency) {
        this.transparency = transparency;
    }
}
