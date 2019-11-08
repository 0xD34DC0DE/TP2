package com.grokonez.jwtauthentication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "admin", catalog = "tp2")
@PrimaryKeyJoinColumn(referencedColumnName = "email")
public class Admin extends Account {

	@Column(name = "admin_level", columnDefinition = "Varchar(10) default 'ACTIF'")
	private String adminLevel;

	public Admin() {
	}

	@Override
	public String toString() {
		return "Admin [adminLevel=" + adminLevel + "]";
	}

	

}
