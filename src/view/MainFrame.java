package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Choice;
import java.awt.BorderLayout;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import java.awt.Color;

import javax.swing.JProgressBar;
import javax.swing.border.LineBorder;
import javax.swing.JCheckBox;

import controller.Controller;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class MainFrame {

	private JFrame frame;
	private JTextField tLook;
	private JTextField tPut;
	private JTextField tCustom;
	private ArrayList<JCheckBox> arrayJCheckBox;
	private JProgressBar progressBarTask;
	private JProgressBar progressBarFile;
	private JFileChooser fileChooser;
	
	private JLabel status; 
	private static MainFrame window; 
	
	private Controller c; 
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			// Set cross-platform Java L&F (also called "Metal")
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			// handle exception
		} catch (ClassNotFoundException e) {
			// handle exception
		} catch (InstantiationException e) {
			// handle exception
		} catch (IllegalAccessException e) {
			// handle exception
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static MainFrame getInstance(){
		if( window != null) return window; 
		return null; 
	}
	/**
	 * Create the application.
	 */
	public MainFrame() {
		fileChooser = new JFileChooser();
		arrayJCheckBox = new ArrayList<>();
		c = new Controller();
		initialize();
		progressBarTask.setStringPainted(true);
		progressBarFile.setStringPainted(true);
		progressBarFile.setMaximum(100);
	}

	private String choose() {
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnValue = fileChooser.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			return selectedFile.getAbsolutePath();
		}
		return null;
	}

	public void setStatus(String text){
		status.setText(text);
	}
	
	public void setMaxJProgressBarTask(int max) {
		progressBarTask.setMaximum(max);
	}

	public void setProgressBarFile(int progress) {
		progressBarFile.setValue(progress);
	}

	public void setProgressBarTask(int progress) {
		progressBarTask.setValue(progress);
	}
	
	public void setProgressBarFileLabel(String text) {
		progressBarFile.setString(text); 
	}

	public void setProgressBarTaskLabel(String text) {
		progressBarTask.setString(text); 
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 850, 640);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Look at");
		lblNewLabel.setBounds(12, 11, 55, 16);
		frame.getContentPane().add(lblNewLabel);

		tLook = new JTextField();
		tLook.setBounds(12, 29, 365, 20);
		frame.getContentPane().add(tLook);
		tLook.setColumns(10);

		tPut = new JTextField();
		tPut.setColumns(10);
		tPut.setBounds(12, 70, 365, 20);
		frame.getContentPane().add(tPut);

		JLabel lblPutAt = new JLabel("Put at");
		lblPutAt.setBounds(12, 55, 55, 16);
		frame.getContentPane().add(lblPutAt);

		JButton btnChoose = new JButton("Choose");
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tLook.setText(choose());
			}
		});

		btnChoose.setBounds(387, 26, 98, 26);
		frame.getContentPane().add(btnChoose);

		JButton button = new JButton("Choose");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tPut.setText(choose());
			}
		});

		button.setBounds(389, 67, 98, 26);
		frame.getContentPane().add(button);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Files types", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 101, 794, 304);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Audio", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 20, 186, 228);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JCheckBox chckbxMp = new JCheckBox("mp3");
		chckbxMp.setBounds(6, 20, 97, 23);
		panel_1.add(chckbxMp);
		arrayJCheckBox.add(chckbxMp);

		JCheckBox chckbxWma = new JCheckBox("wma");
		chckbxWma.setBounds(6, 46, 97, 23);
		panel_1.add(chckbxWma);
		arrayJCheckBox.add(chckbxWma);

		JCheckBox chckbxAcc = new JCheckBox("acc");
		chckbxAcc.setBounds(6, 72, 97, 23);
		panel_1.add(chckbxAcc);
		arrayJCheckBox.add(chckbxAcc);

		JCheckBox chckbxOgg = new JCheckBox("ogg");
		chckbxOgg.setBounds(6, 98, 97, 23);
		panel_1.add(chckbxOgg);
		arrayJCheckBox.add(chckbxOgg);

		JCheckBox chckbxAc = new JCheckBox("ac3");
		chckbxAc.setBounds(6, 124, 97, 23);
		panel_1.add(chckbxAc);
		arrayJCheckBox.add(chckbxAc);

		JCheckBox chckbxWav = new JCheckBox("wav");
		chckbxWav.setBounds(6, 150, 97, 23);
		panel_1.add(chckbxWav);
		arrayJCheckBox.add(chckbxWav);

		JCheckBox chckbxMp_1 = new JCheckBox("mp2");
		chckbxMp_1.setBounds(6, 176, 97, 23);
		panel_1.add(chckbxMp_1);
		arrayJCheckBox.add(chckbxMp_1);

		tCustom = new JTextField();
		tCustom.setBounds(10, 273, 761, 20);
		panel.add(tCustom);
		tCustom.setColumns(10);

		JLabel lblCustomFilesTypes = new JLabel("Custom files types, for exemple: iso,raw,java,js,py,xml,css,html,...");
		lblCustomFilesTypes.setBounds(10, 259, 355, 14);
		panel.add(lblCustomFilesTypes);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Video", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panel_2.setBounds(206, 20, 186, 228);
		panel.add(panel_2);

		JCheckBox chckbxAvi = new JCheckBox("avi");
		chckbxAvi.setBounds(6, 20, 97, 23);
		panel_2.add(chckbxAvi);
		arrayJCheckBox.add(chckbxAvi);

		JCheckBox chckbxMov = new JCheckBox("mov");
		chckbxMov.setBounds(6, 46, 97, 23);
		panel_2.add(chckbxMov);
		arrayJCheckBox.add(chckbxMov);

		JCheckBox chckbxRmvb = new JCheckBox("rmvb");
		chckbxRmvb.setBounds(6, 72, 97, 23);
		panel_2.add(chckbxRmvb);
		arrayJCheckBox.add(chckbxRmvb);

		JCheckBox chckbxMkv = new JCheckBox("mkv");
		chckbxMkv.setBounds(6, 98, 97, 23);
		panel_2.add(chckbxMkv);
		arrayJCheckBox.add(chckbxMkv);

		JCheckBox chckbxgp = new JCheckBox("3gp");
		chckbxgp.setBounds(6, 124, 97, 23);
		panel_2.add(chckbxgp);
		arrayJCheckBox.add(chckbxgp);

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Image", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panel_3.setBounds(402, 20, 186, 228);
		panel.add(panel_3);

		JCheckBox chckbxBmp = new JCheckBox("bmp");
		chckbxBmp.setBounds(6, 20, 97, 23);
		panel_3.add(chckbxBmp);
		arrayJCheckBox.add(chckbxBmp);

		JCheckBox chckbxGif = new JCheckBox("gif");
		chckbxGif.setBounds(6, 46, 97, 23);
		panel_3.add(chckbxGif);
		arrayJCheckBox.add(chckbxGif);

		JCheckBox chckbxJpeg = new JCheckBox("jpg");
		chckbxJpeg.setBounds(6, 72, 97, 23);
		panel_3.add(chckbxJpeg);
		arrayJCheckBox.add(chckbxJpeg);

		JCheckBox chckbxPng = new JCheckBox("png");
		chckbxPng.setBounds(6, 98, 97, 23);
		panel_3.add(chckbxPng);
		arrayJCheckBox.add(chckbxPng);

		JCheckBox chckbxCrd = new JCheckBox("crd");
		chckbxCrd.setBounds(6, 124, 97, 23);
		panel_3.add(chckbxCrd);
		arrayJCheckBox.add(chckbxCrd);

		JCheckBox chckbxIa = new JCheckBox("ai");
		chckbxIa.setBounds(6, 150, 97, 23);
		panel_3.add(chckbxIa);
		arrayJCheckBox.add(chckbxIa);

		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Text", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panel_4.setBounds(598, 20, 186, 228);
		panel.add(panel_4);

		JCheckBox chckbxTxt = new JCheckBox("txt");
		chckbxTxt.setBounds(6, 20, 97, 23);
		panel_4.add(chckbxTxt);
		arrayJCheckBox.add(chckbxTxt);

		JCheckBox chckbxDoc = new JCheckBox("doc");
		chckbxDoc.setBounds(6, 46, 97, 23);
		panel_4.add(chckbxDoc);
		arrayJCheckBox.add(chckbxDoc);

		JCheckBox chckbxXls = new JCheckBox("xls");
		chckbxXls.setBounds(6, 72, 97, 23);
		panel_4.add(chckbxXls);
		arrayJCheckBox.add(chckbxXls);

		JCheckBox chckbxPpt = new JCheckBox("ppt");
		chckbxPpt.setBounds(6, 98, 97, 23);
		panel_4.add(chckbxPpt);
		arrayJCheckBox.add(chckbxPpt);

		JCheckBox chckbxPdf = new JCheckBox("pdf");
		chckbxPdf.setBounds(6, 124, 97, 23);
		panel_4.add(chckbxPdf);
		arrayJCheckBox.add(chckbxPdf);

		status = new JLabel("Step 2: Copying files..");
		status.setBounds(12, 416, 136, 14);
		frame.getContentPane().add(status);

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5.setBounds(12, 441, 794, 121);
		frame.getContentPane().add(panel_5);
		panel_5.setLayout(null);

		progressBarTask = new JProgressBar();
		progressBarTask.setBounds(10, 36, 774, 14);
		panel_5.add(progressBarTask);

		JLabel lblTask = new JLabel("Task");
		lblTask.setBounds(10, 11, 46, 14);
		panel_5.add(lblTask);

		progressBarFile = new JProgressBar();
		progressBarFile.setBounds(10, 86, 774, 14);
		panel_5.add(progressBarFile);

		JLabel lblFile = new JLabel("File");
		lblFile.setBounds(10, 61, 46, 14);
		panel_5.add(lblFile);

		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp = "";
				
				for (JCheckBox a : arrayJCheckBox)
					if (a.isSelected())
						temp += a.getText() + ",";
				
				String[] array =  temp.substring(0,temp.length()-1).split( Pattern.quote(",")); 
			
					new Thread( new Runnable(){
						@Override
						public void run() {
							c.startCopy(tLook.getText(), tPut.getText(),array);
						}
					}).start(); 
				
			}
		});
		
		btnStart.setBounds(10, 567, 89, 23);
		frame.getContentPane().add(btnStart);
	}
}
