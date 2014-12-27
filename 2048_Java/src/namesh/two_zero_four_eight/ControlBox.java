package namesh.two_zero_four_eight;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ControlBox extends JFrame {
	
	/**
	 * 	 @author namesh-kher
	 */
	private static final long serialVersionUID = -2108949871176690655L;
	
	/* Constants */
	public static final int controlBoxPositionX = 250;
	public static final int controlBoxPositionY = 0;
	public static final String gameOverMessage = "Your Game is Over. Click 'New Game' to try again.";
	public static final String gameContinueMessage = "Congratulations. You Win ! Do you want to continue ? ";
	
	/* Instance variables */
	private JLabel headerL;
	private JPanel controlPanel;
	private Dimension dimension;
	
	public ControlBox() {
		this.controlPanel = new JPanel(new FlowLayout());
		//this.setSize(300, 150);
		this.setLayout(new GridLayout(3, 1));
		this.add(controlPanel);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent wEvent) {
				System.exit(0);
			}
		});
		setLocation(controlBoxPositionX, controlBoxPositionX);
		setVisible(true);
	}
	
	/* Routine called when the game ends without a win */
	public void gameOver() {
		setDimension(400, 150);
		setSize(getDimension());
		headerL = new JLabel(gameOverMessage, JLabel.CENTER);
		this.add(headerL);
		JButton newGameButton = new JButton("New Game");
		JButton exitButton = new JButton("Exit");
		
		// Code for New Game Button
		newGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new GameContainer();
			}
		});
		
		// Code for New Game Button
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		// Adding the buttons
		this.controlPanel.add(newGameButton);
		this.controlPanel.add(exitButton);
		setVisible(true);
	}
	
	/* Routine called when the 2048 block is achieved */
	public void winTileAchieved() {
		setDimension(500, 150);
		setSize(getDimension());
		this.headerL = new JLabel(gameContinueMessage, JLabel.CENTER);
		this.add(headerL);
		JButton continueButton = new JButton("Continue");
		JButton newGameButton = new JButton("New Game");
		JButton exitButton = new JButton("Exit");
		
		// Code for Continue Button
		continueButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		
		// Code for New Game Button
		newGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new GameContainer();
			}
		});
		
		// Code for New Game Button
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		// Adding the buttons
		this.controlPanel.add(continueButton);
		this.controlPanel.add(newGameButton);
		this.controlPanel.add(exitButton);
		setVisible(true);
	}
	
	public void setDimension(int x, int y) {
		this.dimension = new Dimension(x, y);
	}
	
	public Dimension getDimension() {
		return this.dimension;
	}
}
