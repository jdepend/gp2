package com.rofine.gp.listener.notification;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import com.rofine.gp.domain.organization.target.scheme.event.SchemeStartedEvent;

@Service
public class NotificationSchemeStartedListener implements ApplicationListener<SchemeStartedEvent> {

	@Override
	public void onApplicationEvent(SchemeStartedEvent event) {
		System.out.println(event.getSource());
	}
}
