import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Operations {

    public int calculate(int number1, String operationType, int number2){
        int result = 0;
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
