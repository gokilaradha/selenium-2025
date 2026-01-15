package constants;
import utils.CommonUtils;
public class FileConstants {
	public static final String ROOT_PATH = System.getProperty("user.dir");
	public static final String TEST_DATA_FILE_PATH = ROOT_PATH+"/src/main/java/testdata/sfdctestdata.properties";
	public static final String TEST_DATA_FILE_TO_UPLOAD =ROOT_PATH+"/explaination.txt";
	public static final String PHOTO_UPLOAD_PATH =ROOT_PATH +"/src/main/java/flowers.jpg";
	public static final String REPORT_PATH = 
			System.getProperty("user.dir")+"/src/main/resources/reports/"+CommonUtils.getTimeStamp()+".html";
}

