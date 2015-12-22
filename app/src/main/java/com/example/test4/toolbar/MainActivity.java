package com.example.test4.toolbar;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements TextView.OnEditorActionListener{
    private MenuItem myActionMenuItem;
    private EditText myActionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        myActionMenuItem = menu.findItem(R.id.my_action);
        View actionView = myActionMenuItem.getActionView();
        if(actionView != null) {
            myActionEditText = (EditText) actionView.findViewById(R.id.myActionEditText);

            if(myActionEditText != null) {
             myActionEditText.setOnEditorActionListener(this);
            }
        }

    MenuItemCompat.setOnActionExpandListener(myActionMenuItem, new MenuItemCompat.OnActionExpandListener() {

        @Override
        public boolean onMenuItemActionCollapse(MenuItem item) {
            return true;
        }
        @Override
        public boolean onMenuItemActionExpand(MenuItem item) {
            if(myActionEditText != null) {
                myActionEditText.setText("");
            }

            return true;
        }

    });
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            toggleActionBar();
        }

        return true;
    }
    private void toggleActionBar() {
        ActionBar actionBar = getActionBar();

        if(actionBar != null) {
            if(actionBar.isShowing()) {
                actionBar.hide();
            }
            else {
                actionBar.show();
            }
        }
    }
    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if(keyEvent != null) {

            if(keyEvent.getAction() == KeyEvent.ACTION_DOWN && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                CharSequence textInput = textView.getText();

                Toast.makeText(this, textInput, Toast.LENGTH_SHORT).show();
                MenuItemCompat.collapseActionView(myActionMenuItem);
            }
        }
        return false;
    }


        }

