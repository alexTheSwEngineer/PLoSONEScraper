package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by atrposki on 11/14/2016.
 */
public class SearchResults {
     int numFound;
     List<Paper> docs;

    public int getNumFound() {
        return numFound;
    }

    public SearchResults setNumFound(int numFound) {
        this.numFound = numFound;
        return  this;
    }

    public List<Paper> getDocs() {
        return docs;
    }

    public SearchResults setDocs(List<Paper> docs) {
        this.docs = docs;
        return  this;
    }

    public static SearchResults empty()
    {
        return new SearchResults().setDocs(new ArrayList<>())
                                  .setNumFound(0);
    }

}
