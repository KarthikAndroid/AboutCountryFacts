
package com.about.country.model;

import java.util.List;


public class AboutCountryDetails {


    private String title;

    private List<AboutCountryListItem> rows = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AboutCountryListItem> getRows() {
        return rows;
    }

    public void setRows(List<AboutCountryListItem> rows) {
        this.rows = rows;
    }

}
