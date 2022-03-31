package education.cccp.tp11listview;

import static android.R.layout.simple_list_item_1;
import static education.cccp.tp11listview.MainActivity.PERSONS_KEY;
import static education.cccp.tp11listview.R.id.personListViewId;
import static education.cccp.tp11listview.R.layout.activity_second;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import education.cccp.tp11listview.controller.PersonDao;
import education.cccp.tp11listview.model.Person;

public class SecondActivity extends AppCompatActivity {

    private ListView personsListView;
    public static final String PERSON_KEY = "person_key";

    private void logPersons() {
        Log.d(SecondActivity.class.getSimpleName(),
                ((List<Person>) getIntent().getSerializableExtra(PERSONS_KEY))
                        .toArray()[0]
                        .toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_second);
        personsListView = findViewById(personListViewId);
        logPersons();
        personsListView.setAdapter(new ArrayAdapter<>(this,
                simple_list_item_1,
                (List<Person>) getIntent().getSerializableExtra(PERSONS_KEY)));

        personsListView.setOnItemClickListener((adapterView, view, index, l) -> {
            //retrieve person's clicked
            setResult(RESULT_OK,
                    new Intent().putExtra(
                            PERSON_KEY,
                            PersonDao.getAllPersonnes()
                                    .get(index)
                    ));
            finish();
        });

    }
}