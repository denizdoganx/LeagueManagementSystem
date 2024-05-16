
public class Player {
	//Player class was created with its basic features
	//An extra attribute called player id has been created for easy deletion.
	private int playerId;
	private int licenseNumber;
	private String name;
	private Date dateOfBirth;
	private String nationality;
	private Address address;
	private Phone phone;
	private String team;
	private Date startDate;
	private Date endDate;
	//Two constructors created for player
	private int salary;
	private String positionalRole;
	public Player(int licenseNumber, String name,Date dateOfBirth, String nationality, Address address, Phone phone, String team, Date startDate, Date endDate, int salary, String positionalRole) {
		this.licenseNumber = licenseNumber;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.nationality = nationality;
		this.address = address;
		this.phone = phone;
		this.team = team;
		this.startDate = startDate;
		this.endDate = endDate;
		this.salary = salary;
		this.positionalRole = positionalRole;
	}
	public Player() {
		
	}
	public int getLicenseNumber() {
		return licenseNumber;
	}
	public void setLicenseNumber(int liceseNumber) {
		this.licenseNumber = liceseNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Phone getPhone() {
		return phone;
	}
	public void setPhone(Phone phone) {
		this.phone = phone;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getPositionalRole() {
		return positionalRole;
	}
	public void setPositionalRole(String positionalRole) {
		this.positionalRole = positionalRole;
	}
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	public String displayPlayer() {
		return name + "  " + "Team:" + team + "  " + "Nationality:" + nationality + "  " + "Start Date:" + startDate.displayDate() + "  " + "End Date:" + endDate.displayDate() + "  " + "Position:" + positionalRole + "  " + "Salary " + salary + "$";
	}
}
