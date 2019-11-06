package com.fabeme.tp2backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "admin", catalog = "tp2")
@PrimaryKeyJoinColumn(referencedColumnName = "email")
public class Admin extends Account {

	//Admin level is used for what ? I don't see the need for that
	@Column(name = "admin_level", columnDefinition = "Varchar(10) default 'ACTIVE'")
	private String adminLevel;

	public Admin() {
		type =  AccountTypes.ADMIN.toString();
	}

	@Override
	public String toString() {
		return "Admin [adminLevel=" + adminLevel + "]";
	}

}
