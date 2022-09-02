/**
 * Written by Nicole Vu, vu000166 and Gina Yi, yi000058
 * Triangle class creates triangular shapes
 * Assumes that triangle is isosceles
 */

import java.awt.Color;
import java.lang.Math;

public class Triangle {

    // Initiate variables
    private double xPos, yPos, width, height;
    private Color color;

    // Constructor
    public Triangle(double x, double y, double w, double h) {
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
        return width + 2.0 * Math.sqrt(Math.pow(0.5 * width, 2) + Math.pow(height, 2));
    }

    public double calculateArea() {
        return (0.5) * width * height;
    }

    public String toString() {
        return "x position: " + xPos + ", y position: " + yPos +
                ", width: " + width + ", height: " + height;
    }

    // Main class for testing purpose only
//    public static void main(String[] args) {
//        Triangle t1 = new Triangle(0,0,1,1);
//        System.out.println(t1.toString());
//        System.out.printf("Perimeter: %.2f\n", t1.calculatePerimeter());
//        System.out.printf("Area: %.2f\n", t1.calculateArea());
//    }
}
