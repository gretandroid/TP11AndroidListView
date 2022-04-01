package education.cccp.tp11listview;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;
import static education.cccp.tp11listview.R.id.editTextPersonFirstNameId;
import static education.cccp.tp11listview.R.id.editTextPersonLastNameId;
import static education.cccp.tp11listview.R.layout.activity_main;
import static education.cccp.tp11listview.SecondActivity.CURRENT_PERSON_KEY;
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

public class MainActivity extends AppCompatActivity {
    public static final String PERSON_LIST_KEY = "person_list_key";
    public static final int OUT_OF_BOUND_INDEX = -1;
    public static final String EMPTY_FIELD = "";

    private EditText editTextPersonFirstName;
    private EditText editTextPersonLastName;

    private int indiceSelected = OUT_OF_BOUND_INDEX;
    // declare a launcher to call an intent to start
    // an execution process ActivityResultContracts
    private ActivityResultLauncher<Intent> intentActivityResultLauncher;

    private void initializePersonFields(String firstName, String lastName) {
        editTextPersonFirstName.setText(firstName);
        editTextPersonLastName.setText(lastName);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        editTextPersonFirstName = findViewById(editTextPersonFirstNameId);
        editTextPersonLastName = findViewById(editTextPersonLastNameId);
        intentActivityResultLauncher = registerForActivityResult(
                new StartActivityForResult(),
                (ActivityResult activityResult) -> {
                    Intent data = activityResult.getData();
                    if (activityResult.getResultCode() == RESULT_OK) {
                        Person person = (Person) Objects.requireNonNull(data)
                                .getSerializableExtra(CURRENT_PERSON_KEY);
                        initializePersonFields(person.getFirstName(),
                                person.getLastName());

                    }
                }
        );
    }

    public void onClickCreateButtonEvent(View view) throws Exception {
        makeText(this,
                save(new Person(editTextPersonFirstName.getText().toString(),
                        editTextPersonLastName.getText().toString())).toString(),
                LENGTH_LONG)
                .show();
        initializePersonFields(EMPTY_FIELD,
                EMPTY_FIELD);
    }

    public void onClickEditButtonEvent(View view) {
    }

    public void onClickDeleteButtonEvent(View view) {
    }

    public void onClickShowAllButtonEvent(View view) {
    }
}