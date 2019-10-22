package com.tom.guess;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int secret=new Random().nextInt(10)+1;
    private EditText number;
    String TAG=MainActivity.class.getSimpleName();
    private TextView message;
    static int count=0;
    private Button resetBotton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.d(TAG, "secret: "+secret);
        number = findViewById(R.id.inputText);
        message = findViewById(R.id.message);
        resetBotton = findViewById(R.id.resetButton);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    public void reset(View view){
        secret=new Random().nextInt(10)+1;
        Log.d(TAG, "secret: "+secret);
        count=0;
        message.setText("");
        number.setText("");
        resetBotton.setVisibility(View.GONE);
    }

    public void guess(View view){
        try {
            int num = Integer.parseInt(number.getText().toString());
            if(num>secret){
                message.setText("小一點");
            }else if(num<secret){
                message.setText("大一點");
            }else{
                message.setText("答對了");
                resetBotton.setVisibility(View.VISIBLE);
            }
        }catch(Exception i){
            message.setText("請輸入數字!!!");
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
}
