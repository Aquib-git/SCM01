package com.kng.brtrprts.brtmodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/*import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;*/

@Entity
@Data

@Table(name="DEPARTMENT_SETUP",catalog="ECLINIC_KNG")
public class DepartmentFilterSetup {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="DEPARTMENT_ID")
	private int departmentId;
	
	@Column(name="DEPARTMENT_NAME")
	private String departmentName;
	
	@Column(name="DEPTNAME_AR")
	private String departmentNameAR;
	
	@Column(name="DIFF_REGNO")
	private String diffRegno;
	
	@Column(name="DEPT_PREFIX")
	private String deptPrefix;
	
	@Column(name="ACTIVE_STATUS")
	private String activStatus;
	
	@Column(name="GROUP_ID")
	private String groupId;
	
	@Column(name="DEPT_TYPE")
	private String deptType;
	
	@Column(name="DEPT_CODE")
	private String deptCode;

	public int getDepartmentId() {
		return departmentId;
	}

	/*
	 * public void setDepartmentId(int departmentId) { this.departmentId =
	 * departmentId; }
	 * 
	 * public String getDepartmentName() { return departmentName; }
	 * 
	 * public void setDepartmentName(String departmentName) { this.departmentName =
	 * departmentName; }
	 * 
	 * public String getDepartmentNameAR() { return departmentNameAR; }
	 * 
	 * public void setDepartmentNameAR(String departmentNameAR) {
	 * this.departmentNameAR = departmentNameAR; }
	 * 
	 * public String getDiffRegno() { return diffRegno; }
	 * 
	 * public void setDiffRegno(String diffRegno) { this.diffRegno = diffRegno; }
	 * 
	 * public String getDeptPrefix() { return deptPrefix; }
	 * 
	 * public void setDeptPrefix(String deptPrefix) { this.deptPrefix = deptPrefix;
	 * }
	 * 
	 * public String getActivStatus() { return activStatus; }
	 * 
	 * public void setActivStatus(String activStatus) { this.activStatus =
	 * activStatus; }
	 * 
	 * public String getGroupId() { return groupId; }
	 * 
	 * public void setGroupId(String groupId) { this.groupId = groupId; }
	 * 
	 * public String getDeptType() { return deptType; }
	 * 
	 * public void setDeptType(String deptType) { this.deptType = deptType; }
	 * 
	 * public String getDeptCode() { return deptCode; }
	 * 
	 * public void setDeptCode(String deptCode) { this.deptCode = deptCode; }
	 */
	
	
	
	
}
