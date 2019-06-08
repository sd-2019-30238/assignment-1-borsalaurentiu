package mediator;

public interface Mediator {
	void registerHandler(Handler handler);
	String handle(Colleague colleague);
}