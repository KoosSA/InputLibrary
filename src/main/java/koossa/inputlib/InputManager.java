package koossa.inputlib;

import java.util.ArrayList;
import java.util.List;

public class InputManager {
	
	private InputData data;
	private List<Integer> keysJustPressed;
	private List<Integer> keysPrevPressed;
	private List<Integer> keysDown;
	private List<Integer> mouseJustPressed;
	private List<Integer> mouseDown;
	private List<IInputHandler> handlers;
	
	
	public InputManager() {
		data = new InputData();
		data = InputData.loadOverride();
		keysJustPressed = new ArrayList<Integer>();
		keysPrevPressed = new ArrayList<Integer>();
		keysDown = new ArrayList<Integer>();
		handlers = new ArrayList<IInputHandler>();
	}
	
	protected InputData getData() {
		return data;
	}

	protected void update() {
		handlers.forEach(h -> h.handleInput(this));
		keysJustPressed.forEach(key -> {
			if (!keysPrevPressed.contains((Integer) key)) {
				keysPrevPressed.add(key);
			}
		});
		keysJustPressed.clear();
	}

	protected void dispose() {
		data.saveOverride();
	}
	
	protected void registerKeyPress(int keycode) {
		if (keysPrevPressed.contains(keycode) && !keysDown.contains(keycode)) {
			keysDown.add(keycode);
			keysPrevPressed.remove((Integer) keycode);
		} else {
			keysJustPressed.add(keycode);
		}
	}
	
	protected void registerKeyReleased(int keycode) {
		keysDown.remove((Integer) keycode);
		keysJustPressed.remove((Integer) keycode);
		keysPrevPressed.remove((Integer) keycode);
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

}
