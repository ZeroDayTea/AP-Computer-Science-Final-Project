//� A+ Computer Science
//www.apluscompsci.com
//Name - Patrick Dobranowski

import javax.swing.JFrame;
import java.util.Random;
import java.awt.*;

public class DobranowskiStarFighter extends JFrame
{
	//width and height in pixels
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;

	public DobranowskiStarFighter()
	{
		super("STARFIGHTER");
		setSize(WIDTH,HEIGHT);

		//initializes main component that will control game functions
		DobranowskiOuterSpace mainGame = new DobranowskiOuterSpace();

		getContentPane().add(mainGame);

		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main( String args[] )
	{
		DobranowskiStarFighter run = new DobranowskiStarFighter();
	}
}
