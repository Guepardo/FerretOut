package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import util.Files;
import view.MainFrame;

public class Controller {
	private Files files;

	public Controller() {
		files = new Files();
	}

	public void startCopy(String pathIn, String pathOut, String[] extensions) {
		// Indexandar arquivos;
		MainFrame.getInstance().setStatus("Step 1: Indexing Files...");
		ArrayList<String> filesFiltered = new ArrayList<>();

		String temp = files.discoveryDir(new File(pathIn)).toString();

		for (String a : temp.split(";")) {
			for (String b : extensions) {
				 String[] aux =a.split(Pattern.quote("."));
				if (aux[aux.length-1].equals(b)) {
					filesFiltered.add(a);
					break;
				}
			}
		}

		// Criar diretórios;
		MainFrame.getInstance().setStatus("Step 2: Creating directories...");
		files.createDir(extensions, pathOut);

		// Iniciar Cópias;
		MainFrame.getInstance().setStatus("Step 3: Copying files...");
		MainFrame.getInstance().setMaxJProgressBarTask(filesFiltered.size());
		
		int count = 0; 
		for (String a : filesFiltered) {
			String[] aux = a.split(Pattern.quote("."));
			File source = new File(a);
			MainFrame.getInstance().setProgressBarTask(++count);
			MainFrame.getInstance().setProgressBarTaskLabel(count+" of "+ filesFiltered.size());
			try {
				files.copy(source, new File(pathOut + File.separator + "Backup" + File.separator + aux[aux.length - 1] + File.separator + source.getName()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		MainFrame.getInstance().setStatus("Done.");
	}
}
