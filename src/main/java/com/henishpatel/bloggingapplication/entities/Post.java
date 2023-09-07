package com.henishpatel.bloggingapplication.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "post")
@NoArgsConstructor
@Getter
@Setter
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;

	@Column(name = "post_title", length = 100, nullable = false)
	private String title;

	@Column(length = 1000000000)
	private String content;

	@Column(nullable = false)
	private String imageName;

	private Date addedDate;

	@ManyToOne
	@JoinColumn(name = "category_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Category category;

	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;

	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Comment> comments=new HashSet<>();

}
