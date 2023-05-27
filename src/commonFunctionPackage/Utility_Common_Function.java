package commonFunctionPackage;

 import java.io.File;
 
import java.io.FileInputStream;

import java.io.FileNotFoundException;

import java.io.FileWriter;

import java.io.IOException;

import java.util.ArrayList;

import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utility_Common_Function {
	
             public static void evidencecreator(String filename,String requestBody,String responseBody) throws IOException {
                   File newfile=new File("C:\\Users\\Manu\\Desktop"+filename+".txt");
                  System.out.println("a new file is created to save request and response :"+newfile.getName());
                   FileWriter datawrite=new FileWriter(newfile);
                  datawrite.write("request body"+requestBody+"\n\n");
                   datawrite.write("response body"+responseBody);
                  datawrite.close();
                  System.out.println("resquestbody and response body are saved in file :"+newfile.getName());
                     }
                   public static ArrayList<String> readdataexcel(String sheetname,String testcasename) throws IOException 
                        {
                           ArrayList<String> arraydata=new ArrayList<String>();
                 FileInputStream fis=new FileInputStream("C:\\Users\\Manu\\Desktop\\Book.xlsx");
                   XSSFWorkbook workbook=new XSSFWorkbook(fis);
                     int countofsheet=workbook.getNumberOfSheets();
                       for (int i=0;i<countofsheet;i++)
                         {
                         String filesheetname=workbook.getSheetName(i);
                     if (filesheetname.equalsIgnoreCase(sheetname))
                       {
                    XSSFSheet sheet=workbook.getSheetAt(i);
                    Iterator<Row> rows=sheet.iterator();
                 while(rows.hasNext())
                     {
            Row r2=rows.next();
                    if (r2.getCell(0).getStringCellValue().equalsIgnoreCase(testcasename))
                     {
                Iterator<Cell> cellvalues=r2.cellIterator();
                 while(cellvalues.hasNext())
                    {
                   String testdata=cellvalues.next().getStringCellValue();
                          arraydata.add(testdata);
               }
            }
          }
        }
      }
                 workbook.close();
             return arraydata;
   }
}

