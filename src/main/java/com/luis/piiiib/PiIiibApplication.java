package com.luis.piiiib;

import com.luis.piiiib.entities.Turma;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PiIiibApplication {

    public static void main(String[] args) {
        SpringApplication.run(PiIiibApplication.class, args);
        new Turma("Turma 1A");
        new Turma("Turma 1B");
        new Turma("Turma 2A");
        new Turma("Turma 2B");
    }
}
