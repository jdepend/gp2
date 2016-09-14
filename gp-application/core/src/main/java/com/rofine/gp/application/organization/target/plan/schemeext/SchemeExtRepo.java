package com.rofine.gp.application.organization.target.plan.schemeext;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SchemeExtRepo extends CrudRepository<SchemeExt, String> {

	public List<SchemeExt> findByIdIn(List<String> ids);

}
