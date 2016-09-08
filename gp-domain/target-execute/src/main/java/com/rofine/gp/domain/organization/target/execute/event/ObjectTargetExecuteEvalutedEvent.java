package com.rofine.gp.domain.organization.target.execute.event;

import org.springframework.context.ApplicationEvent;

public class ObjectTargetExecuteEvalutedEvent extends ApplicationEvent{

	public ObjectTargetExecuteEvalutedEvent(Object source) {
		super(source);
	}

}
