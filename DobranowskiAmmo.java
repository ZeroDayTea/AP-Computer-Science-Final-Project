//� A+ Computer Science
//www.apluscompsci.com
//Name - Patrick Dobranowski

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

//Controls movement of ammo after player has fired it and controls the moving graphics of the fired ammo
public class DobranowskiAmmo extends DobranowskiMovingThing 
{
	private int speed;
	private boolean alive = true;

	//constructor methods
	//constructor methods with fewer parameters will call more specific constructors with hard-coded and set values
	public DobranowskiAmmo() {
		this(5, 5, 5);
	}

	public DobranowskiAmmo(int x, int y) {
		this(x, y, 5);
	}

	public DobranowskiAmmo(int x, int y, int s) {
		super(x, y);
		speed = s;
	}

	public void setSpeed(int s) {
		speed = s;
	}

	public int getSpeed() {
		return speed;
	}

	public void draw(Graphics window) {
		window.setColor(Color.yellow);
		window.fillRect(getX(), getY(), 10, 10);
	}

	//receives input about the movement of ammo and adds to the X and Y position of the ammo object accordingly
	//upon being fired each ammo has a movement of "UP"
	public void move(String direction) {
		if (direction.equals("RIGHT")) {
			setX(getX() + speed);
			// setY(getY() + speed);
		}
		if (direction.equals("LEFT")) {
			setX(getX() - speed);
			// setY(getY() + speed);
		}
		if (direction.equals("UP")) {
			// setX(getX() - speed);
			setY(getY() - speed);
		}
		if (direction.equals("DOWN")) {
			// setX(getX() - speed);
			setY(getY() + speed);
		}
	}
	
	//check collision of ammo against aliens to see if player hit an alien
	public boolean didCollide(DobranowskiAlien al) {
		if (getX() + 10 >= al.getX() && getX() <= al.getX() + al.getWidth()
				&& getY() - 10 >= al.getY()
				&& getY() <= al.getY() + al.getHeight()) {
			kill();
			return true;
		} else {
			return false;
		}
	}

	//check if ammo is alive by checking whether it has left the bounds of the screen
	public boolean isAlive() {
		if (getY() < 0) {
			kill();
		}
		return alive;
	}

	//upon leaving the screen or hitting an alien, the "alive" property of the ammo is set to false and it is no longer drawn and its position is no longer updated
	public void kill() {
		alive = false;
	}

	public String toString() {
		return super.toString() + " " + getSpeed();
	}

}
