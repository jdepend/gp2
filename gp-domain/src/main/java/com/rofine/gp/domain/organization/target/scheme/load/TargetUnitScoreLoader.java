package com.rofine.gp.domain.organization.target.scheme.load;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rofine.gp.domain.organization.score.UnitScoreLoader;
import com.rofine.gp.domain.organization.target.scheme.SchemeDomainService;
import com.rofine.gp.domain.organization.target.scheme.model.Scheme;
import com.rofine.gp.domain.organization.target.scheme.model.SchemeObject;

@Service
public class TargetUnitScoreLoader implements UnitScoreLoader {
	
	@Autowired
	private SchemeDomainService schemeDomainService;

	@Override
	public Map<String, Float> loadScore(int year) {
		
		List<Scheme> schemes = schemeDomainService.listScheme(year);

		Map<String, Float> scores = new HashMap<String, Float>();
		Float score;
		for (Scheme scheme : schemes) {
			for (SchemeObject schemeObject : scheme.getSchemeObjects()) {
				score = scores.get(schemeObject.getObjectId());
				if (score == null) {
					scores.put(schemeObject.getObjectId(), schemeObject.getScore());
				} else {
					score += schemeObject.getScore();
					scores.put(schemeObject.getObjectId(), score);
				}
			}
		}
		
		return scores;
	}

	@Override
	public String getSource() {
		return "target";
	}

}
