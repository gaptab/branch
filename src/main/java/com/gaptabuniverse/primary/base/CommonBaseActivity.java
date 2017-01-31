package com.gaptabuniverse.primary.base;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.gaptabuniverse.primary.R;
import com.gaptabuniverse.primary.ScoresActivity;
import com.gaptabuniverse.primary.SettingsActivity;

public class CommonBaseActivity extends AppCompatActivity {

    // Things here are common to ALL activities.

    protected static final int REQUEST_WRITE_EXTERNAL_STORAGE = 121;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        Intent intent = null;
        switch (id) {

            case R.id.menu_scores:
                intent = new Intent(this, ScoresActivity.class);
                break;
            case R.id.menu_settings:
                intent = new Intent(this, SettingsActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    protected boolean hasStorageAccess() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }


    protected void checkStorageAccess() {
        boolean beendenied = getSharedPreferences(this.getClass().getName(), MODE_PRIVATE).getBoolean("denied", false);
        if (!beendenied && ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_EXTERNAL_STORAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_WRITE_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this, R.string.write_perms_granted, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, R.string.write_perms_denied, Toast.LENGTH_LONG).show();
                    getSharedPreferences(this.getClass().getName(), MODE_PRIVATE).edit().putBoolean("denied", true).apply();
                }
            }
        }
    }
}
