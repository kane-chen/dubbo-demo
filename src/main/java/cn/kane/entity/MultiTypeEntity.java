package cn.kane.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class MultiTypeEntity implements Serializable{

	/**
	 * serialize-UID
	 */
	private static final long serialVersionUID = 1674869528361135793L;

	private Long id ;
	
	private String name ;
	
	private Date birth ;
	
	private List<String> exps ;
	
	private Boolean bool ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public List<String> getExps() {
		return exps;
	}

	public void setExps(List<String> exps) {
		this.exps = exps;
	}

	public Boolean getBool() {
		return bool;
	}

	public void setBool(Boolean bool) {
		this.bool = bool;
	}
	
	
}
