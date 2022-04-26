import org.testng.annotations.DataProvider;

public class ExcelDataProviders {
    @DataProvider
    public Object[][] usersFromSheet1() throws Exception {
        String path = "src/main/resources/users-QT-IL-LT0253.xlsx";
        ExcelReader excelReader = new ExcelReader(path);
        return excelReader.getSheetDataForTDD();
    }


    @DataProvider
    public Object[][] usersFromSheet2() throws Exception {
        String path = "src/main/resources/users-QT-IL-LT0253.xlsx";
        ExcelReader excelReader = new ExcelReader(path,"Sheet2");
        return excelReader.getCustomSheetForTDD();
    }

   /* @DataProvider
    public Object[][] usersForApi() throws Exception{
        String path = "src/main/resources/usersForReqres.xlsx";
        ExcelReader excelReader = new ExcelReader(path);
        return excelReader.getSheetDataForTDD();
    }*/


}
