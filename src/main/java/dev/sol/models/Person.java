package dev.sol.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import dev.sol.utils.DateUtil;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import net.datafaker.Faker;

public class Person implements Serializable {
    public static Person GENERATE() {
        Faker faker = new Faker();
        String firstname = faker.name().firstName();
        String lastname = faker.name().lastName();
        String street = faker.address().streetAddress();
        String city = faker.address().city();
        String postalcode = faker.address().postcode();
        LocalDate birthdate = DateUtil.randomize(
                LocalDate.now().minusYears(65),
                LocalDate.now().minusYears(18));

        return new Person(firstname,
                lastname,
                street,
                city,
                postalcode,
                birthdate);
    }

    public static List<Person> GENERATE_LIST() {
        List<Person> personlist = new LinkedList<>();
        for (int i = 0; i < 50; i++)
            personlist.add(Person.GENERATE());
        return personlist;
    }

    private final StringProperty _id;
    private final StringProperty _firstname;
    private final StringProperty _lastname;
    private final StringProperty _street;
    private final StringProperty _city;
    private final StringProperty _postalcode;
    private final ObjectProperty<LocalDate> _birthdate;

    public Person() {
        this("",
                "",
                "",
                "",
                "",
                LocalDate.now().minusYears(18));
    }

    public Person(Person person) {
        this(person.getFirstname(),
                person.getLastname(),
                person.getStreet(),
                person.getCity(),
                person.getPostalcode(),
                person.getBirthdate());
    }

    public Person(String firstname,
            String lastname,
            String street,
            String city,
            String postalcode,
            LocalDate birthdate) {
        _id = new SimpleStringProperty(UUID.randomUUID().toString());

        _firstname = new SimpleStringProperty(firstname);
        _lastname = new SimpleStringProperty(lastname);
        _street = new SimpleStringProperty(street);
        _city = new SimpleStringProperty(city);
        _postalcode = new SimpleStringProperty(postalcode);
        _birthdate = new SimpleObjectProperty<>(birthdate);
    }

    public StringProperty idProperty() {
        return _id;
    }

    public String getId() {
        return idProperty().get();
    }

    public StringProperty firstnameProperty() {
        return _firstname;
    }

    public String getFirstname() {
        return firstnameProperty().get();
    }

    public void setFirstname(String firstname) {
        firstnameProperty().set(firstname);
    }

    public StringProperty lastnameProperty() {
        return _lastname;
    }

    public String getLastname() {
        return lastnameProperty().get();
    }

    public void setLastname(String lastname) {
        lastnameProperty().set(lastname);
    }

    public StringProperty streetProperty() {
        return _street;
    }

    public String getStreet() {
        return streetProperty().get();
    }

    public void setStreet(String street) {
        streetProperty().set(street);
    }

    public StringProperty cityProperty() {
        return _city;
    }

    public String getCity() {
        return cityProperty().get();
    }

    public void setCity(String city) {
        cityProperty().set(city);
    }

    public StringProperty postalcodeProperty() {
        return _postalcode;
    }

    public String getPostalcode() {
        return postalcodeProperty().get();
    }

    public void setPostalcode(String postalcode) {
        postalcodeProperty().set(postalcode);
    }

    public ObjectProperty<LocalDate> birthdateProperty() {
        return _birthdate;
    }

    public LocalDate getBirthdate() {
        return birthdateProperty().get();
    }

    public void setBirthdate(LocalDate birthdate) {
        birthdateProperty().set(birthdate);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        Person other = (Person) obj;
        if (getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!getId().equals(other.getId()))
            return false;
        return true;
    }
}
