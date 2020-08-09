package com.example.recyclerview.ViewUI;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import com.example.recyclerview.R;

public class HomeScreen extends AppCompatActivity implements mainFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}