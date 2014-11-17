package com.karmelos.kpoll.model;

import java.io.Serializable;




public class InterestArea implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4196208686357159716L;

	private Long id;
	
	private String description;


	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the selected
	 */
	
	
}
