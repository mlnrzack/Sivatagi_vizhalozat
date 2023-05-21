package game.IO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class InfoLog
{
	String infoLog = "";
	//Itt minden is kiírásra kerül
	public void WriteInfoLog(String newInfo)
	{
		//TODO
		infoLog += infoLog + "\n";
	}
	
	public void WriteOutInfoLog()
	{
		//itt írjuk ki fájlba a teljes infolog-ot.
		try 
    	{
    		FileOutputStream fos = new FileOutputStream(new File("/infolog.txt"));
    		ObjectOutputStream oos = new ObjectOutputStream(fos);

    		oos.writeObject(infoLog);

    		oos.close();    		
    		fos.close();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
	}
}
