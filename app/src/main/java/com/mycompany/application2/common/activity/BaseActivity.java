package com.mycompany.application2.common.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import com.mycompany.application2.App;
import com.mycompany.application2.R;
import com.mycompany.application2.util.UiUtils;

public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initStatusBar();
        initActionBar();
    }

    protected void initStatusBar() {
        UiUtils.setStatusBarLightMode(this, getResources().getBoolean(R.bool.light_status_bar));
    }

    protected void initActionBar() {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(canBack());
        }
    }

    public boolean canBack() {
        return false;
    }

    public App getApp() {
        return App.getApp();
    }

    protected void onHomeItemClick(MenuItem item) {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onHomeItemClick(item);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}