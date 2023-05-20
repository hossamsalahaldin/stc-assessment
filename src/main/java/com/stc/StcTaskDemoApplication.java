package com.stc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

@SpringBootApplication
public class StcTaskDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(StcTaskDemoApplication.class, args);
		try {
			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection("jdbc:postgresql://db:5432/stc-assessment",
					"postgres", "admin");
			ResultSet rs = conn.createStatement().executeQuery("SELECT COUNT(*) FROM \"stc-assessment\".\"Permission_groups\"");
			System.out.println("************ The result is : ");
			while(rs.next()){
				System.out.println(rs.getInt(1));
			}
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
