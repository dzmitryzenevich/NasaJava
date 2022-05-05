package com.dzenlab.nasajava.presentation.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import com.dzenlab.nasajava.R;
import com.dzenlab.nasajava.presentation.fragment.MainFragment;
import com.dzenlab.nasajava.presentation.utils.BackPressedListener;

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

    @Override
    public void onBackPressed() {

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);

        if(fragment instanceof BackPressedListener) {

            BackPressedListener backPressedListener = (BackPressedListener) fragment;

            if(backPressedListener.isCloseApp()) {

                super.onBackPressed();
            }

        } else {

            super.onBackPressed();
        }
    }
}