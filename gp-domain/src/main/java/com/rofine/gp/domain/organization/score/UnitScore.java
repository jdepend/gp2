package com.rofine.gp.domain.organization.score;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rofine.gp.platform.entity.IdEntity;

@Entity
@Table(name = "domain_unit_score")
public class UnitScore extends IdEntity {

	@Column(name = "unit_id", length = 36)
	private String unitId;

	@Column(name = "unit_type", length = 32)
	private String unitType;

	@Column(length = 32)
	private String source;

	private int year;

	private Float score;

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Override
	public String toString() {
		return "UnitScore [unitId=" + unitId + ", unitType=" + unitType + ", source=" + source + ", year=" + year
				+ ", score=" + score + "]";
	}
}
