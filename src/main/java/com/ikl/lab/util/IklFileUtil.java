package com.ikl.lab.util;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * @author dewadkar infant util Jun 21, 2015 3:06:14 PM
 * 
 */
public class IklFileUtil {

	public static List<File> listFile(String pathname) {
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

				files.add(listfiles[i]);
			}

		}
		return files;
	}
}
