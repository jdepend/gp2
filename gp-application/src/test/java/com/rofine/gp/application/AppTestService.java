package com.rofine.gp.application;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rofine.gp.application.organization.target.execute.audit.AuditFillVO;
import com.rofine.gp.application.organization.target.execute.audit.ObjectTargetExecuteAuditService;
import com.rofine.gp.application.organization.target.plan.PlanAppService;
import com.rofine.gp.application.organization.target.plan.SchemeVO;
import com.rofine.gp.domain.DomainTestService;
import com.rofine.gp.domain.organization.target.TargetException;
import com.rofine.gp.domain.organization.target.execute.model.ObjectTargetExecute;
import com.rofine.gp.platform.exception.GpException;
import com.rofine.gp.platform.user.User;
import com.rofine.gp.platform.user.UserImpl;

@Service
public class AppTestService extends DomainTestService {
	
	@Autowired
	private PlanAppService planAppService;
	
	@Autowired
	private ObjectTargetExecuteAuditService objectTargetExecuteAuditService;

	/**
	 * 增加审核用户audit_fill_222，audit_fill_333；
	 * 
	 * 2016-06-15：
	 * 
	 * 222部门用户filler222进行填报40；
	 * 222部门用户audit_fill_222进行审核；
	 * 333部门用户filler333进行填报20；
	 * 333部门用户audit_fill_333进行审核；
	 * 999部门用户evaluater999进行打分25；
	 * 888部门用户evaluater888进行打分35；
	 * 
	 * 2016-12-01：
	 * 
	 * 222部门用户filler222进行填报40； 
	 * 222部门用户audit_fill_222进行审核；
	 * 333部门用户filler333进行填报20；
	 * 333部门用户audit_fill_333进行审核；
	 * 999部门用户evaluater999进行打分25； 
	 * 888部门用户evaluater888进行打分35；
	 * 
	 * @throws GpException
	 */
	@Override
	protected void execute() throws TargetException {

		fill222();
		
		auditFill222();

		fill333();

		auditFill333();

		evaluate999();

		evaluate888();
	}
	
	@Override
	protected void createSchemeSelf() {
		// 创建方案
		SchemeVO schemeVO = new SchemeVO();
		schemeVO.setName("我的方案");
		schemeVO.setYear(2016);
		schemeVO.setTargetLevelCount(4);
		schemeVO.setDescription("这是我的方案");

		planAppService.createScheme(schemeVO, adminUser);
		
		scheme = schemeVO.getScheme();
	}
	
	protected void auditFill222() {
		// 填报审核用户222登录
		User auditFillUser222 = new UserImpl();

		auditFillUser222.setId("auditFiller222");
		auditFillUser222.setName("填报审核用户222");
		auditFillUser222.setOrgId("org111");
		auditFillUser222.setDeptId("dept222");// 填报部门
		auditFillUser222.setRoleIds(Arrays.asList("role_audit_fill_111", "role_audit_fill_222"));

		// 获取填报审核数据
		List<ObjectTargetExecute> executeFill222s = objectTargetExecuteAuditService.getAuditFillingExecutes(
				scheme.getId(), auditFillUser222);

		assertTrue(executeFill222s.size() == 2);

		List<AuditFillVO> audit222s = new ArrayList<AuditFillVO>();
		AuditFillVO audit222;
		for (ObjectTargetExecute execute : executeFill222s) {
			audit222 = new AuditFillVO();
			audit222.setExecuteId(execute.getId());
			audit222.setResult(true);

			audit222s.add(audit222);
		}

		objectTargetExecuteAuditService.auditFill(audit222s, auditFillUser222);

	}
	
	protected void auditFill333() {
		// 填报审核用户333登录
		User auditFillUser333 = new UserImpl();

		auditFillUser333.setId("auditFiller333");
		auditFillUser333.setName("填报审核用户333");
		auditFillUser333.setOrgId("org111");
		auditFillUser333.setDeptId("dept333");// 填报部门
		auditFillUser333.setRoleIds(Arrays.asList("role_audit_fill_111", "role_audit_fill_222"));

		// 获取填报审核数据
		List<ObjectTargetExecute> executeFill333s = objectTargetExecuteAuditService.getAuditFillingExecutes(
				scheme.getId(), auditFillUser333);

		assertTrue(executeFill333s.size() == 2);

		List<AuditFillVO> audit333s = new ArrayList<AuditFillVO>();
		AuditFillVO audit333;
		for (ObjectTargetExecute execute : executeFill333s) {
			audit333 = new AuditFillVO();
			audit333.setExecuteId(execute.getId());
			audit333.setResult(true);

			audit333s.add(audit333);
		}

		objectTargetExecuteAuditService.auditFill(audit333s, auditFillUser333);

	}

	@Override
	protected void clear() {
		planAppService.clearSchemes();
	}
}
