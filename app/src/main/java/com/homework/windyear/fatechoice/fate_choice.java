package com.homework.windyear.fatechoice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class fate_choice extends AppCompatActivity {
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose);
        // 通过intent获取上一个activity传过来的主题名字的数据
        Intent intent = getIntent();
        String theme_name = intent.getStringExtra("theme_name");
        // 设置主题的名字
        TextView tv_theme_name = findViewById(R.id.fate_choice_name);
        tv_theme_name.setText(theme_name);
        // 通过sharedprefer
        SharedPreferences theme = getSharedPreferences("theme", MODE_PRIVATE);
        // SharedPreferences.Editor editor = theme.edit();
        // 首先获取主题对应的选项
        String choices = theme.getString(theme_name, null);
        // 如果有内容，逐个添加checkbox
        if(choices != null){
            // 使用/要加上双反斜杠，|是转义字符。提取出所有选项
            String[] all_theme_name = choices.split("\\|");
            LinearLayout linearLayout = findViewById(R.id.choice_linear_layout);
            for(int i = 0; i < all_theme_name.length; i++){
                TextView btn = new TextView(fate_choice.this);
                btn.setText(all_theme_name[i]);
                // 不用设置按钮居中
                // btn.setGravity(Gravity.CENTER_HORIZONTAL);
                linearLayout.addView(btn);
            }
            // 最后加一个进度条
            ProgressBar progressBar = new ProgressBar(fate_choice.this);
            // 设置不显示
            progressBar.setVisibility(View.INVISIBLE);
            linearLayout.addView(progressBar);
        }
        // 绑定一个随机选择的事件
        Button btn_choose = findViewById(R.id.btn_start_choose);
        btn_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout linearLayout = findViewById(R.id.choice_linear_layout);
                // 子控件的数量
                int number_of_choice = linearLayout.getChildCount();
                // 显示进度条
                View view = linearLayout.getChildAt(number_of_choice-1);
                if (view instanceof ProgressBar){
                    ProgressBar progressBar = (ProgressBar)view;
                    progressBar.setVisibility(View.VISIBLE);
                }
                // 减去两个控件
                number_of_choice -= 2;
                // 直接循环选择checkbox,显示为循环的状态。
                // 这种方式有问题。
                // 获取所有的选择子控件
//                int loop = 100;
//                while(loop > 0){
//                    for(int i = 0; i < number_of_choice; i++){
//                        View view = linearLayout.getChildAt(i+2);
//                        if (view instanceof CheckBox){
//                            TextView cb = (CheckBox)view;
//                            cb.setChecked(true);
//                            try {
//                                Thread.sleep(1000);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                            cb.setChecked(false);
//                        }
//                    }
//                    loop -= 1;
//                }
                // 使用一个handler延迟执行
                handler.postDelayed(runnable, 2000);

            }
        });
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            LinearLayout linearLayout = findViewById(R.id.choice_linear_layout);
            // 子控件的数量
            int number_of_choice = linearLayout.getChildCount();
            // 不显示进度条
            View view1 = linearLayout.getChildAt(number_of_choice-1);
            if (view1 instanceof ProgressBar){
                ProgressBar progressBar = (ProgressBar)view1;
                progressBar.setVisibility(View.INVISIBLE);
            }
            number_of_choice -= 3;
            // 随机选择一个答案并且显示
            Random random = new Random();
            int select = random.nextInt(number_of_choice);
            View view2 = linearLayout.getChildAt(select+2);
            if (view2 instanceof TextView){
                TextView cb = (TextView) view2;
                cb.setBackgroundColor(0xff0099cc);
            }
        }
    };
}
