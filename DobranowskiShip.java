//� A+ Computer Science
//www.apluscompsci.com
//Name - Patrick Dobranowski

import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;

public class DobranowskiShip extends DobranowskiMovingThing
{
	private int speed;
	private Image image;

	//all constructors
	//constructors with fewer parameters will simply call full constructors with hard-coded set values
	//sample parameters taken by doing some googling and also just experimenting around
	public DobranowskiShip()
	{
		this(200,200,100,100,5);
	}

	public DobranowskiShip(int x, int y)
	{
	   this(x,y,100,100,5);
	}

	public DobranowskiShip(int x, int y, int s)
	{
		this(x,y,100,100,s);
	}

	public DobranowskiShip(int x, int y, int w, int h, int s)
	{
		super(x, y, w, h);
		speed=s; 
		//get ship image for drawing
		try
		{
			URL url = getClass().getResource("playership.jpg");
			image = ImageIO.read(url);
		}
		//if ship image file is not found
		catch(Exception e)
		{
			System.out.println(":( ship file not found");
		}
	}


	public void setSpeed(int s)
	{
		speed=s;
	}

	public int getSpeed()
	{
	   return speed;
	}

	//subtracts or adds to current X and Y position based on given key input
	//Arrow keys move the player ship up, down, left, and right
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
		if(direction.equals("UP"))
		{
			setY(getY() - speed);
		}
		if(direction.equals("DOWN"))
		{
			setY(getY() + speed);
		}
	}

	//redraws the position of the ship each frame with updated X and Y position
	public void draw( Graphics window )
	{
		window.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);
	}
	
	//check for collision with Alien ship and return true if collided in current frame to end game
	//returns false if no collision was detected
	public boolean didCollide(DobranowskiAlien al) {
		if (getX() + getWidth()-30 >= al.getX() && getX() <= al.getX() + al.getWidth()-30
				&& getY() + getHeight()-30 >= al.getY()
				&& getY() <= al.getY() + al.getHeight()-30) {
			return true;
		} else {
			return false;
		}
	}

	public String toString()
	{
		return super.toString() +" speed: "+ getSpeed();
	}
}
