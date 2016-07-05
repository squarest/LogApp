package com.example.chudofom.logapp;

import android.databinding.DataBindingUtil;
import android.databinding.tool.DataBindingBuilder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chudofom.logapp.databinding.ActivityMainBinding;

import java.util.regex.Pattern;

public class MainActivity extends ActionBarActivity implements MainView {
    ActivityMainBinding binder;
    MainPresenterIm presenter;
    boolean ready;
    boolean ready2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binder = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binder.setLoginbool(false);
        setListeners();
        presenter=new MainPresenterIm(this);
        binder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.butClicked();
            }
        });
    }

    @Override
    public String getEmail() {
        return binder.editText.getText().toString();
    }

    @Override
    public String getPassword() {
        return binder.editText2.getText().toString();
    }

    private void setListeners() {
        binder.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ready = s.toString().matches("^.+@.+$");
                binder.setLoginbool(ready && ready2);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binder.editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ready2 = s.length() > 5 && s.toString().matches(".*\\d+.*");
                binder.setLoginbool(ready && ready2);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @Override
    public void showInf(String email, String password) {
        final String text = String.format("email: %s,\npassword: %s", email, password);
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }
}