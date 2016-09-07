//Source file: E:\\my_workspace\\workprojects\\201605��Ʒ�з�\\project\\gp-domain\\src\\main\\java\\com\\rofine\\gp\\domain\\DesignModel\\DesignElement\\domain\\organization\\target\\execute\\TargetStateVO.java

package com.rofine.gp.domain.organization.target.execute;

public class TargetStatVO {

	private String targetId;

	private String frequencyCode;

	private int waitEvaluateCount;

	private int evaluatingCount;

	private int evaluatedCount;

	private int overdueEvaluateCount;

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public String getFrequencyCode() {
		return frequencyCode;
	}

	public void setFrequencyCode(String frequencyCode) {
		this.frequencyCode = frequencyCode;
	}

	public int getWaitEvaluateCount() {
		return waitEvaluateCount;
	}

	public void setWaitEvaluateCount(int waitEvaluateCount) {
		this.waitEvaluateCount = waitEvaluateCount;
	}

	public int getEvaluatingCount() {
		return evaluatingCount;
	}

	public void setEvaluatingCount(int evaluatingCount) {
		this.evaluatingCount = evaluatingCount;
	}

	public int getEvaluatedCount() {
		return evaluatedCount;
	}

	public void setEvaluatedCount(int evaluatedCount) {
		this.evaluatedCount = evaluatedCount;
	}

	public int getOverdueEvaluateCount() {
		return overdueEvaluateCount;
	}

	public void setOverdueEvaluateCount(int overdueEvaluateCount) {
		this.overdueEvaluateCount = overdueEvaluateCount;
	}

	@Override
	public String toString() {
		return "TargetStatVO [targetId=" + targetId + ", frequencyCode=" + frequencyCode + ", waitEvaluateCount="
				+ waitEvaluateCount + ", evaluatingCount=" + evaluatingCount + ", evaluatedCount=" + evaluatedCount
				+ ", overdueEvaluateCount=" + overdueEvaluateCount + "]";
	}
}
