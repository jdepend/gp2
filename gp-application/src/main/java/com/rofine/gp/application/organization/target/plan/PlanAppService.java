//Source file: E:\\my_workspace\\workprojects\\201605��Ʒ�з�\\project\\gp-application\\src\\main\\java\\com\\rofine\\gp\\application\\DesignModel\\DesignElement\\application\\organization\\target\\plan\\PlanAppService.java

package com.rofine.gp.application.organization.target.plan;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rofine.gp.application.organization.target.plan.schemeext.SchemeExt;
import com.rofine.gp.application.organization.target.plan.schemeext.SchemeExtService;
import com.rofine.gp.domain.organization.target.TargetException;
import com.rofine.gp.domain.organization.target.scheme.SchemeAdminDomainService;
import com.rofine.gp.domain.organization.target.scheme.SchemeDomainService;
import com.rofine.gp.domain.organization.target.scheme.SchemeObjectVO;
import com.rofine.gp.domain.organization.target.scheme.model.Scheme;
import com.rofine.gp.domain.organization.target.scheme.model.SchemeObject;
import com.rofine.gp.domain.organization.target.scheme.model.Target;
import com.rofine.gp.domain.organization.target.scheme.model.TargetType;
import com.rofine.gp.platform.user.User;

@Service
@Transactional(rollbackFor = Exception.class)
public class PlanAppService {

	@Autowired
	private SchemeDomainService schemeDomainService;

	@Autowired
	private SchemeExtService schemeExtService;

	@Autowired
	private SchemeAdminDomainService schemeAdminDomainService;

	/**
	 * @throws TargetException
	 * @roseuid 5739B670019C
	 */
	public void target2object(String schemeId, String targetId,
			List<String> objectIds) throws TargetException {

		List<SchemeObjectVO> objects = new ArrayList<SchemeObjectVO>();
		SchemeObjectVO object;

		for (String objectId : objectIds) {
			object = new SchemeObjectVO();
			object.setObjectId(objectId);

			objects.add(object);
		}

		schemeDomainService.target2object(schemeId, targetId, objects);

	}

	/**
	 * @param schemeId
	 * @throws TargetException
	 * @roseuid 573C0E7502A7
	 */
	public void startScheme(String schemeId) throws TargetException {
		schemeDomainService.startScheme(schemeId);
	}

	public void closeScheme(String schemeId) throws TargetException {
		schemeDomainService.closeScheme(schemeId);
	}

	public void createScheme(SchemeVO schemeVO, User user) {
		Scheme scheme = schemeVO.getScheme();
		schemeDomainService.createScheme(scheme, user);
		schemeVO.setId(scheme.getId());

		SchemeExt schemeExt = schemeVO.getSchemeExt();
		schemeExt.setId(scheme.getId());

		schemeExtService.createSchemeExt(schemeExt, user);
	}

	public void createTarget(Target target) throws TargetException {
		schemeDomainService.createTarget(target);

	}

	public void createSchemeObject(SchemeObject object) throws TargetException {
		schemeDomainService.createSchemeObject(object);

	}

	public void createTargetType(TargetType targetType) throws TargetException {
		schemeDomainService.createTargetType(targetType);

	}

	public TargetType getTargetType(String id) {
		return schemeDomainService.getTargetType(id);
	}

	public Target getTarget(String schemeId, String targetId) {
		return schemeDomainService.getTarget(schemeId, targetId);
	}

	public void updateTarget(Target target) throws TargetException {
		schemeDomainService.updateTarget(target);
	}

	public Page<SchemeVO> listScheme(Pageable pageable) {

		Page<Scheme> schemes = schemeDomainService.listScheme(pageable);

		List<String> schemeIds = new ArrayList<String>();
		for (Scheme scheme : schemes.getContent()) {
			schemeIds.add(scheme.getId());
		}

		Map<String, SchemeExt> schemeExtMap = schemeExtService
				.listScheme(schemeIds);

		List<SchemeVO> schemeList = new ArrayList<SchemeVO>();
		SchemeVO schemeVO;
		for (Scheme scheme : schemes) {
			schemeVO = new SchemeVO(scheme, schemeExtMap.get(scheme.getId()));
			schemeList.add(schemeVO);
		}

		Page<SchemeVO> schemePage = new PageImpl<SchemeVO>(schemeList,
				pageable, schemes.getTotalElements());

		return schemePage;
	}

	public SchemeVO getScheme(String schemeId) {

		Scheme scheme = schemeDomainService.getScheme(schemeId);

		SchemeExt schemeExt = schemeExtService.getScheme(schemeId);

		SchemeVO schemeVO = new SchemeVO(scheme, schemeExt);

		return schemeVO;
	}

	public void clearSchemes() {

		schemeAdminDomainService.clearSchemes();

		schemeExtService.clearSchemeExts();
	}
}
