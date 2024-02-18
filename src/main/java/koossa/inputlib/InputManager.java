package koossa.inputlib;

import java.util.ArrayList;
import java.util.List;

class InputManager {
	
	private InputData data;
	private List<Integer> keysJustPressed;
	private List<Integer> keysDown;
	private List<Integer> mouseJustPressed;
	private List<Integer> mouseDown;
	
	public InputManager() {
		data = new InputData();
		data = InputData.loadOverride();
		keysJustPressed = new ArrayList<Integer>();
		keysDown = new ArrayList<Integer>();
	}
	
	public InputData getData() {
		return data;
	}

	public void update() {
		
	}

	public void dispose() {
		data.saveOverride();
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
