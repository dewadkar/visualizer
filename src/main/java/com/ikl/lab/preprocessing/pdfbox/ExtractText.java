package com.ikl.lab.preprocessing.pdfbox;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.FileUtils;

/**
 * @author dewadkar infant preprocessing.pdfbox Jun 21, 2015 11:52:15 AM
 * 
 */
public class ExtractText {

	public static final PDFTextExtractor pdfextractor = new PDFTextExtractor();

	public static void main(String[] args) {

		ExtractText et = new ExtractText();
		String filePath = "C:\\IBM\\Workspace_keplar\\infant\\data\\papers";
		List<File> files = et.listFile(filePath);

	}

	public List<File> listFile(String pathname) {
		List<File> files = new LinkedList<File>();
		File f = new File(pathname);
		File[] listfiles = f.listFiles();
		for (int i = 0; i < listfiles.length; i++) {
			if (listfiles[i].isDirectory()) {
				File[] internalFile = listfiles[i].listFiles();
				for (int j = 0; j < internalFile.length; j++) {
					System.out.println(internalFile[j]);
					if (internalFile[j].isDirectory()) {
						String name = internalFile[j].getAbsolutePath();
						listFile(name);
					}

				}
			} else {
				if (listfiles[i].getName().endsWith("pdf")) {
					System.out.println(listfiles[i]);
					files.add(listfiles[i]);
					String filePath = listfiles[i]
							.getAbsolutePath();
					String content = PDFTextExtractor.pdftoText(filePath);
					try {
						FileUtils.write(new File(
								"C:\\IBM\\Workspace_keplar\\infant\\data\\textfiles\\"
										+ listfiles[i].getName().replace(
												".pdf", ".txt")), content);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}
		return files;
	}

}
