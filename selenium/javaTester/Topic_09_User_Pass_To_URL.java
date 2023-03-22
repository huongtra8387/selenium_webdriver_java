package javaTester;

public class Topic_09_User_Pass_To_URL {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "http://the-internet.herokuapp.com/basic_auth";
		String userName = "admin";
		String passWord = "admin";
		String[] arrayURL = url.split("//");
		url = arrayURL[0] + "//" + userName + ":" + passWord + "@" + arrayURL[1];
		System.out.print(url);
	}

}
