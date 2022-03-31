package education.cccp.tp11listview.controller;

import java.util.ArrayList;
import java.util.List;

import education.cccp.tp11listview.model.Person;

public class PersonDao {
    private static final List<Person> persons = new ArrayList<>();

    public static void addPerson(Person p) {
        persons.add(p);
    }

    public static List<Person> getAllPersonnes() {
        return persons;
    }
}
