package com.homework.windyear.fatechoice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 这里跳转到添加主题的activity
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(MainActivity.this, add_choice_theme.class);
                startActivity(intent);
            }
        });
        // Button button = (Button)findViewById(R.id.button);
        // 绑定一个时间监听器，跳转到另外一个activity
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, fate_choice.class);
//                startActivity(intent);
//            }
//        });

        // 动态地添加Button和点击事件
        // 采用 sharedpreferences 进行存储，分别存储主题名字，然后根据主题的名字存储
        SharedPreferences theme = getSharedPreferences("theme", MODE_PRIVATE);
        SharedPreferences.Editor editor = theme.edit();
        // 首先获取主题的名字
        String theme_name = theme.getString("theme", null);
        if(theme_name == null){
            return;
        }
        else{
            String[] all_theme_name = theme_name.split("|");
            LinearLayout linearLayout = findViewById(R.id.themes);
            for(int i = 0; i < all_theme_name.length; i++){
                Button btn = new Button(MainActivity.this);
                btn.setText(all_theme_name[i]);
                // 设置按钮居中
                btn.setGravity(Gravity.CENTER_HORIZONTAL);
                // 设置事件监听
                btn.setOnClickListener(click_to_choose);
                linearLayout.addView(btn);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // 一个通用的单击事件，对应每个不同的主题，进入选择activity的时候传入对应主题的名字
    private View.OnClickListener click_to_choose = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, fate_choice.class);
            Button btn = (Button)v;
            // 通过intent传递主题名字到另外一个activity
            String theme_name = btn.getText().toString();
            intent.putExtra("theme_name", theme_name);
            startActivity(intent);
        }
    };
}
