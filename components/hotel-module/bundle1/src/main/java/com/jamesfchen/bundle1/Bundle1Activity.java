package com.jamesfchen.bundle1;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Copyright ® $ 2017
 * All right reserved.
 *
 * @author: jamesfchen
 * @since: Jun/13/2021  Sun
 */
public class Bundle1Activity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        tv.setText("hotel bundle1");
        tv.setTextColor(Color.BLACK);
        tv.setGravity(Gravity.CENTER);
        setContentView(tv);
    }

}
