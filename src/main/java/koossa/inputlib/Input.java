package koossa.inputlib;

import java.util.HashMap;

import com.koossa.logger.Log;

public class Input implements Runnable {

	private static boolean running = false;
	private static float updatesPerSecond = 100.000f;
	private static float trueDeltaTime = 0;
	private static InputManager manager = new InputManager();
	private static HashMap<String, InputManager> allManagers = new HashMap<String, InputManager>();

	public static void init(float updatesPerSecond) {
		Input.updatesPerSecond = updatesPerSecond;
		new Thread(new Input(), "inputThread").start();
	}

	public static float stop() {
		running = false;
		return trueDeltaTime;
	}
	
	public static void addKeyPressEvent(int keycode) {
		if (manager == null) return;
		manager.registerKeyPress(keycode);
	}
	
	public static void addKeyReleasedEvent(int keycode) {
		if (manager == null) return;
		manager.registerKeyReleased(keycode);
	}
	
	public static void setKeyBinding(String function, Integer key) {
		if (manager == null) return;
		manager.getData().getKeyBindings().put(function, key);
	}
	
	public static void setMouseBinding(String function, Integer button) {
		if (manager == null) return;
		manager.getData().getMouseBindings().put(function, button);
	}
	
	public static void activateInputManager(String managerId) {
		Log.info(Input.class, "Setting input manager to: " + managerId);
		manager = allManagers.get(managerId);
	}
	
	public static void registerNewInputManger(String managerId) {
		Log.info(Input.class, "Registering new input manager: " + managerId);
		allManagers.put(managerId, new InputManager());
	}
	
	public static void registerInputHandler(String managerId, IInputHandler handler) {
		InputManager m = allManagers.get(managerId);
		if (m != null) {
			m.addInputHandler(handler);
		}
	}

	@Override
	public void run() {
		Log.info(this, "Starting input library.");
		running = true;
//		registerNewInputManger("DEFAULT_INPUT_MANAGER");
//		activateInputManager("DEFAULT_INPUT_MANAGER");
		startLoop();
		dispose();
		Log.info(this, "Input library stopped.");
	}

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

	private void dispose() {
		running = false;
		InputManager.dispose();
	}

}
