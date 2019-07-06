package gui.fileoperation.inherit.filter;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class TxtFileFilter extends FileFilter {

	@Override
	public boolean accept(File file) {
		String fileName = file.getName();
		return file.isDirectory() || fileName.toLowerCase().endsWith(".txt");
	}

	@Override
	public String getDescription() {
		return "*.txt";
	}

}
