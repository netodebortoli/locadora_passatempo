package com.locadora.backendlocadora;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.locadora.backendlocadora.domain.Ator;
import com.locadora.backendlocadora.domain.Classe;
import com.locadora.backendlocadora.domain.Diretor;
import com.locadora.backendlocadora.domain.Titulo;
import com.locadora.backendlocadora.domain.enums.Categoria;
import com.locadora.backendlocadora.repositories.AtorRepository;
import com.locadora.backendlocadora.repositories.ClasseRepository;
import com.locadora.backendlocadora.repositories.DiretorRepository;
import com.locadora.backendlocadora.repositories.TituloRepository;

@SpringBootApplication
public class BackendLocadoraApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendLocadoraApplication.class, args);
    }

    @Bean
    CommandLineRunner initDataBase(AtorRepository atorRepository,
                                   DiretorRepository diretorRepository,
                                   ClasseRepository classeRepository,
                                   TituloRepository tituloRepository) {
        return args -> {
            atorRepository.deleteAll();
            diretorRepository.deleteAll();
            classeRepository.deleteAll();
            tituloRepository.deleteAll();
            Ator ator1 = new Ator();
            ator1.setNome("Christoph Waltz");
            Ator ator2 = new Ator();
            ator2.setNome("Brad Pitt");
            atorRepository.save(ator1);
            atorRepository.save(ator2);

            Diretor diretor1 = new Diretor();
            diretor1.setNome("Quentin Tarantino");
            diretorRepository.save(diretor1);

            Classe classe1 = new Classe();
            classe1.setNome("Elite");
            classe1.setValor(5.00);
            classe1.setPrazoDevolucao(3);
            classeRepository.save(classe1);

            Titulo titulo1 = new Titulo();
            titulo1.setNome("Bastardos Inglórios");
            titulo1.setAno("2009");
            titulo1.setClasse(classe1);
            titulo1.setSinopse("Durante a Segunda Guerra Mundial, na França, judeus americanos espalham o terror" +
                    " entre o terceiro Reich. Ao mesmo tempo, Shosanna, uma judia que fugiu dos nazistas, planeja vingança" +
                    " quando um evento em seu cinema reunirá os líderes do partido.");
            titulo1.setCategoria(Categoria.ACAO);
            titulo1.setDiretor(diretor1);
            titulo1.getAtores().add(ator1);
            titulo1.getAtores().add(ator2);
            tituloRepository.save(titulo1);
        };
    }

}
