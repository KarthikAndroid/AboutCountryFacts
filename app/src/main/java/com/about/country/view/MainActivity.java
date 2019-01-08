package com.about.country.view;



import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.about.country.R;
import com.about.country.model.AboutCountryListItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * The App uses the Local JSON File from Assets. The URL for JSON is not working so we have added the Local JSON in the Asset from the
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout swipeContainer;

    @BindView(R.id.recycleViewContainer)
    RecyclerView countryListView;

    private CountryRecycleAdapter aboutCountryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_list);

        ButterKnife.bind(this);

        initUI();

    }

    void initUI() {

        aboutCountryAdapter = new CountryRecycleAdapter(this,new ArrayList<AboutCountryListItem>());

        countryListView.setLayoutManager(new LinearLayoutManager(this));
        countryListView.setAdapter(aboutCountryAdapter);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeContainer.setRefreshing(false);
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }


}
