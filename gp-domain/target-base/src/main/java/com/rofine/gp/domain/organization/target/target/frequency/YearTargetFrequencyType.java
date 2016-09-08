//Source file: E:\\my_workspace\\workprojects\\201605��Ʒ�з�\\project\\gp-domain\\src\\main\\java\\com\\rofine\\gp\\domain\\DesignModel\\DesignElement\\domain\\organization\\target\\target\\frequency\\YearTargetFrequencyType.java

package com.rofine.gp.domain.organization.target.target.frequency;

import java.util.ArrayList;
import java.util.List;

import com.rofine.gp.domain.organization.target.TargetException;
import com.rofine.gp.platform.exception.GpException;
import com.rofine.gp.platform.util.DateUtil;

public class YearTargetFrequencyType implements TargetFrequencyType {

	/**
	 * @throws GpException
	 * @roseuid 573BDF66009D
	 */
	public List<TargetFrequency> createTargetFrequencys() throws TargetException {
		List<TargetFrequency> targetFrequencys = new ArrayList<TargetFrequency>();

		TargetFrequency targetFrequency = new TargetFrequency();
		targetFrequency.setCode("Y01");

		int currentYear = DateUtil.getSysYear();
		targetFrequency.setName(currentYear + "年度");

		try {
			targetFrequency.setStartDate(DateUtil.createDate(currentYear + "-01-01"));
			targetFrequency.setEndDate(DateUtil.createDate(currentYear + "-12-31"));
			targetFrequency.setRemindDate(DateUtil.createDate(currentYear + "-11-15"));
			targetFrequency.setLimitDate(DateUtil.createDate((currentYear + 1) + "-01-15"));
		} catch (GpException e) {
			throw new TargetException(e);
		}

		targetFrequencys.add(targetFrequency);

		return targetFrequencys;
	}
}
