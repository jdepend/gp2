//Source file: E:\\my_workspace\\workprojects\\201605��Ʒ�з�\\project\\gp-portal\\src\\main\\java\\com\\rofine\\gp\\portal\\DesignModel\\DesignElement\\portal\\organization\\target\\plan\\PlanController.java

package com.rofine.gp.portal.organization.target.plan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rofine.gp.application.organization.target.plan.PlanAppService;
import com.rofine.gp.application.organization.target.plan.SchemeVO;
import com.rofine.gp.domain.organization.target.TargetException;
import com.rofine.gp.domain.organization.target.scheme.model.Scheme;
import com.rofine.gp.domain.organization.target.scheme.model.SchemeObject;
import com.rofine.gp.domain.organization.target.scheme.model.Target;
import com.rofine.gp.domain.organization.target.scheme.model.TargetType;
import com.rofine.gp.platform.exception.GpException;
import com.rofine.gp.platform.user.User;
import com.rofine.gp.portal.security.UserUtil;

@Controller
@RequestMapping(value = "/scheme")
public class PlanController {

	@Autowired
	private PlanAppService planAppService;

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createScheme() {

		return "scheme_create";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createScheme(@ModelAttribute("scheme") SchemeVO scheme) throws GpException {

		User user = UserUtil.getUser();

		planAppService.createScheme(scheme, user);

		Map<String, Object> rtn = new HashMap<String, Object>();

		rtn.put("code", "1");
		rtn.put("msg", "操作成功");

		return rtn;

	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listScheme(Model model) {

		Pageable pageable = new PageRequest(0, 20);
		Page<SchemeVO> schemes = planAppService.listScheme(pageable);

		model.addAttribute("schemes", schemes);

		return "scheme_list";
	}

	@RequestMapping(value = "/list/page/{page}/size/{size}", method = RequestMethod.GET)
	public String listScheme(Model model, @PathVariable int page, @PathVariable int size) {

		Pageable pageable = new PageRequest(page, size);
		Page<SchemeVO> schemes = planAppService.listScheme(pageable);

		model.addAttribute("schemes", schemes);

		return "scheme_list";
	}

	@RequestMapping(value = "/{schemeId}/view", method = RequestMethod.GET)
	public String viewScheme(@PathVariable String schemeId, Model model) {

		SchemeVO scheme = planAppService.getScheme(schemeId);

		model.addAttribute("scheme", scheme);

		return "scheme_view";
	}

	@RequestMapping(value = "/{schemeId}/object/create", method = RequestMethod.GET)
	public String createSchemeObject(@PathVariable String schemeId, Model model) {

		model.addAttribute("schemeId", schemeId);

		return "object_create";
	}

	@RequestMapping(value = "/{schemeId}/object/create", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createSchemeObject(@ModelAttribute("object") SchemeObject object) throws TargetException {

		planAppService.createSchemeObject(object);

		Map<String, Object> rtn = new HashMap<String, Object>();

		rtn.put("code", "1");
		rtn.put("msg", "操作成功");

		return rtn;

	}

	@RequestMapping(value = "/{schemeId}/target/type/create", method = RequestMethod.GET)
	public String createRootTargetType(@PathVariable String schemeId, Model model) {

		model.addAttribute("schemeId", schemeId);

		return "target_type_root_create";
	}

	@RequestMapping(value = "/{schemeId}/target/type/create", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createTargetType(@ModelAttribute("targetType") TargetType targetType)
			throws TargetException {

		planAppService.createTargetType(targetType);

		Map<String, Object> rtn = new HashMap<String, Object>();

		rtn.put("code", "1");
		rtn.put("msg", "操作成功");

		return rtn;

	}

	@RequestMapping(value = "/{schemeId}/target/type/{parentId}/target/type/create", method = RequestMethod.GET)
	public String createChildTargetType(@PathVariable String schemeId, @PathVariable String parentId, Model model) {

		model.addAttribute("schemeId", schemeId);
		model.addAttribute("parentId", parentId);

		return "target_type_child_create";
	}

	@RequestMapping(value = "/{schemeId}/target/type/{parentId}/target/type/create", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createTargetType(@PathVariable String parentId,
			@ModelAttribute("targetType") TargetType targetType) throws TargetException {

		planAppService.createTargetType(targetType);

		Map<String, Object> rtn = new HashMap<String, Object>();

		rtn.put("code", "1");
		rtn.put("msg", "操作成功");

		return rtn;

	}

	@RequestMapping(value = "/{schemeId}/target/create", method = RequestMethod.GET)
	public String createTarget(@PathVariable String schemeId, Model model) {

		model.addAttribute("schemeId", schemeId);

		return "target_create";
	}

	@RequestMapping(value = "/{schemeId}/target/create", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createTarget(@ModelAttribute("target") Target target) throws TargetException {

		planAppService.createTarget(target);

		Map<String, Object> rtn = new HashMap<String, Object>();

		rtn.put("code", "1");
		rtn.put("msg", "操作成功");

		return rtn;

	}

	@RequestMapping(value = "/{schemeId}/target/type/{targetTypeId}/target/create", method = RequestMethod.GET)
	public String createLefTarget(@PathVariable String schemeId, @PathVariable String targetTypeId, Model model) {

		model.addAttribute("schemeId", schemeId);
		model.addAttribute("targetTypeId", targetTypeId);

		return "target_lef_create";
	}

	@RequestMapping(value = "/{schemeId}/target/type/{targetTypeId}/target/create", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createLefTarget(@ModelAttribute("target") Target target) throws TargetException {

		planAppService.createTarget(target);

		Map<String, Object> rtn = new HashMap<String, Object>();

		rtn.put("code", "1");
		rtn.put("msg", "操作成功");

		return rtn;

	}

	@RequestMapping(value = "/{schemeId}/target/{targetId}/update", method = RequestMethod.GET)
	public String updateTarget(@PathVariable String schemeId, @PathVariable String targetId, Model model) {

		Target target = planAppService.getTarget(schemeId, targetId);

		model.addAttribute("target", target);

		return "target_update";
	}

	@RequestMapping(value = "/{schemeId}/target/{targetId}/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateTarget(@ModelAttribute("target") Target target) throws TargetException {

		planAppService.updateTarget(target);

		Map<String, Object> rtn = new HashMap<String, Object>();

		rtn.put("code", "1");
		rtn.put("msg", "操作成功");

		return rtn;

	}

	@RequestMapping(value = "/{schemeId}/target/{targetId}/2object", method = RequestMethod.GET)
	public String target2object(@PathVariable String schemeId, @PathVariable String targetId, Model model) {

		model.addAttribute("schemeId", schemeId);
		model.addAttribute("targetId", targetId);

		return "target2object";
	}

	@RequestMapping(value = "/{schemeId}/target/{targetId}/2object", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> target2object(@PathVariable String schemeId, @PathVariable String targetId,
			@RequestParam(required = true) List<String> objectIds) throws TargetException {

		planAppService.target2object(schemeId, targetId, objectIds);

		Map<String, Object> rtn = new HashMap<String, Object>();

		rtn.put("code", "1");
		rtn.put("msg", "操作成功");

		return rtn;

	}

	/**
	 * @throws TargetException
	 * @roseuid 573C0E4F033F
	 */
	@RequestMapping(value = "/{schemeId}/start", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> start(@PathVariable String schemeId) throws TargetException {

		planAppService.startScheme(schemeId);

		Map<String, Object> rtn = new HashMap<String, Object>();

		rtn.put("code", "1");
		rtn.put("msg", "操作成功");

		return rtn;

	}
	
	/**
	 * @throws TargetException
	 * @roseuid 573C0E4F033F
	 */
	@RequestMapping(value = "/{schemeId}/close", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> close(@PathVariable String schemeId) throws TargetException {

		planAppService.closeScheme(schemeId);

		Map<String, Object> rtn = new HashMap<String, Object>();

		rtn.put("code", "1");
		rtn.put("msg", "操作成功");

		return rtn;

	}
}
