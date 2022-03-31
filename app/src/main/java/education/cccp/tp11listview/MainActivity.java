package education.cccp.tp11listview;

import static education.cccp.tp11listview.R.layout.activity_main;
import static education.cccp.tp11listview.controller.PersonDao.addPerson;
import static education.cccp.tp11listview.controller.PersonDao.getAllPersonnes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

import education.cccp.tp11listview.model.Person;

public class MainActivity extends AppCompatActivity {
    public static final String PERSONS_KEY = "persons_key";

    private EditText editTextPersonFirstName;
    private EditText editTextPersonLastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        editTextPersonFirstName = findViewById(R.id.editTextPersonFirstNameId);
        editTextPersonLastName = findViewById(R.id.editTextPersonLastNameId);
    }

    public void onClickSaveButonEvent(View view) {
        addPerson(new Person(1,
                editTextPersonFirstName.getText().toString(),
                editTextPersonLastName.getText().toString()));
        startActivity(new Intent(this,
                SecondActivity.class)
                .putExtra(PERSONS_KEY,
                        (Serializable) getAllPersonnes()));
    }
}