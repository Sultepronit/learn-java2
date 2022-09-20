package app;

@Entity("user")
public class User {
	//@Field("id") if there is only value
	//@Field(true) if there is only isKey
	@Field(columnName="id", isKey=true)
	private Long id;
	
	@Field
	private String name;
	
	@Field
	private String password;
	
	private int sequense;
	
	public User(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
}
