package com.integrate.todo;

import com.integrate.todo.db.DBUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

@SpringBootApplication
public class TodoApplication implements CommandLineRunner {

	@Autowired
	private DBUtils dbUtils;

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		this.dbUtils.initialize();
	}
}
