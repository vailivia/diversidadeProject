package br.com.diversidade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class DiversidadeApplication {

	public static void main(String[] args) {

		try {
			Connection conexao = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl", "rm558929","030306");
			System.out.println("Conexão realizada!");
		} catch (SQLException e) {
			System.err.println(e.getMessage());

		}
	}
}

