/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.

 * Redistribution of original or derived work requires permission of course staff.
 */
// My 2nd Attempt
package turtle;

import java.util.List;
import java.util.ArrayList;

public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle t, int sideLength) {
    	t.forward(sideLength);
    	t.turn(90);
    	t.forward(sideLength);
    	t.turn(90);
    	t.forward(sideLength);
    	t.turn(90);
    	t.forward(sideLength);
    	t.turn(90);
       // throw new RuntimeException("implement me!");
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
    	double s=(double)180*(sides-2)/sides;
    	return s;
     // throw new RuntimeException("implement me!");
    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
     double S=180-angle;
        double sides=(360/S);
      //  System.out.println(Math.round(sides));
        return (int) Math.round(sides);
        
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
    	
    	double angle=calculateRegularPolygonAngle(sides);
    	
    	turtle.forward(sideLength);
    
    	while(sides!=0) {
    		turtle.turn(180-angle);
    		turtle.forward(sideLength);
    		
    		sides--;
    	}
    	
        
    }

    /**
     * Given the current direction, current location, and a target location, calculate the heading
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentHeading. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentHeading current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to heading (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateHeadingToPoint(double currentHeading, int currentX, int currentY,
                                                 int targetX, int targetY) {
    	double tanvalue;
    	double arctanvalue;
    	double angle = 0;
    	if(currentY == targetY)
    	{
    		if(currentX > targetX)
    			angle = 270;
    		else angle = 90;
    		
    	}
    	else{
    		tanvalue = (double)(currentX - targetX)/(double)(currentY - targetY);
    		arctanvalue = Math.toDegrees(Math.atan(tanvalue));
    		if(currentX > targetX && currentY > targetY)
    		    angle = 180 + arctanvalue;
    		if(currentX <= targetX && currentY > targetY)
    			angle = 180 + arctanvalue;
    		if(currentX > targetX && currentY < targetY)
    			angle = 360 + arctanvalue;
    		if(currentX <= targetX && currentY < targetY)
    			angle = arctanvalue;
    	}
    	angle = angle - currentHeading;
    	return angle <0 ? angle+360 : angle;
    }

    /**
     * Given a sequence of points, calculate the heading adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateHeadingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of heading adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateHeadings(List<Integer> xCoords, List<Integer> yCoords) {
    	int n = xCoords.size();
    	List<Double> Degrees=new ArrayList<>();
    	double angle = calculateHeadingToPoint(0, xCoords.get(0), yCoords.get(0), xCoords.get(1), yCoords.get(1));
    	Degrees.add(angle);
    	double adjustment = 0.0;
    	for(int i = 1; i < n-1; i++)
    	{
    		adjustment = calculateHeadingToPoint(angle, xCoords.get(i), yCoords.get(i), xCoords.get(i+1), yCoords.get(i+1));
    		angle = angle +adjustment;
    		angle = angle > 360 ? angle-360 : angle;
    		Degrees.add(adjustment);
    	}
    	return Degrees;
    }

    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle, int sidelength) {
    	PenColor pencolor = null;
    	PenColor[] arr= {pencolor.CYAN,pencolor.GREEN,pencolor.RED,pencolor.MAGENTA,pencolor.BLACK};
    	PenColor[] arr1= {pencolor.GRAY,pencolor.BLUE,pencolor.PINK,pencolor.MAGENTA,pencolor.GREEN};
    	turtle.turn(10);
    	turtle.color(arr[0]); 
    	turtle.forward(sidelength);
    	for(int i = 1; i < 5; i++)
    	{
    		turtle.turn(144);
            turtle.color(arr[i]);                    
    		turtle.forward(sidelength);
    	}
        int side=5;
        double angles=calculateRegularPolygonAngle(side);
        turtle.turn(angles);
        turtle.color(arr1[0]); 
        turtle.forward(92);
        
        for(int i = 1; i < 5; i++) {
        	turtle.turn(180-angles);
        	turtle.color(arr1[i]);  
        	turtle.forward(92);
        }
        turtle.turn(240);
        turtle.color(pencolor.RED);      
        turtle.forward(100);
        turtle.turn(235);
        turtle.color(pencolor.RED);  
        turtle.forward(95);
        turtle.turn(60);
        turtle.color(pencolor.RED);  
        turtle.forward(100);
        turtle.turn(234);
        turtle.color(pencolor.RED);  
        turtle.forward(105);
        turtle.turn(60);
        turtle.color(pencolor.RED);  
        turtle.forward(100);
        turtle.turn(231);
        turtle.color(pencolor.RED);  
        turtle.forward(113);
        turtle.turn(55);
        turtle.color(pencolor.RED);  
        turtle.forward(100);
        turtle.turn(232);
        turtle.color(pencolor.RED);  
        turtle.forward(113);
        turtle.turn(50);
        turtle.color(pencolor.RED);  
        turtle.forward(110);
        turtle.turn(230);
        turtle.color(pencolor.RED);  
        turtle.forward(113);
        
      
    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();
        
        	
     //   drawSquare(turtle, 150);

        // draw the window
       // turtle.draw();
       
      // drawRegularPolygon(turtle,5,50);				 // 8 for star
        drawPersonalArt(turtle, 150);
 
        
      turtle.draw();
    //    double sides=5.33;
     //   System.out.println(Math.round(sides));
    }

}
