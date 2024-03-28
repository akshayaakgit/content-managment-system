package com.example.cms.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "blog")
@Getter
@Setter
public class Blog {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long blogId;
	//@NotBlank
	//@NotNull
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Title should contain only alphabets")
	private String title;
	//@NotEmpty(message = "At least one topic must be specified")
	private String[] topics;
	private String about;
	@ManyToMany()
	private List<User> users = new ArrayList<>();
}
