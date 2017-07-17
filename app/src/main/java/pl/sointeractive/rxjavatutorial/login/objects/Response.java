package pl.sointeractive.rxjavatutorial.login.objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patryk Serek on 17.07.2017
 */

public class Response {

    private List<Person> results = new ArrayList<>();

    public List<Person> getResults() {
        return results;
    }
}
