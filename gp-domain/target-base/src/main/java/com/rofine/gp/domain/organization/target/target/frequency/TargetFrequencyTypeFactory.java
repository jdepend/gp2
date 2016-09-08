package com.rofine.gp.domain.organization.target.target.frequency;

import com.rofine.gp.domain.organization.target.TargetException;

public class TargetFrequencyTypeFactory {

	public static final String TargetFrequencyType_Year = "year";

	public static final String TargetFrequencyType_HalfYear = "half_year";

	public static TargetFrequencyType create(String frequencyType)
			throws TargetException {

		switch (frequencyType) {
		case TargetFrequencyType_Year:
			return new YearTargetFrequencyType();
		case TargetFrequencyType_HalfYear:
			return new HalfYearTargetFrequencyType();
		default:
			throw new TargetException("frequencyType=" + frequencyType
					+ "没有配置。");
		}
	}
}
