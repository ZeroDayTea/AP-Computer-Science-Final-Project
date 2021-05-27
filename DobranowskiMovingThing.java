//� A+ Computer Science
//www.apluscompsci.com
//Name - Patrick Dobranowski


import java.awt.Color;
import java.awt.Graphics;

//all moving objects such as player, aliens, and bullets inherit from these broader class
public class DobranowskiMovingThing
{
	private int xPos;
	private int yPos;
	private int width;
	private int height;

	public DobranowskiMovingThing()
	{
		xPos = 10;
		yPos = 10;
		width = 10;
		height = 10;
	}

	public DobranowskiMovingThing(int x, int y)
	{
		xPos = x;
		yPos = y;
		width = 10;
		height = 10;
	}

	public DobranowskiMovingThing(int x, int y, int w, int h)
	{
		xPos = x;
		yPos = y;
		width = w;
		height = h;
	}

	//setter methods that change position of given object
	public void setPos( int x, int y)
	{
		xPos = x;
		yPos = y;
	}

	public void setX(int x)
	{
		xPos = x;
	}

	public void setY(int y)
	{
		yPos = y;
	}

	//getter methods that return a given variable in the DobranowskiMovingThing class
	public int getX()
	{
		return xPos;   //finish this method
	}

	public int getY()
	{
		return yPos;  //finish this method
	}

	//more setter methods that change the look and layout of the given moving object
	public void setWidth(int w)
	{
		width = w;
	}

	public void setHeight(int h)
	{
		height = h;
	}

	//more getter methods that return parts of the look and layout of the given moving object
	public int getWidth()
	{
		return width;  //finish this method
	}

	public int getHeight()
	{
		return height;  //finish this method
	}

	public void move(String direction)
	{

	}
	public void draw(Graphics window)
	{

	}

	public String toString()
	{
		return getX() + " " + getY() + " " + getWidth() + " " + getHeight();
	}
}
