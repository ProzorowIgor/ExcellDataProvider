import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

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

    @Test(dataProvider = "usersForApi", dataProviderClass = ExcelDataProviders.class)
    public void checkUsers(String... params){
        int id = (int) Double.parseDouble(params[0]);
        Response response = given()
                .contentType(ContentType.JSON)
                .get("https://reqres.in/api/users/"+id)
                .then().log().body().extract().response();
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.getInt("data.id"),id);
        Assert.assertEquals(jsonPath.getString("data.email"),params[1]);
        Assert.assertEquals(jsonPath.getString("data.first_name"),params[2]);
        Assert.assertEquals(jsonPath.getString("data.last_name"),params[3]);
        Assert.assertEquals(jsonPath.getString("data.avatar"),params[4]);
    }





}
