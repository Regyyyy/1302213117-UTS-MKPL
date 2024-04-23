package lib;

public class Person {
    private enum Gender{
        LakiLaki,
        Perempuan
    }

    private String name;
    private String firstName;
    private String lastName;
    private String idNumber;
    private Gender gender;
    private String address;

    public Person(String name, String idNumber, Gender gender, String address) {
        this.name = name;
        this.idNumber = idNumber;
        this.gender = gender;
        this.address = address;
    }

    public Person(String firstName, String lastName, String idNumber, Gender gender, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.gender = gender;
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public Gender getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }
}
