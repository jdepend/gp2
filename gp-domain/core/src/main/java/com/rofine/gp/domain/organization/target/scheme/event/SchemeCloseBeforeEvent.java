package com.rofine.gp.domain.organization.target.scheme.event;

import org.springframework.context.ApplicationEvent;

public class SchemeCloseBeforeEvent extends ApplicationEvent{

	public SchemeCloseBeforeEvent(Object source) {
		super(source);
	}

}
