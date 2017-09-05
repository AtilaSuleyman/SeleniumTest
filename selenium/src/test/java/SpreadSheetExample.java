import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

//@RunWith(Parameterized.class)
public class SpreadSheetExample {

    @Test
    public  void spreadSheet(){
        SpreadSheetReader sheetReader = new SpreadSheetReader(System.getProperty("user.dir") + "/src/main/resources/credentials.xlsx");

        int[] numberOfRows = {0,1};
        for (int rowNo : numberOfRows){
             sheetReader.readRow(rowNo, "sheet1");
        }

        List<String> row = sheetReader.readRow(1, "sheet1");

        for(String cell : row){
            System.out.println(cell);
        }


    }


}
