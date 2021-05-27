//� A+ Computer Science
//www.apluscompsci.com
//Name - Patrick Dobranowski

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

import java.util.ArrayList;
import java.util.List;

//class that controlls list of all aliens
public class DobranowskiAlienHorde {
	//list of all aliens
	private List<DobranowskiAlien> aliens;
	//right, left, and down movement of aliens
	private boolean right = true;
	private boolean left = false;
	private boolean down = true;
	//score to be updated as player removes aliens from this list of aliens in DobranowskiAlienHorde
	private int score = 0;
	private int size = 0;

	//initial constructor with size of aliens in horde
	//size of alien horde increases stays constant for now but could increase with every new level
	public DobranowskiAlienHorde(int size) {
		aliens = new ArrayList<DobranowskiAlien>(size);
		this.size = size;
	}

	//add to alien horde and control drawing of alienship image for every alien in the horde
	public void add(DobranowskiAlien al) {
		aliens.add(al);
	}

	public void drawEmAll(Graphics window) {
		if (getSize() > 0) {
			for (DobranowskiAlien a : aliens) {
				a.draw(window);
			}
		}
	}

	//generate horde by creating an alien at given set increments
	public void generateHorde(int x, int y, int w, int h, int s) {
		int xPos = x;
		for (int i = 0; i < size; i++) {
			aliens.add(new DobranowskiAlien(x, y, w, h, s));
			if (x >= 600) {
				x = xPos;
				y = y + 10 + h;
			} else {
				x = x + w + 10;
			}
		}
	}

	//move all aliens to the right at the same rate then move down when reaching end of screen, shift aliens left after they have moved right for one cycle
	//input for what to do with aliens and moves all aliens in a given direction at the same rate
	//input comes from changing the right, left, and down variables
	public void moveEmAll() {
		if (getSize() > 0) {
			if (aliens.get(0).getX() <= 0) {
				right = true;
				left = false;
				down = true;
			} else if (aliens.get(getSize() - 1).getX()
					+ aliens.get(getSize() - 1).getWidth() >= 800 - 18) {
				right = false;
				left = true;
				down = true;
			}
			if (right == true) {
				for (DobranowskiAlien a : aliens) {
					a.move("RIGHT");
				}
			} else {
				for (DobranowskiAlien a : aliens) {
					a.move("LEFT");
				}
			}
			if (down == true) {
				for (DobranowskiAlien a : aliens) {
					a.move("DOWN");
					a.move("DOWN");
					a.move("DOWN");
					a.move("DOWN");
				}
				down = false;
			}
		}

	}

	//remove aliens from list if they have collided with a shot from the player
	//remove these aliens causes them to no longer be drawn and for calculations of movement on them to stop
	public void removeDeadOnes(DobranowskiBullets shots) {
		if (getSize() > 0) {
			for (int i = 0; i < getSize(); i++) {
				if (shots.getSize() > 0) {
					for (int j = 0; j < shots.getSize(); j++) {
						if (shots.getAmmo(j).didCollide(aliens.get(i))) {
							score += 10;
							aliens.remove(i);
							i = 0;
							break;
						}
					}
				}
			}
		}
	}

	//check if game has ended by checking whether player has collided with every alien ship
	//seems pretty inefficient... there must be a better way to check for collision instead of comparing against every other alien
	public boolean endGame(DobranowskiShip playerShip) {
		if (getSize() > 0) {
			for (int i = 0; i < getSize(); i++) {
				if (playerShip.didCollide(aliens.get(i))) {
					return true;
				}
				else if((aliens.get(getSize() - 1).getY()>=600))
				{
					return true;
				}
			}
		}
		return false;
	}

	//default getter and setter methods for various alien horde properties
	public List<DobranowskiAlien> getList() {
		return aliens;
	}

	public int getSize() {
		return aliens.size();
	}

	public int getScore() {
		return score;
	}

	public void setScore(int s) {
		score = s;
	}

	public String toString() {
		return "";
	}
}
