package com.rofine.gp.domain.organization.score;

import java.util.Map;

public interface UnitScoreLoader {
	
	
	/**
	 * 分数来源
	 * 
	 * @return
	 */
	public String getSource();
	/**
	 * 计算年度组织单元分数
	 * 
	 * @param year
	 * @return
	 */
	public Map<String, Float> loadScore(int year);

}
