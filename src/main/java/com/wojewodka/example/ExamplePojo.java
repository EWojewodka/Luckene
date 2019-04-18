package com.wojewodka.example;

import com.wojewodka.luckene.annotation.LuckeneField;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExamplePojo {
	
	@LuckeneField
	private String content;
	private LocalDateTime createdAt = LocalDateTime.now();
	@LuckeneField
	private double version;
	
}
