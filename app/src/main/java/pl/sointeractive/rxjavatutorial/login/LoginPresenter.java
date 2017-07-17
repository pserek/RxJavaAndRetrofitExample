package pl.sointeractive.rxjavatutorial.login;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import pl.sointeractive.rxjavatutorial.login.objects.Person;
import pl.sointeractive.rxjavatutorial.retrofit.RetrofitProvider;
import pl.sointeractive.rxjavatutorial.login.objects.Response;

/**
 * Created by Patryk Serek on 17.07.2017
 */

class LoginPresenter implements LoginContract.Presenter {

    private static final String TAG = LoginPresenter.class.getSimpleName();
    private LoginContract.View view;
    private RetrofitProvider retrofitProvider;
    private Disposable personDisposable, peopleDisposable;

    LoginPresenter() {
        retrofitProvider = new RetrofitProvider();
    }

    @Override
    public void attachView(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        dispose();
        view = null;
    }

    @Override
    public void getPerson() {
        view.showProgress(true);
        personDisposable = retrofitProvider.getRetrofitService().getPerson("3")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onPersonReceived, this::doOnError);
    }

    @Override
    public void getPeople() {
        peopleDisposable = retrofitProvider.getRetrofitService().getPeople()
                .map(Response::getResults)
                .doOnNext(persons -> Log.d(TAG, "getPeople: List size: " + persons.size()))
                .flatMap(Observable::fromIterable)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::logPerson, this::doOnError);
    }

    private void logPerson(Person person) {
        Log.d(TAG, "logPerson: Name: " + person.getName());
        Log.d(TAG, "logPerson: Gender: " + person.getGender());
        Log.d(TAG, "logPerson: Height: " + person.getHeight());
        Log.d(TAG, "logPerson: Mass: " + person.getMass());
    }

    private void onPersonReceived(Person person) {
        if (view != null) {
            Log.d(TAG, "onPersonReceived: Person received");
            view.showProgress(false);
            view.showPerson(person);
        }
    }

    private void doOnError(Throwable throwable) {
        Log.e(TAG, "doOnError: " + throwable.getMessage());
    }

    private void dispose() {
        if (!personDisposable.isDisposed()) {
            personDisposable.dispose();
        }
        if (!peopleDisposable.isDisposed()) {
            peopleDisposable.dispose();
        }
    }
}