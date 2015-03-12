package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import controller.Controller;
import util.Files;

public class Main {
	public static void main(String[] args) throws IOException {
		//String[] diretorios = discoveryDir(new File("E:/")).toString().split(";");
	
		//for (String a : diretorios)
			//System.out.println(a);
		//System.out.println("Quantidade de diretórios encontrados " + diretorios.length);
		
		//copy(new File("E:/Automata (2014) 720p DualAudio/Automata (2014) 720p DualAudio.mkv"), new File("E:/filme.mkv"));
		//Files file = new Files(); 
		Controller c = new Controller(); 
		
		String[] temp = {"mkv","mp4","avi"}; 
		
		c.startCopy("E:/", "G:/",temp);
	}
	
	static void copy(File src, File dst) throws IOException {
		long tam = src.getTotalSpace(); 
		float loops = (int) (tam/1024); 
		int count = 0; 
		
        InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(dst);        
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
        	System.out.println("Concluído: "+ ((count++/loops)*100)); 
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
    }

	public static StringBuilder discoveryDir(File dir) {
		StringBuilder t = new StringBuilder();
		File[] content = dir.listFiles();

		if (content != null) {
			for (File temp : content) {
				System.out.println(temp.getName());
				if (temp.isDirectory()) {
					StringBuilder aux = discoveryDir(temp);

					if (aux.length() > 0) {
						t.append(aux + ";");
					}
				} else {
					if (temp.getName().contains(".mkv"))
						t.append(temp.getAbsolutePath() + ";");
				}
			}
		}
		return t;

	}
}
