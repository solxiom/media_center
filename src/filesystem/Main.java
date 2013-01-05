/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filesystem;

import imdb.domain.DataObject;
import imdb.domain.IdSearchOptions;
import imdb.service.DataServiceImpl;
import imdb.domain.TitleSearchOptions;
import imdb.service.DataService;

/**
 *
 * @author kavan
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//http://imdbapi.org/
//?title=amelia+&type=json&plot=full&episode=1&limit=1&yg=1&mt=none&lang=en-US&offset=&aka=simple&release=simple&year=2009
//tt1129445
        DataService service = new DataServiceImpl();
        TitleSearchOptions options = new TitleSearchOptions("amelia","2009");
        IdSearchOptions idOptions = new IdSearchOptions("tt1129445");
//        options.setOffset("0");
//        options.setPlot("simple");
//        idOptions.setPlot("simple");
//        DataObject data = service.getDataByTitle("http://imdbapi.org", options);
        DataObject data = service.getDataById("http://imdbapi.org", idOptions);
        if (data != null) {
            if (data.getError() != null) {
                System.out.println("error: " +data.getError());
            } else {
                System.out.println("plot: " + data.getGenres());
                System.out.println("total_found: " + data.getTotal_found());  
                System.out.println(""+data.getImdb_id());
            }
        } else {
            System.out.println("error");
        }
    }
}
