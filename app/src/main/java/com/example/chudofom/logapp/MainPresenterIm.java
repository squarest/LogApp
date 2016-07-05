package com.example.chudofom.logapp;

/**
 * Created by Chudofom on 04.07.16.
 */
public class MainPresenterIm implements MainPresenter {
    MainView mainView;

    public MainPresenterIm(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void butClicked() {
        mainView.showInf(mainView.getEmail(),mainView.getPassword());
    }
}
