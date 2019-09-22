package com.ocp.io;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class FileExplorer {
    public static void main(String [] args){
      Scanner sc = new Scanner(System.in);
      String input = "";
      FileManager fm = new FileManager();
      UserManager um = new UserManager();
      System.out.println("FileExplorer v0.1");
      System.out.println("Current directory: ");
      fm.pwd();
      do {
        input = sc.next();
        System.out.println("----------------------------");
        switch(input){
          case "mkf":
            input = sc.next();
            fm.mkf(input);
            break;
          case "ls":
            fm.ls();
            break;
          case "rm":
            input = sc.next();
            fm.rm(input);
            break;
          case "exit":
          case "quit":
          case "q":
            System.out.println("Bye!");
            break;
          case "pwd":
            fm.pwd();
            break;
          case "cd":
            fm.cd(sc.next());
            break;
          case "mv":
            fm.mv(sc.next(), sc.next());
            break;
          case "vwb":
        	fm.readUsingByteStream(sc.next());
        	break;
          case "vwbbuf":
        	fm.readUsingByteAndBufferedStream(sc.next());
        	break;
          case "vwc":
        	fm.readUsingCharacterStream(sc.next());
        	break;
          case "vwcbuf":
        	fm.readUsingCharacterAndBufferedStream(sc.next());
        	break;
          case "vwcb":
        	fm.readCharacterUsingStream(sc.next());
        	break;
          case "ub":
        	fm.appendUsingByteStream(sc.next(), sc.next());
        	break;
          case "uc":
        	fm.appendUsingCharacterStream(sc.next(), sc.next());
        	break;
          case "ob":
        	fm.overwriteUsingByteStream(sc.next(), sc.next());
        	break;
          case "oc":
        	fm.overwriteUsingCharacterStream(sc.next(), sc.next());
        	break;
          case "ocb":
        	fm.overwriteCharacterUsingStream(sc.next(), sc.next());
        	break;
          case "opw":
            fm.overwriteWithPrintWriter(sc.next(), sc.next());
            break;
          case "ops":
        	fm.overwriteWithPrintStream(sc.next(), sc.next());
        	break;
          case "cu":
        	um.createUser(sc.next(), sc.next());
        	break;
          case "pu":
        	fm.persistUserObjects(um.users);
        	break;
          case "du":
        	fm.displayUserObjects();
        	break;
          case "help":
        	displayHelp();
        	break;
          default:
            System.out.println("Your command was not recognized.");
            displayHelp();
        }
        System.out.println("----------------------------");
      } while(!input.matches("(exit|quit|q)"));
    }
    
    private static void displayHelp() {
        System.out.println("mkf <newfilename>   -Create a new file");
        System.out.println("ls                  -List Files in a directory");
        System.out.println("rm                  -Remove file. Be carefull!!!");
        System.out.println("pwd                 -Display current directory.");
        System.out.println("cd <directoryname>  -Change directory");
        System.out.println("mv <oldname> <newname>  -Rename file");
        System.out.println("vwb <filename>		-View File using Byte Stream");
        System.out.println("vwbbuf <filename>	-View File using Buffered Byte Stream");
        System.out.println("vwc <filename>		-View File using Character Stream");
        System.out.println("vwcbuf <filename>	-View File using Buffered Byte Stream");
        System.out.println("ub <filename> <text>	-Append text to a file using Byte Stream");
        System.out.println("uc <filename> <text>	-Append text to a file using Character Stream");
        System.out.println("ob <filename> <text>	-Overwrite a file using Byte Stream");
        System.out.println("oc <filename> <text>	-Overwrite a file using Character Stream");
        System.out.println("vwcb <filename		-View File Character using Byte Stream");
        System.out.println("ocb <filename> <text>	-Overwrite a file with Character on Byte Stream");
        System.out.println("opw <filename> <text>	-Overwrite with PrintWriter");
        System.out.println("ops <filename> <text>   -Overwrite with PrintStream");
        System.out.println("cu <name> <email>		-Create new user");
        System.out.println("pu					-Persist users to file");
        System.out.println("du					-Display persisted users.");
        System.out.println("exit|quit|q         -Exit program");
    }
}