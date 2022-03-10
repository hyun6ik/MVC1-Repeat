package hyun6ik.servlet.basic;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HelloData {

    private String username;
    private int age;

    public HelloData(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public HelloData of(String username, int age) {
        return new HelloData(username, age);
    }
}
