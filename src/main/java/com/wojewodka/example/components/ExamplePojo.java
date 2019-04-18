package com.wojewodka.example.components;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExamplePojo {

	private String content;
	private LocalDateTime createdAt = LocalDateTime.now();
	private double version;

}
