package com.homework.windyear.fatechoice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class add_choice_theme extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_choice_theme);
        //通过单击按钮添加plate text用于增加选项
        Button btn_add_choice = findViewById(R.id.button_add_choice);
        btn_add_choice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取线性布局
                LinearLayout linearLayout = findViewById(R.id.linear_choice);
                // 新建一个plain text
                EditText editText = new EditText(add_choice_theme.this);
                editText.setHint("请输入你的神圣选择！");
                linearLayout.addView(editText);
            }
        });

        Button bun_save_choice = findViewById(R.id.button_save_theme);
        bun_save_choice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout linearLayout = findViewById(R.id.linear_choice);
                String choice = "";
                for( int i = 0; i < linearLayout.getChildCount(); i++){
                    View viewChildren = linearLayout.getChildAt(i);
                    if(viewChildren instanceof  EditText){
                        EditText editText = (EditText)viewChildren;
                        // 选项之间使用|分隔开
                        if(choice != ""){
                            choice += "|";
                        }
                        choice += editText.getText().toString();
                    }
                }
                // 采用 sharedpreferences 进行存储，分别存储主题名字，然后根据主题的名字存储
                SharedPreferences theme = getSharedPreferences("theme", MODE_PRIVATE);
                SharedPreferences.Editor editor = theme.edit();

                EditText theme_text = findViewById(R.id.edit_theme);
                String theme_name = theme_text.getText().toString();
                if ((theme_name == "") || (choice == "")) {
                    Toast.makeText(getApplicationContext(), "请填写主题和选项后点击保存主题按钮", Toast.LENGTH_SHORT).show();
                }
                else{
                    // 有一个关键字负责存储所有的主题名字, 相同的key会有问题，应该查看是否已经存在相同的主题。
                    String all_theme_name = theme.getString("theme", null);
                    if(all_theme_name != ""){
                        all_theme_name += "|";
                    }
                    all_theme_name += theme_name;
                    editor.putString("theme", all_theme_name);
                    editor.putString(theme_name, choice);
                    //之前忘记提交了
                    editor.commit();
                    Intent intent = new Intent(add_choice_theme.this, MainActivity.class);
                    startActivity(intent);
                    //提交后返回主界面
                    finish();

                }
                // String save_choice = theme.getString(theme_name, null);
                // Toast.makeText(getApplicationContext(), save_choice, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public  void onBackPressed(){
        Intent intent = new Intent(add_choice_theme.this, MainActivity.class);
        startActivity(intent);
        // 结束当前的activity,等到后面返回时再重新加载
        // 因为下一个activity会改变布局
        finish();
    }
}
