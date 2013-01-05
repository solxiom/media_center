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
public class Main_imdb_service {

    /**
     * @param args the command line arguments
     */
    public static void main_imdb_service(String[] args) {
//http://imdbapi.org/
//?title=amelia+&type=json&plot=full&episode=1&limit=1&yg=1&mt=none&lang=en-US&offset=&aka=simple&release=simple&year=2009
//tt1129445
        DataService service = new DataServiceImpl();
        TitleSearchOptions options = new TitleSearchOptions("amelia","2009");
        
        options.setOffset("0");
        DataObject data = service.getDataByTitle("http://imdbapi.org", options);

        if (data != null) {
            if (data.getError() != null) {
                System.out.println("error: " +data.getError());
            } else {
                System.out.println("plot: " + data.getPlot());
                System.out.println("total_found: " + data.getTotal_found());  
        
            }
        } else {
            System.out.println("error");
        }
    }
}
