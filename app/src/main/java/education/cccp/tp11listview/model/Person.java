package education.cccp.tp11listview.model;

import java.io.Serializable;

public class Person implements Serializable {
    static final long serialVersionUID = 1L;
    private Integer id;
    private String firstName;
    private String lastName;

    public Person(Integer id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
