package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class XLutilities {
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public CellStyle style;
	public XSSFCell cell;
	String path;
	
    public XLutilities(String path)
    {
    	this.path=path;
    }
	
    public int getRowCount(String SheetName) throws IOException
    //we will call this method by sheetName
    {
    	fi=new FileInputStream(path);
    	workbook=new XSSFWorkbook(fi);
    	sheet=workbook.getSheet(SheetName);
    	int rowCount=sheet.getLastRowNum();
    	workbook.close();
    	fi.close();
    	return rowCount;//this will return the row count i.e, how many no. of rows are there in excel sheet
    }
    
    public int getCellCount(String SheetName,int rownum) throws IOException
    {
    	//when we pass sheetname and rownum it will count no. of rows in particular row
        fi=new FileInputStream(path);
    	workbook=new XSSFWorkbook(fi);
    	sheet=workbook.getSheet(SheetName);
    	row=sheet.getRow(rownum);
    	int CellCount=row.getLastCellNum();
    	workbook.close();
    	fi.close();
    	return CellCount;
    }
    
    public String getCellData(String sheetName,int rownum,int colnum) throws IOException
    {
    	//when we pass sheetname, rownum and colnum it will return the data
   
    	fi=new FileInputStream(path);
    	workbook=new XSSFWorkbook(fi);
    	sheet=workbook.getSheet(sheetName);
    	row=sheet.getRow(rownum);
    	cell=row.getCell(colnum);
    	
    	
    	DataFormatter formatter = new DataFormatter();
    	String data;
    	try {
    		data = formatter.formatCellValue(cell);
    	}
    	catch(Exception e)
    	{
    		data="";
    	}
    	workbook.close();
    	fi.close();
    	return data;
    }
    
    public void setCellDats(String sheetName,int rownum,int colnum,String data) throws IOException
    {
    	//when we pass sheetname,rownum,colnum and data it will insert the data into sheet
    
    	File xlfile=new File(path);
    	if(!xlfile.exists())
    	{
    		workbook=new XSSFWorkbook();
    		fo=new FileOutputStream(path);
    		workbook.write(fo);
    	}
    	
    	fi=new FileInputStream(path);
    	workbook=new XSSFWorkbook(fi);
    	
    	if(workbook.getSheetIndex(sheetName)==-1)//If sheet not exists then create new sheet
    	      workbook.createSheet(sheetName);
    		sheet=workbook.getSheet(sheetName);
    		
    		
       if(sheet.getRow(rownum)==null)//if row not exists then create new row
    	   sheet.createRow(rownum);
       row=sheet.getRow(rownum);
    	
      cell=row.createCell(colnum);
      cell.setCellValue(data);
      fo=new FileOutputStream(path);
      workbook.write(fo);
      workbook.close();
      fi.close();
      fo.close();
    }
    
    //fileGreenColor and fillredcolor wiilbe used in web automation and in api testing we dont use this
    public void fileGreenColor(String sheetName,int rownum,int colnum) throws IOException    	
    {
    	fi=new FileInputStream(path);
    	workbook=new XSSFWorkbook(fi);
    	sheet=workbook.getSheet(sheetName);
    	
    	row=sheet.getRow(rownum);
    	cell=row.getCell(colnum);
    	
    	style=workbook.createCellStyle();
    	
    	style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
    	style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    	
    	cell.setCellStyle(style);
    	workbook.write(fo);
    	workbook.close();
    	fi.close();
    	fo.close(); 	
    }
    
    public void fillRedColor(String sheetName,int rownum,int colnum) throws IOException
    {
    	fi=new FileInputStream(path);
    	workbook=new XSSFWorkbook(fi);
    	sheet=workbook.getSheet(sheetName);
    	row=sheet.getRow(rownum);
    	cell=row.getCell(colnum);
    	
    	style=workbook.createCellStyle();
    	
    	style.setFillForegroundColor(IndexedColors.RED.getIndex());
    	style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    	
    	cell.setCellStyle(style);
    	workbook.write(fo);
    	workbook.close();
    	fi.close();
    	fo.close();
    	
    	
    	
}
}
