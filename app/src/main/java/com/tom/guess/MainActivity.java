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
    private Button resetButton;
    private Button guessButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.d(TAG, "secret: "+secret);
        findView();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    private void findView() {
        number = findViewById(R.id.inputText);
        message = findViewById(R.id.message);
        resetButton = findViewById(R.id.resetButton);
        guessButton = findViewById(R.id.guessBotton);
    }
    public void reset(View view){
        secret=new Random().nextInt(10)+1;
        Log.d(TAG, "secret: "+secret);
        count=0;
        message.setText("");
        number.setText("");
        resetButton.setVisibility(View.GONE);
        guessButton.setVisibility(View.VISIBLE);
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
                resetButton.setVisibility(View.VISIBLE);
                guessButton.setVisibility(View.GONE);
                count--;
            }
            count++;
            if(count>=4){
                message.setText("太多次了!重來吧!");
                resetButton.setVisibility(View.VISIBLE);
                guessButton.setVisibility(View.GONE);
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
