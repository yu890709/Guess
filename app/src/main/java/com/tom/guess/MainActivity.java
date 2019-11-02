package com.tom.guess;

import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
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
    static int count=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.d(TAG, "secret: "+secret);
        number = findViewById(R.id.inputText);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }
    public void reset(){
        secret=new Random().nextInt(10)+1;
        Log.d(TAG, "secret: "+secret);
        count=0;
        number.setText("");
    }

    public void guess(View view){
        count++;
        String message="答對了\n猜了"+count+"次";
        DialogInterface.OnClickListener listener=new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                reset();
            }
        };
        try {
            int num = Integer.parseInt(number.getText().toString());
            if(num!=secret) {
                if (count >= 3) {
                    message = "太多次了!重來吧!";
                } else {
                    if (num > secret) {
                        message = "小一點\n剩" + (3 - count) + "次";
                        listener = null;
                    } else if (num < secret) {
                        message = "大一點\n剩" + (3 - count) + "次";
                        listener = null;
                    }
                }
            }
            showAltertDialog(message, listener);
        }catch(Exception i){
            count--;
            message="請輸入數字!";
            listener=null;
            showAltertDialog(message, listener);
        }
    }
    private void showAltertDialog(String message, DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("訊息")
                .setMessage(message)
                .setPositiveButton("OK",listener)
                .show();
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
