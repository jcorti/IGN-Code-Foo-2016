package com.ign.corti;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import javax.swing.JTextArea;
/**
 * switching Keybord Keycodes 
 * @author corti
 *
 */
public class KeybordSetup implements KeyListener{
	//Instance variables
	private JTextArea ta;

	private boolean shiftDepress = false;
	
	private HashMap<Integer, Character> keyMap;
	private HashMap<Integer, Character> capKeyMap;
	
	public static boolean qwerty = false;
	/**
	 * Constructor
	 * 
	 * @param tArea this allows me to acces the textArea in the other class and change the contents
	 */
	public KeybordSetup(JTextArea tArea)
	{
		ta = tArea; 
		keyMap = new HashMap<>();
		capKeyMap = new HashMap<>(); 
	}
	/**
	 * this will find what key was pressed 
	 */
	public void keyPressed(KeyEvent e) 
	{
		if(!qwerty){
			int kCode = e.getExtendedKeyCode();
			update();
			Character tc;
		//this will check if Caps lock is on
			boolean capIsOn = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
		
			if(kCode == KeyEvent.VK_CAPS_LOCK){
				capIsOn = !capIsOn;
			}
			if(kCode == KeyEvent.VK_SHIFT){
				shiftDepress = true;
			}
			if(capIsOn && !shiftDepress){
				tc = capKeyMap.get(kCode);
			}else{
				tc = keyMap.get(kCode);
			}
			
			if(tc != null){
				writeField(tc);
			}
		}else{
			writeField(e.getKeyChar());
		}
	}
	/**
	 * This will see if the shift key was released 
	 */
 	public void keyReleased(KeyEvent e) 
 	{
 		int kCode = e.getKeyCode();
 		if(kCode == KeyEvent.VK_SHIFT){
			shiftDepress = false;
		}
 	}
 	//this will destroy the key typed since we have a method that will tell the computer what key was pressed 
 	public void keyTyped(KeyEvent e) 
 	{
 			e.consume();	
	}
 	/**
 	 * this will write what we typed on the textArea
 	 * @param wc
 	 */
 	private void writeField(Character wc)
 	{
 		int car = ta.getCaretPosition();
 		ta.insert(Character.toString(wc),car);
 	}
 	/**
 	 * update remaps all of the keys from qwerty to azrty using key codes as the key for our map
 	 */
 	public void update()
	{
 	// row 1

 		keyMap.put(16777394, '`');	   					keyMap.put(KeyEvent.VK_AMPERSAND, '1');
 		keyMap.put(16777449, '2'); 						keyMap.put(KeyEvent.VK_QUOTEDBL, '3');
 		keyMap.put(KeyEvent.VK_QUOTE, '4');  			keyMap.put(KeyEvent.VK_LEFT_PARENTHESIS, '5');
 		keyMap.put(KeyEvent.VK_MINUS, '6');       		keyMap.put(16777440, '7');
 		keyMap.put(KeyEvent.VK_UNDERSCORE, '8');  		keyMap.put(16777415, '9');
 		keyMap.put(16777448, '0');						keyMap.put(KeyEvent.VK_RIGHT_PARENTHESIS, '-');
 		keyMap.put(KeyEvent.VK_EQUALS, '=');

 	// Cap

 		capKeyMap.put(KeyEvent.VK_AMPERSAND, '!');		   capKeyMap.put(16777449, '@');
 		capKeyMap.put(KeyEvent.VK_QUOTEDBL, '#');	 	   capKeyMap.put(KeyEvent.VK_QUOTE, '$');
 		capKeyMap.put(KeyEvent.VK_LEFT_PARENTHESIS, '%');  capKeyMap.put(KeyEvent.VK_MINUS, '^');
 		capKeyMap.put(16777440, '&'); 				       capKeyMap.put(KeyEvent.VK_UNDERSCORE, '*');
 		capKeyMap.put(16777415, '(');		 	 	   	   capKeyMap.put(16777448, ')');
 		capKeyMap.put(KeyEvent.VK_RIGHT_PARENTHESIS, '_'); capKeyMap.put(KeyEvent.VK_EQUALS, '+');
 		capKeyMap.put(16777394, '`');
 	
 	// row 2
 	
 		keyMap.put(KeyEvent.VK_A, 'q');			        keyMap.put(KeyEvent.VK_Z, 'w');
 		keyMap.put(KeyEvent.VK_E, 'e');			        keyMap.put(KeyEvent.VK_R, 'r');
 		keyMap.put(KeyEvent.VK_T, 't');			        keyMap.put(KeyEvent.VK_Y, 'y');
 		keyMap.put(KeyEvent.VK_U, 'u');			        keyMap.put(KeyEvent.VK_I, 'i');
 		keyMap.put(KeyEvent.VK_O, 'o');			        keyMap.put(KeyEvent.VK_P, 'p');
 		keyMap.put(KeyEvent.VK_DEAD_CIRCUMFLEX, '[');   keyMap.put(KeyEvent.VK_DOLLAR, ']');
 		
 	// Cap	
 		capKeyMap.put(KeyEvent.VK_A, 'Q');	 			capKeyMap.put(KeyEvent.VK_Z, 'W');
 		capKeyMap.put(KeyEvent.VK_E, 'E'); 				capKeyMap.put(KeyEvent.VK_R, 'R');
 		capKeyMap.put(KeyEvent.VK_T, 'T');	 			capKeyMap.put(KeyEvent.VK_Y, 'Y');
 		capKeyMap.put(KeyEvent.VK_U, 'U'); 				capKeyMap.put(KeyEvent.VK_I, 'I');
 		capKeyMap.put(KeyEvent.VK_O, 'O'); 				capKeyMap.put(KeyEvent.VK_P, 'P');
 		capKeyMap.put(KeyEvent.VK_DEAD_CIRCUMFLEX, '{');capKeyMap.put(KeyEvent.VK_DOLLAR, '}');
 	
 	// row 3
 		keyMap.put(KeyEvent.VK_Q, 'a'); 				keyMap.put(KeyEvent.VK_S, 's');
 		keyMap.put(KeyEvent.VK_D, 'd');					keyMap.put(KeyEvent.VK_F, 'f');
 		keyMap.put(KeyEvent.VK_G, 'g'); 				keyMap.put(KeyEvent.VK_H, 'h');
 		keyMap.put(KeyEvent.VK_J, 'j');					keyMap.put(KeyEvent.VK_K, 'k');
 		keyMap.put(KeyEvent.VK_L, 'l');					keyMap.put(KeyEvent.VK_M, ';');
 		keyMap.put(16777465, '\'');                	    keyMap.put(KeyEvent.VK_ASTERISK, '\\');

 	// Cap
 		capKeyMap.put(KeyEvent.VK_Q, 'A'); 				capKeyMap.put(KeyEvent.VK_S, 'S');
 		capKeyMap.put(KeyEvent.VK_D, 'D'); 				capKeyMap.put(KeyEvent.VK_F, 'F');
 		capKeyMap.put(KeyEvent.VK_G, 'G'); 				capKeyMap.put(KeyEvent.VK_H, 'H');
 		capKeyMap.put(KeyEvent.VK_J, 'J'); 				capKeyMap.put(KeyEvent.VK_K, 'K');
 		capKeyMap.put(KeyEvent.VK_L, 'L'); 				capKeyMap.put(KeyEvent.VK_M, ':');
 		capKeyMap.put(16777465, '"'); 					capKeyMap.put(KeyEvent.VK_ASTERISK, '|');

 	// row 4
 		keyMap.put(KeyEvent.VK_W, 'z'); 				keyMap.put(KeyEvent.VK_X, 'x');
 		keyMap.put(KeyEvent.VK_C, 'c');					keyMap.put(KeyEvent.VK_V, 'v');
 		keyMap.put(KeyEvent.VK_B, 'b');					keyMap.put(KeyEvent.VK_N, 'n');
 		keyMap.put(KeyEvent.VK_COMMA, 'm');				keyMap.put(KeyEvent.VK_SEMICOLON, ',');
 		keyMap.put(KeyEvent.VK_COLON, '.');				keyMap.put(KeyEvent.VK_EXCLAMATION_MARK, '/');

 	// Cap	
 		capKeyMap.put(KeyEvent.VK_W, 'Z'); 				capKeyMap.put(KeyEvent.VK_X, 'X');
 		capKeyMap.put(KeyEvent.VK_C, 'C'); 				capKeyMap.put(KeyEvent.VK_V, 'V');
 		capKeyMap.put(KeyEvent.VK_B, 'B'); 				capKeyMap.put(KeyEvent.VK_N, 'N');
 		capKeyMap.put(KeyEvent.VK_COMMA, 'M');			capKeyMap.put(KeyEvent.VK_SEMICOLON, '<');
 		capKeyMap.put(KeyEvent.VK_COLON, '>');			capKeyMap.put(KeyEvent.VK_EXCLAMATION_MARK, '?');


 	// space bar
 		keyMap.put(KeyEvent.VK_SPACE, ' ');
	}
}