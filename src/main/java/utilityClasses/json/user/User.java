package utilityClasses.json.user;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {


	public User(String firstName, String lastName, int age, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.email = email;
	}
	private String firstName;
    private String lastName;
    private int age;
    private String email;

}
