
public class TFF {
	/*The purpose of designing this class was to check the crowded that will occur in the Main class.
	in this class, methods in the team class in general were called and possible errors in adding 
	players or adding matches etc. were tried to be caught.*/
	private Referee[] referees;
	private Team[] teams;
	private Stadium[] stadiums;
	private int countOfTeams = 0;
	private int countOfReferees = 0;
	private int refereeId = 1;
	private int countOfStadiums = 0;
	private int stadiumId = 1;
	public void addMatch(Match match) {
		//Checking the minimum requirements for a match first
		if(countOfReferees > 2 && countOfStadiums > 0 && countOfTeams > 1) {
			boolean homeTeamControl = false;
			boolean awayTeamControl = false;
			boolean stadiumControl = false;
			boolean isGoalNegativeNumber = true;
			int refereesNumberControl = 0;
			//the negativity of the number of goals is controlled
			if(match.getNumberOfGoalsAway() < 0 || match.getNumberOfGoalsHome() < 0) {
				isGoalNegativeNumber = false;
			}
			//checking the presence of teams
			for(int i = 0;i < teams.length; i++) {
				if(match.getHomeTeam().equals(teams[i].getName())) {
					homeTeamControl = true;
				}
				else if(match.getAwayTeam().equals(teams[i].getName())) {
					awayTeamControl = true;
				}
				if(homeTeamControl && awayTeamControl) {
					break;
				}
			}
			//checking the presence of the stadium
			for(int i = 0;i < stadiums.length; i++) {
				if(stadiums[i].getStadiumId() == match.getStadiumId()) {
					stadiumControl = true;
				}
			}
			//checking the presence of referees
			for(int i = 0;i < referees.length; i++) {
				if(referees[i].getRefereeId() == match.getRefereeId1() || referees[i].getRefereeId() == match.getRefereeId2() || referees[i].getRefereeId() == match.getRefereeId3()){
					refereesNumberControl++;
				}
				if(refereesNumberControl == 3) {
					break;
				}
			}
			//appropriate error messages written
			if(refereesNumberControl != 3) {
				System.out.println("!!! ERROR !!! You cannot manage a match with a referee who is not registered in the system !");
			}
			if(!stadiumControl) {
				System.out.println("!!! ERROR !!! You tried to play a match in a stadium that is not registered in the system !");
			}
			if(!awayTeamControl) {
				System.out.println("!!! ERROR !!! A team named away team is not registered in the system !");
			}
			if(!homeTeamControl) {
				System.out.println("!!! ERROR !!! A team named home team is not registered in the system !");
			}
			if(!isGoalNegativeNumber) {
				System.out.println("!!! ERROR !!! The number of goals cannot be negative !");
			}
			else if(stadiumControl && awayTeamControl && homeTeamControl && refereesNumberControl == 3 && isGoalNegativeNumber) {
				int homeGoals = match.getNumberOfGoalsHome();
				int awayGoals = match.getNumberOfGoalsAway();
				//Checks are made according to which team wins and points are added
				if(homeGoals > awayGoals) {
					for(int i = 0;i < teams.length; i++) {
						if(teams[i].getName().equals(match.getHomeTeam())) {
							teams[i].setScoreOfTeam(teams[i].getScoreOfTeam() + 3);
							break;
						}
					}
				}
				else if(homeGoals < awayGoals) {
					for(int i = 0;i < teams.length; i++) {
						if(teams[i].getName().equals(match.getAwayTeam())) {
							teams[i].setScoreOfTeam(teams[i].getScoreOfTeam() + 3);
							break;
						}
					}
				}
				else {
					int count = 0;
					for(int i = 0;i < teams.length; i++) {
						if(teams[i].getName().equals(match.getAwayTeam()) || teams[i].getName().equals(match.getHomeTeam())) {
							teams[i].setScoreOfTeam(teams[i].getScoreOfTeam() + 1);
							count++;
						}
						if(count == 2) {
							break;
						}
					}
				}
				Main.commandCounter++;
			}
		}
		else {
			System.out.println("!!! ERROR !!! To add a match you must first add at least 3 referees, at least 2 teams and at least one stadium !");
		}
	}
	public void addTeam(Team team) {
		//The presence of the team is checked to see if it has been added before.
		boolean isThereThisTeam = false;
		if( teams != null) {
			for(int i = 0;i < teams.length; i++) {
				if(teams[i].getName().toLowerCase().equals(team.getName().toLowerCase())) {
					isThereThisTeam = true;
					break;
				}
			}
		}
		if(isThereThisTeam) {
			System.out.println("ERROR !!! You tried to add a team you added before !");
		}
		else {
			if(countOfTeams <= 21) {
				//is later added to the array called teams
				if(countOfTeams != 0) {
					Team[] tempArray = new Team[countOfTeams];
					for(int i = 0;i < tempArray.length; i++) {
						tempArray[i] = teams[i];
					}
					teams = new Team[countOfTeams + 1];
					for(int i = 0;i < tempArray.length; i++) {
						teams[i] = tempArray[i];
					}
					teams[countOfTeams] = team;
				}
				else {
					teams = new Team[countOfTeams + 1];
					teams[countOfTeams] = team;
				}
				Main.commandCounter++;
				countOfTeams++;
			}
			else {
				System.out.println("ERROR !!! You cannot add more than 21 teams !");
			}
		}
	}
	public void addReferee(Referee referee) {
		//checking the presence of the referee
		boolean isThereThisReferee = false;
		if(referees != null) {
			for(int i = 0;i < referees.length; i++) {
				if(referees[i].getPhone().getNumber() == referee.getPhone().getNumber()) {
					isThereThisReferee = true;
					break;
				}
			}
		}
		if(isThereThisReferee) {
			System.out.println("ERROR !!! You tried to add a referee you added before !");
		}
		else {
			//is later added to the series called referees
			referee.setRefereeId(refereeId);
			if(countOfReferees != 0) {
				Referee[] tempArray = new Referee[countOfReferees];
				for(int i = 0;i < tempArray.length; i++) {
					tempArray[i] = referees[i];
				}
				referees = new Referee[countOfReferees + 1];
				for(int i = 0;i < tempArray.length; i++) {
					referees[i] = tempArray[i];
				}
				referees[countOfReferees] = referee;
			}
			else {
				referees = new Referee[countOfReferees + 1];
				referees[countOfReferees] = referee;
			}
			Main.commandCounter++;
			refereeId++;
			countOfReferees++;
		}
	}
	public void addStadium(Stadium stad) {
		//Has such a stadium been added before, it is controlled
		boolean isThereThisStadium = false;
		if(stadiums != null) {
			for(int i = 0;i < stadiums.length; i++) {
				if(stadiums[i].getName().equals(stad.getName())) {
					isThereThisStadium = true;
					break;
				}
			}
		}
		if(isThereThisStadium) {
			System.out.println("!!! ERROR !!! You tried to add a stadium that was added before !");
		}
		else {
			//Afterwards, dynamics are created and added as a new stadium.
			stad.setStadiumId(stadiumId);
			if(countOfStadiums != 0) {
				Stadium[] tempArray = new Stadium[countOfStadiums];
				for(int i = 0;i < tempArray.length; i++) {
					tempArray[i] = stadiums[i];
				}
				stadiums = new Stadium[countOfStadiums + 1];
				for(int i = 0;i < tempArray.length; i++) {
					stadiums[i] = tempArray[i];
				}
				stadiums[countOfStadiums] = stad;
			}
			else {
				stadiums = new Stadium[countOfStadiums + 1];
				stadiums[countOfStadiums] = stad;
			}
			Main.commandCounter++;
			stadiumId++;
			countOfStadiums++;
		}
	}
	public void addCompany(Team.Company company) {
		if(teams != null) {
			boolean teamNameControl = false;
			for(int i = 0;i < teams.length; i++) {
				//if there is a team in the given team name, we will add that team as a sponsor.
				if((teams[i].getName().toLowerCase()).equals(company.getTeam().toLowerCase())) {
					teams[i].setCompany(company);
					teamNameControl = true;
					Main.commandCounter++;
					break;
				}
			}
			if(!teamNameControl) {
				System.out.println("!!! ERROR !!! You haven't added a team with this name yet !!!");
			}
		}
		else {
			System.out.println("!!! ERROR !!! You Must add a team before adding sponsor companies !!!");
		}
	}
	public void addCoach(Team.Coach coach) {
		if(teams != null) {
			//First of all, the contract start date is before the end date it is checked
			if(Main.DateControl(coach.getStartDate(), coach.getEndDate())) {
				//then the truth of date is checked
				if(Main.IsTheDateReal(coach.getStartDate()) && Main.IsTheDateReal(coach.getEndDate())) {
					//After that, the existence of the named team is checked
					boolean teamNameControl = false;
					for(int i = 0;i < teams.length; i++) {
						//if there is a named team, the coach will be added to that team.
						if(teams[i].getName().equals(coach.getTeam())) {
							teams[i].setCoach(coach);
							teamNameControl = true;
							Main.commandCounter++;
							break;
						}
					}
					if(!teamNameControl) {
						System.out.println("!!! ERROR !!! You haven't added a team with this name yet !!!");
					}
				}
				else {
					System.out.println("!!! ERROR !!! You have entered an incorrect date !!!");
				}
			}
			else {
				System.out.println("!!! ERROR !!! Contract start date must be earlier than contract end !!!");
			}
		}
		else {
			System.out.println("!!! ERROR !!! You must add a team before adding coaches !!!");
		}
	}
	public void addPlayer(Player player) {
		if(teams != null) {
			//required date checks are called from the Main class
			if(Main.DateControl(player.getStartDate(), player.getEndDate())) {
				if(Main.IsTheDateReal(player.getDateOfBirth()) && Main.IsTheDateReal(player.getStartDate()) && Main.IsTheDateReal(player.getEndDate())) {
					//After checking the correctness of the given team's name, the addPlayer command of the team class is called.
					boolean teamNameControl = false;
					for(int i = 0;i < teams.length; i++) {
						if(teams[i].getName().equals(player.getTeam())) {
							teams[i].addPlayer(player);
							teamNameControl = true;
							Main.commandCounter++;
							break;
						}
					}
					if(!teamNameControl) {
						System.out.println("!!! ERROR !!! You haven't added a team with this name yet !!!");
					}
				}
				else {
					System.out.println("!!! ERROR !!! You have entered an incorrect date !!!");
				}
			}
			else {
				System.out.println("!!! ERROR !!! Contract start date must be earlier than contract end !!!");
			}
		}
		else {
			System.out.println("!!! ERROR !!! You must first add a team !");
		}
	}
	public void deletePlayer(int licenseNumber) {
		//First of all, the presence of the team is checked
		if(teams != null) {
			//The license number given afterwards is checked whether there is a player or not.
			boolean isThereThisLicenseNumber = false;
			for(int i = 0;i < teams.length; i++) {
				if(teams[i].getPlayers() != null) {
					Player[] playersArray = teams[i].getPlayers();
					for(int j = 0;j < playersArray.length; j++) {
						if(playersArray[j].getLicenseNumber() == licenseNumber) {
							//Calling team class's deletePlayer command if the given license number is correct
							teams[i].deletePlayer(playersArray[j].getPlayerId());
							isThereThisLicenseNumber = true;
							Main.commandCounter++;
							break;
						}
					}
					//break commands are used to avoid unnecessary checks.
					if(isThereThisLicenseNumber) {
						break;
					}
				}
			}
			if(!isThereThisLicenseNumber) {
				System.out.println("!!! ERROR !!! NO PLAYER WITH THIS LICENSE WAS FOUND !!!");
			}
		}
		else {
			System.out.println("!!! ERROR !!! YOU MUST FIRST ADD A TEAM !!!");
		}
	}
	public void listAllTeams() {
		//The team class's displayTeam method is called
		if(teams != null) {
			System.out.println("\n# LIST OF TEAMS \n");
			for(int i = 0;i < teams.length; i++) {
				System.out.println("\t" + teams[i].displayTeam());
			}
		}
		else {
			System.out.println("ERROR !!! TEAM LIST NOT FOUND !!!");
		}
	}
	public void listAllPlayers() {
		//The team class's player listing method is called
		if(teams != null) {
			System.out.println("\n# LIST OF ALL PLAYERS\n");
			for(int i = 0;i < teams.length; i++) {
				teams[i].listPlayer();
			}
		}
		else {
			System.out.println("ERROR !!! YOU MUST ADD A TEAM FIRST !!!");
		}
	}
	public void listAllSponsorCompanies() {
		/*Since the sponsor companies are written within the team class, 
		the getter methods of the team class and the Company class are used.*/
		System.out.println("\n# LIST OF SPONSOR COMPANIES\n");
		boolean isList = false;
		for(int i = 0; i < teams.length; i++) {
			if(teams[i].getCompany() != null) {
				Team.Company comp = teams[i].new Company();
				comp = teams[i].getCompany();
				System.out.println("\t" + comp.displayCompany());
				isList = true;
			}
		}
		if(!isList) {
			System.out.println("!!! ERROR !!! LIST NOT FOUND");
		}
	}
	public void listAllReferees() {
		//The referee list was created by calling display method from the referee class.
		if(referees != null) {
			System.out.println("# LIST OF REFEREES\n");
			for(int i = 0;i < referees.length; i++) {
				System.out.println("\t" + referees[i].displayReferee());
			}
		}
		else {
			System.out.println("!!! ERROR !!! List not found !!!");
		}
	}
	public String[] scoreTable() {
		//I did it using the name of each team together with their score in a string array to be able to sort.
		String[] scoreTable = new String[teams.length];
		String  tempOrder;
		for(int i = 0;i < teams.length; i++) {
			scoreTable[i] = teams[i].getName() + " " + teams[i].getScoreOfTeam();
		}
		for(int i = 0;i < scoreTable.length; i++) {
			for(int j = i + 1;j < scoreTable.length; j++) {
				String[] tempScore1 = scoreTable[i].split(" ");
				String[] tempScore2 = scoreTable[j].split(" ");
				if(Integer.valueOf(tempScore1[1]) < Integer.valueOf(tempScore2[1])) {
					tempOrder = scoreTable[i];
					scoreTable[i] = scoreTable[j];
					scoreTable[j] = tempOrder;
				}
			}
		}
		return scoreTable;
	}
	public String findBiggestStadium() {
		//the biggest stadium is in this function
		int biggestCapacity = 0;
		String stadName = "";
		for(int i = 0;i < stadiums.length; i++) {
			if(biggestCapacity < stadiums[i].getCapacity()) {
				biggestCapacity = stadiums[i].getCapacity();
				stadName = stadiums[i].getName();
			}
		}
		String stadiumName = stadName + "   " + biggestCapacity;
		return stadiumName;
	}
	public void increaseAllRefereeSalaries() {
		//procedure by which referee salaries are increased
		for(int i = 0;i < referees.length; i++) {
			double salary = referees[i].getSalary();
			salary = salary + salary * 0.1 ;
			referees[i].setSalary(salary);
		}
	}
	//The Referee and Stadium class is written in the inner class of the TFF because they cannot survive without the TFF
    public class Referee {
    	//Referee class was created with its basic features
		private String name;
		private Address address;
		private Phone phone;
		private double salary;
		private int refereeId;
		public int getRefereeId() {
			return refereeId;
		}
		public void setRefereeId(int id) {
			this.refereeId = id;
		}
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
		public double getSalary() {
			return salary;
		}
		public void setSalary(double salary) {
			this.salary = salary;
		}
		public String displayReferee() {
			return "ID:" + refereeId + "  " + name + "  " + "Address:" + address.displayAddress() + "  " + "Phone " + phone.displayPhone() + "  " + "Salary " + salary + "$";
		}
	}
    public class Stadium {
    	//This class was created with its basic features
		private String name;
		private String city;
		private int capacity;
		private boolean lighthing;
		private String surface;
		private int stadiumId;
		public int getStadiumId() {
			return stadiumId;
		}
		public void setStadiumId(int id) {
			stadiumId = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public int getCapacity() {
			return capacity;
		}
		public void setCapacity(int capacity) {
			this.capacity = capacity;
		}
		public boolean getLighthing() {
			return lighthing;
		}
		public void setLighthing(boolean lighthing) {
			this.lighthing = lighthing;
		}
		public String getSurface() {
			return surface;
		}
		public void setSurface(String surface) {
			this.surface = surface;
		}
	}
	
}
