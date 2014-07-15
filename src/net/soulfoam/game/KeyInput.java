package net.soulfoam.game;

import java.awt.event.*;

import net.soulfoam.game.entities.Player;


public class KeyInput implements KeyListener, MouseListener,
		MouseMotionListener, MouseWheelListener {

	public Game game;
	public Player player;
	
	public boolean isMouseLeft = false;
	public boolean isMouseRight = false;
	
	public KeyInput(Game game){
		this.game = game;
		
		game.addKeyListener(this);
		game.addMouseListener(this);
		game.addMouseMotionListener(this);
		game.addMouseWheelListener(this);
	}
	
    public class Key {
        private int numTimesPressed = 0;
        private boolean pressed = false;

        public int getNumTimesPressed() {
            return numTimesPressed;
        }

        public boolean isPressed() {
            return pressed;
        }

        public void toggle(boolean isPressed) {
            pressed = isPressed;
            if (isPressed) numTimesPressed++;
        }
    }

    public Key jump = new Key();
    public Key left = new Key();
    public Key right = new Key();
	public Key shoot = new Key();
	public Key invy = new Key();
    public void keyPressed(KeyEvent e) {
        toggleKey(e.getKeyCode(), true);
    }

    public void keyReleased(KeyEvent e) {
        toggleKey(e.getKeyCode(), false);
    }

    public void keyTyped(KeyEvent e) {
    }

    public void toggleKey(int keyCode, boolean isPressed) {
        if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
            jump.toggle(isPressed);
        }

        if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
            left.toggle(isPressed);
        }
        if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
            right.toggle(isPressed);
        }
        
        if (keyCode == KeyEvent.VK_F) {
            invy.toggle(isPressed);
        }
       
        if (keyCode == KeyEvent.VK_SPACE) {
        	shoot.toggle(isPressed);
        }

    }


	public void mouseDragged(MouseEvent e) {
		Game.mse.setLocation(e.getX(), e.getY());
	}

	public void mouseMoved(MouseEvent e) {
		Game.mse.setLocation(e.getX(), e.getY());
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			isMouseLeft = true;
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			isMouseRight = true;
		}

	}

	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			isMouseLeft = false;
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			isMouseRight = false;
		}
	}

	public void mouseWheelMoved(MouseWheelEvent arg0) {

		
	}

}
