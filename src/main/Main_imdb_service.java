/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import service.DataConverter;
import service.domain.DataObject;
import service.dataService.ImdbDataService;
import service.domain.TitleSearchOptions;
import service.DataService;
import service.dataService.DataObjectConverterImpl;
import service.dataService.ImdbServer;

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
        DataConverter converter = new DataObjectConverterImpl();
        DataService service = new ImdbDataService(new ImdbServer(), converter);
        TitleSearchOptions options = new TitleSearchOptions("amelia", "2009");

        options.setOffset("0");
        DataObject data = service.getDataByTitle("http://imdbapi.org", options);

        if (data != null) {
            if (data.getError() != null) {
                System.out.println("error: " + data.getError());
            } else {
                System.out.println("plot: " + data.getStoryLine());
                System.out.println("total_found: ");

            }
        } else {
            System.out.println("error");
        }
    }
}
