package constants;

import java.time.Duration;

public class WaitConstants {
	public static final Duration WAIT_FOR_ELEMENT_TO_BE_CLICKABLE = Duration.ofSeconds(10);
	public static final Duration WAIT_FOR_ELEMENT_TO_DISAPPEAR = Duration.ofSeconds(50);
	public static final Duration IMPLICIT_WAIT_DURATION = Duration.ofSeconds(20);
	public static final String ROOT_PATH = System.getProperty("user.dir");
	public static final String TEST_DATA_FILE_PATH = ROOT_PATH+"/src/main/java/testdata/sfdctestdata.properties";
}
