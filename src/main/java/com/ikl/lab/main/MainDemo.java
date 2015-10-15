package com.ikl.lab.main;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.ikl.lab.beans.Concept;
import com.ikl.lab.neo4j.AlchemiResponseProcessor;
import com.ikl.lab.neo4j.Neo4jConceptWriter;
import com.ikl.lab.preprocessing.pdfbox.ExtractText;
import com.ikl.lab.preprocessing.pdfbox.PDFTextExtractor;
import com.ikl.lab.util.IklFileUtil;

/**
 * @author dewadkar infant main Jun 21, 2015 4:03:35 PM
 * 
 */
public class MainDemo {

	public static void main(String[] args) {

		MainDemo demo = new MainDemo();

		// ---------------------------- to run on single file - on command
		// prompt : Just uncomment this block and comment other blocks
		// if (args.length < 2) {
		// System.out
		// .println(" Please specify two arguments : dir path for index and PDF file path.");
		// System.exit(0);
		// }
		//
		// demo.initSingleFileFlow(args[0], args[1]);

		// ------------------------- To test on local environment in IDE use
		// this block
		demo.init();

		// ---------------------------- If need to run in IDE for multiple files
		// soterd in folder use this block.
		// final String dbStorePath = "<PATH TO STORE DB CREATED BY PROGRAM>";
		// final String dirPath =
		// "<WHERE ALL PDF FILES STORED AND NEED TO USE IT FOR TEST>";
		// demo.initFolderFlow(dbStorePath, dirPath);
	}

	public void init() {

		List<File> files = IklFileUtil.listFile("data/extracted/");
		AlchemiResponseProcessor rr = new AlchemiResponseProcessor();
		Neo4jConceptWriter ncw = new Neo4jConceptWriter("C:\\datas\\mongodb\\");
		try {
			for (File file : files) {
				String content = FileUtils.readFileToString(file);
				List<Concept> concepts = rr.getDocConcepts(content);
				ncw.writeConceptToNeo4j(concepts, file.getName());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void initSingleFileFlow(final String dbStorePath,
			final String filePath) {

		File file = new File(filePath);
		if (file.isFile()) {
			String content = PDFTextExtractor.pdftoText(filePath);
			AlchemiResponseProcessor rr = new AlchemiResponseProcessor();
			List<Concept> concepts = rr.getDocConcepts(content);
			Neo4jConceptWriter ncw = new Neo4jConceptWriter(dbStorePath);
			ncw.writeConceptToNeo4j(concepts, file.getName());
		} else {
			System.out.println("  Please check file path.");
		}

	}

	public void initFolderFlow(final String dbStorePath, final String dirPath) {

		ExtractText et = new ExtractText();

		List<File> files = et.listFile(dirPath);

		for (File file : files) {

			if (file.isFile()) {
				String content = PDFTextExtractor.pdftoText(file
						.getAbsolutePath());
				AlchemiResponseProcessor rr = new AlchemiResponseProcessor();
				List<Concept> concepts = rr.getDocConcepts(content);
				Neo4jConceptWriter ncw = new Neo4jConceptWriter(dbStorePath);
				ncw.writeConceptToNeo4j(concepts, file.getName());
			} else {
				System.out.println("  Please check file path.");
			}
		}

	}

}
