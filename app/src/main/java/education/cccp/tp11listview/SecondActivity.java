package education.cccp.tp11listview;

import static education.cccp.tp11listview.MainActivity.PERSONS_KEY;
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

    private ListView personListView;
    public static final String PERSON_KEY="person_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_second);
        personListView = findViewById(R.id.personListViewId);
        Log.d(SecondActivity.class.getSimpleName(),
                ((List<Person>) getIntent().getSerializableExtra(PERSONS_KEY))
                        .toArray()[0]
                        .toString());
        personListView.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                (List<Person>) getIntent().getSerializableExtra(PERSONS_KEY)));
        personListView.setOnItemClickListener((adapterView, view, index, l) -> {
            getIntent().putExtra(PERSON_KEY,PersonDao.getAllPersonnes().get(index));
        });
    }
}