
public class Phone {
	//phone class was created with its basic features
	private int countryCode;
	private int areaCode;
	private int number;
	public int getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(int countryCode) {
		this.countryCode = countryCode;
	}
	public int getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(int areaCode) {
		this.areaCode = areaCode;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String displayPhone() {
		return "+" + countryCode + "(" + areaCode + ")" + number;
	}
}
