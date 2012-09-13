
import java.util.ArrayList;

public class Quiz06 {
    public static void main(String[] args){
        ArrayList<Person> people = new ArrayList<>();
        
        people.add(new Student("Anthony", "Anthony@Awesome.com", Student.STATUS_JUNIOR, 3.5));
        people.add(new Student("James", "James@Programmer.com", Student.STATUS_SENIOR, 4.0));
        
        people.add(new Staff("Jane", "Jane@Staff.com", "MCS22", 50000, "Janitor"));
        people.add(new Staff("Steve", "Steve@Staff.com", "MCS30", 62000, "Secretary"));
        
        people.add(new Faculty("Albert", "Albert@Proff.com", "MCS01", 90000, Faculty.RANK_ASSOICIATE_PROFESSOR));
        people.add(new Faculty("Frank", "Frank@Proff.com", "MCS04", 75000, Faculty.RANK_LECTURER));
        
        for(int i = 0; i < people.size(); i ++){
            System.out.println(people.get(i));
        }
    }
}
