package bll;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.WaitingListDAO;
import model.WaitingList;

public class WaitingListBLL {

		public ArrayList<WaitingList> getWaitingList(String title) throws SQLException{
			ArrayList<WaitingList> waitingList = new ArrayList<WaitingList>();
			waitingList = WaitingListDAO.getWaitingList(title);
			return waitingList;
		}
}
