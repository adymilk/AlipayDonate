package com.adymilk.alipaydonate;

import android.content.Intent;
import android.net.Uri;
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
        Button button4 = (Button) findViewById(R.id.button4);



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

//                    用户输入不为空的情况下判断
                    if (!(Url.isEmpty())) {
//                        用户输入不合法的情况判断
                        if (Url.length()>44){
                            String substring = Url.substring(0,22);
                            if (substring.equals("HTTPS://QR.ALIPAY.COM/")){
                                String pay_key = Url.substring(22,Url.length());
//                            System.out.println(pay_key);
                                AlipayZeroSdk.startAlipayClient(MainActivity.this, pay_key);
                            }else {
                                Toast.makeText(MainActivity.this, "url 不合法！", Toast.LENGTH_SHORT).show();
//                            System.out.println(substring);
                            }
                        }else{
                            Toast.makeText(MainActivity.this, "url 不合法！", Toast.LENGTH_SHORT).show();
                        }
//                       用户输入为空的情况下提示
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

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                joinQQGroup("JXqo5mGe-tD5-KJEhvumvUuMJnweqmA4");
            }
        });

    }

    /****************
     *
     * 发起添加群流程。群号：超级脚本软件交流群(539300032) 的 key 为： JXqo5mGe-tD5-KJEhvumvUuMJnweqmA4
     * 调用 joinQQGroup(JXqo5mGe-tD5-KJEhvumvUuMJnweqmA4) 即可发起手Q客户端申请加群 超级脚本软件交流群(539300032)
     *
     * @param key 由官网生成的key
     * @return 返回true表示呼起手Q成功，返回fals表示呼起失败
     ******************/
    public boolean joinQQGroup(String key) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D" + key));
        // 此Flag可根据具体产品需要自定义，如设置，则在加群界面按返回，返回手Q主界面，不设置，按返回会返回到呼起产品界面    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            startActivity(intent);
            return true;
        } catch (Exception e) {
            // 未安装手Q或安装的版本不支持
            return false;
        }
    }

}

