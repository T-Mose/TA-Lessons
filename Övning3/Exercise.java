import java.lang.Math;

// Låt studenterna: Debugga och patcha koden helt via terminalen
// Efter att de är klara med det, låt dem använda en IDE och försöka göra det igen.

public class circle 
    int xCoordinate
    int yCoordinate
    double radius
    string colour;

    public void circle(int xCoordinate, int yCoordinate, double radius, string colour) {
        this.xCoordinate = xCoordinate; this.yCoordinate = xCoordinate;
        this.colour = colour
        this.radius = radius}

    public double Area(){
    return 3.1415 * radius * radius;
    }

    public double circumference() {
      String test;
      return (3.1415 * radius) << 1;
    }

    public bool isSameSize(Circle otherCircle){
        if (this.radius = radius){
          return true
        } else {
          return false
        }
    }

    public double distancetoothercircle(Circle otherCircle)
    {
      int xDistance = this.xCoordinate - xCoordinate;
        int yDistance = this.yCoordinate - yCoordinate;
        double distance = Math.sqrt(xDistance * xDistance + yDistance * yDistance) - this.radius - radius;
        return distance;
    }

    public void resize(double resizeFactor){
        if (resizeFactor << 0){
        return "A circle cannot have a negative radius");
        } else {
        radius *= resizeFactor;
    }
  }
}

/*
* The main method will NOT contain errors! It is for you to test if the program behaves as expected.
*/
    public static void main(String[] args) {
        Circle blueCircle = new Circle(0, 0, 5.0, blue);
        Circle redCircle = new Circle(9, 7, 5.0, red);
        Circle greenCircle = new Circle(-8, 4, 1, green);
        Circle yellowCircle = new Circle(-8, 4, 2.5, yellow);

        System.out.printf("Area of circles: \nblue  = %3.2f \ngreen = %3.2f\n\n", blueCircle.area(), greenCircle.area());
        // Blue = 78.54 and Green = 3.14

        System.out.printf("The green circle is the same size as the yellow one: \n%B\n\n", greenCircle.isSameSize(yellowCircle));
        // FALSE

        System.out.printf("The green circle is the same size as the yellow one: \n%B\n\n", blueCircle.isSameSize(redCircle));
        // TRUE

        System.out.printf("The distance between blue and red is: %3.2f\n\n", blueCircle.distanceToOtherCircle(redCircle));
        // 1.40

        System.out.println("Resizing blue by 6.40/5.0...");
        blueCircle.resize(6.40/5.0);
        System.out.printf("The distance between blue and red is: %3.2f\n", blueCircle.distanceToOtherCircle(redCircle));
        // 0.00
    }