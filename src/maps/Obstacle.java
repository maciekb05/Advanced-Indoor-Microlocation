package maps;


public class Obstacle {
    private double layoutX;
    private double layoutY;
    private double width;
    private double height;
    private int transparency;

    public Obstacle(String layoutX, String layoutY, String width, String height) {
        if(layoutX.equals("") || layoutX==null)
            this.layoutX=0;
        else
            this.layoutX = Double.parseDouble(layoutX);
        if(layoutY.equals("") || layoutY==null)
            this.layoutY=0;
        else
            this.layoutY = Double.parseDouble(layoutY);
        this.width = Double.parseDouble(width);
        this.height = Double.parseDouble(height);

    }


    public double getLayoutX() {
        return layoutX;
    }

    public void setLayoutX(double layoutX) {
        this.layoutX = layoutX;
    }

    public double getLayoutY() {
        return layoutY;
    }
    public void setLayoutY(double layoutY) {
        this.layoutY = layoutY;
    }

    public double getWidth() {
        return width;
    }
    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }

    public int getTransparency() {
        return transparency;
    }
    public void setTransparency(int transparency) {
        this.transparency = transparency;
    }
}
