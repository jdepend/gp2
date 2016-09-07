package com.rofine.gp.domain.organization.assessment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rofine.gp.domain.organization.score.UnitScoreLoader;

@Service
public class AssessmentUnitScoreLoader implements UnitScoreLoader {

	@Override
	public String getSource() {
		return "assessment";
	}

	@Override
	public Map<String, Float> loadScore(int year) {
		Map<String, Float> scores = new HashMap<String, Float>();
		
		scores.put("dept222", 20.0F);
		scores.put("dept444", 40.0F);
		
		return scores;
	}

}
