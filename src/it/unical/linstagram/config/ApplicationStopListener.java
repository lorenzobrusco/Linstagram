package it.unical.linstagram.config;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStopListener
implements ApplicationListener<ContextClosedEvent> {

	
	//TODO VEDERE SE CLEAR CONTEXT È GIUSTO DA CHIAMARE
	@Override
	public void onApplicationEvent(final ContextClosedEvent event) {

		SecurityContextHolder.clearContext();
		return;
	}
	



} // class
