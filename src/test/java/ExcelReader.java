import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {
    private final String excelFilePath;
    private XSSFSheet sheet;
    private XSSFWorkbook book;
    private String sheetName;


    public ExcelReader(String excelFilePath) throws IOException {
        this.excelFilePath = excelFilePath;
        File file = new File(excelFilePath);
        try{
            FileInputStream fileInputStream = new FileInputStream(file);
            book = new XSSFWorkbook(fileInputStream);
            sheet = book.getSheet("Sheet1");
        } catch (IOException e){
            throw new IOException("Wrong format");
        }
    }

    public ExcelReader(String excelFilePath, String sheetName) throws IOException {
        this.excelFilePath = excelFilePath;
        this.sheetName = sheetName;
        File file = new File(excelFilePath);
        try{
            FileInputStream fileInputStream = new FileInputStream(file);
            book = new XSSFWorkbook(fileInputStream);
            sheet = book.getSheet(sheetName);
        } catch (IOException e){
            throw new IOException("Wrong format");
        }
    }


    private String cellToString(XSSFCell cell) throws Exception {
        Object result = null;
        CellType type = cell.getCellType();
        switch (type){
            case NUMERIC:
                result = cell.getNumericCellValue();
                break;
            case STRING:
                result = cell.getStringCellValue();
                break;
            case FORMULA:
                result = cell.getCellFormula();
                break;
            case BLANK:
                result = "";
                break;
            default: throw new Exception("Error of reading file");
        }
        return result.toString();
    }


    private int xlsxCountColumn(){
        return sheet.getRow(0).getLastCellNum();
    }


    private int xlsxCountRow(){
        return sheet.getLastRowNum() + 1;
    }


    public String[][] getSheetDataForTDD() throws Exception {
        File file = new File(excelFilePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        book = new XSSFWorkbook(fileInputStream);
        sheet = book.getSheet("Sheet1");
        int numberOfColumn = xlsxCountColumn();
        int numberOfRows = xlsxCountRow();
        String[][] data = new String[numberOfRows-1][numberOfColumn];
        for(int i = 1; i<numberOfRows; i++){
            for(int j = 0; j<numberOfColumn; j++){
                XSSFRow row = sheet.getRow(i);
                XSSFCell cell = row.getCell(j);
                String value = cellToString(cell);
                data[i-1][j] = value;
                if(value == null){
                    System.out.println("Empty fields");
                }
            }
        }
        return data;
    }


    public String[][] getCustomSheetForTDD() throws Exception {
        File file = new File(excelFilePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        book = new XSSFWorkbook(fileInputStream);
        sheet = book.getSheet(sheetName);
        int numberOfColumn = xlsxCountColumn();
        int numberOfRows = xlsxCountRow();
        String[][] data = new String[numberOfRows-1][numberOfColumn];
        for(int i = 1; i<numberOfRows; i++){
            for(int j = 0; j<numberOfColumn; j++){
                XSSFRow row = sheet.getRow(i);
                XSSFCell cell = row.getCell(j);
                String value = cellToString(cell);
                data[i-1][j] = value;
                if(value == null){
                    System.out.println("Empty cells");
                }
            }
        }
        return data;
    }
}
