//Source file: E:\\my_workspace\\workprojects\\201605��Ʒ�з�\\project\\gp-portal\\src\\main\\java\\com\\rofine\\gp\\portal\\DesignModel\\DesignElement\\portal\\organization\\target\\execute\\ExecuteController.java

package com.rofine.gp.portal.organization.target.execute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rofine.gp.application.organization.target.execute.ExecuteAppService;
import com.rofine.gp.domain.organization.target.TargetException;
import com.rofine.gp.domain.organization.target.domain.EvaluateVO;
import com.rofine.gp.domain.organization.target.domain.FillVO;
import com.rofine.gp.domain.organization.target.domain.ObjectTargetExecuteVO;
import com.rofine.gp.domain.organization.target.domain.TargetStatVO;
import com.rofine.gp.platform.exception.GpException;
import com.rofine.gp.platform.user.User;
import com.rofine.gp.portal.security.UserUtil;

@Controller
@RequestMapping(value = "/scheme")
public class ExecuteController {

	@Autowired
	private ExecuteAppService executeAppService;

	@RequestMapping(value = "/{schemeId}/fill", method = RequestMethod.GET)
	public String fill(@PathVariable String schemeId, Model model) throws Exception {

		User user = UserUtil.getUser();
		List<ObjectTargetExecuteVO> executes = executeAppService.getFillingExecutes(schemeId, user);

		model.addAttribute("executes", executes);

		return "fill";
	}

	@RequestMapping(value = "/{schemeId}/fill", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> fill(@PathVariable String schemeId, @RequestParam List<String> ids,
			@RequestParam List<Float> scores) throws GpException {

		User user = UserUtil.getUser();
		List<FillVO> fills = new ArrayList<FillVO>();
		FillVO fill;
		for (int index = 0; index < ids.size(); index++) {
			fill = new FillVO();
			fill.setExecuteId(ids.get(index));
			fill.setScore(scores.get(index));

			fills.add(fill);
		}
		executeAppService.fill(schemeId, fills, user);

		Map<String, Object> rtn = new HashMap<String, Object>();

		rtn.put("code", "1");
		rtn.put("msg", "操作成功");

		return rtn;
	}


	@RequestMapping(value = "/{schemeId}/evaluate", method = RequestMethod.GET)
	public String evaluate(@PathVariable String schemeId, Model model) throws GpException {

		User user = UserUtil.getUser();
		List<ObjectTargetExecuteVO> executes = executeAppService.getEvaluatingExecutes(schemeId, user);

		model.addAttribute("executes", executes);

		return "evaluate";
	}

	@RequestMapping(value = "/{schemeId}/evaluate", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> evaluate(@PathVariable String schemeId, @RequestParam List<String> ids,
			@RequestParam List<Float> scores) throws GpException {

		User user = UserUtil.getUser();
		List<EvaluateVO> evaluates = new ArrayList<EvaluateVO>();
		EvaluateVO evaluate;
		for (int index = 0; index < ids.size(); index++) {
			evaluate = new EvaluateVO();
			evaluate.setExecuteId(ids.get(index));
			evaluate.setScore(scores.get(index));

			evaluates.add(evaluate);
		}
		executeAppService.evaluate(evaluates, user);

		Map<String, Object> rtn = new HashMap<String, Object>();

		rtn.put("code", "1");
		rtn.put("msg", "操作成功");

		return rtn;
	}

	@RequestMapping(value = "/{schemeId}/operated/list", method = RequestMethod.GET)
	public String list(@PathVariable String schemeId, Model model) throws GpException {

		User user = UserUtil.getUser();
		List<ObjectTargetExecuteVO> executes = executeAppService.getOperatedExecutes(schemeId, user);

		model.addAttribute("executes", executes);

		return "list";
	}

	@RequestMapping(value = "/{schemeId}/execute/{executeId}/delete", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> evaluate(@PathVariable String schemeId, @PathVariable String executeId)
			throws TargetException {

		List<String> executeIds = new ArrayList<String>();
		executeIds.add(executeId);
		executeAppService.deleteExecutes(executeIds);

		Map<String, Object> rtn = new HashMap<String, Object>();

		rtn.put("code", "1");
		rtn.put("msg", "操作成功");

		return rtn;
	}

	/**
	 * @roseuid 573B11E502B6
	 */
	@RequestMapping(value = "/{schemeId}/monitor", method = RequestMethod.GET)
	public String monitor(@PathVariable String schemeId, Model model) {

		List<TargetStatVO> targets = executeAppService.monitor(schemeId);

		model.addAttribute("targets", targets);

		return "scheme_monitor";

	}
}
