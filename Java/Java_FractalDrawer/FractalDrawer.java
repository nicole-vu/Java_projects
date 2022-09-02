/**
 * Written by Nicole Vu, vu000166 and Gina Yi, yi000058
 * FractalDrawer class draws a fractal of a shape indicated by user input
 */

import java.awt.Color;
import java.lang.Math;
import java.util.Scanner;

public class FractalDrawer {
    // Initialize variables
    private double totalArea = 0;  // member variable for tracking the total area

    Color c1 = new Color(16, 85, 154);
    Color c2 = new Color(219, 76, 119);
    Color c3 = new Color(249, 198, 215);
    Color c4 = new Color(60, 162, 200);
    private Color[] col = {c1, c2, c3, c4};

    // Contructor
    public FractalDrawer() {
    }

    /**
     * drawFractal creates a new Canvas object
     * and determines which shapes to draw a fractal by calling appropriate helper function
     *
     * @param type The type of shape to draw (circle, rectangle or triangle)
     * @return the area of the fractal
     */
    public double drawFractal(String type) {
        Canvas drawing = new Canvas(800, 800);
        if (type.equalsIgnoreCase("circle")) {
            drawCircleFractal(100, (drawing.getWidth() / 2), (drawing.getHeight() / 2), Color.BLUE, drawing, 0);
        } else if (type.equalsIgnoreCase("triangle")) {
            drawTriangleFractal(100, 100, (drawing.getWidth() / 2) - 50, (drawing.getHeight() / 2) + 50, Color.BLUE, drawing, 0);
        } else if (type.equalsIgnoreCase("rectangle")) {
            drawRectangleFractal(100, 100, (drawing.getWidth() / 2) - 50, (drawing.getHeight() / 2) - 50, Color.BLUE, drawing, 0);
        } else {
            System.out.println("Not drawable");
        }
        return totalArea;
    }

    /**
     * drawTriangleFractal draws a triangle fractal using recursive techniques
     *
     * @param width  The width of the triangle
     * @param height The height of the triangle
     * @param x      x coordinate of the bottom left corner of the triangle
     * @param y      y coordinate of the bottom left corner of the triangle
     * @param c      The color of the triangle
     * @param can    The canvas where the triangle is drawn on
     * @param level  The level of current layer
     */
    public void drawTriangleFractal(double width, double height, double x, double y, Color c, Canvas can, int level) {
        Triangle myTriangle = new Triangle(x, y, width, height);
        if (level <= 8) {
            can.drawShape(myTriangle);

            c = col[level % 4]; // iterate though color array
            myTriangle.setColor(c);

            totalArea += myTriangle.calculateArea();
            // System.out.println(totalArea);

            // Recursive calls
            drawTriangleFractal(width / 2, height / 2, x + width, y, c, can, level + 1);
            drawTriangleFractal(width / 2, height / 2, x - (width / 2), y, c, can, level + 1);
            drawTriangleFractal(width / 2, height / 2, x + (width / 4), y - height, c, can, level + 1);
        }

    }

    /**
     * drawCircleFractal draws a circle fractal using recursive techniques
     *
     * @param radius The radius of the circle
     * @param x      x coordinate of the circle's center
     * @param y      y coordinate of the circle's center
     * @param c      The color of the circle
     * @param can    The canvas where the circle is drawn on
     * @param level  The level of current layer
     */
    public void drawCircleFractal(double radius, double x, double y, Color c, Canvas can, int level) {
        Circle myCircle = new Circle(x, y, radius);
        if (level <= 8) {
            can.drawShape(myCircle);

            c = col[level % 4]; // iterate though color array
            myCircle.setColor(c);

            totalArea += myCircle.calculateArea();

            // Recursive calls
            drawCircleFractal(radius / 2, x + (1.5 * radius), y, c, can, level + 1);
            drawCircleFractal(radius / 2, x - (1.5 * radius), y, c, can, level + 1);
            drawCircleFractal(radius / 2, x, y + (1.5 * radius), c, can, level + 1);
            drawCircleFractal(radius / 2, x, y - (1.5 * radius), c, can, level + 1);
        }
    }

    /**
     * drawRectangleFractal draws a rectangle fractal using recursive techniques
     *
     * @param width  The width of the rectangle
     * @param height The height of the rectangle
     * @param x      x coordinate of the upper left corner of the rectangle
     * @param y      y coordinate of the upper left corner of the rectangle
     * @param c      The color of the rectangle
     * @param can    The canvas where the rectangle is drawn on
     * @param level  The level of current layer
     */
    public void drawRectangleFractal(double width, double height, double x, double y, Color c, Canvas can, int level) {
        Rectangle myRectangle = new Rectangle(x, y, width, height);
        if (level <= 8) {
            can.drawShape(myRectangle);

            c = col[level % 4]; // iterate though color array
            myRectangle.setColor(c);

            totalArea += myRectangle.calculateArea();

            // Recursive calls
            drawRectangleFractal(width / 2, height / 2, x + (width), y + (height / 2), c, can, level + 1);
            drawRectangleFractal(width / 2, height / 2, x - (width / 2), y, c, can, level + 1);
            drawRectangleFractal(width / 2, height / 2, x, y + (height), c, can, level + 1);
            drawRectangleFractal(width / 2, height / 2, x + (width / 2), y - (height / 2), c, can, level + 1);
        }
    }

    /**
     * main asks user for shape input, and then draws the corresponding fractal.
     * and prints the area of fractal
     */
    public static void main(String[] args) {
        System.out.println("Enter a shape to create fractal: ");
        Scanner input = new Scanner(System.in);
        String shape = input.next();
        FractalDrawer myFractal = new FractalDrawer();

        // Check if the input is valid
        while (!(shape.equalsIgnoreCase("circle") || shape.equalsIgnoreCase("triangle") ||
                shape.equalsIgnoreCase("rectangle"))) {
            System.out.println("Cannot draw " + shape + ". Please enter circle, triangle, or rectangle.");
            System.out.println("Enter a shape to create fractal: ");
            shape = input.next();
        }
        System.out.println(myFractal.drawFractal(shape));
    }
}
