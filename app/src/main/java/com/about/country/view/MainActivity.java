package com.about.country.view;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.about.country.R;


/**
 * The App uses the Local JSON File from Assets. The URL for JSON is not working so we have added the Local JSON in the Asset from the
 */
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_list);

    }


}
