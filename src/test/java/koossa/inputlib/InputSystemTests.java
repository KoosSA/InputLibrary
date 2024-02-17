package koossa.inputlib;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.koossa.filesystem.CommonFolders;
import com.koossa.filesystem.Files;
import com.koossa.filesystem.RootFileLocation;
import com.koossa.logger.Log;
import com.koossa.savelib.SaveSystem;

class InputSystemTests {
	
	@BeforeAll
	static void init() {
		Files.init("Resources", RootFileLocation.LOCAL);
		Log.init(Files.getCommonFolder(CommonFolders.Logs), true);
		SaveSystem.init(Files.getCommonFolder(CommonFolders.Saves), Files.getFolder("Data"));
		System.out.println("Starting tests");
	}
	
	@AfterAll
	static void dispose() {
		Log.disposeAll();
		System.out.println("Stopping tests");
	}

	@Test
	void updateFrequencyTest1Hz() throws InterruptedException {
		Input.init(1.000f);
		Thread.sleep(5000);
		float deltaTime = Input.stop();
		assertEquals(1.000f, 1.000f / deltaTime, 0.1f);
		Thread.sleep(5000);
	}
	
	@Test
	void updateFrequencyTest10Hz() throws InterruptedException {
		Input.init(10.000f);
		Thread.sleep(5000);
		float deltaTime = Input.stop();
		assertEquals(10.000f, 1.000f / deltaTime, 1.0f);
		Thread.sleep(5000);
	}
	
	@Test
	void updateFrequencyTest75Hz() throws InterruptedException {
		Input.init(75.000f);
		Thread.sleep(5000);
		float deltaTime = Input.stop();
		assertEquals(75.000f, 1.000f / deltaTime, 7.5f);
		Thread.sleep(5000);
	}
	
	@Test
	void updateFrequencyTest135Hz() throws InterruptedException {
		Input.init(135.000f);
		Thread.sleep(5000);
		float deltaTime = Input.stop();
		assertEquals(135.000f, 1.000f / deltaTime, 13.5f);
		Thread.sleep(5000);
	}

}
