/**
 * Written by Nicole Vu, vu000166 and Gina Yi, yi000058
 * Circle class creates circular shapes
 */

import java.awt.Color;
import java.lang.Math;

public class Circle {
    // Initiate variables
    private double xPos, yPos, radius;
    private Color color;

    // Constructor
    public Circle(double x, double y, double rad) {
        xPos = x;
        yPos = y;
        radius = rad;
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

    public double getRadius() {
        return radius;
    }

    public void setColor(Color newColor) {
        color = newColor;
    }

    public void setPos(double newX, double newY) {
        xPos = newX;
        yPos = newY;
    }

    public void setRadius(double newRad) {
        radius = newRad;
    }

    // Operators
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }

    public double calculateArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    public String toString() {
        return "x position: " + xPos + ", y position: " + yPos + ", radius: " + radius;
    }

    // Main class for testing purpose only
//    public static void main(String[] args) {
//        Circle c1 = new Circle(0,0,2.5);
//        System.out.println(c1.toString());
//        System.out.printf("Perimeter: %.2f\n", c1.calculatePerimeter());
//        System.out.printf("Area: %.2f\n", c1.calculateArea());
//    }
}
