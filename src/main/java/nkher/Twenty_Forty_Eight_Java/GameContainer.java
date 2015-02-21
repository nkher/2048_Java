package nkher.Twenty_Forty_Eight_Java;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class GameContainer extends JFrame implements KeyListener{
	
	/**
	 *	@author namesh-kher 
	 */
	
	private static final long serialVersionUID = 1L;
	
	/* Constants */
	public static final int gameBoxPositionX = 350;
	public static final int gameBoxPositionY = 0;
	public static final int gameBoxXDim = 500;
	public static final int gameBozYDim = 500;
	public static final int cols = 4;
	public static final int rows = 4;
	public static final int min = rows - cols;
	public static final int max = rows - 1;
	
	/* Instance variables */
	private String title;
	private Dimension dimension;
	private GridLayout gridLayout;
	private Random randNuGen = new Random();
	private int prevAddedPos;
	private ControlBox controlBox;
	private boolean gameWon = false;
	
	
	/* Constructor */
	public GameContainer() {
		addKeyListener(this);
		setFocusable(true);
		this.title = "New Game!!";
		
		// Setting the dimensions and positioning the main frame
		dimension = new Dimension(gameBozYDim, gameBozYDim);
		setSize(dimension);
		setTitle(title);
		setName("Main Container");
		setLocation(gameBoxPositionX, gameBoxPositionY);
		
		// Setting the close operation
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Setting the layout
		gridLayout = new GridLayout(rows, cols, 10, 10);
		
		setLayout(gridLayout);

		// Adding the components
		for (int i=0;i<gridLayout.getColumns();i++) {
			for(int j=0;j<gridLayout.getRows();j++) {  
				Block block = new Block();
				add(block);
			}
		}
				
		// Filling two blocks at random
		Block b1 = getBlockRandomly(getRandomNumber(), getRandomNumber());
		b1.setstartingValue();
		Block b2 = getBlockRandomly(getRandomNumber(), getRandomNumber());
		while(b2 == b1) {
			b2 = getBlockRandomly(getRandomNumber(), getRandomNumber());
		}
		b2.setstartingValue();
		setVisible(true);
		
	}
	
	/* Routine to generate a random number between max and min values */
	public int getRandomNumber() {
		return randNuGen.nextInt((max)-(min)+1)+(min);
	}
	
	/* Routine to get a block from the frame by position */
	public Block getBlockRandomly(int x, int y) {
		return (Block) this.getContentPane().getComponent(y * 4 + x);
	}

	/* Routine for Up Arrow Key Press */
	public void moveup() {
		ReturnFields returnFields = new ReturnFields();
		int k, m, i, f;
		for(i=0;i<cols;i++) {
			int top = i;
			k = top + 4; 
			this.prevAddedPos = Integer.MAX_VALUE;
			for(m=k;m<=(top+(3*4));m=m+4) {
				f = m;
				while(f > top) {
					returnFields = shiftingLogic(f, f-4, returnFields);
					if(returnFields.getreturnValue() == 1)
						break;
					else 
						f=f-4;
				}
			}
		}
		if(returnFields.isChange())
			fillRandomFreeBlock();
		check2048Tile();
	}
	
	/* Routine for Down Arrow Key Press */
	public void movedown() {
		ReturnFields returnFields = new ReturnFields();
		int k, m, i, f;
		for(i=12;i<(cols*rows);i++) {
			int bottom = i;
			k = bottom - 4; 
			this.prevAddedPos = Integer.MAX_VALUE;
			for(m=k;m>=(bottom-(3*4));m=m-4) {
				f = m;
				while(f < bottom) {
					returnFields = shiftingLogic(f, f+4, returnFields);
					if(returnFields.getreturnValue() == 1)
						break;
					else 
						f=f+4;
				}
			}
		}
		if(returnFields.isChange())
			fillRandomFreeBlock();
		check2048Tile();
	}

	/* Routine for Right Arrow Key Press */
	public void moveright() {
		ReturnFields returnFields = new ReturnFields();
		int j = 0, k, m, i, f;
		for (i=rows-1;i>=0;i--) {
			int last = j*4+3;
			k = last-1;
			this.prevAddedPos = Integer.MAX_VALUE;
			for(m=k;m>=k-2;m--) {
				f = m;
				while (f < last) {
					returnFields = shiftingLogic(f, f+1, returnFields);
					if(returnFields.getreturnValue() == 1)
						break;
					else 
						f++;
				} // end of while-loop
			} //  end of inner for-loop
			j++;
		} //  end of outer for-loop
		if(returnFields.isChange())
			fillRandomFreeBlock();
		check2048Tile();
	}
	
	/* Routine for Left Arrow Key Press */
	public void moveleft() {
		ReturnFields returnFields = new ReturnFields();
		int j = 0;
		for (int i=rows-1;i>=0;i--) { // 3 2 1 0
			int first = j*4; // 0
			int k = first+1; // 1
			int m;
			this.prevAddedPos = Integer.MAX_VALUE;
			for(m=k;m<=k+2;m++) { // 1
				int f = m;
				while (f > first) {
					returnFields = shiftingLogic(f, f-1, returnFields);
					if(returnFields.getreturnValue() == 1)
						break;
					else 
						f--;
				} // end of while-loop
				//k--;
			} //  end of inner for-loop
			j++;
		} //  end of outer for-loop
		if(returnFields.isChange())
			fillRandomFreeBlock();
		check2048Tile();
	}
	
	/* Routine that runs common logic for shifting blocks within the main game board */
	public ReturnFields shiftingLogic(int x, int y, ReturnFields returnFields) {
		Block curr = (Block) getContentPane().getComponent(x);
		Block prev = (Block) getContentPane().getComponent(y);
		if((curr.getValue() == null && prev.getValue() == null) || (curr.getValue() == null && prev.getValue() != null)) {
			returnFields.setreturnValue(1);
			return returnFields;
		}
		else if (curr.getValue() != null && prev.getValue() == null) {
			this.getContentPane().remove(y);
			this.getContentPane().add(new Block(curr.getValue()), y);
			this.getContentPane().remove(x);
			this.getContentPane().add(new Block(), x);
			returnFields.setisChange(true);
			setVisible(true);
		}
		else if (curr.getValue() != null && prev.getValue() != null) {
			if(Integer.parseInt(curr.getValue()) == Integer.parseInt(prev.getValue()) && (prevAddedPos != y)) {
				this.getContentPane().remove(y);
				int newVal = 2 * Integer.parseInt(curr.getValue());
				this.getContentPane().add(new Block(String.valueOf(newVal)), y);
				this.getContentPane().remove(x);
				this.getContentPane().add(new Block(), x);
				prevAddedPos = y;
				returnFields.setisChange(true);
				setVisible(true);
			}
			returnFields.setreturnValue(1);
			return returnFields;
		}
		returnFields.setreturnValue(0);
		return returnFields;
	}
	
	/* Routine to fill a random free block */
	public void fillRandomFreeBlock() {
		ArrayList<Integer> emptyPositionsList = new ArrayList<Integer>();
		for(int i=0;i<(rows*cols);i++) {
			Block b = (Block) getContentPane().getComponent(i);
			if (b.getValue() == null) 
				emptyPositionsList.add(i);
		}
		
		// Check if there is not free block
		if(emptyPositionsList.isEmpty())
			gameOverWithoutWin();
		
		// get the random number from the array list and display it
		int emptyPos = emptyPositionsList.get(randNuGen.nextInt(emptyPositionsList.size()));
		this.getContentPane().remove(emptyPos);
		this.getContentPane().add(new Block("2"), emptyPos);
		setVisible(true);
	}
	
	/* Routine to check if the 2048 tile has been created */
	public void check2048Tile() {
		for(int i=0;i<(rows*cols);i++) {
			Block b = (Block) getContentPane().getComponent(i);
			if(b.getValue() != null) {
				if (Integer.parseInt(b.getValue()) == 2048 && !gameWon) {
					gameOverWithWin();
					gameWon = true;
					break;
				}
			}
		}
	}
	
	/* Routine when the game ends but is not won */
	public void gameOverWithoutWin() {
		controlBox = new ControlBox();
		setVisible(true);
		controlBox.gameOver();
	}
	
	/* Routine when the game ends and is won */
	public void gameOverWithWin() {
		controlBox = new ControlBox();
		setVisible(true);
		controlBox.winTileAchieved();
	}
	
	/* Routine when a key is typed -- Not in use */
	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("Key Typed !");
	}

	/* Routine when any key is typed. Contains logic for all direction keys */
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			moveright();
		}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			moveleft();
		}
		else if (e.getKeyCode() == KeyEvent.VK_UP) {
			moveup();
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			movedown();
		}
		else if(e.getKeyCode() == KeyEvent.VK_C) {
			gameOverWithoutWin();
		}
		else if(e.getKeyCode() == KeyEvent.VK_B) {
			gameOverWithWin();
		}
	}

	/* Routine when a key is released -- Not in use */
	@Override
	public void keyReleased(KeyEvent e) {
	}
	
	/* Main Function that runs the game - Has to be shifted to another class */
//	public static void main(String[] args) {
//		@SuppressWarnings("unused")
//		GameContainer gameContainer = new GameContainer();
//	}
}
