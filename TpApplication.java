package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TpApplication {

	public static void main(String[] args) {
		Match.getNumberOfGoals("AS ROMA",2015);
	}

}
