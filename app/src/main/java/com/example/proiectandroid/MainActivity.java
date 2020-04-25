package com.example.proiectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

import java.text.DecimalFormat;
import java.util.Stack;
import java.lang.Math;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private StringBuilder expression = new StringBuilder();
    private StringBuilder aux = new StringBuilder();
    private TextView textView;
    private Boolean signUsed = false;
    private Boolean resultClicked = false;
    private Boolean equationDone = false;
    private Boolean sinActive = false;
    private Boolean cosActive = false;
    private Boolean tanActive = false;
    private Boolean nlogActive = false;
    private Boolean logActive = false;
    private Boolean radActive = false;
    private Integer functionActive = 0;
    private StringBuilder number = new StringBuilder();
    private Boolean error = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
    }

    public void onClick(View view){
        if(error) {
            textView.setText("");
            error = false;
        }
        switch(view.getId()){
            case R.id.multiply:
                if(functionActive > 0){
                    expression.append(solveCalc());
                    functionActive = 0;
                    number.setLength(0);
                    signUsed = false;
                }
                if(!signUsed) {
                    expression.append("*");
                    signUsed = true;
                    equationDone = false;
                }
                break;
            case R.id.division:
                if(functionActive > 0){
                    expression.append(solveCalc());
                    functionActive = 0;
                    number.setLength(0);
                    signUsed = false;
                }
                if(!signUsed){
                    expression.append("/");
                    signUsed = true;
                    equationDone = false;
                }
                break;
            case R.id.sum:
                if(functionActive > 0){
                    expression.append(solveCalc());
                    functionActive = 0;
                    number.setLength(0);
                    signUsed = false;
                }
                if(!signUsed){
                    expression.append("+");
                    signUsed = true;
                    equationDone = false;
                }
                break;
            case R.id.difference:
                if(functionActive > 0){
                    expression.append(solveCalc());
                    functionActive = 0;
                    number.setLength(0);
                    signUsed = false;
                }
                if(!signUsed){
                    expression.append("-");
                    signUsed = true;
                    equationDone = false;
                }
                break;
            case R.id.modulo:
                if(functionActive > 0){
                    expression.append(solveCalc());
                    functionActive = 0;
                    number.setLength(0);
                    signUsed = false;
                }
                if(!signUsed){
                    expression.append("%");
                    signUsed = true;
                    equationDone = false;
                }
                break;
            case R.id.clear:
                signUsed = false;
                expression.setLength(0);
                textView.setText("0");
                equationDone = true;
                break;
            case R.id.result:
                if(functionActive > 0){
                    expression.append(solveCalc());
                    functionActive = 0;
                    number.setLength(0);
                }
                resultClicked = true;
                solveEquation();
                equationDone = true;
                break;
            case R.id.change_sign:
                aux.append("-1*(");
                aux.append(expression.toString());
                aux.append(")");
                expression.setLength(0);
                expression.append(aux);
                aux.setLength(0);
                break;
            case R.id.sinus:
                sinActive = !sinActive;
                if(sinActive) {
                    functionActive = 1;
                    findViewById(R.id.sinus).getBackground().setColorFilter(0xe0f47521, PorterDuff.Mode.SRC_ATOP);
                }
                else {
                    functionActive = 0;
                    findViewById(R.id.sinus).getBackground().clearColorFilter();
                }
                break;
            case R.id.cosinus:
                cosActive = !cosActive;
                if(cosActive){
                    functionActive = 2;
                    findViewById(R.id.cosinus).getBackground().setColorFilter(0xe0f47521,PorterDuff.Mode.SRC_ATOP);
                }
                else{
                    functionActive = 0;
                    findViewById(R.id.cosinus).getBackground().clearColorFilter();
                }
                break;
            case R.id.tangent:
                tanActive = !tanActive;
                if(tanActive){
                    functionActive = 3;
                    findViewById(R.id.tangent).getBackground().setColorFilter(0xe0f47521,PorterDuff.Mode.SRC_ATOP);
                }
                else {
                    functionActive = 0;
                    findViewById(R.id.tangent).getBackground().clearColorFilter();
                }
                break;
            case R.id.rad:
                radActive = !radActive;
                if(radActive) {
                    functionActive = 4;
                    findViewById(R.id.rad).getBackground().setColorFilter(0xe0f47521, PorterDuff.Mode.SRC_ATOP);
                }
                else {
                    functionActive = 0;
                    findViewById(R.id.rad).getBackground().clearColorFilter();
                }
                break;
            case R.id.simple_log:
                logActive = !logActive;
                if(logActive) {
                    functionActive = 5;
                    findViewById(R.id.simple_log).getBackground().setColorFilter(0xe0f47521, PorterDuff.Mode.SRC_ATOP);
                }
                else {
                    functionActive = 0;
                    findViewById(R.id.simple_log).getBackground().clearColorFilter();
                }
                break;
            case R.id.nlog:
                nlogActive = !nlogActive;
                if(nlogActive){
                    functionActive = 6;
                    findViewById(R.id.nlog).getBackground().setColorFilter(0xe0f47521,PorterDuff.Mode.SRC_ATOP);
                }
                else {
                    functionActive = 0;
                    findViewById(R.id.nlog).getBackground().clearColorFilter();
                }
                break;
            case R.id.open_parenthesis:
                if(sinActive) {
                    expression.append("sin(");
                    sinActive = false;
                    findViewById(R.id.sinus).getBackground().clearColorFilter();
                }
                else if(cosActive){
                    expression.append("cos(");
                    cosActive = false;
                    findViewById(R.id.cosinus).getBackground().clearColorFilter();
                }
                else if(tanActive){
                    expression.append("tan(");
                    tanActive = false;
                    findViewById(R.id.tangent).getBackground().clearColorFilter();
                }
                else if(radActive){
                    expression.append("rad(");
                    radActive = false;
                    findViewById(R.id.rad).getBackground().clearColorFilter();
                }
                else if(logActive){
                    expression.append("log(");
                    logActive = false;
                    findViewById(R.id.simple_log).getBackground().clearColorFilter();
                }
                else if(nlogActive){
                    expression.append("nlog(");
                    nlogActive = false;
                    findViewById(R.id.nlog).getBackground().clearColorFilter();
                }
                else
                    expression.append("(");
                functionActive = 0;
                break;
            case R.id.closed_parenthesis:
                expression.append(")");
                break;
            default:
                if(equationDone){
                    textView.setText("");
                    expression.setLength(0);
                    equationDone = false;
                }
                Button btn = findViewById(view.getId());
                if(functionActive > 0)
                    number.append(btn.getText());
                else{
                    expression.append(btn.getText());
                    signUsed = false;
                }
                break;
        }
        if(!resultClicked && expression.length() > 0){
            if(!equationDone)
                textView.setText(expression.toString());
        }
        else {
            expression.setLength(0);
            expression.append(textView.getText());
            resultClicked = false;
        }
    }

    public String solveCalc(){
        DecimalFormat df = new DecimalFormat("#.##");
        switch (functionActive){
            case 1:
                findViewById(R.id.sinus).getBackground().clearColorFilter();
                return df.format(Math.sin(Double.valueOf(number.toString())));
            case 2:
                findViewById(R.id.cosinus).getBackground().clearColorFilter();
                return df.format(Math.cos(Double.valueOf(number.toString())));
            case 3:
                findViewById(R.id.tangent).getBackground().clearColorFilter();
                return df.format(Math.tan(Double.valueOf(number.toString())));
            case 4:
                findViewById(R.id.rad).getBackground().clearColorFilter();
                return df.format(Math.sqrt(Double.valueOf(number.toString())));
            case 5:
                findViewById(R.id.simple_log).getBackground().clearColorFilter();
                return df.format(Math.log10(Double.valueOf(number.toString())));
            case 6:
                findViewById(R.id.nlog).getBackground().clearColorFilter();
                return df.format(Math.log(Double.valueOf(number.toString())));
            default:
                return "";
        }
    }

    public void solveEquation(){
        if(checkFormula())
            parseEquation();
        else{
            textView.setText("Eroare");
            error = true;
        }
    }

    public void parseEquation(){
        int i = 0;
        DecimalFormat df = new DecimalFormat("#.##");
        StringBuilder number = new StringBuilder();
        Stack<Double> operandStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();
        double leftOperand;
        double rightOperand;

            while (i < expression.length()) {
                if (Character.isDigit(expression.charAt(i))) {
                    while (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.') {
                        number.append(expression.charAt(i));
                        i += 1;
                        if(i == expression.length())
                            break;
                    }
                    operandStack.push(Double.parseDouble(number.toString()));
                    number.setLength(0);
                }
                else if(Character.isLetter(expression.charAt(i))){
                    switch (expression.charAt(i)){
                        case 's':
                            operatorStack.push('A');
                            i += 3;
                            break;
                        case 'c':
                            operatorStack.push('B');
                            i += 3;
                            break;
                        case 't':
                            operatorStack.push('C');
                            i += 3;
                            break;
                        case 'r':
                            operatorStack.push('D');
                            i += 3;
                            break;
                        case 'l':
                            operatorStack.push('E');
                            i += 3;
                            break;
                        case 'n':
                            operatorStack.push('F');
                            i += 4;
                            break;
                         default:
                             break;
                    }
                }
                else if(expression.charAt(i) == '-' && i == 0){
                    number.append('-');
                    i += 1;
                    while(Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.'){
                        number.append(expression.charAt(i));
                        i+=1;
                        if(i == expression.length())
                            break;
                    }
                    operandStack.push(Double.parseDouble(number.toString()));
                    number.setLength(0);
                }
                else if(expression.charAt(i) == '-' && expression.charAt(i-1) == '('){
                    number.append('-');
                    i += 1;
                    while(Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.'){
                        number.append(expression.charAt(i));
                        i+=1;
                        if(i == expression.length())
                            break;
                    }
                    operandStack.push(Double.parseDouble(number.toString()));
                    number.setLength(0);
                }
                else if (expression.charAt(i) == '(') {
                    operatorStack.push('(');
                    i += 1;
                }
                else if (expression.charAt(i) == ')') {
                    while(operatorStack.peek() != '('){
                        rightOperand = operandStack.pop();
                        leftOperand = operandStack.pop();
                        operandStack.push(executeOperation(leftOperand, rightOperand, operatorStack.pop()));
                        i += 1;
                    }
                    operatorStack.pop();
                }
                else {
                    if(operatorStack.size() == 0){
                        operatorStack.push(expression.charAt(i));
                    }
                    else if (evaluatePriority(expression.charAt(i), operatorStack.peek())) {
                        operatorStack.push(expression.charAt(i));
                    } else {
                        rightOperand = operandStack.pop();
                        leftOperand = operandStack.pop();
                        operandStack.push(executeOperation(leftOperand, rightOperand, operatorStack.pop()));
                        operatorStack.push(expression.charAt(i));
                    }
                    i += 1;
                }


            }
        while(operatorStack.size() != 0){
            if(Character.isLetter(operatorStack.peek())){
                System.out.println("Sunt aici");
                switch (operatorStack.peek()){
                    case 'A':
                        operandStack.push(Double.valueOf(df.format(Math.sin(operandStack.pop()))));
                        operatorStack.pop();
                        break;
                    case 'B':
                        operandStack.push(Double.valueOf(df.format(Math.cos(operandStack.pop()))));
                        operatorStack.pop();
                        break;
                    case 'C':
                        operandStack.push(Double.valueOf(df.format(Math.tan(operandStack.pop()))));
                        operatorStack.pop();
                        break;
                    case 'D':
                        operandStack.push(Double.valueOf(df.format(Math.sqrt(operandStack.pop()))));
                        operatorStack.pop();
                        break;
                    case 'E':
                        operandStack.push(Double.valueOf(df.format(Math.log10(operandStack.pop()))));
                        operatorStack.pop();
                        break;
                    case 'F':
                        operandStack.push(Double.valueOf(df.format(Math.log(operandStack.pop()))));
                        operatorStack.pop();
                        break;
                    default:
                        break;
                }
            }
            else {
                rightOperand = operandStack.pop();
                leftOperand = operandStack.pop();
                operandStack.push(executeOperation(leftOperand, rightOperand, operatorStack.pop()));
            }
        }

        if(Math.floor(operandStack.peek()) == operandStack.peek())
            this.textView.setText(String.valueOf((int) operandStack.peek().doubleValue()));

        else
            this.textView.setText(String.valueOf(operandStack.peek()));


    }

    public double executeOperation(double leftOperand, double rightOperand, char operator){
        switch (operator){
            case '+':
                return leftOperand + rightOperand;
            case '-':
                return leftOperand - rightOperand;
            case '*':
                return leftOperand * rightOperand;
            case '/':
                return leftOperand / rightOperand;
            case '%':
                return leftOperand % rightOperand;
            case '^':
                return Math.pow(leftOperand, rightOperand);
            default:
                return 0;
        }
    }

    public boolean evaluatePriority(char operator, char topStack){
        if(operator == '(')
            return true;
        else if((operator == '+' || operator == '-') && topStack != '(')
            return false;
        else if((operator == '*' || operator == '/' || operator == '%') && topStack != '(')
            return topStack == '+' || topStack == '-';
        else
            return topStack != '^';
    }

    public boolean checkFormula(){
        Pattern p = Pattern.compile("[+*/^%].*");
        Matcher m = p.matcher(expression.toString());
        if(m.matches())
            return false;
        p = Pattern.compile(".*\\([+\\-*^/%].*");
        m = p.matcher(expression.toString());
        if(m.groupCount() > 0)
            return false;
        p = Pattern.compile(".*[+\\-*/^%]\\).*");
        m = p.matcher(expression.toString());
        if(m.groupCount() > 0)
            return false;
        p = Pattern.compile(".*/0.*");
        m = p.matcher(expression.toString());
        if(m.groupCount() > 0)
            return false;
        p = Pattern.compile(".*0[0-9]+.*");
        m = p.matcher(expression.toString());
        if(m.groupCount() > 0)
            return false;
        if(expression.toString().equals(""))
            return false;
        return true;
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("lifecycle","onStart invoked");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("lifecycle","onResume invoked");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("lifecycle","onPause invoked");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d("lifecycle","onStop invoked");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("lifecycle","onRestart invoked");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("lifecycle","onDestroy invoked");
    }

}
