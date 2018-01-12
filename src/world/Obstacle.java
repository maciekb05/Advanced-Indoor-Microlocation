package world;

/**
 * Obstacle class represents walls or additional obstacles
 */
public class Obstacle {
    private double layoutX;
    private double layoutY;
    private double width;
    private double height;
    private String fill;

    /**
     * Main constructor with parameters
     * @param layoutX horizontal x position
     * @param layoutY vertical y position
     * @param width width of obstacle
     * @param height height of obstacle
     * @param fill color of representation of obstacle
     */
    public Obstacle(String layoutX, String layoutY, String width, String height, String fill) {
        if(layoutX.equals(""))
            this.layoutX=0;
        else
            this.layoutX = Double.parseDouble(layoutX);
        if(layoutY.equals(""))
            this.layoutY=0;
        else
            this.layoutY = Double.parseDouble(layoutY);

        this.width = Double.parseDouble(width);
        this.height = Double.parseDouble(height);
        this.fill = fill;
    }

    /**
     * Gets x position
     */
    public double getLayoutX() {
        return layoutX;
    }

    /**
     * Gets y position
     */
    public double getLayoutY() {
        return layoutY;
    }

    /**
     * Gets width
     */
    public double getWidth() {
        return width;
    }

    /**
     * Gets height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Gets fill color
     */
    public String getFill() {
        return fill;
    }
}
