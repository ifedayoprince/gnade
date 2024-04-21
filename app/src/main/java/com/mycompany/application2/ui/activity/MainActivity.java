package com.mycompany.application2.ui.activity;

import android.os.Bundle;
import com.mycompany.application2.R;
import com.mycompany.application2.common.activity.BaseActivity;
import android.content.Context;

public class MainActivity extends BaseActivity { 

public static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
    }

}
/*don't forget to subscribe my YouTube channel for more Tutorial and mod*/
/*
https://youtube.com/channel/UC_lCMHEhEOFYgJL6fg1ZzQA */
