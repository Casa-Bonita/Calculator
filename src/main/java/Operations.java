import java.text.DecimalFormat;
import java.util.List;

public class Operations {
    int splitter = 0;
    double result = 0.0;
    String formattedResult = "";

    public String calculate(List<String> listData, String operationType){

        for (int i = 0; i < listData.size(); i++) {
            System.out.println(listData.get(i));
            if(listData.get(i).equals(operationType)){
                splitter = i;
            }
        }

        double number1 = Double.parseDouble(listData.get(splitter - 1));

        double number2 = Double.parseDouble(listData.get(listData.size() - 1));

        switch (operationType){
            case "+":
                result = number1 + number2;
                formattedResult = format(result);
                break;
            case "-":
                result = number1 - number2;
                formattedResult = format(result);
                break;
            case "*":
                result = number1 * number2;
                formattedResult = format(result);
                break;
            case "/":
                result = number1 / number2;
                formattedResult = format(result);
                break;
        }

        return formattedResult;
    }

    public String format (double result){
        String tempResult = new DecimalFormat("#0.000").format(result);
        return tempResult.replace(',', '.');
    }
}
