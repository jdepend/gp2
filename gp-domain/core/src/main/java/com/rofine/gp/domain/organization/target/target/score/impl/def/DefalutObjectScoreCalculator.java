package com.rofine.gp.domain.organization.target.target.score.impl.def;

import org.springframework.stereotype.Service;

import com.rofine.gp.domain.organization.target.TargetException;
import com.rofine.gp.domain.organization.target.scheme.model.ObjectTarget;
import com.rofine.gp.domain.organization.target.scheme.model.SchemeObject;
import com.rofine.gp.domain.organization.target.target.score.ObjectScoreCalculator;

@Service
public class DefalutObjectScoreCalculator implements ObjectScoreCalculator {

	@Override
	public void calculate(SchemeObject schemeObject) throws TargetException {
		Float score = 0.0F;
		int weight = 0;
		for (ObjectTarget objectTarget : schemeObject.getObjectTargets()) {
			if (objectTarget.getScore() != null) {
				if (objectTarget.getScore() != null) {
					score += objectTarget.getWeight() * objectTarget.getScore();
				}
				weight += objectTarget.getWeight();
			}
		}
		if (weight != 0) {
			schemeObject.setScore(score / weight);
		} else {
			throw new TargetException("schemeObject=" + schemeObject.getId() + "关联的指标权重和为零，无法计算被考核单元分值");
		}

	}

}
