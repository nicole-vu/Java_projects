/**
 * Written by Nicole Vu, vu000166 and Gina Yi, yi000058
 * Rectangle class creates rectangular shapes
 */

import java.awt.Color;

public class Rectangle {
    // Initiate variable
    private double xPos, yPos, width, height;
    private Color color;

    // Constructor
    public Rectangle(double x, double y, double w, double h) {
        xPos = x;
        yPos = y;
        width = w;
        height = h;
        color = Color.BLACK;
    }

    // Accessor methods
    public Color getColor() {
        return color;
    }

    public double getXPos() {
        return xPos;
    }

    public double getYPos() {
        return yPos;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void setColor(Color newColor) {
        color = newColor;
    }

    public void setPos(double newX, double newY) {
        xPos = newX;
        yPos = newY;
    }

    public void setWidth(double newWidth) {
        width = newWidth;
    }

    public void setHeight(double newHeight) {
        height = newHeight;
    }

    // Operators
    public double calculatePerimeter() {
        return 2.0 * (width + height);
    }

    public double calculateArea() {
        return width * height;
    }

    public String toString() {
        return "x position: " + xPos + ", y position: " + yPos +
                ", width: " + width + ", height: " + height;
    }

    // Main class for testing purpose only
//    public static void main(String[] args) {
//        Rectangle r1 = new Rectangle(0,0,1,1);
//        System.out.println(r1.toString());
//        System.out.printf("Perimeter: %.2f\n", r1.calculatePerimeter());
//        System.out.printf("Area: %.2f\n", r1.calculateArea());
//    }
}
