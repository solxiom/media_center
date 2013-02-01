/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.domain.tomatoes;

/**
 *
 * @author kavan
 */
public class TMRatings {
   
    String critics_rating;
    String critics_score;
    String audience_rating;
    String audience_score;

    public TMRatings() {
    }

    public String getCritics_rating() {
        return critics_rating;
    }

    public void setCritics_rating(String critics_rating) {
        this.critics_rating = critics_rating;
    }

    public String getCritics_score() {
        return critics_score;
    }

    public void setCritics_score(String critics_score) {
        this.critics_score = critics_score;
    }

    public String getAudience_rating() {
        return audience_rating;
    }

    public void setAudience_rating(String audience_rating) {
        this.audience_rating = audience_rating;
    }

    public String getAudience_score() {
        return audience_score;
    }

    public void setAudience_score(String audience_score) {
        this.audience_score = audience_score;
    }
    
}

/*
ratings: {
critics_rating: "Rotten",
critics_score: 3,
audience_rating: "Spilled",
audience_score: 39
},
*/