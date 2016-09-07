package com.rofine.gp.domain.organization.target.scheme.event;

import org.springframework.context.ApplicationEvent;

public class SchemeStartedEvent extends ApplicationEvent{

	public SchemeStartedEvent(Object source) {
		super(source);
	}

}
