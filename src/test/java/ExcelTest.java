import org.testng.annotations.Test;

public class ExcelTest {

    @Test(dataProvider = "usersFromSheet1", dataProviderClass = ExcelDataProviders.class)
    public void readExcelSheetDefault(String... params){
        System.out.println("Id: " + params[0] + " Name: " + params[1] + " Surname: " + params[2]);
    }


    @Test(dataProvider = "usersFromSheet2", dataProviderClass = ExcelDataProviders.class)
    public void readExcelSheet2(String params1, String params2){
        System.out.println("Login:" + params1);
        System.out.println("Password:" + params2);
    }
}
