package com.ocp.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Map.Entry;
import java.io.FileOutputStream;

public class FileManager {

	private File file;

	public FileManager() {
		file = new File("").getAbsoluteFile();
	}

	public FileManager(String path) {
		file = new File(path).getAbsoluteFile();
	}

	public void mkf(String fileName) {
		File nf = new File(file, fileName);
		try {
			nf.createNewFile();
			if (nf.exists())
				System.out.println(fileName + " was created. " + nf.lastModified());
			else
				System.out.println(fileName + " creation failed.");
		} catch (IOException e) {
			System.out.println(fileName + " creation failed.");
		}
	}

	public void ls() {
		for (String s : file.list())
			System.out.println(s);
	}

	public void rm(String fileName) {
		File fd = new File(file, fileName);
		if (fd.exists()) {
			fd.delete();
			System.out.println(fileName + " has been deleted.");
		}
	}

	public void cd(String path) {
		if (path.matches("\\w\\:.*"))
			file = new File(path);
		else if ("..".equals(path))
			file = file.getParentFile();
		else
			file = new File(file, path);
		pwd();
	}

	public void pwd() {
		System.out.println(file.getAbsolutePath());
	}

	public void mv(String oldName, String newName) {
		File nf = new File(file, oldName);
		nf.renameTo(new File(newName));
		pwd();
	}
	
	public void readUsingByteStream(String fileName) {
		try(FileInputStream fis = new FileInputStream(new File(file, fileName))){
			StringBuilder sb = new StringBuilder();
			if(fis.markSupported()) {
				fis.mark(2);
				sb.append((char)fis.read());
				sb.append((char)fis.read());
				fis.reset();
				sb.append((char)fis.read());
				sb.append((char)fis.read());
			}else {
				while(fis.available() > 0) {
					sb.append((char)fis.read());
				}
			}
			System.out.println(sb.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void readUsingByteAndBufferedStream(String fileName) {
		File nf = new File(file, fileName);
		try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(nf))){
			StringBuilder sb = new StringBuilder();
			while(bis.available() > 0)
				sb.append((char)bis.read());
			System.out.println(sb.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void appendUsingByteStream(String fileName, String text) {
		File nf = new File(file, fileName);
		System.out.println(fileName + " updated on " + nf.lastModified() + ". File Size: " + nf.length());
		try(FileOutputStream fos = new FileOutputStream(nf, true)){
			fos.write(text.getBytes());
			System.out.println(fileName + " updated on " + nf.lastModified() + ". File Size: " + nf.length());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void readUsingCharacterStream(String fileName) {
		try(FileReader fr = new FileReader(new File(file, fileName))){
			StringBuilder sb = new StringBuilder();
			int c;
			fr.skip(2);
			while((c = fr.read()) > -1)
				sb.append((char)c);
			System.out.println(sb.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void readUsingCharacterAndBufferedStream(String fileName) {
		try(BufferedReader br = new BufferedReader(new FileReader(new File(file, fileName)))){
			String line = null;
			while((line = br.readLine()) != null)
				System.out.println(line);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void appendUsingCharacterStream(String fileName, String text) {
		File nf = new File(file, fileName);
		System.out.println(fileName + " updated on " + nf.lastModified() + ". File Size: " + nf.length());
		try(FileWriter fw = new FileWriter(nf, true)){
			fw.write(text);
			System.out.println(fileName + " updated on " + nf.lastModified() + ". File Size: " + nf.length());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void overwriteUsingByteStream(String fileName, String text) {
		File nf = new File(file, fileName);
		System.out.println(fileName + " updated on " + nf.lastModified() + ". File Size: " + nf.length());
		try(FileOutputStream fos = new FileOutputStream(nf, false)){
			fos.write(text.getBytes());
			System.out.println(fileName + " updated on " + nf.lastModified() + ". File Size: " + nf.length());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void overwriteUsingCharacterStream(String fileName, String text) {
		File nf = new File(file, fileName);
		System.out.println(fileName + " updated on " + nf.lastModified() + ". File Size: " + nf.length());
		try (FileWriter fw = new FileWriter(nf, false)) {
			fw.write(text);
			System.out.println(fileName + " updated on " + nf.lastModified() + ". File Size: " + nf.length());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void readCharacterUsingStream(String fileName) {
		File nf = new File(file, fileName);
		try(InputStreamReader isr = new InputStreamReader(new FileInputStream(nf))){
			int c = -1;
			StringBuilder sb = new StringBuilder();
			while((c = isr.read()) > -1) {
				sb.append((char)c);
			}
			System.out.println(sb.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void overwriteCharacterUsingStream(String fileName, String text) {
		File nf = new File(file, fileName);
		System.out.println(fileName + " updated on " + nf.lastModified() + ". File Size: " + nf.length());
		try(OutputStreamWriter osr = new OutputStreamWriter(new FileOutputStream(nf, false))){
			osr.write(text);
			System.out.println(fileName + " updated on " + nf.lastModified() + ". File Size: " + nf.length());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void overwriteWithPrintWriter(String fileName, String text) {
		File nf = new File(file, fileName);
		System.out.println(fileName + " updated on " + nf.lastModified() + ". File Size: " + nf.length());
		try(PrintWriter pw = new PrintWriter(nf)){
			pw.format("%1d %2d %3d %4s", 1, 2, 3, text);
			System.out.println(fileName + " updated on " + nf.lastModified() + ". File Size: " + nf.length());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void overwriteWithPrintStream(String fileName, String text) {
		File nf = new File(file, fileName);
		System.out.println(fileName + " updated on " + nf.lastModified() + ". File Size: " + nf.length());
		try(PrintStream ps = new PrintStream(nf)){
			ps.format("%1d %2d %3d %4s", 1, 2, 3, text);
			System.out.println(fileName + " updated on " + nf.lastModified() + ". File Size: " + nf.length());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void persistUserObjects(Map<String, User> users) {
		File nf = new File(file, "users.dat");
		try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(nf)))){
			oos.writeObject(users);
			System.out.println("Users persisted.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void displayUserObjects() {
		File nf = new File(file, "users.dat");
		try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(nf)))){
			Map<String,User> users = (Map<String,User>) ois.readObject();
			for(Entry<String,User> entry: users.entrySet()) {
				System.out.println(entry.getValue().getName() + ", " + entry.getValue().getEmail() + ", " + entry.getValue().getDateCreated());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
