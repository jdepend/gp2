package com.rofine.gp.domain.organization.target.scheme.event;

import org.springframework.context.ApplicationEvent;

public class SchemeStartBeforeEvent extends ApplicationEvent{

	public SchemeStartBeforeEvent(Object source) {
		super(source);
	}

}
