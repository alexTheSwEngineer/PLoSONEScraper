package models;

/**
 * Created by atrposki on 11/14/2016.
 */
public class SearchResponce {
    SearchResults searchResults;

    public SearchResults getSearchResults() {
        return searchResults!=null?this.searchResults: SearchResults.empty();
    }

    public SearchResponce setSearchResults(SearchResults searchResults) {
        this.searchResults = searchResults;
        return  this;
    }

    public static SearchResponce empty() {
        return new SearchResponce().setSearchResults(SearchResults.empty());
    }
}
