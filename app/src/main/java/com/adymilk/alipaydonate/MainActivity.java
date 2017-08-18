package com.adymilk.alipaydonate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import moe.feng.alipay.zerosdk.AlipayZeroSdk;

import static moe.feng.alipay.zerosdk.AlipayZeroSdk.hasInstalledAlipayClient;
import static moe.feng.alipay.zerosdk.AlipayZeroSdk.openAlipayBarcode;
import static moe.feng.alipay.zerosdk.AlipayZeroSdk.openAlipayScan;
import static moe.feng.alipay.zerosdk.AlipayZeroSdk.startAlipayClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.tv1);
       final EditText editText = (EditText) findViewById(R.id.edit_text);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);



        /**
         *判断是否安装支付宝客户端
         */
        if(!hasInstalledAlipayClient(MainActivity.this)){
            textView.setText("您未安装支付宝！无法使用本程序！！！");
            editText.setEnabled(false);
            button1.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(false);
        }else{
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String Url = editText.getText().toString();
                    if (!(Url.isEmpty())) {
                       String pay_key = Url.substring(22,Url.length());
                        System.out.println(pay_key);
                        AlipayZeroSdk.startAlipayClient(MainActivity.this, pay_key);
                    }else {
                        Toast.makeText(MainActivity.this, "不能为空！", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

        /**
         *打开支付宝扫一扫界面
         */
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAlipayScan(MainActivity.this);
            }
        });

        /**
         * 打开支付宝付款码
         */
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAlipayBarcode(MainActivity.this);
            }
        });





    }
}

