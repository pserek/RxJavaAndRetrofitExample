package pl.sointeractive.rxjavatutorial.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import pl.sointeractive.rxjavatutorial.login.objects.Person;
import pl.sointeractive.rxjavatutorial.R;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private LoginContract.Presenter presenter;
    private TextView nameText;
    private TextView genderText;
    private TextView heightText;
    private TextView weightText;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        nameText = (TextView) findViewById(R.id.name);
        genderText = (TextView) findViewById(R.id.gender);
        heightText = (TextView) findViewById(R.id.height);
        weightText = (TextView) findViewById(R.id.weight);
        progressBar = (ProgressBar) findViewById(R.id.progress);

        presenter = new LoginPresenter();
        presenter.attachView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getPerson();
        presenter.getPeople();
    }


    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public void showPerson(Person person) {
        Log.d(TAG, "showPerson: Person shown");
        nameText.setText(person.getName());
        genderText.setText(person.getGender());
        heightText.setText(person.getHeight());
        weightText.setText(person.getMass());
    }

    @Override
    public void showProgress(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}