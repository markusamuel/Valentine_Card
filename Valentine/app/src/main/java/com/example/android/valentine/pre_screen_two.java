package com.example.android.valentine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Markus on 2/15/2017.
 */
public class pre_screen_two extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pre_screen);
    }

    public void showGreetings(View view){

        Intent intent= new Intent(this,Screen_two.class);
        startActivity(intent);



    }
}
