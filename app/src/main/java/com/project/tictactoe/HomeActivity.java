/***
 *@Author Julio Argutea
 *Date: 2/26/2018
 */
package com.project.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//constructor
public class HomeActivity extends AppCompatActivity {

    //variables
    private EditText answer;
    private Button start;


    //methods
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //typecast
        answer = (EditText) findViewById(R.id.answer);
        start = (Button) findViewById(R.id.start);
        inIt();
    }


    public void inIt() {
        //clickListener when you click on start
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = answer.getText().toString().trim();

                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                Bundle bundle = new Bundle();

                bundle.putString("NAME", string);
                intent.putExtras(bundle);

                if (string.equals("")) {
                    string = "(empty)";
                }

                startActivity(intent);
            }
        });
    }
}

