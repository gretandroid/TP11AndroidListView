package education.cccp.tp11listview;

import static android.R.layout.simple_list_item_1;
import static education.cccp.tp11listview.MainActivity.PERSONS_KEY;
import static education.cccp.tp11listview.R.id.personListViewId;
import static education.cccp.tp11listview.R.layout.activity_second;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import education.cccp.tp11listview.controller.PersonDao;
import education.cccp.tp11listview.model.Person;

public class SecondActivity extends AppCompatActivity {

    public static final String PERSON_KEY = "person_key";

    private void logPersons() {
        //noinspection unchecked
        Log.d(SecondActivity.class.getSimpleName(),
                ((List<Person>) getIntent().getSerializableExtra(PERSONS_KEY))
                        .toArray()[0]
                        .toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_second);
        ListView personsListView = findViewById(personListViewId);
        logPersons();
        //noinspection unchecked
        personsListView.setAdapter(new ArrayAdapter<>(this,
                simple_list_item_1,
                (List<Person>) getIntent()
                        .getSerializableExtra(PERSONS_KEY)));

        personsListView.setOnItemClickListener((AdapterView<?> adapterView,
                                                View view,
                                                int index,
                                                long l) -> {
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