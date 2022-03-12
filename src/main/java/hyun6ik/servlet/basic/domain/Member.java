package hyun6ik.servlet.basic.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private int age;

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public static Member of(String username, int age) {
        return new Member(username, age);
    }
}
