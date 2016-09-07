package com.rofine.gp.domain.organization.score;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class UnitScoreService {

	@Autowired
	private UnitScoreRepo unitScoreRepo;

	@Autowired
	private List<UnitScoreLoader> loaders;

	public List<UnitScore> getScores(int year) {
		return unitScoreRepo.findByYear(year);
	}

	public void create(int year) {
		unitScoreRepo.deleteByYear(year);
		for (UnitScoreLoader loader : loaders) {
			Map<String, Float> scores = loader.loadScore(year);
			List<UnitScore> unitScores = new ArrayList<UnitScore>();
			UnitScore unitScore;
			for (String unitId : scores.keySet()) {
				unitScore = new UnitScore();
				unitScore.setUnitId(unitId);
				unitScore.setScore(scores.get(unitId));
				unitScore.setYear(year);
				unitScore.setSource(loader.getSource());
				unitScores.add(unitScore);
			}
			unitScoreRepo.save(unitScores);
		}
	}
}
