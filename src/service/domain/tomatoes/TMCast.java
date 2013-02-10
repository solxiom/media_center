/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.domain.tomatoes;

/**
 *
 * @author kavan
 */
public class TMCast {

    String name;
    String id;
    String[] characters;

    public TMCast() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getCharacters() {
        return characters;
    }

    public void setCharacters(String[] characters) {
        this.characters = characters;
    }
}
/*
abridged_cast: [
{
name: "Adam Sandler",
id: "162652550",
characters: [
"Jack Sadelstein",
"Jill Sadelstein"
]
},
{
name: "Katie Holmes",
id: "162652648",
characters: [
"Erin"
]
}
*/