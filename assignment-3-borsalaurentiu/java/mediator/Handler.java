package mediator;

public interface Handler {
	public String handle(Colleague colleague);
	public String getType();
}
