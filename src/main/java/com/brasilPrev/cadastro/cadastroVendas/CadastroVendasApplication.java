package com.brasilPrev.cadastro.cadastroVendas;

import javax.activation.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@SpringBootApplication
public class CadastroVendasApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadastroVendasApplication.class, args);
	}

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUrl("jdbc:sqlserver://brasilprevcadastro.database.windows.net");
        dataSource.setUsername("c.meirelles.junior");
        dataSource.setPassword("Abacate@01");
        return (DataSource) dataSource;
    }
}
