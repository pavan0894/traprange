/**
 * Copyright (C) 2015, GIAYBAC
 *
 * Released under the MIT license
 */
package com.giaybac.traprange.test;

import com.giaybac.traprange.PDFTableExtractor;
import com.giaybac.traprange.PDFTableExtractor2;
import com.giaybac.traprange.entity.Table;
import com.giaybac.traprange.entity.TableCell;
import com.giaybac.traprange.entity.TableRow;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;

/**
 *
 * @author THOQ LUONG Mar 22, 2015 5:36:40 PM
 */
public class TestExtractor1 {

	// --------------------------------------------------------------------------
	// Members
	// --------------------------------------------------------------------------
	// Initialization and releasation
	// --------------------------------------------------------------------------
	// Getter N Setter
	// --------------------------------------------------------------------------
	// Method binding
	@Test
	public void test() throws IOException {
		PropertyConfigurator.configure(TestExtractor1.class.getResource("/com/giaybac/traprange/log4j.properties"));

		String homeDirectory = System.getProperty("user.dir");

		String sourceDirectory = Paths.get(homeDirectory, "_Docs").toString();
		String resultDirectory = Paths.get(homeDirectory, "_Docs", "result").toString();

		PDFTableExtractor2 extractor = (new PDFTableExtractor2())
				.setSource(sourceDirectory + File.separator + "sample-11.pdf");
		extractor.exceptLine(new int[] {0,1,2,3,4,5,6,16,17});

		List<Table> tables = extractor.extract();
		//try (Writer writer = new OutputStreamWriter(new FileOutputStream(resultDirectory + "//sample-11.html"),
		//		"UTF-8")) {
			for (Table table : tables) {
				List<TableRow> tr = table.getRows();
				System.out.println("tr.size()=" + tr.size());

				//writer.write("Page: " + (table.getPageIdx() + 1) + "\n");
				//writer.write(table.toConsolidatedHtml());
				ArrayList<String[]> list = table.toConsolidatedHtml();

				for(String[] sts: list){
					for(String st: sts){
						System.out.print(st + ",");
					}
					System.out.println();
				}
				
				/*int i = 0;
				for(TableRow row: tr){
					System.out.println("Row: " + i++);
					for(TableCell cell : row.getCells()){
						System.out.println("cell.getContent()=" + cell.getContent());
					}
					
				}*/
		}
	}
	// --------------------------------------------------------------------------
	// Implement N Override
	// --------------------------------------------------------------------------
	// Utils
	// --------------------------------------------------------------------------
	// Inner class
}
