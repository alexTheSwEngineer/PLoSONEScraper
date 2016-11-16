
import models.SearchResponce;

import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * Created by atrposki on 11/14/2016.
 */
public class PosSearch implements Iterator<SearchResponce> {

    IGetJson<SearchResponce> client;
    int currentPage;
    int totallPages;
    Optional<SearchResponce> nextSite;
    String url;

    public PosSearch(IGetJson<SearchResponce> client, String url) throws IOException {
        //"http://journals.plos.org/plosone/search?filterJournals=PLoSONE&resultsPerPage=60&q=bitcoin&page=1"
        this.url = url.replace("/search?", "/dynamicSearch?")
                .replace("&page=1", "");
        this.client = client;
        currentPage = 1;
        totallPages=Integer.MAX_VALUE;
        fetchNext();
        totallPages = this.nextSite
                          .map(x->x.getSearchResults().getNumFound())
                          .orElse(0)/60;
    }

    public boolean hasNext() {
        return nextSite.isPresent();
    }

    public SearchResponce next() {
        Optional<SearchResponce> next = nextSite;
        this.fetchNext();
        return next.orElse(SearchResponce.empty());
    }

    private void fetchNext() {
        try {
            if(currentPage>=totallPages)
            {
                this.nextSite = Optional.empty();
                return;
            }

            SearchResponce searchResults = client.Get(String.format("%s&page=%s", url, currentPage));
            this.nextSite = Optional.of(searchResults);
            currentPage++;

        } catch (IOException e) {
            e.printStackTrace();
            this.nextSite = Optional.empty();
            throw  new Error("Something went wrong",e);
        }
    }

    public void remove() {
        return;
    }

    public void forEachRemaining(Consumer<? super SearchResponce> consumer) {
        while (hasNext()) {
            SearchResponce target = next();
            consumer.accept(target);
        }
    }
}
