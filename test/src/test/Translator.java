package test;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTextArea;

/*
 * Translator
 * 
 * Detects key presses and 
 */
public class Translator implements KeyListener {
	
	// Initial hash map size for translation map
	// Map will have around 45 actual items
	private static final int MAP_SIZE = 60;
	
	// Certain non QWERTY characters are represented using their extended key codes
	private static final int EVK_SUP2 = 16777394;       // Superscript 2
	private static final int EVK_EACCENT = 16777449;    // e'
	private static final int EVK_EBACKTICK = 16777448;  // e`
	private static final int EVK_CCEDILLA = 16777415;  // c,
	private static final int EVK_ABACKTICK = 16777440;  // a`
	private static final int EVK_UACCENT = 16777465;    // u'

	// Maps from AZERTY to QWERTY for non-shift and shift
	private Map<Integer, Character> translationMap;
	private Map<Integer, Character> translationMapShift;
	
	// Text area this translator is translating for
	private JTextArea textArea;
	
	// Keyboard states to determine upper/lower case
	private boolean capsLockOn;
	private boolean shiftOn;
	
	/**
	 * Constructor
	 * 
	 * Initialized with text area this is translating for.
	 * Initializes all the translation hash mappings.
	 * 
	 * @param textArea The text area this is translating for.
	 */
	public Translator(JTextArea textArea)
	{
		this.textArea = textArea;

		// Get state of caps lock when translator is initialized
		capsLockOn = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
		
		// Initialize translation map
		translationMap = new HashMap<Integer, Character>(MAP_SIZE);
		translationMapShift = new HashMap<Integer, Character>(MAP_SIZE);

		// Fills the hash maps with actual mappings
		initializeTranslationMap();
	}
	
	/**
	 * keyPressed
	 * 
	 * Handles key pressed events.  Finds translation
	 * for key pressed.  Toggles caps lock and handles
	 * shift down.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		
		// Key codes to determine which key was pressed
		int extKeyCode = e.getExtendedKeyCode();
		
		// Translated character from AZERTY to QWERTY
		Character translatedChar;
		
		// Caps is toggled
		if(extKeyCode == KeyEvent.VK_CAPS_LOCK)
		{
			capsLockOn = !capsLockOn;
			
			// No more processing needed for caps lock
			return;
		}
		// Shift is pressed down
		else if(extKeyCode == KeyEvent.VK_SHIFT)
		{
			shiftOn = true;
			
			// No more processing needed for shift
			return;
		}
		
		// Get the translated character from mapping
		if(isUpperCase())
		{
			translatedChar = translationMapShift.get(extKeyCode);	
		}
		else
		{
			translatedChar = translationMap.get(extKeyCode);	
		}
		
		if(translatedChar != null)
			writeTextArea(translatedChar);
	}

	/**
	 * keyReleased
	 * 
	 * Handles key released events.  Only key to
	 * handle is releasing of shift key.
	 */
	@Override
	public void keyReleased(KeyEvent e) {

		int keyCode = e.getKeyCode();
		
		// Shift is off
		if(keyCode == KeyEvent.VK_SHIFT)
		{
			shiftOn = false;
		}

	}

	/**
	 * keyTyped
	 * 
	 * Handles key typed events.
	 * Consumes all events to prevent typing of
	 * untranslated keys on the text area.
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		e.consume();
	}
	
	/**
	 * isUpperCase
	 * 
	 * Checks state of caps lock and shift.  If only one
	 * of the states is on, then keyboard presses should
	 * be "upper".  If both are off or on, then keyboard
	 * presses should be "lower".
	 * 
	 * @return Return true if keys should be "upper" case, false otherwise
	 */
	private boolean isUpperCase()
	{
		return capsLockOn != shiftOn;
	}
	
	/*
	 * writeTextArea
	 * 
	 * Writes the character writeChar to the text area
	 * this translator is translating for.
	 */
	private void writeTextArea(Character writeChar)
	{
		// Get where the caret is in the text area
		int caretPosition = textArea.getCaretPosition();
		
		// Write character to where the caret is currently
		textArea.insert(Character.toString(writeChar), caretPosition);
	}
	
	/**
	 * initializeTranslationMap
	 * 
	 * Fills the translation maps with actual
	 * mappings from AZERTY key codes to QWERTY characters.
	 */
	private void initializeTranslationMap()
	{

		// Goes row by row on keyboard
		
		// without shift
		// row 1
		translationMap.put(EVK_SUP2, '`');
		translationMap.put(KeyEvent.VK_AMPERSAND, '1');
		translationMap.put(EVK_EACCENT, '2');
		translationMap.put(KeyEvent.VK_QUOTEDBL, '3');
		translationMap.put(KeyEvent.VK_QUOTE, '4');
		translationMap.put(KeyEvent.VK_LEFT_PARENTHESIS, '5');
		translationMap.put(KeyEvent.VK_MINUS, '6');
		translationMap.put(EVK_EBACKTICK, '7');
		translationMap.put(KeyEvent.VK_UNDERSCORE, '8');
		translationMap.put(EVK_CCEDILLA, '9');
		translationMap.put(EVK_ABACKTICK, '0');
		translationMap.put(KeyEvent.VK_RIGHT_PARENTHESIS, '-');
		translationMap.put(KeyEvent.VK_EQUALS, '=');
		
		// row 2
		translationMap.put(KeyEvent.VK_A, 'q');
		translationMap.put(KeyEvent.VK_Z, 'w');
		translationMap.put(KeyEvent.VK_E, 'e');
		translationMap.put(KeyEvent.VK_R, 'r');
		translationMap.put(KeyEvent.VK_T, 't');
		translationMap.put(KeyEvent.VK_Y, 'y');
		translationMap.put(KeyEvent.VK_U, 'u');
		translationMap.put(KeyEvent.VK_I, 'i');
		translationMap.put(KeyEvent.VK_O, 'o');
		translationMap.put(KeyEvent.VK_P, 'p');
		translationMap.put(KeyEvent.VK_DEAD_CIRCUMFLEX, '[');
		translationMap.put(KeyEvent.VK_DOLLAR, ']');
		
		// row 3
		translationMap.put(KeyEvent.VK_Q, 'a');
		translationMap.put(KeyEvent.VK_S, 's');
		translationMap.put(KeyEvent.VK_D, 'd');
		translationMap.put(KeyEvent.VK_F, 'f');
		translationMap.put(KeyEvent.VK_G, 'g');
		translationMap.put(KeyEvent.VK_H, 'h');
		translationMap.put(KeyEvent.VK_J, 'j');
		translationMap.put(KeyEvent.VK_K, 'k');
		translationMap.put(KeyEvent.VK_L, 'l');
		translationMap.put(KeyEvent.VK_M, ';');
		translationMap.put(EVK_UACCENT, '\'');
		translationMap.put(KeyEvent.VK_ASTERISK, '\\');
		
		// row 4
		translationMap.put(KeyEvent.VK_W, 'z');
		translationMap.put(KeyEvent.VK_X, 'x');
		translationMap.put(KeyEvent.VK_C, 'c');
		translationMap.put(KeyEvent.VK_V, 'v');
		translationMap.put(KeyEvent.VK_B, 'b');
		translationMap.put(KeyEvent.VK_N, 'n');
		translationMap.put(KeyEvent.VK_COMMA, 'm');
		translationMap.put(KeyEvent.VK_SEMICOLON, ',');
		translationMap.put(KeyEvent.VK_COLON, '.');
		translationMap.put(KeyEvent.VK_EXCLAMATION_MARK, '/');
		
		// space bar
		translationMap.put(KeyEvent.VK_SPACE, ' ');
		
		// with shift
		// row 1
		translationMapShift.put(EVK_SUP2, '~');
		translationMapShift.put(KeyEvent.VK_AMPERSAND, '!');
		translationMapShift.put(EVK_EACCENT, '@');
		translationMapShift.put(KeyEvent.VK_QUOTEDBL, '#');
		translationMapShift.put(KeyEvent.VK_QUOTE, '$');
		translationMapShift.put(KeyEvent.VK_LEFT_PARENTHESIS, '%');
		translationMapShift.put(KeyEvent.VK_MINUS, '^');
		translationMapShift.put(EVK_EBACKTICK, '&');
		translationMapShift.put(KeyEvent.VK_UNDERSCORE, '*');
		translationMapShift.put(EVK_CCEDILLA, '(');
		translationMapShift.put(EVK_ABACKTICK, ')');
		translationMapShift.put(KeyEvent.VK_RIGHT_PARENTHESIS, '_');
		translationMapShift.put(KeyEvent.VK_EQUALS, '+');
		
		// row 2
		translationMapShift.put(KeyEvent.VK_A, 'Q');
		translationMapShift.put(KeyEvent.VK_Z, 'W');
		translationMapShift.put(KeyEvent.VK_E, 'E');
		translationMapShift.put(KeyEvent.VK_R, 'R');
		translationMapShift.put(KeyEvent.VK_T, 'T');
		translationMapShift.put(KeyEvent.VK_Y, 'Y');
		translationMapShift.put(KeyEvent.VK_U, 'U');
		translationMapShift.put(KeyEvent.VK_I, 'I');
		translationMapShift.put(KeyEvent.VK_O, 'O');
		translationMapShift.put(KeyEvent.VK_P, 'P');
		translationMapShift.put(KeyEvent.VK_DEAD_CIRCUMFLEX, '{');
		translationMapShift.put(KeyEvent.VK_DOLLAR, '}');
		
		// row 3
		translationMapShift.put(KeyEvent.VK_Q, 'A');
		translationMapShift.put(KeyEvent.VK_S, 'S');
		translationMapShift.put(KeyEvent.VK_D, 'D');
		translationMapShift.put(KeyEvent.VK_F, 'F');
		translationMapShift.put(KeyEvent.VK_G, 'G');
		translationMapShift.put(KeyEvent.VK_H, 'H');
		translationMapShift.put(KeyEvent.VK_J, 'J');
		translationMapShift.put(KeyEvent.VK_K, 'K');
		translationMapShift.put(KeyEvent.VK_L, 'L');
		translationMapShift.put(KeyEvent.VK_M, ':');
		translationMapShift.put(EVK_UACCENT, '"');
		translationMapShift.put(KeyEvent.VK_ASTERISK, '|');
		
		// row 4
		translationMapShift.put(KeyEvent.VK_W, 'Z');
		translationMapShift.put(KeyEvent.VK_X, 'X');
		translationMapShift.put(KeyEvent.VK_C, 'C');
		translationMapShift.put(KeyEvent.VK_V, 'V');
		translationMapShift.put(KeyEvent.VK_B, 'B');
		translationMapShift.put(KeyEvent.VK_N, 'N');
		translationMapShift.put(KeyEvent.VK_COMMA, 'M');
		translationMapShift.put(KeyEvent.VK_SEMICOLON, '<');
		translationMapShift.put(KeyEvent.VK_COLON, '>');
		translationMapShift.put(KeyEvent.VK_EXCLAMATION_MARK, '?');
		
		// space bar
		translationMapShift.put(KeyEvent.VK_SPACE, ' ');
	}

}