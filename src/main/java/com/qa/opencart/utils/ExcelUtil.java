package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	
	//Create method to read data from excel sheet
	
	//initialise the path of excel sheet where it is present in workspace
	private static final String TEST_DATA_SHEET_PATH = "./src/test/resources/testdata/OpenCartTestData.xlsx";
	private static Workbook workbook;
	private static Sheet sheet;
	
	//this method tells give me sheet name
	public static Object[][] getTestData(String sheetName)
	{
		Object data[][] = null;
		try {
			//using this to establish connection with excel file. This is coming from java
			FileInputStream ip = new FileInputStream(TEST_DATA_SHEET_PATH);
			
			//Start using apachi poi api
			workbook = WorkbookFactory.create(ip); //This will create the copy of the entire stream in the memory. It will load the data from excel file
			sheet = workbook.getSheet(sheetName);
			
//	        int rowCount = sheet.getPhysicalNumberOfRows();
//	        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
			
			//This method should return 2D object array and it will given to dataprovider
			//Dataprovider always return 2D object array
			//create blank object array
			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];//getLastCellNum will give column count
		
			//Object[][] data = new Object[rowCount - 1][colCount]; // exclude header row
			
			//to go to each row and column
			for(int i=0;i<sheet.getLastRowNum();i++)
			{
				for(int j=0;j<sheet.getRow(i+1).getLastCellNum();j++)
				{
					//with the help of for loop we will start filling the 2D array
					data[i][j] = sheet.getRow(i+1).getCell(j).toString();//Excel string converting to java string using toString()
					
				}
			}
		
//			for (int i = 1; i < rowCount; i++) { // start from 1 to skip header
//	            Row row = sheet.getRow(i);
//	            for (int j = 0; j < colCount; j++) {
//	                Cell cell = row.getCell(j);
//	                data[i - 1][j] = cell != null ? cell.toString() : "";
//	            }
//	        }
//			
		} catch (IOException | InvalidFormatException e) {
			
			e.printStackTrace();
		}
		
		return data;
	}
	

}
