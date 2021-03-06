package com.example.zishanchouhan.expenseanalyser.Expensesclass;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zishanchouhan.expenseanalyser.Expenses;
import com.example.zishanchouhan.expenseanalyser.MainActivity;
import com.example.zishanchouhan.expenseanalyser.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by Zishan Chouhan on 16-Jul-18.
 */

public class Adapter_Expenses extends ArrayAdapter<ExpensesModel>{

    Context mCtx;
    int listLayoutRes;
    List<ExpensesModel> employeeList;
    SQLiteDatabase mDatabase;


    public Adapter_Expenses(Context mCtx, int listLayoutRes, List<ExpensesModel> employeeList ,SQLiteDatabase mDatabase) {
        super(mCtx, listLayoutRes, employeeList);

        this.mCtx = mCtx;
        this.listLayoutRes = listLayoutRes;
        this.employeeList = employeeList;
        this.mDatabase = mDatabase;
    }
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(listLayoutRes, null);
        final ExpensesModel expenses = employeeList.get(position);


        TextView textViewcat = view.findViewById(R.id.category);
        TextView textViewamount= view.findViewById(R.id.amount);
        TextView textViewJoiningDate = view.findViewById(R.id.textView);


        textViewcat.setText(expenses.getCategory());
        textViewamount.setText(String.valueOf(expenses.getAmount()));
        textViewJoiningDate.setText(expenses.getJoiningDate());



        view.findViewById(R.id.deletebtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteEmployee(expenses);

            }
        });
        //  Button buttonDelete = view.findViewById(R.id.buttonDeleteEmployee);
        Button buttonEdit = view.findViewById(R.id.buttonedit);



        //adding a clicklistener to button
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateEmployee(expenses);
            }
        });

        return view;
    }
    private void updateEmployee(final ExpensesModel expenses) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.update, null);
        builder.setView(view);
        final AlertDialog alertDialog=builder.create();
        alertDialog.show();


        final EditText editTextamount = view.findViewById(R.id.edittext1);
        final Spinner spinner1 = view.findViewById(R.id.spinner);

        Calendar calendar = Calendar.getInstance();
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());


        TextView dateView = (TextView)view.findViewById(R.id.textview3);
        dateView.setText(currentDate);


        editTextamount.setText(String.valueOf(expenses.getAmount()));



        view.findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amount = editTextamount.getText().toString().trim();
                String cat = spinner1.getSelectedItem().toString() .trim();

                String sql = "UPDATE table_one SET category = ?,amount = ? WHERE id = ?";

                mDatabase.execSQL(sql, new String[]{cat, amount, String.valueOf(expenses.getId())});
                Toast.makeText(mCtx, "Updated Successfully", Toast.LENGTH_SHORT).show();
                reloadEmployeesFromDatabase();

                alertDialog.dismiss();
            }
        });


    }
    private void deleteEmployee(final ExpensesModel expenses){
        AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
        builder.setTitle("Are you sure?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String sql = "DELETE FROM table_one WHERE id = ?";
                mDatabase.execSQL(sql, new Integer[]{expenses.getId()});
                ((Activity)getContext()).finish();
                getContext().startActivity(new Intent(getContext(),MainActivity.class));
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
   //     reloadEmployeesFromDatabase();
      //  Intent i=new Intent(getApplicaBaseContext(),ExMain.class);
      //  startActivity(i);
    }






    private void reloadEmployeesFromDatabase() {
       try {
           String sql = "SELECT * FROM table_one";
           Cursor cursor = mDatabase.rawQuery(sql, null);
           employeeList.clear();
           while (cursor.moveToNext()) {


               employeeList.add(new ExpensesModel(
                       cursor.getInt(0),
                       cursor.getString(1),
                       cursor.getString(2),
                       cursor.getDouble(3)
               ));
           }

           cursor.close();
       }catch(Exception x){Toast.makeText(getContext(),x.getMessage()+"",Toast.LENGTH_LONG).show();}
       // notifyDataSetChanged();
    }



}
