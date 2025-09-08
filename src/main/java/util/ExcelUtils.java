package util;

import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {
    public static String getCellValue(String filePath, String sheetName, int row, int col) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {
            Sheet sheet = workbook.getSheet(sheetName);
            return sheet.getRow(row).getCell(col).getStringCellValue();
        } catch (IOException e) {
            throw new RuntimeException("Error reading Excel file: " + e.getMessage());
        }
    }
}
