import java.lang.Math;


//Class names should be capitalized and needs to match the file name 
public class Circle {
    
    // A specific number that is used multiple times can be implemented as a constant
    private final static double PI = 3.1415;

    //Semi-colons are needed, it's good to specify field accessibility also String should be capitalized 
    private int xCoordinate;
    private int yCoordinate;
    private double radius;
    private String colour;

    //The constructor should not have a return type. Format lines. String should be capitalized
    public Circle(int xCoordinate, int yCoordinate, double radius, String colour) {
        this.xCoordinate = xCoordinate; 
        this.yCoordinate = yCoordinate; //Not xCoordinate!
        this.colour = colour;
        this.radius = radius;
    }

    //Method names should not be capitalized
    public double area(){
        return PI * radius * radius;
    }

    //An attempt to bit shift, which only works for integers
    public double circumference(){
        return PI * radius * 2;
    }

    //Redundant if-statement and checking equality between doubles without tolerance is bad practice. Bool is not a type in java
    public boolean isSameSize(Circle otherCircle){
        double tolerance = 0.0000001;
        return Math.abs(this.radius - otherCircle.radius) < tolerance;
    }

    //Remember to use otherCircle.[field] to access fields. Camelcase!
    public double distanceToOtherCircle(Circle otherCircle) {
        int xDistance = this.xCoordinate - otherCircle.xCoordinate;
        int yDistance = this.yCoordinate - otherCircle.yCoordinate;
        double distance = Math.sqrt(xDistance * xDistance + yDistance * yDistance) - this.radius - otherCircle.radius;
        return Math.max(0, distance); // (Circles that touch could return zero instead of negative values)
    }

    //Can't return without return type. Should print error message
    public void resize(double resizeFactor){
        if (resizeFactor > 0){
            radius *= resizeFactor;
        } else {
            System.out.println( "A circle cannot have a negative radius");
        }
    }



/*
* The main method will NOT contain errors! It is for you to test if the program behaves as expected.
*/
    public static void main(String[] args) {
        Circle blueCircle = new Circle(0, 0, 5.0, "blue");
        Circle redCircle = new Circle(9, 7, 5.0, "red");
        Circle greenCircle = new Circle(-8, 4, 1.0, "green");
        Circle yellowCircle = new Circle(-8, 4, 2.5, "yellow");

        System.out.printf("Area of circles: \nblue  = %3.2f \ngreen = %3.2f\n\n", blueCircle.area(), greenCircle.area());
        // Blue = 78.54 and Green = 3.14

        System.out.printf("The green circle is the same size as the yellow one: \n%B\n\n", greenCircle.isSameSize(yellowCircle));
        // FALSE

        System.out.printf("The blue circle is the same size as the red one: \n%B\n\n", blueCircle.isSameSize(redCircle));
        // TRUE

        System.out.printf("The distance between blue and red is: %3.2f\n\n", blueCircle.distanceToOtherCircle(redCircle));
        // 1.40

        System.out.println("Resizing blue by 6.40/5.0...");
        blueCircle.resize(6.40/5.0);
        System.out.printf("The distance between blue and red is: %3.2f\n", blueCircle.distanceToOtherCircle(redCircle));
        // 0.00
    }
}