package com.onsite.utilities_page;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	private final String path;

	public ExcelUtils(String path) {
        this.path = path;
    }

    public int getRowCount(String sheetName) throws IOException {
        try (FileInputStream fis = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            XSSFSheet sheet = workbook.getSheet(sheetName);
            return (sheet != null) ? sheet.getLastRowNum() : 0;
        }
    }

    public int getCellCount(String sheetName, int rownum) throws IOException {
        try (FileInputStream fis = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            XSSFSheet sheet = workbook.getSheet(sheetName);
            XSSFRow row = (sheet != null) ? sheet.getRow(rownum) : null;
            return (row != null) ? row.getLastCellNum() : 0;
        }
    }

    public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
        try (FileInputStream fis = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            XSSFSheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) return "";
            XSSFRow row = sheet.getRow(rownum);
            if (row == null) return "";
            XSSFCell cell = row.getCell(colnum);
            if (cell == null) return "";

            DataFormatter formatter = new DataFormatter();
            return formatter.formatCellValue(cell);
        }
    }

    public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {
        File file = new File(path);
        XSSFWorkbook workbook;

        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(path)) {
                workbook = new XSSFWorkbook(fis);
            }
        } else {
            workbook = new XSSFWorkbook();
        }

        XSSFSheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) sheet = workbook.createSheet(sheetName);

        XSSFRow row = sheet.getRow(rownum);
        if (row == null) row = sheet.createRow(rownum);

        XSSFCell cell = row.createCell(colnum);
        cell.setCellValue(data);

        try (FileOutputStream fos = new FileOutputStream(path)) {
            workbook.write(fos);
        }
        workbook.close();
    }
}
