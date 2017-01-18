package com.example.praneethambati.bearcatsilencer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button locationBTN;
    EditText locationsSelectedET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationBTN = (Button) findViewById(R.id.locationBTN);


        locationBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,LocationsActivity.class);
                startActivityForResult(i,1);
            }
        });


    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        locationsSelectedET = (EditText) findViewById(R.id.editText);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                String stredittext=data.getStringExtra("locations");
                locationsSelectedET.setText(stredittext);

            }
        }
    }


}
