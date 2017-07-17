package pl.sointeractive.rxjavatutorial.login;

import pl.sointeractive.rxjavatutorial.login.objects.Person;

/**
 * Created by Patryk Serek on 17.07.2017
 */

interface LoginContract {

    interface View {
        void showPerson(Person person);
        void showProgress(boolean show);
    }

    interface Presenter {
        void attachView(LoginContract.View view);
        void detachView();
        void getPerson();
        void getPeople();
    }
}
