package koossa.inputlib;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.koossa.filesystem.CommonFolders;
import com.koossa.filesystem.Files;
import com.koossa.filesystem.RootFileLocation;
import com.koossa.logger.Log;

class InputSystemTests {

	@Test
	void updateFrequencyTest1Hz() throws InterruptedException {
		Files.init("Resources", RootFileLocation.LOCAL);
		Log.init(Files.getCommonFolder(CommonFolders.Logs), true);
		Input.init(1.000f);
		Thread.sleep(5000);
		float deltaTime = Input.stop();
		Log.disposeAll();
		assertEquals(1.000f, 1.000f / deltaTime, 0.05f);
	}
	
	@Test
	void updateFrequencyTest10Hz() throws InterruptedException {
		Files.init("Resources", RootFileLocation.LOCAL);
		Log.init(Files.getCommonFolder(CommonFolders.Logs), true);
		Input.init(10.000f);
		Thread.sleep(5000);
		float deltaTime = Input.stop();
		Log.disposeAll();
		assertEquals(10.000f, 1.000f / deltaTime, 0.05f);
	}
	
	@Test
	void updateFrequencyTest75Hz() throws InterruptedException {
		Files.init("Resources", RootFileLocation.LOCAL);
		Log.init(Files.getCommonFolder(CommonFolders.Logs), true);
		Input.init(75.000f);
		Thread.sleep(5000);
		float deltaTime = Input.stop();
		Log.disposeAll();
		assertEquals(75.000f, 1.000f / deltaTime, 0.05f);
	}
	
	@Test
	void updateFrequencyTest135Hz() throws InterruptedException {
		Files.init("Resources", RootFileLocation.LOCAL);
		Log.init(Files.getCommonFolder(CommonFolders.Logs), true);
		Input.init(135.000f);
		Thread.sleep(5000);
		float deltaTime = Input.stop();
		Log.disposeAll();
		assertEquals(135.000f, 1.000f / deltaTime, 0.05f);
	}

}
