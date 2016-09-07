package com.rofine.gp.domain.organization.target.scheme.event;

import java.util.List;

import org.springframework.context.ApplicationEvent;

import com.rofine.gp.domain.organization.target.scheme.SchemeObjectVO;

public class Target2ObjectCreatedEvent extends ApplicationEvent {

	private String schemeId;
	private String targetId;
	private List<SchemeObjectVO> objects;

	public Target2ObjectCreatedEvent(Object source) {
		super(source);
	}

	public Target2ObjectCreatedEvent(String schemeId, String targetId, List<SchemeObjectVO> objects) {
		super(targetId);
		this.schemeId = schemeId;
		this.targetId = targetId;
		this.objects = objects;
	}

	public String getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public List<SchemeObjectVO> getObjects() {
		return objects;
	}

	public void setObjects(List<SchemeObjectVO> objects) {
		this.objects = objects;
	}

}
