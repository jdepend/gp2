package com.rofine.gp.application.organization.target.execute.audit;

import java.util.List;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import com.rofine.gp.domain.organization.target.execute.event.ObjectTargetExecuteFilledEvent;
import com.rofine.gp.domain.organization.target.execute.model.ObjectTargetExecute;

@Service
public class ObjectTargetExecuteFilledListener implements ApplicationListener<ObjectTargetExecuteFilledEvent> {

	@Override
	public void onApplicationEvent(ObjectTargetExecuteFilledEvent event) {
		List<ObjectTargetExecute> executes = (List<ObjectTargetExecute>)event.getSource();
		executes.forEach(execute ->{execute.setState(ObjectTargetExecuteAudit.State_Fill_Auditing);});
	}
}
