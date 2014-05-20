package utility;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;



/*
 * This class provides static methods to read data from an Excel sheet as a
 * two dimensional array of Strings. The data type of the returned value will 
 * always be String regardless of the data type of the stored value in Excel sheet.
 */

public class ExcelReader
{
	/* data types to be recognized from Excel file */
	public enum DataType
	{
		BOOLEAN, 
		DATE, 
		DOUBLE, 
		FLOAT, 
		INTEGER, 
		LONG, 
		STRING
	};	
	
	/* returns the workbook object from the specified xls/xlsx file */ 
	protected static Workbook getWorkbook(String fileName) throws Exception
	{
		Workbook workbook = null;			
		
		InputStream stream = null;

		if (fileName.toLowerCase().endsWith(".xls"))
		{
			stream = new FileInputStream(fileName);
			workbook = new HSSFWorkbook(new POIFSFileSystem(stream));
		}
		else if (fileName.toLowerCase().endsWith(".xlsx"))
		{
			stream = new FileInputStream(fileName);
			//workbook = new XSSFWorkbook(stream);
		}
		else
			throw new Exception("Invalid file extension - should be .xls or .xlsx");
		
		return workbook;
	}
	
	@SuppressWarnings("incomplete-switch")
	protected static Object getCellValue(DataType dataTypeOfColumn, Cell cell)
	{
		Object data = null;
		
		switch (cell.getCellType())
		{
			case Cell.CELL_TYPE_STRING:
				data = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_NUMERIC:
				{
					if (dataTypeOfColumn == null)
					{
						if (DateUtil.isCellDateFormatted(cell))
							data = cell.getDateCellValue().toString();
						else
							data = new Double(cell.getNumericCellValue()).toString();
					}
					else
					{
						switch (dataTypeOfColumn)
						{
						case DATE:
							data = cell.getDateCellValue().toString();
							break;
						case DOUBLE:
							data = new Double(cell.getNumericCellValue()).doubleValue();
							break;
						case FLOAT:
							data = new Double(cell.getNumericCellValue()).floatValue();
							break;
						case INTEGER:
							data = new Double(cell.getNumericCellValue()).intValue();
							break;
						case LONG:
							data = new Double(cell.getNumericCellValue()).longValue();
							break;	
						case STRING:
							data = "" + (new Double(cell.getNumericCellValue()).intValue());
							break;
							
						}					
					}
				}
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				data = new Boolean(cell.getBooleanCellValue()).toString();
				break;
			case Cell.CELL_TYPE_FORMULA:
				data = cell.getCellFormula().toString();
				break;				
			case Cell.CELL_TYPE_BLANK:
				data = "";
				break;
				
			default:
				data = "";
		}
	
		return data;
	}	
	
	/* returns the data available in the specified sheet as 2D array of String.
	 * Please note that, if no value is available in a cell, empty string ("") is set
	 */
	public static String[][] getData(Sheet sheet)
	{        
        String[][] data = null;
		
		int iLastColNum = 0;	
		int i = 0, j = 0;
		int iColIndex;
		Cell cell;						
		
		int iNoOfTotalRows = sheet.getPhysicalNumberOfRows();	//TODO check for 0
		int iStartCol = sheet.getRow(sheet.getFirstRowNum()).getFirstCellNum();
		
		int n = sheet.getFirstRowNum() + iNoOfTotalRows;
			
		Row row;
		for (i = sheet.getFirstRowNum(); i< n; i++)
		{
			row= sheet.getRow(i);			
			
		    if (row != null && row.getLastCellNum() > iLastColNum)
		    	iLastColNum = row.getLastCellNum(); //gets the index of the last cell PLUS ONE
		}
		
		data = new String[iNoOfTotalRows][iLastColNum - iStartCol];			
		
		i = 0;
		for (Row row1 : sheet)
		{				
			iColIndex = iStartCol;			
			for (j=0; iColIndex < iLastColNum; j++, iColIndex++)
			{					
				cell = row1.getCell(iColIndex);				
				
				//cell without any value is null; check and assign empty string
				if (cell != null)
					data[i][j] = getCellValue(DataType.STRING, cell).toString();
				else
					data[i][j] = "";										
			}	
			i++;				
		}		
		
		return data;
	}
	
	/* 
	 * Retrieves the data available in Excel sheet in 2D array. All data 
	 * is read as String only.
	 * 
	 * Data type for each column has been hard-coded as DataType.STRING. 
	 * 
	 * IMPORTANT NOTE: If no value is available in a cell, empty string ("") is set.  
	 *      
	 */	
	public static String[][] readData(String strFileName, String strSheetName) 
			 throws Exception
	{		
		checkFileExists(strFileName);
		
		Workbook workbook = getWorkbook(strFileName);		
		Sheet sheet = workbook.getSheet(strSheetName);
		
		if (sheet == null)
			throw new Exception("Sheet " + strSheetName + " of " + strFileName + " does not exist.");		
	
		return getData(sheet);		
	}
	
	
	/* 
	 * Retrieves the data available in Excel sheet in 2D array. All data 
	 * is read as String only.
	 * 
	 * Data type for each column has been hard-coded as DataType.STRING. 
	 * 
	 * IMPORTANT NOTE: If no value is available in a cell, empty string ("") is set.  
	 *      
	 */	
	public static String[][] readData(String strFileName, int iSheetNum) 
			 throws Exception
	{	
		checkFileExists(strFileName);
		
		Workbook workbook = getWorkbook(strFileName);		
		Sheet sheet = workbook.getSheetAt(iSheetNum);	
	
		if (sheet == null)
			throw new Exception("Sheet " + iSheetNum + " of " + strFileName + " does not exist.");
		
		return getData(sheet);		
	}	
	
	
	
	/* 
     * This method is used for DataMapper.
     *  
     * iVarNameColNum - column number where the variable name(s) is/are available
     * iDataColNum - column number where the data is available
     * 
     * Both the column values are retrieved as 2D array of String. 
     * The resulting array will always have 2 columns and n rows based on the data. 
     * 
     * IMPORTANT NOTE: If no value is available in a cell, empty string ("") is set.  
     *      
     */ 
    public static String[][] readTwoColumns(String strFileName, String strSheetName, int iVarNameColNum, int iDataColNum) 
			 throws Exception
	{		
    	checkFileExists(strFileName);
    	
		Workbook workbook = getWorkbook(strFileName);		
		Sheet sheet = workbook.getSheet(strSheetName);
		
		if (sheet == null)
			throw new Exception("Sheet " + strSheetName + " of " + strFileName + " does not exist.");		
	
		int iStartRow = sheet.getFirstRowNum();		
		int iLastRow = sheet.getLastRowNum();		
		
		String[][] data = new String[iLastRow - iStartRow + 1][2];
		
		Row row;
		Cell cell;
		
		int iRowIndex = 0;		
		
		for (int i = iStartRow; i <= iLastRow; i++)
        {               
        	row = sheet.getRow(i);
        	
        	if (row != null)
        	{
        		//retrieved data contains col1 and col2 values in a row
        		if (iVarNameColNum >= row.getFirstCellNum())
        		{
        		cell = row.getCell(iVarNameColNum);
	        	if (cell != null)
	                data[iRowIndex][0] = getCellValue(DataType.STRING, cell).toString();
	            else
	                data[iRowIndex][0] = "";
        		}
        		else
	                throw new Exception("Variable Name column " + iVarNameColNum + " of " + strFileName + " does not exist");
        		
	        	if (iDataColNum < row.getLastCellNum())
	        	{	        		
		        	cell = row.getCell(iDataColNum);
		        	if (cell != null)
		                data[iRowIndex][1] = getCellValue(DataType.STRING, cell).toString();
		            else
		                data[iRowIndex][1] = "";
	        	}
	        	else
	                throw new Exception("Data column " + iDataColNum + " of " + strFileName + " does not exist");	        	
        	}
        	else
        	{
        		data[iRowIndex][0] = "";
        		data[iRowIndex][1] = "";
        	}
        	
            iRowIndex++;
        }         
		
		return data;		
	}
    
    /*     
     *  
     * Retrieves the values from the start row till the end row specified as 
     * 2D array of String.
     *  
     * iStartRow - starting column number
     * iEndRow - ending column number (inclusive)

     * IMPORTANT NOTE: If no value is available in a cell, empty string ("") is set.  
     *      
     */ 
    public static String[][] readRangeOfRows(String strFileName, int iSheetNum, int iStartRow, int iEndRow) 
			 throws Exception
	{		
    	
   	    checkFileExists(strFileName);
   	    
   	    
		Workbook workbook = getWorkbook(strFileName);		
		Sheet sheet = workbook.getSheetAt(iSheetNum);
		
		if (sheet == null)
			throw new Exception("Sheet " + iSheetNum + " of " + strFileName + " does not exist.");		
		
		Row row = sheet.getRow(0);
		
		int iNoOfColumns = row.getPhysicalNumberOfCells();
		
		String[][] data = new String[iEndRow - iStartRow + 1][iNoOfColumns];
				
		Cell cell;	
	
		int j =0;
		int iRowIndex = 0;
		for (int i = iStartRow; i <= iEndRow; i++, iRowIndex++)
        {               
	       	row = sheet.getRow(i);
	       	
	       	if (row != null)
	       	{
	       		for (j=0; j < iNoOfColumns; j++)
				{					
					cell = row.getCell(j);				
					
					//cell without any value is null; check and assign empty string
					if (cell != null)
						data[iRowIndex][j] = getCellValue(DataType.STRING, cell).toString();
					else
						data[iRowIndex][j] = "";										
				}	       			        	
	       	}
	       	else
	       	{
	       		for (j=0; j < iNoOfColumns; j++)
	       		    data[iRowIndex][j] = "";	       		
	       	}	       
        }
		System.out.println(data);
	    return data;		
	}
    
    /* returns true if the sheet with the given name is available in the file; else
     * returns false 
     */
    public static boolean isSheetAvailable(String fileName, String sheetName)
    {
    	try
    	{
	    	Workbook workbook = getWorkbook(fileName);
	    	
	    	if (workbook.getSheet(sheetName) != null)
	    		return true;
	    	else
	    		return false;	    	
    	}
    	catch (Exception e)
    	{
    		return false;
    	}
    }
    
    private static boolean checkFileExists(String strFileName) throws Exception
    {
		if (!(new File(strFileName).exists()))
		{
			throw new Exception("File " + strFileName + " does not exist");
		}
		
		return true;
    }
    
    public static String[][] getDataRange(String strFileName, String strSheetName, int iStartRow,int iStartCol, int iEndRow, int iEndCol ) 
	 throws Exception
	 {
   	    checkFileExists(strFileName);
		Workbook workbook = getWorkbook(strFileName);		
		Sheet sheet = workbook.getSheet(strSheetName);
		
		if (sheet == null)
			throw new Exception("Sheet " + strSheetName + " of " + strFileName + " does not exist.");		

		
		Row row = sheet.getRow(0);		
		int iNoOfColumns = iEndCol;
		
		String[][] data = new String[iEndRow - iStartRow + 1][iNoOfColumns];
		Cell cell;	
		
		int j =0;
		int iRowIndex = 0;
		for (int i = iStartRow; i <= iEndRow; i++, iRowIndex++)
        {               
	       	row = sheet.getRow(i);
	       	
	       	if (row != null)
	       	{
	       		for (j=0; j < iNoOfColumns; j++)
				{					
					cell = row.getCell(j);				
					
					//cell without any value is null; check and assign empty string
					if (cell != null)
						data[iRowIndex][j] = getCellValue(DataType.STRING, cell).toString();
					else
						data[iRowIndex][j] = "";										
				}	       			        	
	       	}
	       	else
	       	{
	       		for (j=0; j < iNoOfColumns; j++)
	       		    data[iRowIndex][j] = "";	       		
	       	}	       
        }
		System.out.println("***************************************************");
		//System.out.println(data);
	    return data;	
	 }
    
}

