import java.io.*;
import java.nio.charset.Charset;

public class Main {
	
	static TFF tff = new TFF();
	/*I counted the commands from the file with a counter each time, if the command is not executed, 
	I showed the command line to the user on the screen.*/
	static int commandCounter = 0 ;
	static boolean uploadInput() throws IOException {
		File file = new File("D:\\input.txt");
		if(file.exists()) {
			FileReader fReader = new FileReader(file, Charset.forName("utf-8"));
			BufferedReader bReader = new BufferedReader(fReader);
			String line;
			int previousCommandNumber = - 1;
			String previousCommand = "";
			while((line = bReader.readLine()) != null) {
				if(previousCommandNumber == commandCounter) {
					System.out.println(">>> " + previousCommand);
				}
				previousCommandNumber = commandCounter;
				previousCommand = line;
				findCommand(line);
			}
			bReader.close();
			return true;
		}
		else {
			return false;
		}
	}
 	//the appropriate command is available in this function
	//here I thought each referee, player, and coach was added with country attributes
	static void findCommand(String line) {
		//properties of each command broken down by semicolon
		String[] properties = line.split(";");
		//address, phone and date properties were sent to the address creation, phone creation and date creation functions sequentially.
		String[] addressProperties = new String[4];
		int[] phoneProperties = new int[3];
		int[] startDateProperties = new int[3];
		int[] endDateProperties = new int[3];
		int[] birthDateProperties = new int[3];
		if(line.startsWith("AddTeam") && properties.length == 5) {
			//TFF's addTeam method was called after the relevant checks were made for this.
			try {
				Team team = new Team();
				team.setName(properties[1]);
				team.setYear(Integer.valueOf(properties[2]));
				team.setCups(Integer.valueOf(properties[3]));
				team.setColors(properties[4]);
				tff.addTeam(team);
			}
			catch (Exception e) {
				System.out.println("!!! ERROR !!! Enter the year and cup in numeric format !!!");
			}
		}
		else if(line.startsWith("AddCoach") && properties.length == 17) {
			try {
				//address and date phone strings are created here for the coach, then the appropriate functions are called
				for(int i = 2;i < 16; i++) {
					if(i < 6) {
						addressProperties[i - 2] = properties[i]; 
					}
					else if(i > 5 && i < 9) {
						phoneProperties[i - 6] = Integer.valueOf(properties[i]);
					}
					else if(i > 9 && i < 13) {
						startDateProperties[i - 10] = Integer.valueOf(properties[i]);
					}
					else if(i > 12) {
						endDateProperties[i - 13] = Integer.valueOf(properties[i]);
					}
				}
				Address address = CreateAddress(addressProperties);
				Phone phone = CreatePhone(phoneProperties);
				Date startDate = CreateDate(startDateProperties);
				Date endDate = CreateDate(endDateProperties);
				Team tempTeam = new Team();
				//Since the coach class is the inner class of the team class, a temporary tempTeam object was used to get an empty object from the coach class.
				Team.Coach coach = tempTeam.new Coach();
				coach.setName(properties[1]);
				coach.setAddress(address);
				coach.setPhone(phone);
				coach.setTeam(properties[9]);
				coach.setStartDate(startDate);
				coach.setEndDate(endDate);
				coach.setSalary(Integer.valueOf(properties[16]));
				//Afterwards, the coach given to the relevant team with the team name given in the addCoach method from the TFF class has been added.
				tff.addCoach(coach);
			}
			catch (Exception e) {
				System.out.println("!!! ERROR !!! Enter the date, phone number and Coach salary in numeric format !!!");
			}
		}
		else if(line.startsWith("AddPlayer") && properties.length == 23) {
			try {
				///address and date phone strings are created here for the player, then the appropriate functions are called
				for(int i = 3;i < 21; i++) {
					if(i < 6) {
						birthDateProperties[i - 3] = Integer.valueOf(properties[i]);
					}
					else if(i > 6 && i < 11) {
						addressProperties[i - 7] = properties[i];
					}
					else if(i > 10 && i < 14) {
						phoneProperties[i - 11] = Integer.valueOf(properties[i]);
					}
					else if(i > 14 && i < 18) {
						startDateProperties[i - 15] = Integer.valueOf(properties[i]);
					}
					else if(i > 17) {
						endDateProperties[i - 18] = Integer.valueOf(properties[i]);
					}
				}
				Address address = CreateAddress(addressProperties);
				Phone phone = CreatePhone(phoneProperties);
				Date birthDate = CreateDate(birthDateProperties);
				Date startDate = CreateDate(startDateProperties);
				Date endDate = CreateDate(endDateProperties);
				Player player = new Player();		
				player.setLicenseNumber(Integer.valueOf(properties[1]));
				player.setName(properties[2]);
				player.setDateOfBirth(birthDate);
				player.setNationality(properties[6]);
				player.setAddress(address);
				player.setPhone(phone);
				player.setTeam(properties[14]);
				player.setStartDate(startDate);
				player.setEndDate(endDate);
				player.setSalary(Integer.valueOf(properties[21]));
				player.setPositionalRole(properties[22]);
				//Then, with the help of the addPlayer method in TFF, the given team was found and added to that team as a player.
				tff.addPlayer(player);
			}
			catch (Exception e) {
				System.out.println("!!! ERROR !!! Enter the date, phone number, license number and Player salary in numeric format !!!");
			}
		}
		else if(line.startsWith("AddStadium") && properties.length == 6) {
			try {
				//Stadium object created using tff object since stadiums are TFF inner class
				TFF.Stadium stad = tff.new Stadium();
				stad.setName(properties[1]);
				stad.setCity(properties[2]);
				stad.setCapacity(Integer.valueOf(properties[3]));
				stad.setLighthing(Boolean.valueOf(properties[4]));
				stad.setSurface(properties[5]);
				tff.addStadium(stad);
			}
			catch (Exception e) {
				System.out.println("!!! ERROR !!! Enter the stadium capacity in numerical format and the lighting in true or false form !!!");
			}
		}
		else if((line.startsWith("AddReferee") || (line.startsWith("AddCompany"))) && properties.length == 10) {
			//The control of both the referee and the companies' command line similarity was done in the same place
			try {
				//The address and phone strings are created, then the necessary functions are called
				for(int i = 2;i < 9; i++) {
					if(i < 6) {
						addressProperties[i - 2] = properties[i];
					}
					else {
						phoneProperties[i - 6] = Integer.valueOf(properties[i]);
					}
				}
				Address address = CreateAddress(addressProperties);
				Phone phone = CreatePhone(phoneProperties);
				if(line.startsWith("AddReferee")) {
					TFF.Referee referee = tff.new Referee();
					referee.setName(properties[1]);
					referee.setAddress(address);
					referee.setPhone(phone);
					referee.setSalary(Double.valueOf(properties[9]));
					tff.addReferee(referee);
				}
				else {
					//A temporary object was created again because it is an inner class in the Company inner Team class.
					Team tempTeam = new Team();
					Team.Company company = tempTeam.new Company();
					company.setName(properties[1]);
					company.setAddress(address);
					company.setPhone(phone);
					company.setTeam(properties[9]);
					tff.addCompany(company);
				}
			}
			catch (Exception e) {
				System.out.println("!!! ERROR !!! Enter the referee salary in numerical format !!!");
			}
		}
		else if(line.startsWith("DeletePlayer") && properties.length == 2) {
			try {
				tff.deletePlayer(Integer.valueOf(properties[1]));
			}
			catch (Exception e) {
				System.out.println("!!! ERROR !!! You Must Enter the license number in numerical format");
			}
		}
		else if(line.startsWith("AddMatch") && properties.length == 9) {
			try {
				//created things suitable for adding matches and added to the TFF class
				Match match = new Match();
				match.setHomeTeam(properties[1]);
				match.setNumberOfGoalsHome(Integer.valueOf(properties[2]));
				match.setAwayTeam(properties[3]);
				match.setNumberOfGoalsAway(Integer.valueOf(properties[4]));
				match.setRefereeId1(Integer.valueOf(properties[5]));
				match.setRefereeId2(Integer.valueOf(properties[6]));
				match.setRefereeId3(Integer.valueOf(properties[7]));
				match.setStadiumId(Integer.valueOf(properties[8]));
				tff.addMatch(match);
			}
			catch (Exception e) {
				System.out.println("!!! ERROR !!! Referee ids, stadium id and goal numbers are a number please enter correctly.List the referees or stadiums to learn about the ids.");
				
			}
		}
	}
	static Address CreateAddress(String[] commandLine) {
		//I set address attributes by creating an empty address object
		Address address = new Address();
		address.setStreet(commandLine[0]);
		address.setTown(commandLine[1]);
		address.setCity(commandLine[2]);
		address.setCountry(commandLine[3]);
		return address;
	}
	static Phone CreatePhone(int[] commandLine) {
		//I set phone attributes by creating an empty phone object
		Phone phone = new Phone();
		phone.setCountryCode(commandLine[0]);
		phone.setAreaCode(commandLine[1]);
		phone.setNumber(commandLine[2]);
		return phone;
	}
	static Date CreateDate(int[] commandLine) {
		//I have set a date taken as a parameter, by creating an empty object from the date class, day month year one by one.
		Date date = new Date();
		date.setDay(commandLine[0]);
		date.setMonth(commandLine[1]);
		date.setYear(commandLine[2]);
		return date;
	}
	static void ListMethods() {
		//Listing methods are called here
		tff.listAllTeams();
		tff.listAllPlayers();
		tff.listAllSponsorCompanies();
	}
	static void LeagueStatistics() {
		System.out.println("\n# LEAGUE STATISTICS\n");
		String[] scoreTable = tff.scoreTable();
		//To list the teams, the longest line was found first, then the others were listed by the longest
		int biggestLine = 0;
		//the number of winners has been counted and a corresponding message has been given
		int winnerNumbers = 0;
		String[] winner = scoreTable[0].split(" ");
		for(int i = 0; i < scoreTable.length; i++) {
			if(biggestLine < scoreTable[i].length()) {
				biggestLine = scoreTable[i].length();
			}
		}
		//The list has been made with the necessary spaces.
		for(int i = 0;i < scoreTable.length; i++) {
			String[] tempOrder = scoreTable[i].split(" ");
			int order = i + 1;
			System.out.print("\t" + order + "." + tempOrder[0]);
			for(int j = 0;j < biggestLine - tempOrder[0].length(); j++) {
				System.out.print(" ");
			}
			System.out.println(tempOrder[1]);
			if(winner[1].equals(tempOrder[1])) {
				winnerNumbers++;
			}
		}
		if(winnerNumbers == 1) {
			System.out.println("\n\tWinner !!!  ->  " + winner[0]);
		}
		else if(winnerNumbers > 1) {
			System.out.print("\n\tWinners !!!  ->  ");
			for(int i = 0;i < winnerNumbers; i++) {
				String[] tempOrder = scoreTable[i].split(" ");
				if(i == winnerNumbers - 1) {
					System.out.println(tempOrder[0]);
				}
				else {
					System.out.print(tempOrder[0] + ", ");
				}
			}
		}
		//Later, the biggest stadium was found and written to the console.
		String biggestStadium = tff.findBiggestStadium();
		System.out.println("\n\tBiggest Stadium   ->   " + biggestStadium + "\n");
	}
	static void Operations() {
		////I increased the referee salaries and made a listing
		System.out.println("# OPERATIONS\n");
		System.out.println("\t-> All referee salaries increased by % 10 \n");
		tff.increaseAllRefereeSalaries();
		tff.listAllReferees();
	}
	public static void main(String[] args) throws IOException {
		if(uploadInput()) {
			ListMethods();
			LeagueStatistics();
			Operations();
		}
		else {
			System.out.println("!!! ERROR !!! File Not Found !!");
		}
	}
	////function that checks the accuracy of the given date
	static boolean IsTheDateReal(Date date) {
		int day = date.getDay();
		int month = date.getMonth();
		int year = date.getYear();
		if(day <= 0 || month <= 0 || year <= 0 || day > 31 || month > 12) {
			return false;
		}
		else {
			if(month == 2 || month == 4 || month == 6 || month == 9 || month == 11) {
				if(month == 2) {
					if(day > 29) {
						return false;
					}
					else if(day == 29 && year % 4 != 0) {
						return false;
					}
					return true;
				}
				else {
					if(day == 31) {
						return false;
					}
					return true;
				}
			}
			return true;
		}
	}
	//The function that checks the start and end dates of two given dates
	static boolean DateControl(Date startDate, Date endDate) {
		int startDay = startDate.getDay();
		int startMonth = startDate.getMonth();
		int startYear = startDate.getYear();
		int endDay = endDate.getDay();
		int endMonth = endDate.getMonth();
		int endYear = endDate.getYear();
		if(startYear > endYear) {
			return false;
		}
		else if(startYear < endYear) {
			return true;
		}
		else {
			if(startMonth > endMonth) {
				return false;
			}
			else if(startMonth < endMonth) {
				return true;
			}
			else {
				if(startDay > endDay) {
					return false;
				}
				else {
					return true;
				}
			}
		}
	}
}
