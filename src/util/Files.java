package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;

import view.MainFrame;

public class Files {
	public Files() {

	}

	public void copy(File src, File dst) throws IOException {
		long tam = src.length();
		float loops = (int) (tam / 1024);
		int count = 0;

		InputStream in = new FileInputStream(src);
		OutputStream out = new FileOutputStream(dst);
		byte[] buf = new byte[1024];
		int len;
		DecimalFormat df = new DecimalFormat("#.0");
		
		while ((len = in.read(buf)) > 0) {
			double temp =  ((count++ / loops) * 100);
			MainFrame.getInstance().setProgressBarFileLabel(src.getName()+" | "+df.format(temp)+"%");
			MainFrame.getInstance().setProgressBarFile((int) temp);
			out.write(buf, 0, len);
		}
		in.close();
		out.close();
	}

	public StringBuilder discoveryDir(File dir) {
		StringBuilder t = new StringBuilder();
		File[] content = dir.listFiles();

		if (content != null) {
			for (File temp : content) {
				if (temp.isDirectory()) {
					StringBuilder aux = discoveryDir(temp);

					if (aux.length() > 0) {
						t.append(aux + ";");
					}
				} else {
					t.append(temp.getAbsolutePath() + ";");
				}
			}
		}
		return t;
	}

	public void createDir(String[] extensions, String relativePath) {
		new File(relativePath + File.separator + "Backup").mkdir();
		for (String a : extensions)
			new File(relativePath + File.separator + "Backup" + File.separator + a).mkdir();
	}
}
