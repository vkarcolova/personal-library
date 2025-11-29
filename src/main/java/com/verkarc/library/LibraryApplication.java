package com.verkarc.library;

import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Log
@SpringBootApplication
public class LibraryApplication implements CommandLineRunner
{

    private static final Logger log = LoggerFactory.getLogger(LibraryApplication.class);
    private final DataSource dataSource;
    public LibraryApplication(final DataSource dataSource) {this.dataSource = dataSource;}
	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}


    @Override
    public void run(String... args) throws Exception {
        log.info("Datasource " + dataSource.toString());
        final JdbcTemplate restTemplate = new JdbcTemplate(dataSource);
        restTemplate.execute("select 1");
    }
}
