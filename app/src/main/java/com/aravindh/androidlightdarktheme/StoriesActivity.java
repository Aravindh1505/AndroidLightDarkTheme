package com.aravindh.androidlightdarktheme;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StoriesActivity extends AppCompatActivity {

    private static final String NIGHT_MODE = "night_mode";

    private Toolbar toolbar;
    private SharedPreferences mSharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // init shared preferences
        mSharedPref = getPreferences(Context.MODE_PRIVATE);

        if (isNightModeEnabled()) {
            setAppTheme(R.style.AppTheme_Base_Night);
        } else {
            setAppTheme(R.style.AppTheme_Base_Light);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        StoriesAdapter adapter = new StoriesAdapter(this);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);

        adapter.setNewsList(getStoriesList());
        adapter.setOnItemClickListener(new StoriesAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                String message = getString(R.string.bookmark);
                Toast.makeText(view.getContext(), String.format(message, position), Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setAdapter(adapter);

    }

    private ArrayList<Stories> getStoriesList() {
        ArrayList<Stories> storiesArrayList = new ArrayList<>();

        int index = 1;

        while (index < 20) {
            Stories stories = new Stories(getString(R.string.story_name), getString(R.string.story_description), "23 Mar, 10:17 AM", R.drawable.ic_book_cover);
            storiesArrayList.add(stories);
            index++;
        }

        return storiesArrayList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        // Inflate switch
        Switch mSwitchNightMode = menu.findItem(R.id.action_night_mode)
                .getActionView().findViewById(R.id.item_switch);

        // Get state from preferences
        if (isNightModeEnabled()) {
            mSwitchNightMode.setChecked(true);
        } else {
            mSwitchNightMode.setChecked(false);
        }

        mSwitchNightMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (isNightModeEnabled()) {
                    setIsNightModeEnabled(false);
                    setAppTheme(R.style.AppTheme_Base_Light);
                } else {
                    setIsNightModeEnabled(true);
                    setAppTheme(R.style.AppTheme_Base_Night);
                }

                // Recreate activity
                recreate();
            }
        });

        return true;
    }

    private void setAppTheme(@StyleRes int style) {
        setTheme(style);
    }

    private boolean isNightModeEnabled() {
        return mSharedPref.getBoolean(NIGHT_MODE, false);
    }

    private void setIsNightModeEnabled(boolean state) {
        SharedPreferences.Editor mEditor = mSharedPref.edit();
        mEditor.putBoolean(NIGHT_MODE, state);
        mEditor.apply();
    }
}
