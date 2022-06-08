package Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtils {
	 
	public static FileInputStream fis;
	public static FileOutputStream fio;
	public static XSSFWorkbook Workbook;
	public static XSSFSheet Sheet;
	public static XSSFRow Row;
	public static XSSFCell Cell;
	
	
	public static int getRowCount(String Excelpath,String sheetname) throws Exception {
	
		//get row count from the sheet
		
		fis = new FileInputStream(Excelpath);
		Workbook = new XSSFWorkbook(fis);
		Sheet = Workbook.getSheet(sheetname);//get sheet by sheetname
		int RowCount = Sheet.getLastRowNum();
		Workbook.close();
		fis.close();
		return RowCount;
	}
	
	public static int getCellCount(String Excelpath,String sheetname,int rownum) throws Exception {
		
		//get column count from the row
		fis = new FileInputStream(Excelpath);
		Workbook = new XSSFWorkbook(fis);
		Sheet = Workbook.getSheet(sheetname); //get sheet by index starts from 0
		Row = Sheet.getRow(rownum); //
		int CellCount = Row.getLastCellNum();
		Workbook.close();
		fis.close();
		return CellCount;
		
	}
	
	//get cell value
	public static String getCellData(String Excelpath,String sheetname, int rownum, int colnum) throws Exception{
		
		//get cell value from the row and the column
		
		fis = new FileInputStream(Excelpath);
		Workbook = new XSSFWorkbook(fis);
		Sheet = Workbook.getSheet(sheetname);
		Row = Sheet.getRow(rownum);
		Cell = Row.getCell(colnum);
		String data;
		try {
			DataFormatter formatter = new DataFormatter();
			String celldata = formatter.formatCellValue(Cell);
			return celldata ;
			
			
		} 
		catch (Exception e) {
			data = "";
		}
		//returns the cell value as a string 
		Workbook.close();
		fis.close();
		return data;
		
	}

	public static void setCellData(String excelpath,String sheetname, int rownum, int colnum, String data) throws IOException {
		
		fis = new FileInputStream(excelpath);
		Workbook = new XSSFWorkbook(fis);
		Sheet = Workbook.getSheet(sheetname);
		Row = Sheet.getRow(rownum);
		Cell = Row.createCell(colnum);
		Cell.setCellValue(data);
		fio = new FileOutputStream(excelpath);
		Workbook.write(fio);
		Workbook.close();
		fio.close();
		fis.close();
	

		
	}
	
	

			
}
	
	
	