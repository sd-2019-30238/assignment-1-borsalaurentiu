package observer;

import java.sql.SQLException;
import java.util.ArrayList;

import cqrs.WaitingRead;
import model.WaitingList;

public class Subject {
	static ArrayList<String> emailTo = new ArrayList<String>();
	static ArrayList<WaitingList> waitingList = new ArrayList<WaitingList>();

	public static void addObserver(String title) throws SQLException {
		waitingList = WaitingRead.getWaitingList(title);
		for(WaitingList wt: waitingList) {
			emailTo.add(wt.getName() + "@gmail.com");
		}
		notifyObservers();
	}

	public static void notifyObservers() {
		(ObserverPatternDemo.availabilityObserver).sendMail(emailTo);
	}
}