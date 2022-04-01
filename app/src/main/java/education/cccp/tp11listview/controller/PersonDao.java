package education.cccp.tp11listview.controller;

import java.util.ArrayList;
import java.util.List;

import education.cccp.tp11listview.model.Person;

public class PersonDao {
    private static final List<Person> persons = new ArrayList<>();

    public static Person addPerson(Person p) throws Exception {
        if (persons.add(p)) return p;
        else throw new Exception("malformed exception");
    }

    public static List<Person> getAllPersons() {
        return persons;
    }
}
