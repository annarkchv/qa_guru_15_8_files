package guru.qa.lesson.jsonmodel;

import java.util.List;

public class User {

    public String firstName;
    public String lastName;
    public int age;
    public boolean isAdult;
    public List<String> socialNetworks;
    public Contacts contacts;

    public static class Contacts {
        public String phoneNumber;
        public String email;
    }
}
