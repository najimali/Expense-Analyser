package com.example.zishanchouhan.expenseanalyser.Expensesclass;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zishanchouhan.expenseanalyser.MainActivity;
import com.example.zishanchouhan.expenseanalyser.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Zishan Chouhan on 16-Jul-18.
 */

public class ExMain extends AppCompatActivity implements View.OnClickListener {

    public static final String DATABASE_NAME = "expenses";
    TextView textViewViewEmployees;
    EditText editTextAmount;
    Spinner spinner1, spinner2;
    SQLiteDatabase mDatabase;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_expense);

        editTextAmount = (EditText) findViewById(R.id.edittext1);
        spinner1 = (Spinner) findViewById(R.id.spinner);
        Calendar calendar = Calendar.getInstance();
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        TextView dateView = (TextView) findViewById(R.id.textview3);
        dateView.setText(currentDate);
        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        createEmployeeTable();
    }

    private void createEmployeeTable() {
        mDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS table_one (\n" +
                        "    id INTEGER NOT NULL CONSTRAINT employees_pk PRIMARY KEY AUTOINCREMENT,\n" +
                        "    category varchar(200) NOT NULL,\n" +
                        "    joiningdate datetime NOT NULL,\n" +
                        "    amount double NOT NULL\n" +
                        ");"

        );
    }

    private boolean inputsAreCorrect(String name, String salary) {
        if (name.isEmpty()) {
            editTextAmount.setError("Please enter a Amount");
            editTextAmount.requestFocus();
            return false;
        }


        return true;
    }


    @Override
    public void onClick(View view) {
    }
    private void addEmployee() {


        String amount = editTextAmount.getText().toString().trim();
        String cat = spinner1.getSelectedItem().toString();

        //getting the current time for joining date
//        Calendar cal = Calendar.getInstance();
//        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY hh:mm:ss");
//        String joiningDate = sdf.format(cal.getTime());
        Calendar calendar = Calendar.getInstance();
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        //validating the inptus
        if (inputsAreCorrect(amount,cat)) {

            String insertSQL = "INSERT INTO table_one \n" +
                    "(category,joiningdate, amount)\n" +
                    "VALUES \n" +
                    "(?, ?, ?);";

            //using the same method execsql for inserting values
            //this time it has two parameters
            //first is the sql string and second is the parameters that is to be binded with the query
            mDatabase.execSQL(insertSQL, new String[]{cat,currentDate, amount});

            Toast.makeText(this, " Added Successfully", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.actionbar1,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();

        int id = item.getItemId();


        if(id == R.id.save){
            addEmployee();


//
//            Intent in = new Intent(this,MainActivity.class);
//            startActivity(in);

        }
        return super.onOptionsItemSelected(item);
    }

}
