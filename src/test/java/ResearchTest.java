/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import guru.engine.WebOperation;
import guru.read.excel.ReadExcelFile;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 *
 * @author kohli
 */
public class ResearchTest {
      public WebDriver webdriver;
    public ReadExcelFile file;
    public WebOperation operation;
    public Sheet excelSheet;
    int rowCount;
    
    
    @BeforeTest
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\Data\\chromedriver.exe");

    }
 @Test
    public void ResearchTest() throws IOException, Exception {

        webdriver = new ChromeDriver();
        webdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        file = new ReadExcelFile();
        operation = new WebOperation(webdriver);

        //Read Excel Sheet
        excelSheet = file.readExcel("C:\\Users\\kohli\\OneDrive\\Documents\\NetBeansProjects\\KeyWordDrivenGuru\\src\\main\\java\\guru\\excel\\files\\Research.xlsx", "Sheet1");
//                     
        // Find number Of Rows
        rowCount = excelSheet.getLastRowNum();

        //Read Excel File And Perform Test
        for (int i = 1; i < rowCount + 1; i++) {

            //loop to read each row in Excel
            Row row = excelSheet.getRow(i);

            //Check if the first cell contain a value, if yes, That means it is the new testcase name
            if (row.getCell(0).toString().equalsIgnoreCase("NA")) {

                //Print testcase detail on console
                // System.out.println(row.getCell(1).toString() + "----" + row.getCell(2).toString() + "----"
                //+ row.getCell(3).toString() + "----" + row.getCell(4).toString());
                //Call perform function to perform operation on UI
                operation.perform(row.getCell(1).toString(), row.getCell(2).toString(),
                        row.getCell(3).toString(), row.getCell(4).toString());

            }
        }
    }
}
