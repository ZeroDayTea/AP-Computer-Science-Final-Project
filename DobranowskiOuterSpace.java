//� A+ Computer Science
//www.apluscompsci.com
//Name - Patrick Dobranowski

import java.awt.*;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import static java.lang.Character.*;

import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class DobranowskiOuterSpace extends Canvas implements KeyListener, Runnable {
	//variables used during runtime to refer to player ship, playe rammo, whether player has fired or not,
	//particular aliens, the game window, the list of aliens currently alive, and the shots currently heading in the direction of the alien horde
	private DobranowskiShip ship;
	private DobranowskiAmmo ammo;
	private boolean fired;
	private boolean alien;
	private boolean game;
	private DobranowskiAlienHorde horde;
	private DobranowskiBullets shots;
	private int alienSpeed;
	private int round;
	private Random randInt;

	private boolean[] keys;
	private BufferedImage back;

	private boolean starsDrawn;

	//sets all initialized variables to the player ship, game window, aliens, and other necessary objects that need to be referred to during runtime
	public DobranowskiOuterSpace() {
		//draw background color and initialize random number generator
		setBackground(Color.black);
		starsDrawn = false;
		randInt = new Random();

		//set value of all variables to their starting values
		keys = new boolean[5];
		ship = new DobranowskiShip(350, 400, 100, 100, 3);
		round = 0;
		alienSpeed = 1;
		alien = true;
		game = true;
		
		ammo = new DobranowskiAmmo((ship.getX() + ship.getWidth() / 2) - 5, ship.getY() - 5, 5);
		horde = new DobranowskiAlienHorde(11);
		shots = new DobranowskiBullets();
		this.addKeyListener(this);
		new Thread(this).start();
		setVisible(true);
	}

	//updates the window and drawn objects every frame
	public void update(Graphics window) {
		paint(window);
	}

	//draws strings, calls object draw methods, and updates position of various elements on the screen
	public void paint(Graphics window) {
		if (alien == true) {
			horde.generateHorde(115, 35, 40, 40, alienSpeed);
			alien = false;
		}
		Graphics2D twoDGraph = (Graphics2D) window;
		if (back == null)
			back = (BufferedImage) (createImage(getWidth(), getHeight()));
		Graphics graphToBack = back.createGraphics();
		graphToBack.setColor(Color.BLUE);
		graphToBack.drawString("StarFighter ", 25, 50);
		graphToBack.setColor(Color.BLACK);
		graphToBack.fillRect(0, 0, 800, 600);

		//draw all the stars on the black background
		//stars are continuously redrawn giving the appearance of movement
		graphToBack.setColor(Color.white);
		for(int i = 0; i < 50; i++)
		{
			int X = randInt.nextInt(800);
			int Y = randInt.nextInt(600);
			graphToBack.fillRect(X, Y, 5, 5);
		}
		graphToBack.setColor(Color.BLACK);
		starsDrawn = true;
		

		//sends player ship the necessary string of movement based off input from keyboard
		if (!horde.endGame(ship) || round == 0) {
			if (keys[0] == true) {
				if (ship.getX() > 0 - (ship.getWidth() / 2) + 2) {
					ship.move("LEFT");
				}
			}
			if (keys[1] == true) {
				if (ship.getX() < 800 - (ship.getWidth() / 2) - 18) {
					ship.move("RIGHT");
				}
			}
			if (keys[2] == true) {
				if (ship.getY() > 0 - 18) {
					ship.move("UP");
				}
			}
			if (keys[3] == true) {
				if (ship.getY() < 600 - ship.getHeight() / 2 - 50) {
					ship.move("DOWN");
				}
			}

			if (keys[4] == true && fired == true) {
				shots.add(new DobranowskiAmmo((ship.getX() + ship.getWidth() / 2) - 5, ship.getY() - 5, 5));
			}

			//calls draw method for list of aliens, ammo currently moving, and the player ship
			shots.drawEmAll(graphToBack);
			shots.moveEmAll();
			horde.drawEmAll(graphToBack);
			ship.draw(graphToBack);
			horde.moveEmAll();
			horde.removeDeadOnes(shots);
			shots.cleanEmUp();

			//if player killed all aliens then progress to next round and make aliens faster
			if (horde.getSize() == 0) 
			{
				alien = true;
				alienSpeed++;
				round++;
			}
		} else {
			//run when game ends, prints "GAME OVER :(" to screen and updates drawn aliens and player with positions at end of game
			graphToBack.setColor(Color.RED);
			graphToBack.setFont(new Font(Font.SANS_SERIF, 250, 250));
			graphToBack.drawString("GAME", 25, 300 - 40);
			graphToBack.drawString("OVER :(", 25, 510 - 40);
			shots.end();
			shots.drawEmAll(graphToBack);
			horde.drawEmAll(graphToBack);
			ship.draw(graphToBack);
		}
		//set of instruction in "Warm-Up" Area of Game
		graphToBack.setFont(new Font(Font.SANS_SERIF, 24, 24));
		graphToBack.setColor(Color.WHITE);
		//warm-up round, aliens don't move and allows player to practice controls and shooting
		if (round == 0) {
			graphToBack.drawString("Warm-Up", 335, 30);
			graphToBack.drawString("bullets = spacebar", 300, 530);
			horde.setScore(0);
			if (horde.endGame(ship)) {
				graphToBack.setFont(new Font(Font.SANS_SERIF, 50, 50));
				graphToBack.drawString("DON'T TOUCH THE ALIENS", 60, 300);
			}
		} else {
			graphToBack.setFont(new Font(Font.SANS_SERIF, 24, 24));
			graphToBack.drawString("SCORE: " + horde.getScore(), 335, 30);
		}
		graphToBack.setFont(new Font(Font.SANS_SERIF, 24, 24));
		graphToBack.drawString("ROUND " + round, 15, 30);
		twoDGraph.drawImage(back, null, 0, 0);
		
		//set fired to false to ensure that hitting the spacebar will not create a constant stream of bullets
		//ensures only one ammo is fired per spacebar hit
		fired = false;
		back = null;

	}

	//on key press, change the array of keys able to be pressed to be different
	//these values are then checked later to create specific change in the game
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			keys[0] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			keys[1] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			keys[2] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			keys[3] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			fired = true;
		}

		repaint();
	}

	//ensure that these values do not stay true forever and change them back after play releases the key
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			keys[0] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			keys[1] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			keys[2] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			keys[3] = false;
		}

		repaint();
	}

	public void keyTyped(KeyEvent e) {
		switch (toUpperCase(e.getKeyChar())) {
		case ' ':
			keys[4] = true;
			break;
		}
	}

	public void run() {
		try {
			while (true) {
				Thread.currentThread().sleep(5);
				repaint();
			}
		} catch (Exception e) {
		}
	}
}
