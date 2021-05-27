//� A+ Computer Science 
//www.apluscompsci.com
//Name - Patrick Dobranowski

import java.io.*;
import java.util.*;
import static java.lang.System.*;

public class DobranowskiShiptest
{
    //tester class with main function to ensure ships are drawing correctly and able to move properly
    public static void main( String args[] )
    {
        //initializes a test ship
        DobranowskiMovingThing test = new DobranowskiShip();
        System.out.println("Ship 1 " + test);

        //initializes a test ship
        DobranowskiShip test2 = new DobranowskiShip(50,75);
        System.out.println("Ship 2 " + test2);

        //initializes a test ship
        DobranowskiShip test3 = new DobranowskiShip(7,7,6,5,1);
        test3.setX(3);
        test3.setY(5);
        System.out.println("Ship 3 " + test2);
    }
}
