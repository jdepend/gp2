package com.rofine.gp.application.organization.target.plan.schemeext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rofine.gp.platform.user.User;

@Service
public class SchemeExtService {

	@Autowired
	private SchemeExtRepo schemeExtRepo;

	public void createSchemeExt(SchemeExt schemeExt, User user) {
		schemeExtRepo.save(schemeExt);
	}

	public Map<String, SchemeExt> listScheme(List<String> schemeIds) {
		List<SchemeExt> schemeExts = schemeExtRepo.findByIdIn(schemeIds);
		Map<String, SchemeExt> schemeExtMap = new HashMap<String, SchemeExt>();
		for (SchemeExt schemeExt : schemeExts) {
			schemeExtMap.put(schemeExt.getId(), schemeExt);
		}

		return schemeExtMap;
	}

	public SchemeExt getScheme(String schemeId) {
		return schemeExtRepo.findOne(schemeId);
	}

	public void clearSchemeExts() {
		schemeExtRepo.deleteAll();
	}
}
