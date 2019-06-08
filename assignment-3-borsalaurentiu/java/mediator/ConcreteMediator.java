package mediator;

import java.util.ArrayList;
import java.util.List;

public class ConcreteMediator implements Mediator {
	public List<Handler> handlers;
	
	public ConcreteMediator() {
		handlers = new ArrayList<Handler>();
	}

	public void registerHandler(Handler handler) {
		handlers.add(handler); 
	}

	public String handle(Colleague colleague) {
		String type = colleague.getType();
		String result = null;
		for(Handler handle: handlers) {
			if(handle.getType().equals(type)) {
				result = handle.handle(colleague);
			}
		}
		return result;
	}
}