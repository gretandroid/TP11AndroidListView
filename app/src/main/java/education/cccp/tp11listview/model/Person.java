package education.cccp.tp11listview.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Person implements Serializable {
    static final long serialVersionUID = 1L;
    private final Integer id;
    private final String firstName;
    private final String lastName;

    public Person(String firstName,
                  String lastName) {
        this.id = 1;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @NonNull
    @Override
    public String toString() {
        return new StringBuilder("Person{")
                .append("id=").append(id)
                .append(", firstName='").append(firstName).append('\'')
                .append(", lastName='").append(lastName).append('\'')
                .append('}')
                .toString();
    }
}
