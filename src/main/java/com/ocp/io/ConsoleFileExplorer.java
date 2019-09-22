package com.ocp.io;

import java.io.Console;

public class ConsoleFileExplorer {
	public static void main(String [] args) {
		Console console = System.console();
		if(console == null) {
			System.out.println("Console is not supported on this machine.");
			System.exit(1);
		}else {
			console.printf("File Explorer v2.0\n");
			console.printf("Please enter your command");
			String input = console.readLine();
			console.printf("You've entered: %s\n", input);
		}
	}
}
