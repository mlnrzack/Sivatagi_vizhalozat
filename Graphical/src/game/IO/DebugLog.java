package game.IO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class DebugLog
{
	String debugLog = "";
	
	//Itt csak a debugoláshoz szükséges infók kerülnek kiírásra
	public void WriteDebugLog(String newDebugInfo)
	{
		debugLog += newDebugInfo + "\n";
	}
	
	public void WriteOutDebugLog()
	{
		//itt írjuk ki fájlba a teljes debuglog-ot.
		try 
    	{
    		FileOutputStream fos = new FileOutputStream(new File("/debuglog.txt"));
    		ObjectOutputStream oos = new ObjectOutputStream(fos);

    		oos.writeObject(debugLog);

    		oos.close();    		
    		fos.close();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
	}
}
