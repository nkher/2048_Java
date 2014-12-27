package namesh.two_zero_four_eight;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/* Class that represents a block or a tile in the main game container */
public class Block extends JPanel {
	
	/**
	 *  @author namesh-kher
	 */
	private static final long serialVersionUID = 8238269760247636958L;
	
	/* Instance variables */
	private Graphics graphics;
	private Color color;
	private boolean occupied;
	private String value;
	private JLabel jLabel;
	private Font font;
	
	/* Default Constructor to initialize a block with no value */
	public Block() {
		occupied = false;
		this.setLayout(new GridBagLayout());
		setPreferredSize(getPreferredSize());
		setBackground(getDefaultColor());
		setVisible(true);
	}
	
	/* Constructor to initialize a block with a new value */
	public Block(String value) {
		occupied = false;
		this.setLayout(new GridBagLayout());
		setPreferredSize(getPreferredSize());
		setBackground(getColorByValue(value));
		this.setValue(value); // Setting the value 
		setVisible(true);
	}
	
	/* Getter for dimension */
	public Dimension getPreferredSize() {
		return new Dimension(100, 100);
	}
	
	/* Getter for default color */
	public Color getDefaultColor() {
		color = Color.yellow;
		return color;
	}
	
	/* Routine to set the background colors for the tiles depending upn their value */
	public Color getColorByValue(String value) {
		if(Integer.parseInt(value) == 2) {
			color = new Color(30,144,255); // dogger-blue
			return color;
		}
		else if(Integer.parseInt(value) == 4) {
			color = new Color(0,206,209); // dark turquoise
			return color;
		}
		else if(Integer.parseInt(value) == 8) {
			color = Color.orange;
			return color;
		}
		else if(Integer.parseInt(value) == 16) {
			color = new Color(173,255,47); // green yellow
			return color;
		}
		else if(Integer.parseInt(value) == 32) {
			color = new Color(218,165,32); // golden rod
			return color;
		}
		else if(Integer.parseInt(value) == 64) {
			color = new Color(255,99,71); // tomato red
			return color;		
		}
		else if(Integer.parseInt(value) == 128) {
			color = new Color(112,128,144); // slate gray
			return color;
		}
		else if(Integer.parseInt(value) == 256) {
			color = new Color(240,248,255); // alice blue
			return color;
		}
		else if(Integer.parseInt(value) == 512) {
			color = new Color(189,183,107); // dark khaki
			return color;		
		}
		else if(Integer.parseInt(value) == 1024) {
			color = new Color(240,230,140); // Khaki
			return color;
		}
		else if(Integer.parseInt(value) == 2048) {
			color = new Color(240,255,255); // azure
			return color;
		}
		else if(Integer.parseInt(value) == 4096) {
			color = new Color(238,232,170); // pale golden rod
			return color;
		}
		else if(Integer.parseInt(value) == 8192) {
			color = new Color(123,104,238); // medium slate blue
			return color;
		}
		return null;
	}
	
	/* Getter for graphics */
	public Graphics getGraphics(Graphics g) {
		graphics = (Graphics) g;
		graphics.fillRect(230,80,10,10);
		return graphics;
	}
	
	/* Setter to set the starting value */
	public void setstartingValue() {
		this.value = "2";
		color = new Color(30,144,255);
		setBackground(getColorByValue(this.value));
		jLabel = new JLabel(this.value);
		jLabel.setFont(setFontDetails());
		jLabel.setForeground(Color.BLACK);
		this.add(jLabel);
	}
	
	/* Checks if the block is not empty */
	public boolean occupied() {
		return occupied == true;
	}
	
	/* Setter for Value */
	public void setValue(String value) {
		this.value = value;
		jLabel = new JLabel(this.value);
		jLabel.setFont(setFontDetails());
		jLabel.setForeground(Color.BLACK);
		this.add(jLabel);
	}
	
	/* Getter for Value */
	public String getValue() {
		return this.value;
	}
	
	/* Sets the font and returns the same object */
	public Font setFontDetails() {
		this.font = new Font("Comic Sans MS", 1, 50);
		return this.font;
	}
}
