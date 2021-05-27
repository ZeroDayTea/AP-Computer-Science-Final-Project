//� A+ Computer Science
//www.apluscompsci.com
//Name - Patrick Dobranowski

import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

//class that controls drawing of and position of each alien in the "DobranowskiAlienHorde" list of all alienships
public class DobranowskiAlien extends DobranowskiMovingThing
{
	private int speed;
	private Image image;

	//constructor methods to create alien
	//constructor methods with fewer parameters will call more specific constructors with hard-coded and set values
	//sample parameters taken from googling and just experimenting around
	public DobranowskiAlien()
	{
		this(50,10,40,40,1);
	}

	public DobranowskiAlien(int x, int y)
	{
		this(x,y,40,40,1);
	}

	public DobranowskiAlien(int x, int y, int s)
	{
		this(x,y,40,40,s/2);
	}

	public DobranowskiAlien(int x, int y, int w, int h, int s)
	{
		super(x, y, w,h);
		speed=s/2;
		//get alienship image for drawing later
		try
		{
			URL url = getClass().getResource("alienship.jpg");
			image = ImageIO.read(url);
		}
		//print out error if file is not found in same directory as the DobranowskiAlien.java file
		catch(Exception e)
		{
			System.out.println(":( alienship image file not found");
		}
	}

	//getter and setter methods for alien properties
	//speed of aliens increases as player progresses through levels
	public void setSpeed(int s)
	{
	   speed=s;
	}

	public int getSpeed()
	{
	   return speed;
	}

	//shift alien X and Y coordinates based on input String specifying which direction to move
   public void move(String direction)
   {
	   if(direction.equals("RIGHT"))
		{
			setX(getX() + speed);
		}
		if(direction.equals("LEFT"))
		{
			setX(getX() - speed);
		}
		if(direction.equals("DOWN"))
		{
			setY(getY() + speed);
		}
	}

	//draw the alien image on the screen and update every frame with new X and Y coordinate position
	public void draw( Graphics window )
	{
   		window.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);
	}

	public String toString()
	{
		return super.toString() +" speed: "+ getSpeed();
	}
}
