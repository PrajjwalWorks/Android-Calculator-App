package com.example.calculatorv2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    String oldno;
    String op=null;
    boolean isnewop=true;

    Button one,two,three,four,five,six,seven,eight,nine,zero,clearall,decimal,plus,minus,multiply,divide,equals,percentage,plusminus;
    TextView resulttext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        one=(Button) findViewById(R.id.one);
        two=(Button) findViewById(R.id.two);
        three=(Button) findViewById(R.id.three);
        four=(Button) findViewById(R.id.four);
        five=(Button) findViewById(R.id.five);
        six=(Button) findViewById(R.id.six);
        seven=(Button) findViewById(R.id.seven);
        eight=(Button) findViewById(R.id.eight);
        nine=(Button) findViewById(R.id.nine);
        zero=(Button) findViewById(R.id.zero);
        clearall=(Button) findViewById(R.id.c_btn);
        decimal=(Button) findViewById(R.id.decimal);
        plus=(Button) findViewById(R.id.plus);
        minus=(Button) findViewById(R.id.minus);
        multiply=(Button) findViewById(R.id.multiply);
        divide=(Button) findViewById(R.id.divide);
        equals=(Button) findViewById(R.id.equals);
        percentage=(Button) findViewById(R.id.percentage);
        plusminus=(Button) findViewById(R.id.plusminus);
        resulttext=(TextView) findViewById(R.id.resulttext);
    }

    public void numberevent(View view) {

        view.performHapticFeedback(android.view.HapticFeedbackConstants.KEYBOARD_TAP);

        if(isnewop){
            resulttext.setText("");
        }
        isnewop=false;
        String no=resulttext.getText().toString();
        int viewid=view.getId();
        if(viewid==R.id.one){
            no+="1";
        }
        else if(viewid==R.id.two){
            no+="2";
        }
        else if(viewid==R.id.three){
            no+="3";
        }
        else if(viewid==R.id.four){
            no+="4";
        }
        else if(viewid==R.id.five){
            no+="5";
        }
        else if(viewid==R.id.six){
            no+="6";
        }
        else if(viewid==R.id.seven){
            no+="7";
        }
        else if(viewid==R.id.eight){
            no+="8";
        }
        else if(viewid==R.id.nine){
            no+="9";
        }
        else if(viewid==R.id.zero){
            no+="0";
        }
        else if(viewid==R.id.decimal){
            if (!no.contains(".")) {    // Bugfix: Prevents adding multiple decimal points
                no += ".";
            }
        }
        else if (viewid==R.id.plusminus) {
            if(no.isEmpty() || no.equals(0)){     //Bugfix:Crashes with operations like -----2
                no="0";
            }
            else{
                if(no.startsWith("-")){
                    no=no.substring(1);
                }
                else{
                    no="-"+no;
                }
            }
        }

        resulttext.setText(no);
    }

    public void operatorevent(View view) {

        view.performHapticFeedback(android.view.HapticFeedbackConstants.KEYBOARD_TAP);
        isnewop=true;

        oldno = resulttext.getText().toString();

        int viewid=view.getId();

        if(viewid==R.id.plus){
            op="+";
        }
        else if(viewid==R.id.minus){
            op="-";
        }
        else if(viewid==R.id.multiply){
            op="*";
        }
        else if(viewid==R.id.divide){
            op="/";
        }

    }

    public void resultequalevent(View view) {

        view.performHapticFeedback(android.view.HapticFeedbackConstants.KEYBOARD_TAP);
        if (op == null || oldno == null) {
            return; // No operation to perform
        }
        String newno = resulttext.getText().toString();
        float result = 0;
        if(op.equals("+")){
            result=Float.parseFloat(oldno)+Float.parseFloat(newno);
        }
        else if(op.equals("-")){
            result=Float.parseFloat(oldno)-Float.parseFloat(newno);
        }
        else if(op.equals("*")){
            result=Float.parseFloat(oldno)*Float.parseFloat(newno);
        }
        else if(op.equals("/")){
            result=Float.parseFloat(oldno)/Float.parseFloat(newno);
        }
        resulttext.setText(result+"");
        op = null;  //Bugfix: Crashes when the operator is not  cleared after calculation
        isnewop = true;
    }

    public void clearevent(View view) {

        view.performHapticFeedback(android.view.HapticFeedbackConstants.KEYBOARD_TAP);
        resulttext.setText("0");
        isnewop=true;
    }

    public void percentevent(View view) {

        view.performHapticFeedback(android.view.HapticFeedbackConstants.KEYBOARD_TAP);
        float no = Float.parseFloat(resulttext.getText().toString())/100;
        resulttext.setText(no+"");
        isnewop=true;
    }
}