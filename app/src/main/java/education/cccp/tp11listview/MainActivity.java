package education.cccp.tp11listview;

import static android.widget.Toast.*;
import static android.widget.Toast.LENGTH_LONG;
import static education.cccp.tp11listview.R.id.editTextPersonFirstNameId;
import static education.cccp.tp11listview.R.id.editTextPersonLastNameId;
import static education.cccp.tp11listview.R.layout.activity_main;
import static education.cccp.tp11listview.SecondActivity.PERSON_KEY;
import static education.cccp.tp11listview.controller.PersonDao.addPerson;
import static education.cccp.tp11listview.controller.PersonDao.getAllPersonnes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.Objects;

import education.cccp.tp11listview.model.Person;

public class MainActivity extends AppCompatActivity {
    public static final String PERSONS_KEY = "persons_key";

    private EditText editTextPersonFirstName;
    private EditText editTextPersonLastName;

    // declare a launcher to call an intent to start
    // an execution process ActivityResultContracts
    private ActivityResultLauncher<Intent> intentLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        editTextPersonFirstName = findViewById(editTextPersonFirstNameId);
        editTextPersonLastName = findViewById(editTextPersonLastNameId);
        intentLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            Person person = (Person) Objects.requireNonNull(result.getData())
                                    .getSerializableExtra(PERSON_KEY);
                            Log.d(this.getClass().getSimpleName(),
                                    "Return person : " + person.toString());
                            makeText(getBaseContext(),
                                    person.toString(),
                                    LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    public void onClickSaveButtonEvent(View view) throws Exception {
        addPerson(new Person(editTextPersonFirstName
                .getText()
                .toString(),
                editTextPersonLastName
                        .getText()
                        .toString()));
        intentLauncher.launch(new Intent(this,
                SecondActivity.class).putExtra(PERSONS_KEY,
                (Serializable) getAllPersonnes()));
    }
}