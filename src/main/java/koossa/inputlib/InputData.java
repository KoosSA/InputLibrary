package koossa.inputlib;

import java.util.HashMap;

import com.koossa.savelib.ISavable;
import com.koossa.savelib.SaveSystem;

/**
 * The Class InputData.
 */
public class InputData implements ISavable {
	
	/** The key bindings. */
	private HashMap<String, Integer> keyBindings = new HashMap<String, Integer>();
	
	/** The mouse bindings. */
	private HashMap<String, Integer> mouseBindings = new HashMap<String, Integer>();
	
	/** The invert mouse Y axis. */
	private boolean invertMouseYAxis = false;
	
	
	/**
	 * Gets the key bindings.
	 *
	 * @return the key bindings
	 */
	public HashMap<String, Integer> getKeyBindings() {
		return keyBindings;
	}
	
	/**
	 * Gets the mouse bindings.
	 *
	 * @return the mouse bindings
	 */
	public HashMap<String, Integer> getMouseBindings() {
		return mouseBindings;
	}
	
	/**
	 * Save override.
	 */
	public void saveOverride() {
		save(true, "inputData.json", "InputData");
	}
	
	/**
	 * Load override.
	 *
	 * @return the input data
	 */
	public static InputData loadOverride() {
		return SaveSystem.load(InputData.class, true, "InputData", "inputData.json");
	}
	
	/**
	 * Sets the invert camera pitch.
	 *
	 * @param invertCameraPitch the new invert camera pitch
	 */
	public void setInvertCameraPitch(boolean invertCameraPitch) {
		this.invertMouseYAxis = invertCameraPitch;
	}
	
	/**
	 * Checks if is invert camera pitch.
	 *
	 * @return true, if is invert camera pitch
	 */
	public boolean isInvertCameraPitch() {
		return invertMouseYAxis;
	}

}
