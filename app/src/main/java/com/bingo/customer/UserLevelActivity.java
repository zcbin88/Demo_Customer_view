package com.bingo.customer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bingo.customer.view.UserLevelDial;
import com.bingo.customer.view.UserLevelView;

import java.util.ArrayList;

public class UserLevelActivity extends AppCompatActivity {

    UserLevelView userLevelDial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_level);
        userLevelDial=findViewById(R.id.user_level_view);
        initLevel();
        userLevelDial.setCurrentDialvalue(700);
    }

    public void initLevel() {
        ArrayList<UserLevelDial> datas = new ArrayList<UserLevelDial>();

        UserLevelDial dial = new UserLevelDial();
        dial.dial_s = 350;
        dial.dial_e = 550;
        datas.add(dial);
        UserLevelDial dial2 = new UserLevelDial();
        dial2.dial_s = 551;
        dial2.dial_e = 600;
        datas.add(dial2);
        UserLevelDial dial3 = new UserLevelDial();
        dial3.dial_s = 601;
        dial3.dial_e = 650;
        datas.add(dial3);
        UserLevelDial dial4 = new UserLevelDial();
        dial4.dial_s = 651;
        dial4.dial_e = 700;
        datas.add(dial4);
        UserLevelDial dial5 = new UserLevelDial();
        dial5.dial_s = 701;
        dial5.dial_e = 950;
        datas.add(dial5);
        userLevelDial.setData(datas);
    }

}
