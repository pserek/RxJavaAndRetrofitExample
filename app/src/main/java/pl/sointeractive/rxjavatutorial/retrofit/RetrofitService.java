package pl.sointeractive.rxjavatutorial.retrofit;

import io.reactivex.Observable;
import pl.sointeractive.rxjavatutorial.login.objects.Person;

import pl.sointeractive.rxjavatutorial.login.objects.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Patryk Serek on 17.07.2017
 */

public interface RetrofitService {


    @GET("people/{id}/")
    Observable<Person> getPerson(@Path("id") String id);

    @GET("people/")
    Observable<Response> getPeople();
}