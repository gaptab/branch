/**package com.gaptabuniverse.primary;
 <item android:title="@string/about"
 android:id="@+id/menu_about" />


import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.gaptabuniverse.primary.base.CommonBaseActivity;

public class AboutActivity extends CommonBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutedit);


        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String version = pInfo.versionName;

            TextView txtappname = (TextView) findViewById(R.id.txtappname);
            txtappname.setText(getString(R.string.app_name) + " " + version);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        TextView txtAbout = (TextView) findViewById(R.id.txtAbout);
        txtAbout.setMovementMethod(LinkMovementMethod.getInstance());
        if (Build.VERSION.SDK_INT >= 24) {
            txtAbout.setText(Html.fromHtml(getString(R.string.about_primary), 0));
        } else {
            txtAbout.setText(Html.fromHtml(getString(R.string.about_primary)));

        }
    }
}
 */