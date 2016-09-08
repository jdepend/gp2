//Source file: E:\\my_workspace\\workprojects\\201605��Ʒ�з�\\project\\gp-domain\\src\\main\\java\\com\\rofine\\gp\\domain\\DesignModel\\DesignElement\\domain\\organization\\target\\scheme\\SchemeRepo.java

package com.rofine.gp.domain.organization.target.scheme.repo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.rofine.gp.domain.organization.target.scheme.model.Scheme;

public interface SchemeRepo extends PagingAndSortingRepository<Scheme, String> {

	public List<Scheme> findByYear(int year);

}
