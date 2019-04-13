package com.example.zishanchouhan.expenseanalyser;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

public class MainActivity extends TabActivity {
   private TabHost tabHost;
   private TabHost.TabSpec tab1,tab2;
   FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    tabHost=findViewById(android.R.id.tabhost);

     tab1=tabHost.newTabSpec("Expenses");
     tab2=tabHost.newTabSpec("Balance");
     tab1.setIndicator("Expenses");
     tab1.setContent(new Intent(getApplicationContext(),Expenses.class));
     tab2.setIndicator("Balance");
     tab2.setContent(new Intent(getApplicationContext(),Balance.class));
     tabHost.addTab(tab1);
     tabHost.addTab(tab2);

    }
    }



