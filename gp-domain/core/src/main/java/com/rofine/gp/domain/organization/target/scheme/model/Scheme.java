//Source file: E:\\my_workspace\\workprojects\\201605��Ʒ�з�\\project\\gp-domain\\src\\main\\java\\com\\rofine\\gp\\domain\\DesignModel\\DesignElement\\domain\\organization\\target\\scheme\\Scheme.java

package com.rofine.gp.domain.organization.target.scheme.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotEmpty;

import com.rofine.gp.domain.organization.target.TargetException;
import com.rofine.gp.domain.organization.target.domain.ObjectTargetExecuteConstant;
import com.rofine.gp.domain.organization.target.execute.ObjectTargetExecuteDomainServiceStub;
import com.rofine.gp.domain.organization.target.scheme.SchemeObjectVO;
import com.rofine.gp.platform.entity.IdEntity;
import com.rofine.gp.platform.exception.GpException;

@Entity
@Table(name = "domain_scheme", uniqueConstraints = { @UniqueConstraint(columnNames = "name", name="方案名称不能重复") })
public class Scheme extends IdEntity {

	@NotEmpty(message="名称不能为空")
	@Column(length = 128)
	private String name;

	@NotEmpty(message="状态不能为空")
	@Column(length = 32)
	private String state;

	@Column
	private int year;

	@Column(name = "target_level_count")
	private int targetLevelCount;

	@OneToMany(mappedBy = "scheme", cascade = { CascadeType.ALL })
	@OrderBy("priority ASC")
	private List<Target> targets;

	@OneToMany(mappedBy = "scheme", cascade = { CascadeType.ALL })
	private List<TargetType> targetTypes;

	@OneToMany(mappedBy = "scheme", cascade = { CascadeType.ALL })
	private List<SchemeObjectGroup> schemeObjectGroup;

	@OneToMany(mappedBy = "scheme", cascade = { CascadeType.ALL })
	private List<SchemeObject> schemeObjects;

	@Column(length = 32)
	private String creator;
	
	@Column(name="creator_name",length = 64)
	private String creatorName;

	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "create_org",length = 128)
	private String createOrg;

	public static final String State_Init = "init";

	public static final String State_Started = "started";

	public static final String State_Closed = "closed";

	/**
	 * @throws TargetException
	 * @roseuid 573C0F2901EB
	 */
	public void start() throws TargetException {

		checkStart();

		doStart();
	}

	/**
	 * @param targetId
	 * @param objectIds
	 * @throws GpException
	 * @roseuid 573C106F01BC
	 */
	public void target2object(String targetId, List<SchemeObjectVO> objects) throws TargetException {

		for (SchemeObjectVO object : objects) {

			L: for (SchemeObject schemeObject : this.schemeObjects) {
				if (schemeObject.getId().equals(object.getObjectId())) {
					object.setObject(schemeObject);
					break L;
				}
			}
			if (object.getObject() == null) {
				throw new TargetException("没有发现对应的被考核对象objectId=" + object.getObjectId());
			}
		}

		Target theTarget = targets.stream().filter(target -> target.getId().equals(targetId)).findFirst().get();

		if (theTarget == null) {
			throw new TargetException("没有发现对应的考核指标targetId=" + targetId);
		}

		theTarget.relationObjects(objects);
	}

	/**
	 * @return Boolean
	 * @throws TargetException
	 * @roseuid 573C16610395
	 */
	protected void checkStart() throws TargetException {
		if(!this.state.equals(ObjectTargetExecuteConstant.State_Init)){
			throw new TargetException("考核方案[" + name + "]的状态为[" + state + "]，不能启动");
		}
		if (this.targets.size() == 0) {
			throw new TargetException("考核方案[" + this.name + "]没有关联指标");
		}
		if (this.targetLevelCount > 0) {
			for (TargetType type : this.targetTypes) {
				if (type.getLevel() + 1 < this.targetLevelCount) {
					if (type.getChildren().size() == 0) {
						throw new TargetException("指标类型[" + type.getName() + "]没有关联指标类型");
					}
				} else {
					if (type.getTargets().size() == 0) {
						throw new TargetException("指标类型[" + type.getName() + "]没有关联指标");
					}
				}
			}
		}
		if (this.schemeObjects.size() == 0) {
			throw new TargetException("考核方案[" + this.name + "]没有关联被考核单元");
		}
		for (Target target : this.targets) {
			if (!target.isRelationObject()) {
				throw new TargetException("指标[" + target.getName() + "]没有关联被考核单元");
			}
		}
	}

	/**
	 * @roseuid 573C200E01F6
	 */
	protected void doStart() {
		this.state = State_Started;

		ObjectTargetExecuteDomainServiceStub.getBean().startExecutes(this.getId());

		this.save();

	}
	

	public void close() {
		this.state = State_Closed;
		
		ObjectTargetExecuteDomainServiceStub.getBean().closeExecutes(this.getId());

		this.save();
		
	}

	/**
	 * @roseuid 573C224D015D
	 */
	public void save() {
		this.getRepo("schemeRepo").save(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getTargetLevelCount() {
		return targetLevelCount;
	}

	public void setTargetLevelCount(int targetLevelCount) {
		this.targetLevelCount = targetLevelCount;
	}

	public void setTargetTypes(List<TargetType> targetTypes) {
		this.targetTypes = targetTypes;
	}

	public void setTargets(List<Target> targets) {
		this.targets = targets;
	}

	public List<Target> getTargets() {
		return targets;
	}

	public List<TargetType> getTargetTypes() {
		return targetTypes;
	}

	public List<SchemeObjectGroup> getSchemeObjectGroup() {
		return schemeObjectGroup;
	}

	public void setSchemeObjectGroup(List<SchemeObjectGroup> schemeObjectGroup) {
		this.schemeObjectGroup = schemeObjectGroup;
	}

	public List<SchemeObject> getSchemeObjects() {
		return schemeObjects;
	}

	public void setSchemeObjects(List<SchemeObject> schemeObjects) {
		this.schemeObjects = schemeObjects;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateOrg() {
		return createOrg;
	}

	public void setCreateOrg(String createOrg) {
		this.createOrg = createOrg;
	}

}
