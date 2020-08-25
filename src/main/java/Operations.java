import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class Operations {
    int splitter = 0;

    public int calculate(List<String> listData, String operationType){
        int result = 0;
        for (int i = 0; i < listData.size(); i++) {
            System.out.println(listData.get(i));
            if(listData.get(i).equals(operationType)){
                splitter = i;
            }
        }

        int number1 = Integer.parseInt(listData.get(splitter - 1));

        int number2 = Integer.parseInt(listData.get(listData.size() - 1));

        switch (operationType){
            case "+":
                result = number1 + number2;
                break;
            case "-":
                result = number1 - number2;
                break;
            case "*":
                result = number1 * number2;
                break;
            case "/":
                result = number1 / number2;
                break;
        }

        return result;

    }
}
