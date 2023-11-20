package com.locadora.backendlocadora;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.locadora.backendlocadora.domain.entity.AtorEntity;
import com.locadora.backendlocadora.domain.entity.ClasseEntity;
import com.locadora.backendlocadora.domain.entity.DiretorEntity;
import com.locadora.backendlocadora.domain.entity.ItemEntity;
import com.locadora.backendlocadora.domain.entity.TituloEntity;
import com.locadora.backendlocadora.domain.enums.Categoria;
import com.locadora.backendlocadora.domain.enums.TipoItem;
import com.locadora.backendlocadora.repository.AtorRepository;
import com.locadora.backendlocadora.repository.ClasseRepository;
import com.locadora.backendlocadora.repository.DiretorRepository;
import com.locadora.backendlocadora.repository.ItemRepository;
import com.locadora.backendlocadora.repository.TituloRepository;

@SpringBootApplication
public class BackendLocadoraApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendLocadoraApplication.class, args);
    }

    @Bean
    CommandLineRunner initDataBase(AtorRepository atorRepository,
            DiretorRepository diretorRepository,
            ClasseRepository classeRepository,
            TituloRepository tituloRepository,
            ItemRepository itemRepository) {
        return args -> {
            atorRepository.deleteAll();
            diretorRepository.deleteAll();
            classeRepository.deleteAll();
            tituloRepository.deleteAll();
            itemRepository.deleteAll();

            AtorEntity ator1 = new AtorEntity();
            ator1.setNome("Christoph Waltz");
            AtorEntity ator2 = new AtorEntity();
            ator2.setNome("Brad Pitt");
            atorRepository.save(ator1);
            atorRepository.save(ator2);

            List<AtorEntity> atores = new ArrayList<>();
            for (int i = 0; i < 19; i++) {
                AtorEntity ator = new AtorEntity();
                ator.setNome("Ator " + i);
                atores.add(ator);
            }
            atorRepository.saveAll(atores);

            DiretorEntity diretor1 = new DiretorEntity();
            diretor1.setNome("Quentin Tarantino");
            diretorRepository.save(diretor1);

            ClasseEntity classe1 = new ClasseEntity();
            classe1.setNome("Elite");
            classe1.setValor(5.00);
            classe1.setPrazoDevolucao(3);
            classeRepository.save(classe1);

            TituloEntity titulo1 = new TituloEntity();
            titulo1.setNome("Bastardos Inglórios");
            titulo1.setAno("2009");
            titulo1.setClasse(classe1);
            titulo1.setSinopse("Durante a Segunda Guerra Mundial, na França, judeus americanos espalham o terror" +
                    " entre o terceiro Reich. Ao mesmo tempo, Shosanna, uma judia que fugiu dos nazistas, planeja vingança"
                    +
                    " quando um evento em seu cinema reunirá os líderes do partido.");
            titulo1.setCategoria(Categoria.ACAO);
            titulo1.setDiretor(diretor1);
            titulo1.getAtores().add(ator1);
            titulo1.getAtores().add(ator2);
            tituloRepository.save(titulo1);

            ItemEntity item = new ItemEntity();
            item.setDataAquisicao(Date.valueOf(LocalDate.now()));
            item.setNumSerie("11223345");
            item.setTipoItem(TipoItem.DVD);
            item.setTitulo(titulo1);
            itemRepository.save(item);

            ItemEntity item2 = new ItemEntity();
            item2.setDataAquisicao(Date.valueOf(LocalDate.now()));
            item2.setNumSerie("00559841");
            item2.setTipoItem(TipoItem.BLU_RAY);
            item2.setTitulo(titulo1);
            itemRepository.save(item2);
        };
    }

}
