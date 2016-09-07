package com.rofine.gp.rest.organization;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rofine.gp.domain.organization.score.UnitScore;
import com.rofine.gp.domain.organization.score.UnitScoreService;
import com.rofine.gp.platform.exception.GpException;

@RestController
@RequestMapping(value = "/unit/score")
public class UnitScoreController {

	@Autowired
	private UnitScoreService unitScoreService;

	@RequestMapping(value = "/year/{year}/get", method = RequestMethod.GET)
	public Map<String, Object> getScore(@PathVariable int year) throws GpException {
		
		List<UnitScore> scores = unitScoreService.getScores(year);

		Map<String, Object> rtn = new HashMap<String, Object>();

		rtn.put("code", "1");
		rtn.put("msg", "操作成功");
		rtn.put("data", scores);

		return rtn;

	}

	@RequestMapping(value = "/year/{year}/create", method = { RequestMethod.POST, RequestMethod.GET })
	public Map<String, Object> calScore(@PathVariable int year) throws GpException {

		unitScoreService.create(year);

		Map<String, Object> rtn = new HashMap<String, Object>();

		rtn.put("code", "1");
		rtn.put("msg", "操作成功");

		return rtn;

	}

}
