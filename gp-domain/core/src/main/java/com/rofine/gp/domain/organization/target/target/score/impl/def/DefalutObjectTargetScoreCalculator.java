package com.rofine.gp.domain.organization.target.target.score.impl.def;

import org.springframework.stereotype.Service;

import com.rofine.gp.domain.organization.target.TargetException;
import com.rofine.gp.domain.organization.target.domain.ObjectTargetExecuteVO;
import com.rofine.gp.domain.organization.target.scheme.model.ObjectTarget;
import com.rofine.gp.domain.organization.target.target.score.ObjectTargetScoreCalculator;

@Service
public class DefalutObjectTargetScoreCalculator implements ObjectTargetScoreCalculator {

	@Override
	public void calculate(ObjectTarget objectTarget) throws TargetException {
		Float score = 0.0F;
		for (ObjectTargetExecuteVO execute : objectTarget.getObjectTargetExecutes()) {
			if (execute.getEvaluateScore() != null) {
				score += execute.getEvaluateScore();
			}
		}
		objectTarget.setScore(score);
	}

}
