package koossa.inputlib;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class InputManager.
 */
public class InputManager {

	/** The data. */
	private static InputData data = InputData.loadOverride();
	
	/** The keys just pressed. */
	private List<Integer> keysJustPressed;
	
	/** The keys down. */
	private List<Integer> keysDown;
	
	/** The mouse just pressed. */
	private List<Integer> mouseJustPressed;
	
	/** The mouse down. */
	private List<Integer> mouseDown;
	
	/** The handlers. */
	private List<IInputHandler> handlers;
	
	/** The prev mouse X. */
	private float prevMouseX = 0;
	
	/** The current mouse X. */
	private float currentMouseX = 0;
	
	/** The prev mouse Y. */
	private float prevMouseY = 0;
	
	/** The current mouse Y. */
	private float currentMouseY = 0;
	
	/** The delta mouse X. */
	private float deltaMouseX = 0;
	
	/** The delta mouse Y. */
	private float deltaMouseY = 0;
	
	/**
	 * The scroll X offset.
	 */
	private float scrollXOffset = 0;
	
	/**
	 * The scroll Y offset.
	 */
	private float scrollYOffset = 0;
	
	private List<Integer> charactersPressed;

	/**
	 * Instantiates a new input manager.
	 */
	public InputManager() {
		keysJustPressed = new ArrayList<Integer>();
		keysDown = new ArrayList<Integer>();
		handlers = new ArrayList<IInputHandler>();
		mouseDown = new ArrayList<Integer>();
		mouseJustPressed = new ArrayList<Integer>();
		charactersPressed = new ArrayList<Integer>();
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	protected InputData getData() {
		return data;
	}

	/**
	 * Update.
	 */
	protected void update() {
		deltaMouseX = currentMouseX - prevMouseX;
		deltaMouseY = currentMouseY - prevMouseY;
		prevMouseX = currentMouseX;
		prevMouseY = currentMouseY;
		handlers.forEach(h -> h.handleInput(this));
		keysDown.addAll(keysJustPressed);
		keysJustPressed.clear();
		mouseDown.addAll(mouseJustPressed);
		mouseJustPressed.clear();
		scrollXOffset = 0;
		scrollYOffset = 0;
		charactersPressed.clear();
	}

	/**
	 * Dispose.
	 */
	protected static void dispose() {
		data.saveOverride();
	}

	/**
	 * Register key press.
	 *
	 * @param keycode the keycode
	 */
	protected void registerKeyPress(int keycode) {
		if (!keysJustPressed.contains(keycode) && !keysDown.contains(keycode)) {
			keysJustPressed.add(keycode);
		}
	}

	/**
	 * Register key released.
	 *
	 * @param keycode the keycode
	 */
	protected void registerKeyReleased(int keycode) {
		keysDown.remove((Integer) keycode);
		keysJustPressed.remove((Integer) keycode);
	}
	
	/**
	 * Register mouse button press.
	 *
	 * @param buttonCode the button code
	 */
	protected void registerMouseButtonPress(int buttonCode) {
		if (!mouseJustPressed.contains(buttonCode) && !mouseDown.contains(buttonCode)) {
			mouseJustPressed.add(buttonCode);
		}
	}
	
	/**
	 * Register mouse button release.
	 *
	 * @param buttonCode the button code
	 */
	protected void registerMouseButtonRelease(int buttonCode) {
		mouseDown.remove((Integer) buttonCode);
		mouseJustPressed.remove((Integer) buttonCode);
	}
	
	/**
	 * Register mouse movement.
	 *
	 * @param x the x
	 * @param y the y
	 */
	protected void registerMouseMovement(float x, float y) {
		currentMouseX = x;
		currentMouseY = y;
	}
	
	/**
	 * Registers scroll wheel input.
	 * @param xOffset scroll x offset
	 * @param yOffset scroll y offset
	 */
	public void registerScrollEvent(float xOffset, float yOffset) {
		scrollXOffset = xOffset;
		scrollYOffset = yOffset;
	}

	/**
	 * Registers a character press codepoint
	 * @param codepoint char codepoint
	 */
	public void registerCharPress(int codepoint) {
		charactersPressed.add(codepoint);
	}

	/**
	 * Returns true if the corresponding key was pressed once.
	 *
	 * @param function the function
	 * @return true, if is function key just pressed
	 */
	public boolean isFunctionKeyJustPressed(String function) {
		return keysJustPressed.contains(data.getKeyBindings().get(function));
	}

	/**
	 * Returns true if the corresponding key is being held down.
	 *
	 * @param function the function
	 * @return true, if is function key down
	 */
	public boolean isFunctionKeyDown(String function) {
		return keysDown.contains(data.getKeyBindings().get(function));
	}

	/**
	 * Returns true if the corresponding mouse button was just pressed.
	 *
	 * @param function the function
	 * @return true, if is function mouse button just pressed
	 */
	public boolean isFunctionMouseButtonJustPressed(String function) {
		return mouseJustPressed.contains(data.getMouseBindings().get(function));
	}

	/**
	 * Returns true if the corresponding mouse button is being held down.
	 *
	 * @param function the function
	 * @return true, if is function mouse button down
	 */
	public boolean isFunctionMouseButtonDown(String function) {
		return mouseDown.contains(data.getMouseBindings().get(function));
	}

	/**
	 * Adds the input handler.
	 *
	 * @param handler the handler
	 */
	protected void addInputHandler(IInputHandler handler) {
		if (!handlers.contains(handler))
			handlers.add(handler);
	}
	
	/**
	 * Gets the cursor deltaX movement.
	 *
	 * @return float
	 */
	public float getDeltaMouseX() {
		return deltaMouseX;
	}
	
	/**
	 * Gets the cursor deltaY movement.
	 *
	 * @return the delta mouse Y
	 */
	public float getDeltaMouseY() {
		return deltaMouseY;
	}
	
	/**
	 * Gets the cursor X position
	 * 
	 * @return cursor posX
	 */
	public float getCurrentMouseX() {
		return currentMouseX;
	}
	
	/**
	 * Gets the cursor Y position
	 * 
	 * @return cursor posY
	 */
	public float getCurrentMouseY() {
		return currentMouseY;
	}

	/**
	 * Gets the scroll X offset
	 * @return scroll x offset
	 */
	public float getScrollXOffset() {
		return scrollXOffset;
	}
	
	/**
	 * Gets the scroll Y offset
	 * @return scroll y offset
	 */
	public float getScrollYOffset() {
		return scrollYOffset;
	}

	public List<Integer> getCharactersInput() {
		return charactersPressed;
	}

}
