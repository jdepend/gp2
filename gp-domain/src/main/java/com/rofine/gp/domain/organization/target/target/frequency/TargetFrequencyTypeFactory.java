package com.rofine.gp.domain.organization.target.target.frequency;

import com.rofine.gp.domain.organization.target.TargetException;
import com.rofine.gp.domain.organization.target.scheme.model.Target;

public class TargetFrequencyTypeFactory {

	public static TargetFrequencyType create(String frequencyType) throws TargetException {

		switch (frequencyType) {
		case Target.TargetFrequencyType_Year:
			return new YearTargetFrequencyType();
		case Target.TargetFrequencyType_HalfYear:
			return new HalfYearTargetFrequencyType();
		default:
			throw new TargetException("frequencyType=" + frequencyType + "没有配置。");
		}
	}
}
