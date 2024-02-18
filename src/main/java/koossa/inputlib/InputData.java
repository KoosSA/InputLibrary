package koossa.inputlib;

import java.util.HashMap;

import com.koossa.savelib.ISavable;
import com.koossa.savelib.SaveSystem;

public class InputData implements ISavable {
	
	private HashMap<String, Integer> keyBindings = new HashMap<String, Integer>();
	private HashMap<String, Integer> mouseBindings = new HashMap<String, Integer>();
	private boolean invertCameraPitch = false;
	
	
	
	
	
	public HashMap<String, Integer> getKeyBindings() {
		return keyBindings;
	}
	
	public HashMap<String, Integer> getMouseBindings() {
		return mouseBindings;
	}
	
	public void saveOverride() {
		save(true, "inputData.json", "InputData");
	}
	
	public static InputData loadOverride() {
		return SaveSystem.load(InputData.class, true, "InputData", "inputData.json");
	}
	
	public void setInvertCameraPitch(boolean invertCameraPitch) {
		this.invertCameraPitch = invertCameraPitch;
	}
	
	public boolean isInvertCameraPitch() {
		return invertCameraPitch;
	}

}
