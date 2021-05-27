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

//contains methods for controlling the amount of ammo available to the player
public class DobranowskiBullets {
	//list of all ammo available to the player
	private ArrayList<DobranowskiAmmo> ammo;

	//initializes ammo list with size of 0 initially
	public DobranowskiBullets() {
		ammo = new ArrayList<DobranowskiAmmo>(0);
	}

	//add to list of ammo
	public void add(DobranowskiAmmo a) {
		ammo.add(a);
	}


	public void drawEmAll(Graphics window) {
		if (ammo.size() > 0) {
			for (DobranowskiAmmo a : ammo) {
				a.draw(window);
			}
		}
	}

	public void moveEmAll() {
		if (ammo.size() > 0) {
			for (DobranowskiAmmo a : ammo) {
				a.move("UP");
			}
		}
	}

	//remove used ammo from list by checking whether it is "alive"
	public void cleanEmUp() {
		if (ammo.size() > 0) {
			for (int i = 0; i < ammo.size(); i++) {
				if (!ammo.get(i).isAlive()) {
					ammo.remove(i);
					i = 0;
				}
			}
		}
	}

	//getter methods for specific ammo, the list of ammo, and properties of the ammo list
	public DobranowskiAmmo getAmmo(int pos) {
		return ammo.get(pos);
	}

	public List<DobranowskiAmmo> getList() {
		return ammo;
	}

	public int getSize() {
		return ammo.size();
	}
	
	//at end of game cause all ammo to cease moving
	public void end()
	{
		if (ammo.size() > 0) {
			for (int i = 0; i < ammo.size(); i++) {
				ammo.get(i).setSpeed(0);
			}
		}
	}

	public String toString() {
		return "";
	}
}
