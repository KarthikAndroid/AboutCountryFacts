package com.about.country.view;

import android.app.ProgressDialog;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.about.country.R;
import com.about.country.model.AboutCountryDetails;
import com.about.country.model.AboutCountryListItem;
import com.about.country.viewmodel.AboutContryViewModel;

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

    private ProgressDialog progressBar;

    private CountryRecycleAdapter aboutCountryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_list);

        ButterKnife.bind(this);
        final AboutContryViewModel viewModel =
                ViewModelProviders.of(this).get(AboutContryViewModel.class);
        subscribeUi(viewModel.getAboutCountryItemsList());
        initUI();

    }

    private void showProgress()
    {
        progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);
        progressBar.setTitle("Fetching data..");
        progressBar.show();
    }

    private  void dismissProgressBar()
    {
        try {
            progressBar.dismiss();
        }
        catch (NullPointerException e)
        {
         e.printStackTrace();
        }
        catch (Exception e)
        {
        e.printStackTrace();
        }
    }

    /**
     * Initialize the UI.
     */
    private void initUI() {

        aboutCountryAdapter = new CountryRecycleAdapter(this, new ArrayList<AboutCountryListItem>());
        countryListView.setLayoutManager(new LinearLayoutManager(this));
        countryListView.setAdapter(aboutCountryAdapter);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                final AboutContryViewModel viewModel =
                        ViewModelProviders.of(MainActivity.this).get(AboutContryViewModel.class);

                viewModel.requestAboutCountryDetails();
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        showProgress();
    }

    /**
     * Subscribe to Live Data. Whenever there is a change in the View Model Live data then it will be getting notified.
     *
     * @param liveData
     */
    private void subscribeUi(LiveData<AboutCountryDetails> liveData) {
        // Update the list when the data changes
        liveData.observe(this, new Observer<AboutCountryDetails>() {
            @Override
            public void onChanged(@Nullable AboutCountryDetails aboutCountryListItems) {

                if (aboutCountryListItems != null) {
                    setTitle(aboutCountryListItems.getTitle());
                    aboutCountryAdapter.addListOfRows(aboutCountryListItems.getRows());
                    aboutCountryAdapter.notifyDataSetChanged();
                    swipeContainer.setRefreshing(false);
                } else {
                    Toast.makeText(MainActivity.this, R.string.network_error,Toast.LENGTH_LONG).show();;
                    swipeContainer.setRefreshing(false);
                }
                dismissProgressBar();

            }
        });
    }


}
