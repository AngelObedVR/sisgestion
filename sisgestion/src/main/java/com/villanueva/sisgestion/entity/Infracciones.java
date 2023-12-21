package com.villanueva.sisgestion.entity;

import java.util.Date;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "infracciones")
@EntityListeners(AuditingEntityListener.class)
public class Infracciones {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(unique = true, nullable = false, length = 8)
	private String dni;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	
	@Column(nullable = false, length = 7)
	private String placa;
	
	@Column(nullable = false, length = 200)
	private String infraccion;
	
	@Column(nullable = true, length = 255)
	private String descripcion;

	//@Column(name = "created_at", nullable = false, updatable = false)
	//@Temporal(TemporalType.TIMESTAMP)
	//private Date createdAt;

	//@Column(name = "updated_at", nullable = false)
	//@Temporal(TemporalType.TIMESTAMP)
	//@LastModifiedDate
	//private Date updatedAt;
}
