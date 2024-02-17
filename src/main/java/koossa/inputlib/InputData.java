package koossa.inputlib;

import java.util.HashMap;

import com.koossa.savelib.ISavable;

public class InputData implements ISavable {
	
	private HashMap<String, Integer> keyBindings = new HashMap<String, Integer>(); 
	
	public HashMap<String, Integer> getKeyBindings() {
		return keyBindings;
	}
	
	public void saveOverride() {
		save(true, "inputData.json", "InputData");
	}
	
	public void loadOverride() {
		load(InputData.class, true, "inputData.json", "InputData");
	}

}
