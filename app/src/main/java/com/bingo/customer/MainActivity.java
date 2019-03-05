package com.bingo.customer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button userLevelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userLevelBtn=findViewById(R.id.user_level_btn);
        userLevelBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.user_level_btn:
                Intent intent =new Intent(MainActivity.this,UserLevelActivity.class);
                startActivity(intent);
                break;
                default:
                    break;
        }
    }
}
