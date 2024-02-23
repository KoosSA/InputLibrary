package koossa.inputlib;

import java.util.HashMap;

import com.koossa.logger.Log;

// TODO: Auto-generated Javadoc
/**
 * Interface with interacting with the input library
 * Methods are static. Call {@link Input#init(float)} to initialize library.
 */
public class Input implements Runnable {

	/** The running. */
	private static boolean running = false;
	
	/** The updates per second. */
	private static float updatesPerSecond = 100.000f;
	
	/** The true delta time. */
	private static float trueDeltaTime = 0;
	
	/** The manager. */
	private static InputManager manager = new InputManager();
	
	/** The all managers. */
	private static HashMap<String, InputManager> allManagers = new HashMap<String, InputManager>();

	/**
	 * Initialises the input library. Only needed to be called once
	 *
	 * @param updatesPerSecond the updates per second
	 */
	public static void init(float updatesPerSecond) {
		Input.updatesPerSecond = updatesPerSecond;
		new Thread(new Input(), "inputThread").start();
	}

	/**
	 * Stops the input library.
	 * @return averageDeltaUpdateTime
	 */
	public static float stop() {
		running = false;
		return trueDeltaTime;
	}
	
	/**
	 * Call once when a keyboard key press is detected.
	 *
	 * @param keycode the keycode
	 */
	public static void addKeyPressEvent(int keycode) {
		if (manager == null) return;
		manager.registerKeyPress(keycode);
	}
	
	/**
	 * Call once when a keyboard key release is detected.
	 *
	 * @param keycode the keycode
	 */
	public static void addKeyReleasedEvent(int keycode) {
		if (manager == null) return;
		manager.registerKeyReleased(keycode);
	}
	
	/**
	 * Adds a new keyboard key binding or updates an existing binding.
	 *
	 * @param function the function
	 * @param key the key
	 */
	public static void setKeyBinding(String function, Integer key) {
		if (manager == null) return;
		manager.getData().getKeyBindings().put(function, key);
	}
	
	/**
	 * Adds a new mouse button binding or updates an existing binding.
	 *
	 * @param function the function
	 * @param button the button
	 */
	public static void setMouseBinding(String function, Integer button) {
		if (manager == null) return;
		manager.getData().getMouseBindings().put(function, button);
	}
	
	/**
	 * Sets the supplied input manager as active.
	 *
	 * @param managerId the manager id
	 */
	public static void activateInputManager(String managerId) {
		Log.debug(Input.class, "Setting input manager to: " + managerId);
		manager = allManagers.get(managerId);
		if (manager == null) Log.error(Input.class, "Input manager does not exist. Register it first: " + managerId);
	}
	
	/**
	 * Registers a new input manager (Creates a new manager).
	 *
	 * @param managerId the manager id
	 */
	public static void registerNewInputManger(String managerId) {
		Log.info(Input.class, "Registering new input manager: " + managerId);
		allManagers.put(managerId, new InputManager());
	}
	
	/**
	 * Registers a new input manager class. The object must implement {@link IInputHandler}
	 *
	 * @param managerId the manager id
	 * @param handler the handler
	 */
	public static void registerInputHandler(String managerId, IInputHandler handler) {
		InputManager m = allManagers.get(managerId);
		if (m != null) {
			m.addInputHandler(handler);
		}
	}
	
	/**
	 * Adds a mouse cursor movement (position update) event.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public static void addMouseMovementEvent(float x, float y) {
		manager.registerMouseMovement(x, y);
	}
	
	/**
	 * Call once to register a mouse button press.
	 *
	 * @param button the button
	 */
	public static void addMouseButtonPressedEvent(int button) {
		if (manager == null) return;
		manager.registerMouseButtonPress(button);
	}
	
	/**
	 * Call once to register a mouse button release.
	 *
	 * @param button the button
	 */
	public static void addMouseButtonReleasedEvent(int button) {
		if (manager == null) return;
		manager.registerMouseButtonRelease(button);
	}

	/**
	 * Run.
	 */
	@Override
	public void run() {
		Log.info(this, "Starting input library with a update freq of: " + updatesPerSecond + " /s.");
		running = true;
		startLoop();
		dispose();
		Log.info(this, "Input library stopped.");
	}

	/**
	 * Start loop.
	 */
	private void startLoop() {
		long prevTime = System.nanoTime();
		float targetTime = 1.000f / updatesPerSecond;
		float deltaTime = 0;
		while (running) {
			deltaTime = (float) (System.nanoTime() - prevTime) / 1000000000.00000f;
			if (deltaTime >= targetTime) {
				trueDeltaTime = deltaTime;
				manager.update();
				prevTime = System.nanoTime();
			} else {
				try {
					Thread.sleep((long) ((targetTime - deltaTime) * 1000));
				} catch (InterruptedException e) {
					Log.error(this, "Thread failed to sleep.");
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Dispose.
	 */
	private void dispose() {
		running = false;
		InputManager.dispose();
	}

	

	

}
