import batchJob.BatchJob;
import batchJob.BatchSettings;
import closableUtils.Using;
import com.google.gson.Gson;
import models.Paper;
import models.SearchResponce;
import objectToString.Property;
import objectToString.PropertyPrintSetting;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.http.impl.client.HttpClientBuilder;
import java.io.PrintStream;
import java.util.List;
import java.util.function.Function;

/**
 * Created by atrposki on 11/14/2016.
 */
public class Program {




    public static void main(String[] args) throws Exception {

//        String fileName = args[0];
//        String url = args[1];
        String fileName = "FIleName.txt";
        String url ="http://journals.plos.org/plosone/search?filterJournals=PLoSONE&resultsPerPage=60&q=bitcoin&page=1";
        Program program = new Program(new GetJson<SearchResponce>(HttpClientBuilder.create().build(),new Gson(),SearchResponce.class), System.out);
        program.run(url,fileName);

    }
    private final PrintStream logStream;
    private  IGetJson<SearchResponce> httpGetClient;

    public Program(IGetJson<SearchResponce> httpGetClient, PrintStream logStream) {
        this.httpGetClient = httpGetClient;
        this.logStream = logStream;
    }

    public void run(String url,String fileName) throws Exception {
        final  PosSearch search = new PosSearch(this.httpGetClient, url);
        BatchJob<Paper, Property, PrintStream> csvValues = createPringJob(fullProperties, escapeCsv(x -> x.value), ",", "\n");
        BatchJob<Paper, Property, PrintStream> csvHeaders = createPringJob(fullProperties, escapeCsv(x -> x.name), ",", "\n");
        BatchJob<Paper, Property, PrintStream> log = createPringJob(logProperties, x -> x.name+":"+x.value, "     :     ", "\n");
        Using.Closable(() -> new PrintStream(fileName),
        ps -> {
        csvHeaders.execute(new Paper(),ps);

        while (search.hasNext()) {
                Thread.sleep(Long.parseLong("3000"));
                SearchResponce responce = search.next();

                List<Paper> papers  = responce.getSearchResults()
                                              .getDocs();
                if (papers == null || papers.size()<=0) {
                    continue;
                }

                for (Paper paper :
                        papers) {
                    //log.execute(paper,logStream);
                    //csvValues.execute(paper,logStream);
                    csvValues.execute(paper,ps);
                }
            }
       });
    }


    private  static BatchJob<Paper, Property,PrintStream> createPringJob(BatchSettings<Paper,Property> settings, Function<Property,String> propertyDisplay,String delimiter, String lineEnd) throws Exception {
        return settings.<PrintStream>createJob()
                       .afterLast((p,out)->out.print(lineEnd))
                       .betweenEach((l,r,out)->out.print(delimiter))
                       .applyOnEach((prop,out)->out.print(propertyDisplay.apply(prop)));
    }
    private static BatchSettings<Paper,Property> fullProperties = new PropertyPrintSetting<Paper>(x->x.getId())
            .name("Id")
            .create(x->x.getTitle_display())
            .name("Title")
            .create(", ",x->x.getAuthor_display())
            .name("Authors")
            .create(x->x.getPublication_date().toString())
            .name("Date")
            .create(x->x.getArticle_type())
            .name("Article type")
            .create(x->x.getLink())
            .name("Link")
            .create(x->x.getJournalKey())
            .name("Journal key")
            .create(x->""+x.getCounter_total_all())
            .name("Total count")
            .create(", ",x->x.getCross_published_journal_name())
            .name("Cross published journal names")
            .buildSettings();
    private static BatchSettings<Paper,Property> logProperties = new PropertyPrintSetting<Paper>(x->x.getTitle())
            .name("Title")
            .create(",",x->x.getAuthor_display())
            .name("Authors")
            .buildSettings();
    private static Function<Property,String> escapeCsv(Function<Property,String> f)
    {
        return x-> StringEscapeUtils.escapeCsv(f.apply(x));
    }
}
