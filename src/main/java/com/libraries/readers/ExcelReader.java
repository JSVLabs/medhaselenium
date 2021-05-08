package com.libraries.readers;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelReader {

    public Map<Integer,  Map<String, String>> xlData = null;
    public Map<Integer,  Map<String, String>> xlBp = null;

    public int dataRowCount;
    public int bpCount;

    public int getBpCount() {
        return bpCount;
    }

    public void setBpCount(int bpCount) {
        this.bpCount = bpCount;
    }

    public int getDataRowCount() {
        return dataRowCount;
    }

    public void setDataRowCount(int dataRowCount) {
        this.dataRowCount = dataRowCount;
    }

   /* public void setMapData(String path,int sheetName) throws IOException, FileNotFoundException {
        Cell valueCell = null;
        Cell keyCell=null;


        FileInputStream fis = new FileInputStream(path);

        //Workbook workbook = new HSSFWorkbook(fis);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        XSSFSheet sheet = workbook.getSheetAt(sheetName);

        int lastRow = sheet.getLastRowNum();
        setDataRowCount((lastRow));
        Row firstRow = sheet.getRow(0);
        int lastColum = firstRow.getLastCellNum();

       Map<Integer, Map<String,String>> excelFileMap = new HashMap<Integer, Map<String,String>>();

        Map<String, String> dataMap = new HashMap<String, String>();

        //Looping over entire row
        for(int i=0; i<lastRow; i++){

            Row row = sheet.getRow(i+1);

            for(int j=0;j<lastColum;j++){
                valueCell = row.getCell(j);

                //0th Cell as Key
                keyCell = firstRow.getCell(j);
                //System.out.print(row.getCell(j).getStringCellValue()+"|| ");
                String value = valueCell.getStringCellValue().trim();
                String key = keyCell.getStringCellValue().trim();

                //Putting key & value in dataMap
                dataMap.put(key, value);

            }
            //1st Cell as Value



            //Putting dataMap to excelFileMap
            excelFileMap.put(i+1, dataMap);
        }
        xlData = excelFileMap;

    }

    //Method to retrieve value
    public String getDataByRow(Integer row, String key) throws IOException{

        Map<String, String> m = this.xlData.get(row);
        String value = m.get(key);

        return value;

    }

    public void loadBusinessProcess(String path, int sheetId) throws IOException {
        Cell valueCell = null;
        Cell keyCell=null;
        FileInputStream fis = new FileInputStream(path);

        //Workbook workbook = new HSSFWorkbook(fis);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        XSSFSheet sheet = workbook.getSheetAt(sheetId);

        int lastRow = sheet.getLastRowNum();
        setBpCount((lastRow));
        Row firstRow = sheet.getRow(0);
        int lastColum = firstRow.getLastCellNum();

        Map<Integer, Map<String,String>> excelFileMap = new HashMap<Integer, Map<String,String>>();

        Map<String, String> dataMap = new HashMap<String, String>();

        //Looping over entire row
        for(int i=0; i<lastRow; i++){

            Row row = sheet.getRow(i+1);

            for(int j=0;j<lastColum;j++){
                valueCell = row.getCell(j);

                //0th Cell as Key
                keyCell = firstRow.getCell(j);
                //System.out.print(row.getCell(j).getStringCellValue()+"|| ");
                String value = valueCell.getStringCellValue().trim();
                String key = keyCell.getStringCellValue().trim();

                //Putting key & value in dataMap
                dataMap.put(key, value);

            }
            //1st Cell as Value



            //Putting dataMap to excelFileMap
            excelFileMap.put(i+1, dataMap);
        }
        xlBp = excelFileMap;
    }

    //Method to retrieve value
    public String getBpByRow(Integer row, String key) throws IOException{

        Map<String, String> m = this.xlBp.get(row);
        String value = m.get(key);

        return value;

    }*/

    public Map<Integer,Map<String,String>> loadData(String path, String sheetName) throws IOException {
        Cell valueCell = null;
        Cell keyCell=null;

        //List<String> header = new ArrayList<String>();


        FileInputStream fis = new FileInputStream(path);

        //Workbook workbook = new HSSFWorkbook(fis);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        XSSFSheet sheet = workbook.getSheet(sheetName);

        int lastRow = sheet.getLastRowNum();
        //setDataRowCount((lastRow));
        Row firstRow = sheet.getRow(0);

        int lastColum = firstRow.getLastCellNum();
        String[] header = new String[lastColum];

        for(int col=0;col<lastColum;col++){
            //System.out.println(col);
            header[col] = firstRow.getCell(col).getStringCellValue().trim();
            //System.out.println(header[col]);


        }

        Map<Integer, Map<String,String>> excelFileMap = new HashMap<Integer, Map<String,String>>();



        //Looping over entire row
        for(int i=0; i<lastRow; i++){
            Map<String, String> dataMap = new HashMap<String, String>();
            Row row = sheet.getRow(i+1);

            for(int j=0;j<lastColum;j++){
                valueCell = row.getCell(j);
                String value = "";
                //0th Cell as Key
                //keyCell = firstRow.getCell(j);
                //System.out.print(row.getCell(j).getStringCellValue()+"|| ");
                if(valueCell != null){

                    //System.out.println(valueCell.getCellType());
                    switch (valueCell.getCellType()) {

                        case STRING:
                            value = valueCell.getRichStringCellValue().getString().trim();
                            break;

                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(valueCell)) {
                                value = valueCell.getDateCellValue().toString();
                            } else {
                                value = String.valueOf(valueCell.getNumericCellValue());
                            }
                            break;

                        case BOOLEAN:
                            value = Boolean.toString(valueCell.getBooleanCellValue());
                            break;

                        default:

                    }
                }

                //String key = keyCell.getStringCellValue().trim();
                String key = header[j];
                //Putting key & value in dataMap
                dataMap.put(key, value);

            }
            //1st Cell as Value



            //Putting dataMap to excelFileMap
            excelFileMap.put(i+1, dataMap);
        }
        return excelFileMap;
    }

    public String[] loadBusinessProcess(String path, String sheetName) throws IOException {

        Cell valueCell = null;
        Cell keyCell=null;

        //List<String> header = new ArrayList<String>();


        FileInputStream fis = new FileInputStream(path);

        //Workbook workbook = new HSSFWorkbook(fis);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        XSSFSheet sheet = workbook.getSheet(sheetName);

        int lastRow = sheet.getLastRowNum();
        String[] bps = new  String[lastRow];

        for(int i =0; i<lastRow;i++){
            Row row = sheet.getRow(i+1);
            //bps[i]= row.getCell(0).getStringCellValue().trim();
            bps[i]= row.getCell(1).getStringCellValue().trim();
        }

        return bps;

    }

    public String[] loadPages(String path, String sheetName) throws IOException {

        Cell valueCell = null;
        Cell keyCell=null;

        //List<String> header = new ArrayList<String>();


        FileInputStream fis = new FileInputStream(path);

        //Workbook workbook = new HSSFWorkbook(fis);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        XSSFSheet sheet = workbook.getSheet(sheetName);

        int lastRow = sheet.getLastRowNum();
        String[] bps = new  String[lastRow];

        for(int i =0; i<lastRow;i++){
            Row row = sheet.getRow(i+1);
            bps[i]= row.getCell(0).getStringCellValue().trim();
            //System.out.println (bps[i]);
        }

        return bps;

    }

    public int getRowCount(String path, String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream(path);

        //Workbook workbook = new HSSFWorkbook(fis);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        XSSFSheet sheet = workbook.getSheet(sheetName);

        int lastRow = sheet.getLastRowNum();

        return lastRow;


    }
}
