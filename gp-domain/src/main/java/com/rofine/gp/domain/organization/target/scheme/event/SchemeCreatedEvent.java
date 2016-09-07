package com.rofine.gp.domain.organization.target.scheme.event;

import org.springframework.context.ApplicationEvent;

public class SchemeCreatedEvent extends ApplicationEvent{

	public SchemeCreatedEvent(Object source) {
		super(source);
	}

}
