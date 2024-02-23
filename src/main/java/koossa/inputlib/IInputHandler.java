package koossa.inputlib;

/**
 * Implements this interface within classes that should handle input events.
 * Register the handler using {@link Input#registerInputHandler(String, IInputHandler)}
 */
public interface IInputHandler {
	
	/**
	 * All input checks are done in this method
	 * @param input
	 */
	void handleInput(InputManager input);

}
