//Source file: E:\\my_workspace\\workprojects\\201605��Ʒ�з�\\project\\gp-domain\\src\\main\\java\\com\\rofine\\gp\\domain\\DesignModel\\DesignElement\\domain\\organization\\target\\scheme\\SchemeDomainService.java

package com.rofine.gp.domain.organization.target.scheme;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rofine.gp.domain.organization.target.scheme.model.Scheme;
import com.rofine.gp.domain.organization.target.scheme.repo.SchemeRepo;

@Service
@Transactional(rollbackFor = Exception.class)
public class SchemeAdminDomainService {

	@Autowired
	private SchemeRepo schemeRepo;

	@Autowired
	private ApplicationContext applicationContext;

	public void deleteSchemes(List<Scheme> schemes) {
		schemeRepo.delete(schemes);
	}

	public void clearSchemes() {
		schemeRepo.deleteAll();
	}
}
