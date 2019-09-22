package com.ocp.io;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String email;
	@Builder.Default private LocalDate dateCreated = LocalDate.now();
	
}
