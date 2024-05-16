
public class Team {
	private String name;
	private int year;
	private int cups;
	private String colors;
	private Coach coach;
	private Company company;
	private Player[] players;
	private int scoreOfTeam = 0;
	private int countOfPlayer;
	//The player id is unique, provided by being assigned within the team
	private static int playerId;
	//Written 3 constructors for Team
 	public Team(String name, int year, int cups, String colors, Coach coach, Company company) {
		this.name = name;
		this.year = year;
		this.cups = cups;
		this.colors = colors;
		this.coach = coach;
		this.company = company;
		countOfPlayer = 0;
	}
	public Team(String name, int year, int cups, String colors) {
		this.name = name;
		this.year = year;
		this.cups = cups;
		this.colors = colors;
		countOfPlayer = 0;
	}
	public Team() {
		countOfPlayer = 0;
	}
	public void addPlayer(Player player) {
		//I created a dynamic structure using temporary arrays
		player.setPlayerId(playerId);
		if(countOfPlayer != 0) {
			Player[] tempArray = new Player[countOfPlayer];
			for(int i = 0;i < tempArray.length; i++) {
				tempArray[i] = players[i];
			}
			players = new Player[countOfPlayer + 1];
			for(int i = 0;i < tempArray.length; i++) {
				players[i] = tempArray[i];
			}
			players[countOfPlayer] = player;
		}
		else {
			players = new Player[countOfPlayer + 1];
			players[countOfPlayer] = player;
		}
		playerId++;
		countOfPlayer++;
	}
	public void deletePlayer(int playerId) {
		//I created dynamic structures as in the add process.
		if(countOfPlayer > 1) {
			Player[] tempArray = new Player[countOfPlayer - 1];
			for(int i = 0;i < players.length; i++) {
				if(players[i].getPlayerId() < playerId) {
					tempArray[i] = players[i];
				}
				//I cannot transfer the player requested to be deleted to the new array I created.
				else if(players[i].getPlayerId() == playerId) {
					continue;
				}
				else {
					tempArray[i - 1] = players[i];
				}
			}
			players = new Player[countOfPlayer - 1];
			for(int i = 0;i < tempArray.length; i++) {
				players[i] = tempArray[i];
			}
		}
		else if(countOfPlayer == 1) {
			players = null;
		}
		countOfPlayer--;
	}
	public void listPlayer() {
		//I call the display method of the player class
		if(players != null) {
			for(int i = 0;i < players.length; i++) {
				System.out.println("\t" + players[i].displayPlayer());
			}
		}
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public void setCups(int cups) {
		this.cups = cups;
	}
	public void setColors(String colors) {
		this.colors = colors;
	}
	public void setCoach(Coach coach) {
		this.coach = coach;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public void setScoreOfTeam(int score) {
		this.scoreOfTeam = score;
	}
	public String getName() {
		return this.name;
	}
	public int getYear() {
		return this.year;
	}
	public int getCups() {
		return this.cups;
	}
	public String getColors() {
		return this.colors;
	}
	public Company getCompany() {
		return this.company;
	}
	public Coach getCoach() {
		return this.coach;
	}
	public Player[] getPlayers() {
		return this.players;
	}
	public int getScoreOfTeam() {
		return scoreOfTeam;
	}
	public String displayTeam() {
		//it can be returned in two ways against the possibility of coach not assigned.
		if(coach != null) {
			return name + "  " + "Year:" + year + "  " + "Cups:" + cups + "  " + "Colors:" + colors + "  " + "Coach:" + coach.getName();
		}
		else {
			return name + "  " + "Year:" + year + "  " + "Cups:" + cups + "  " + "Colors:" + colors + "  " + "Coach:" + "Not available";
		}
	}
	//The Coach and Company class is written in the inner class of the team because they cannot survive without the team.
	public class Coach {
		//Coach class was created with its basic features
		private String name;
		private Address address;
		private Phone phone;
		private String team;
		private Date startDate;
		private Date endDate;
		private int salary;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
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
	}
	public class Company {
		//Company class was created with its basic features
		private String name;
		private Address address;
		private Phone phone;
		private String team;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
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
		public String displayCompany() {
			return name + "  " + "Team:" + team + "  " + "Address:" + address.displayAddress() + "  " + "Phone " + phone.displayPhone();
		}
	}
}
