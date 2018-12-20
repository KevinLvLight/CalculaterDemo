package com.example.student.calculaterdemo;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    private boolean clear_flag = false;

    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_0 = (Button) findViewById(R.id.zero);
        Button button_1 = (Button) findViewById(R.id.one);
        Button button_2 = (Button) findViewById(R.id.two);
        Button button_3 = (Button) findViewById(R.id.three);
        Button button_4 = (Button) findViewById(R.id.four);
        Button button_5 = (Button) findViewById(R.id.five);
        Button button_6 = (Button) findViewById(R.id.six);
        Button button_7 = (Button) findViewById(R.id.seven);
        Button button_8 = (Button) findViewById(R.id.eight);
        Button button_9 = (Button) findViewById(R.id.nine);

        Button button_add = (Button) findViewById(R.id.add);
        Button button_minus = (Button) findViewById(R.id.minus);
        Button button_mul = (Button) findViewById(R.id.mul);
        Button button_div = (Button) findViewById(R.id.div);
        Button button_equ = (Button) findViewById(R.id.equ);
        Button button_point = (Button) findViewById(R.id.point);

        Button button_delete = (Button) findViewById(R.id.delete);
        Button button_clear = (Button) findViewById(R.id.clear);

        textView = (TextView) findViewById(R.id.textView);
    }

    public void onClick(View v){
        String str = textView.getText().toString();

        switch (v.getId()){
            case R.id.one:// 若是数字和小数点，则显示出来
            case R.id.two:
            case R.id.three:
            case R.id.four:
            case R.id.five:
            case R.id.six:
            case R.id.seven:
            case R.id.eight:
            case R.id.nine:
            case R.id.zero:
                if(clear_flag) { //若需要清空，设置为空
                    clear_flag = false;
                    str = "";
                    textView.setText("");
                }
                textView.setText(str + ((Button)v).getText()); // 把数字添加到输入框
                break;

            case R.id.point:
                if(clear_flag) {
                    clear_flag = false;
                    str = "";
                    textView.setText("");
                }
                if (str.contains(".")) {// 若已有小数点，无操作
                    return;
                }
                textView.setText(str + ((Button)v).getText()); // 把小数点添加到输入框
                break;

            case R.id.add:// 把加号添加到输入框
            case R.id.minus:
            case R.id.mul:
            case R.id.div:
                if(clear_flag) {
                    clear_flag = false;
                    str = "";
                    textView.setText("");
                }
                if (str.contains(" ")) {// 若已含运算符，无操作（防止出现连续运算符）
                    return;
                }
                textView.setText(str + " " + ((Button)v).getText() + " ");// 运算符前后加空格，添加到输入框
                break;

            case R.id.delete:// 退格键删除最后一位
                if(clear_flag) {
                    clear_flag = false;
                    str = "";
                    textView.setText("");
                }
                if(str!=null && !str.equals("")) { // 不为null或""，删除最后一位
                    textView.setText(str.substring(0, str.length() - 1));
                }
                break;

            case R.id.clear: // 清空键，清屏
                textView.setText("");
                break;

            case R.id.equ:// 若为等号，获取运算结果
                getResult();
                clear_flag = true;// 设置清屏标志位
                break;


        }

    }
    private void getResult() {
        String exp = textView.getText().toString();// 获取输入框的内容

        if(exp == null || exp.equals("")) { // 为空则不作处理
            return;
        }

        if(!exp.contains(" ")) { // 不包含运算符，不作处理
            return;
        }

        double result = 0;// 初始化运算结果
        String s1 = exp.substring(0, exp.indexOf(" ")); // 截取运算符前面的字符串
        String op = exp.substring(exp.indexOf(" ")+1, exp.indexOf(" ")+2); // 截取运算符
        String s2 = exp.substring(exp.indexOf(" ") + 3); // 截取运算符后面的字符串

        if(!s1.equals("") && !s2.equals("")) {// 当 s1 和 s2 均不为空
            double d1 = Double.parseDouble(s1); // 强转为double类型
            double d2 = Double.parseDouble(s2);

            if(op.equals("+")) {
                result = d1 + d2;
            } else if (op.equals("-")) {
                result = d1 - d2;
            } else if (op.equals("*")) {
                result = d1 * d2;
            } else if (op.equals("/")) {
                if(d2 == 0) { // 除数为0，结果设为0
                    result = 0;
                } else {
                    result = d1 / d2;
                }
            }

            if(!s1.contains(".") && !s2.contains(".") && !op.equals("/")) { //若s1和s2都是不包含小数点（即都是整数），且不是除法，输出为整数
                int r = (int)result;
                textView.setText(r + "");
            } else {
                textView.setText(result + "");
            }
        } else if(!s1.equals("") && s2.equals("")) { // s1不为空，s2为空，不作处理
            return;
        } else if((s1.equals("") || s1==null) && !s2.equals("")) { // s1为空，s2不空，把s1当0处理
            double d2 = Double.parseDouble(s2);
            if(op.equals("+")) {
                result = 0 + d2;
            } else if (op.equals("-")) {
                result = 0 - d2;
            } else if (op.equals("*")) {
                result = 0 * d2;
            } else if (op.equals("/")) {
                result = 0;
            }
        } else if(s1.equals("") && s2.equals("")) { // s1、s2都为空
            textView.setText("");
        }
    }
}
