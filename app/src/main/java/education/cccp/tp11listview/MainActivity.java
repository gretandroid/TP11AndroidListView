package education.cccp.tp11listview;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;
import static education.cccp.tp11listview.R.id.editTextPersonFirstNameId;
import static education.cccp.tp11listview.R.id.editTextPersonLastNameId;
import static education.cccp.tp11listview.R.layout.activity_main;
import static education.cccp.tp11listview.SecondActivity.CURRENT_PERSON_INDEX_KEY;
import static education.cccp.tp11listview.SecondActivity.CURRENT_PERSON_KEY;
import static education.cccp.tp11listview.repositories.PersonDao.*;
import static education.cccp.tp11listview.repositories.PersonDao.findAll;
import static education.cccp.tp11listview.repositories.PersonDao.save;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.Objects;

import education.cccp.tp11listview.models.Person;
import education.cccp.tp11listview.repositories.PersonDao;

public class MainActivity extends AppCompatActivity {
    public static final String PERSON_LIST_KEY = "person_list_key";
    public static final int OUT_OF_BOUND_INDEX = -1;
    public static final String EMPTY_FIELD = "";

    private EditText personFirstNameEditText;
    private EditText personLastNameEditText;
    private int indiceSelected = OUT_OF_BOUND_INDEX;
    private ActivityResultLauncher<Intent> intentActivityResultLauncher;

    private void setEditTextPersonFields(String firstName, String lastName) {
        personFirstNameEditText.setText(firstName);
        personLastNameEditText.setText(lastName);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        personFirstNameEditText = findViewById(editTextPersonFirstNameId);
        personLastNameEditText = findViewById(editTextPersonLastNameId);
        intentActivityResultLauncher = registerForActivityResult(
                new StartActivityForResult(),
                (ActivityResult activityResult) -> {
                    Intent data = activityResult.getData();
                    if (activityResult.getResultCode() == RESULT_OK) {
                        Person person = (Person) Objects.requireNonNull(data)
                                .getSerializableExtra(CURRENT_PERSON_KEY);
                        setEditTextPersonFields(person.getFirstName(),
                                person.getLastName());
                        indiceSelected = data.getIntExtra(
                                CURRENT_PERSON_INDEX_KEY,
                                OUT_OF_BOUND_INDEX);
                    }
                }
        );
    }

    public void onClickCreateButtonEvent(View view) throws Exception {
        makeText(this,
                new StringBuilder("personne added")
                        .append(save(new Person(
                                personFirstNameEditText.getText().toString(),
                                personLastNameEditText.getText().toString())))
                        .toString(),
                LENGTH_LONG)
                .show();
        setEditTextPersonFields(EMPTY_FIELD,
                EMPTY_FIELD);
    }

    public void onClickShowAllButtonEvent(View view) {
        intentActivityResultLauncher.launch(new Intent(
                this,
                SecondActivity.class).putExtra(
                PERSON_LIST_KEY,
                (Serializable) findAll()));
    }

    public void onClickDeleteButtonEvent(View view) {
        if (indiceSelected != OUT_OF_BOUND_INDEX) {
            delete(indiceSelected);
            setEditTextPersonFields(EMPTY_FIELD, EMPTY_FIELD);
            makeText(this,
                    "person deleted",
                    LENGTH_SHORT).show();
        }
    }

    public void onClickEditButtonEvent(View view) {
        Person person = findAll().get(indiceSelected);
        person.setLastName(personLastNameEditText.getText().toString());
        person.setFirstName(personFirstNameEditText.getText().toString());
        save(indiceSelected, person);
        makeText(this,
                "personne modified",
                LENGTH_SHORT).show();
    }
}