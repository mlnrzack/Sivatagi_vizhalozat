package tests;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import game.Commands;
import game.GameManager;
import game.elements.ActiveElement;
import game.elements.Cistern;
import game.elements.Pipe;
import game.elements.Pump;
import game.elements.WaterSpring;
import game.interfaces.IElement;
import game.players.Mechanic;
import game.players.Player;
import game.players.Saboteur;

public class Test {
	private String Name;
	private ArrayList<String> InputCommands;
	private ArrayList<String> ExpectedOutput;
	private ArrayList<String> Output;
	private static ArrayList<Test> AllTests = new ArrayList<Test>();

	public Test(File testFile) {
		InputCommands = new ArrayList<String>();
		ExpectedOutput = new ArrayList<String>();
		AllTests.add(this);
		
		Name = testFile.getName().substring(0, testFile.getName().lastIndexOf('.'));
		
		BufferedReader reader;

		try 
		{
			reader = new BufferedReader(new FileReader(testFile));
			String line = reader.readLine();

			while (line != null && !line.equals("-- Expected Output --")) 
			{
				InputCommands.add(line);

				line = reader.readLine();
			}
			
			if (line.equals("-- Expected Output --"))
				line = reader.readLine();
			
			while (line != null) 
			{
				ExpectedOutput.add(line);
				line = reader.readLine();				
			}

			reader.close();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		// It has to be in the end of the ctor, as we use the values set by ctor before this.
		GetOutput();
	}
	
	private void GetOutput() {
		// Credits: https://stackoverflow.com/a/8708357
		// Create a stream to hold the output
		ByteArrayOutputStream testOutput = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(testOutput);
		// IMPORTANT: Save the old System.out!
		PrintStream old = System.out;
		System.setOut(ps);

		Execute();
		 
		String output =	testOutput.toString();
		Output = new ArrayList<String>(Arrays.asList(output.split(System.lineSeparator())));		
		
		System.out.flush();
		System.setOut(old);
	}
	
	public void Execute() {
		InputCommands.forEach(input -> Commands.ExecuteCommand(input));
		GameManager.Reset();
	}
	
	public static void Execute(String testName) {		
		Test test = GetTest(testName);
		test.ManageSuccessfulness();
	}
	
	public static void ListAllTests() {
		GetAllTests().forEach(test -> System.out.println(test.Name));
	}
	
	public static Test GetTest(String testName) {
    	for (Test test : Test.GetAllTests()) {
			if (test.Name.equals(testName)) {
				return test;
			}	
		}
    	return null;
	}
	
	public void ManageSuccessfulness() {
		if (IsSuccessful()) {
			System.out.println(Name + " teszt sikeres");
		} else {
			System.out.println(Name + " teszt sikertelen");
			System.out.println("\tElvárt kimenet");
			ExpectedOutput.forEach(line -> System.out.println(line));
			System.out.println("---------------------------------------------------");
			System.out.println("\tValós kimenet");
			Output.forEach(line -> System.out.println(line));
			System.out.println("---------------------------------------------------");
		}
	}
	
	public boolean IsSuccessful() {
		return Output.equals(ExpectedOutput);
	}
	
	public static void ExecuteAllTests() {
		// Ctor will execute tests.
		ArrayList<Test> tests = GetAllTests();
		
		tests.forEach(test -> test.ManageSuccessfulness());
		int successfulTestsCount = 0;
		for (Test t : tests) {
			if (t.IsSuccessful())
				successfulTestsCount++;
		}
		
		System.out.println(successfulTestsCount + "/" + tests.size() + " teszt sikeres");
	}
	
	public static ArrayList<Test> GetAllTests(){
		if (AllTests != null && !AllTests.isEmpty())
			return AllTests;
		
		ArrayList<Test> tests = new ArrayList<Test>();
		File folder = new File(Test.class.getResource("/TestsFolder").getPath());
		
	    for (final File fileEntry : folder.listFiles()) {
	    	String fileEntryName = fileEntry.getName();
	    	String fileEntryType = fileEntryName.substring(fileEntryName.lastIndexOf('.') + 1, fileEntryName.length()).toLowerCase();
	        if (fileEntry.isFile() && fileEntryType.equals("txt")) {
	        	tests.add(new Test(fileEntry));
	        }
	    }
	    
	    return AllTests;
	}
	
	public static void TestMenu() {
		Scanner in = new Scanner(System.in);
		String input;
		
		do {
			Test.ShowMenuText();
			input = in.nextLine();
			String[] inputSplit = input.split(" ");
			switch (inputSplit[0])
	       	{
	       		case "runtest":
	       		{
	       			if (inputSplit.length < 2) {
	       				System.out.println("Adj meg futtatandó tesztet");
	       			} else {
	       				Test test = Test.GetTest(inputSplit[1]);
	       				
		       			if (test == null) {
		       				System.out.println("Adj meg létező futtatandó tesztet");
		       			} else {
		       				test.ManageSuccessfulness();
		       			}
	       			}
	       			
	                break;
	       		}
	       		case "runAllTests":
	       		{
	       			Test.ExecuteAllTests();
	       			
	                break;
	       		}
	            default:
	                break;
	       	}
		}
		while (!input.equals("exit"));
	}
	
	private static void ShowMenuText() {
		System.out.println("--------------------------------");
		System.out.println("Tesztek:");
		Test.ListAllTests();
		System.out.println("--------------------------------");
		System.out.println("Parancsok:");
		System.out.println("\truntest <testName>");
		System.out.println("\trunAllTests");
		System.out.println("\texit");
		System.out.println("--------------------------------");
		System.out.print("Nagyon szépen megkérlek írj be egy parancsot:");
	}
}
