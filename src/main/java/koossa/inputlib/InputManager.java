package koossa.inputlib;

import java.util.ArrayList;
import java.util.List;

public class InputManager {

	private static InputData data = InputData.loadOverride();
	private List<Integer> keysJustPressed;
	private List<Integer> keysDown;
	private List<Integer> mouseJustPressed;
	private List<Integer> mouseDown;
	private List<IInputHandler> handlers;
	private float prevMouseX = 0;
	private float currentMouseX = 0;
	private float prevMouseY = 0;
	private float currentMouseY = 0;
	private float deltaMouseX = 0;
	private float deltaMouseY = 0;

	public InputManager() {
		keysJustPressed = new ArrayList<Integer>();
		keysDown = new ArrayList<Integer>();
		handlers = new ArrayList<IInputHandler>();
		mouseDown = new ArrayList<Integer>();
		mouseJustPressed = new ArrayList<Integer>();
	}

	protected InputData getData() {
		return data;
	}

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
	}

	protected static void dispose() {
		data.saveOverride();
	}

	protected void registerKeyPress(int keycode) {
		if (!keysJustPressed.contains(keycode) && !keysDown.contains(keycode)) {
			keysJustPressed.add(keycode);
		}
	}

	protected void registerKeyReleased(int keycode) {
		keysDown.remove((Integer) keycode);
		keysJustPressed.remove((Integer) keycode);
	}
	
	protected void registerMouseButtonPress(int buttonCode) {
		if (!mouseJustPressed.contains(buttonCode) && !mouseDown.contains(buttonCode)) {
			mouseJustPressed.add(buttonCode);
		}
	}
	
	protected void registerMouseButtonRelease(int buttonCode) {
		mouseDown.remove((Integer) buttonCode);
		mouseJustPressed.remove((Integer) buttonCode);
	}
	
	protected void registerMouseMovement(float x, float y) {
		currentMouseX = x;
		currentMouseY = y;
	}

	public boolean isFunctionKeyJustPressed(String function) {
		return keysJustPressed.contains(data.getKeyBindings().get(function));
	}

	public boolean isFunctionKeyDown(String function) {
		return keysDown.contains(data.getKeyBindings().get(function));
	}

	public boolean isFunctionMouseButtonJustPressed(String function) {
		return mouseJustPressed.contains(data.getMouseBindings().get(function));
	}

	public boolean isFunctionMouseButtonDown(String function) {
		return mouseDown.contains(data.getMouseBindings().get(function));
	}

	public void addInputHandler(IInputHandler handler) {
		if (!handlers.contains(handler))
			handlers.add(handler);
	}
	
	public float getDeltaMouseX() {
		return deltaMouseX;
	}
	
	public float getDeltaMouseY() {
		return deltaMouseY;
	}

}
