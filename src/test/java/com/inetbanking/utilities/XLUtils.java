package com.inetbanking.utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class XLUtils {

    public static FileInputStream fi;
    public static FileOutputStream fo;
    public static XSSFWorkbook wb;
    public static XSSFSheet ws;
    public static XSSFRow row;
    public static XSSFCell cell;

    public static int getRowCount(String xlfile,String xlsheet) throws IOException{
        fi= new FileInputStream(xlfile);
        wb= new XSSFWorkbook(fi);
        ws=wb.getSheet(xlsheet);
        int rowcount=ws.getLastRowNum();
        wb.close();
        fi.close();
        return rowcount;
    }

    public static int getCellCount(String xlfile,String xlsheet,int rownum) throws IOException{
        fi = new FileInputStream(xlfile);
        wb = new XSSFWorkbook(fi);
        ws=wb.getSheet(xlsheet);
        row= ws.getRow(rownum);
        int cellCount=row.getLastCellNum();
        wb.close();
        fi.close();
        return cellCount;
    }

    public static String getCellData(String xlfile,String xlsheet,int rownum,int cellnum) throws IOException{
        fi = new FileInputStream(xlfile);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlsheet);
        row = ws.getRow(rownum);
        cell= row.getCell(cellnum);
        String cellData ="";
        try{
            DataFormatter formatter = new DataFormatter();
            cellData = formatter.formatCellValue(cell);
        }catch(Exception e){
            System.out.println("There is and error when reading the excell sheet" + e);
        }
        wb.close();
        fi.close();
        return cellData;
    }

    public static void setCellData(String xlfile,String xlsheet,int rownum,int cellnum, String value) throws IOException{
        fi = new FileInputStream(xlfile);
        wb= new XSSFWorkbook(fi);
        ws= wb.getSheet(xlsheet);
        row= ws.getRow(rownum);
        cell=row.getCell(cellnum);
        cell.setCellValue(value);
        fo= new FileOutputStream(xlfile);
        wb.write(fo);
        wb.close();
        fi.close();
        fo.close();
    }


}
