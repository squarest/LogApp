package com.example.chudofom.logapp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.example.chudofom.logapp.databinding.ActivityMainBinding;
import com.jakewharton.rxbinding.widget.RxTextView;

import rx.Observable;

public class MainActivity extends ActionBarActivity implements MainView {
    ActivityMainBinding binder;
    MainPresenterIm presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binder = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binder.setLoginbool(false);
        setListeners();
        presenter = new MainPresenterIm(this);
        binder.button.setOnClickListener(v -> presenter.butClicked());
    }

    public void setListeners() {

        Observable<Boolean> emailValidation = RxTextView.textChanges(binder.editText)
                .map(charSequence -> charSequence.toString())
                .map(s -> s.matches("^.+@.+$"));

        Observable<Boolean> pasValidation = RxTextView.textChanges(binder.editText2)
                .map(charSequence -> charSequence.toString())
                .map(s -> s.length() > 5 && s.matches(".*\\d+.*"));

        Observable.combineLatest(emailValidation, pasValidation, (aBoolean, aBoolean2) -> aBoolean && aBoolean2)
                .subscribe(b -> binder.setLoginbool(b));
    }

    @Override
    public String getEmail() {
        return binder.editText.getText().toString();
    }

    @Override
    public String getPassword() {
        return binder.editText2.getText().toString();
    }


    @Override
    public void showInf(String email, String password) {
        final String text = String.format("email: %s,\npassword: %s", email, password);
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }
}