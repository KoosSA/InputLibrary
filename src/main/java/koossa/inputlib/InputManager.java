package koossa.inputlib;

class InputManager {
	
	private InputData data;
	
	public InputManager() {
		data = new InputData();
		data.loadOverride();
	}
	
	public InputData getData() {
		return data;
	}

	public void update() {
		
	}

	public void dispose() {
		data.saveOverride();
	}

}
