package com.iu.b1.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "members")
public class MemberVO {
	@Id
	@NotEmpty(message = "ID를 입력해주세요")
	private String id;
	@Column(name = "pw")
	private String pw;
	@Transient
	private String pw2;
	private String name;
	private String email;
}
