package runner;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class MemberTask {
	private static String[] members = { "SHaran", "Hello" };

	public static void main(String[] inputs) {
//		if (inputs == null || inputs.length < 1) {
//			System.out.println("No inputs");
//			return;
//		}
//		members = inputs;
		addMember("Sharan");
	}

	private static void addMember(String name) {
		ArrayList<String> memberList = (ArrayList<String>) Arrays.asList(members);

		memberList.add(name);
		System.out.println(memberList);
		

//		ArrayList<String> newList = new ArrayList<String>(memberList);
//		newList.add(name);
//		System.out.println(newList);
	}
}