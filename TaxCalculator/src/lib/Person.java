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

    public Person(String name, String idNumber, Gender gender) {
        this.name = name;
        this.idNumber = idNumber;
    }

    public Person(String firstName, String lastName, String idNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
    }

    public String getName() {
        return name;
    }

    public String getIdNumber() {
        return idNumber;
    }
}
