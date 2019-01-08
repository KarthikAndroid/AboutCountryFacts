package com.about.country.model.repo;




import com.about.country.model.AboutCountryDetails;

import io.reactivex.Observable;
import retrofit2.http.GET;


public interface AboutCountryService {
    String BASE_URL = "https://dl.dropboxusercontent.com/";



    @GET(BASE_URL + "s/2iodh4vg0eortkl/facts.js")
    Observable<AboutCountryDetails> getAboutCountryDetail();

}
