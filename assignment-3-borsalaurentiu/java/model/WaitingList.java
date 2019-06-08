package model;

public class WaitingList {
	private String title;
	private String name;
	private int position;

	public WaitingList(String title, String name, int position) {
		this.title = title;
		this.name = name;
		this.position = position;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
}
