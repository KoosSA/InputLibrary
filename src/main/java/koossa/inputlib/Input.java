package koossa.inputlib;

import com.koossa.logger.Log;

public class Input implements Runnable {

	private static boolean running = false;
	private static float updatesPerSecond = 100.000f;
	private static float trueDeltaTime = 0;

	public static void init(float updatesPerSecond) {
		Input.updatesPerSecond = updatesPerSecond;
		new Thread(new Input(), "inputThread").start();
	}

	public static float stop() {
		running = false;
		return trueDeltaTime;
	}

	@Override
	public void run() {
		Log.info(this, "Starting input library.");
		running = true;
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
				
				prevTime = System.nanoTime();
			}
		}
	}

	private void dispose() {
		running = false;
	}

}
