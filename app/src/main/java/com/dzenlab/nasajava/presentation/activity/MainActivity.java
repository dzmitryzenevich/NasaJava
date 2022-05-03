package com.dzenlab.nasajava.presentation.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.dzenlab.nasajava.R;
import com.dzenlab.nasajava.presentation.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);

        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null) {

            actionBar.hide();
        }

        if (savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }
    }
}