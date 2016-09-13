package com.rofine.gp.domain.organization.target.execute.service;

import java.util.ArrayList;
import java.util.List;

import com.rofine.gp.domain.organization.target.domain.ObjectTargetExecuteVO;
import com.rofine.gp.domain.organization.target.execute.model.ObjectTargetExecute;

public class ObjectTagetExecuteAssembler {

	public static List<ObjectTargetExecuteVO> toVO(List<ObjectTargetExecute> exexutes) {

		List<ObjectTargetExecuteVO> executeVOs = new ArrayList<ObjectTargetExecuteVO>(exexutes.size());
		for (ObjectTargetExecute execute : exexutes) {
			executeVOs.add(execute.toVO());
		}

		return executeVOs;
	}

}
