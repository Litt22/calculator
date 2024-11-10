package com.example.calc3;

import android.text.TextUtils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CalculatorViewModel extends ViewModel {

    private final MutableLiveData<String> display = new MutableLiveData<>("0");
    public LiveData<String> getDisplay(){return display;}

    String currentInput2 = "";
    String previousInput1 = "";
    String operator = "";


    void clear(){
        currentInput2 = "";
        previousInput1 = "";
        operator = "";
        display.setValue("0");
    }

    void appendInput(String value){
        if (value.equals(".") && currentInput2.contains(".")){
            return;
        }
        if (TextUtils.equals(currentInput2, "0") && !value.equals(".")){//протестировать - ввести после нуля сразу точку
            currentInput2=value;
        } else {
            currentInput2+=value;

        }
        display.setValue(currentInput2);
    }

    void deleteInput(){
        if (!currentInput2.isEmpty()) {
            currentInput2 = currentInput2.substring(0, currentInput2.length() - 1);
            display.setValue(currentInput2.isEmpty() ? "0" : currentInput2);
        }

    }
    void setOperator(String op){
        if (!currentInput2.isEmpty()){
            previousInput1=currentInput2;
            currentInput2="";
        }
        operator = op;
    }
    void calcRes() {
        if (!previousInput1.isEmpty() && !currentInput2.isEmpty() && !operator.isEmpty()) {
            try {
                double number1 = Double.parseDouble(previousInput1);
                double number2 = Double.parseDouble(currentInput2);
                double res = 0;
                switch (operator) {
                    case "+":
                        res = number1 + number2;
                        break;
                    case "-":
                        res = number1 - number2;
                        break;
                    case "delete":
                        deleteInput();
                    case "/":
                        if (number2 == 0) {
                            display.setValue("error");
                            return;
                        } else res = number1 / number2;
                        break;
                    case "*":
                        res = number1 * number2;
                        break;
                    default:
                        break;
                }
                //display.setValue(String.valueOf(res));
                operator="";
                previousInput1=String.valueOf(res);
                currentInput2=formatResult(res);
                display.setValue(currentInput2);


            } catch (NumberFormatException e) {
                display.setValue("неверный формат вводa");
            }
        }
    }
    private String formatResult(double value) {
        if (value % 1 == 0) {
            return String.format("%.0f", value);
        } else {
            return String.format("%.2f", value);
        }
    }
}
