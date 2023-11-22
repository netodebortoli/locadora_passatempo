package com.locadora.backendlocadora;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.locadora.backendlocadora.domain.entity.AtorEntity;
import com.locadora.backendlocadora.domain.entity.ClasseEntity;
import com.locadora.backendlocadora.domain.entity.DependenteEntity;
import com.locadora.backendlocadora.domain.entity.DiretorEntity;
import com.locadora.backendlocadora.domain.entity.EnderecoEntity;
import com.locadora.backendlocadora.domain.entity.ItemEntity;
import com.locadora.backendlocadora.domain.entity.SocioEntity;
import com.locadora.backendlocadora.domain.entity.TituloEntity;
import com.locadora.backendlocadora.domain.enums.Categoria;
import com.locadora.backendlocadora.domain.enums.TipoItem;
import com.locadora.backendlocadora.repository.AtorRepository;
import com.locadora.backendlocadora.repository.ClasseRepository;
import com.locadora.backendlocadora.repository.DiretorRepository;
import com.locadora.backendlocadora.repository.ItemRepository;
import com.locadora.backendlocadora.repository.SocioRepository;
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
            ItemRepository itemRepository,
            SocioRepository socioRepository) {

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

            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date dataUtil = formato.parse("23/11/1985");
            java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());

            EnderecoEntity endereco1 = new EnderecoEntity();
            EnderecoEntity endereco2 = new EnderecoEntity();
            endereco1.setLogradouro("Luiz Pancieri");
            endereco1.setNumero("157");
            endereco1.setBairro("Vila Amélia");
            endereco1.setCep("29706290");
            endereco1.setLocalidade("Colatina");
            endereco1.setUf("ES");
            endereco2.setLogradouro("Rua Belarmino Pinto");
            endereco2.setNumero("374");
            endereco2.setBairro("Sapucaia");
            endereco2.setCep("29730000");
            endereco2.setLocalidade("Baixo Guandu");
            endereco2.setUf("ES");

            SocioEntity socio1 = new SocioEntity();
            SocioEntity socio2 = new SocioEntity();
            socio1.setNome("Luis Inácio Lula");
            socio1.setDataNascimento(dataSql);
            socio1.setEndereco(endereco1);
            socio1.setSexo("M");
            socio1.setNumInscricao("00");
            socio1.setCpf("56264994073");
            socio1.setTelefone("27996445522");
            socio2.setNome("Barack Obama");
            socio2.setDataNascimento(dataSql);
            socio2.setEndereco(endereco2);
            socio2.setSexo("M");
            socio2.setNumInscricao("03");
            socio2.setCpf("65448759025");
            socio2.setTelefone("27999223311");

            DependenteEntity dp1 = new DependenteEntity();
            DependenteEntity dp2 = new DependenteEntity();
            dp1.setNome("Dilma");
            dp1.setNumInscricao("01");
            dp1.setSexo("F");
            dp1.setDataNascimento(dataSql);
            dp2.setNome("Bonaro");
            dp2.setNumInscricao("02");
            dp2.setSexo("M");
            dp2.setDataNascimento(dataSql);
            socio1.getDependentes().add(dp1);
            socio1.getDependentes().add(dp2);
            dp1.setSocio(socio1);
            dp2.setSocio(socio1);
            socioRepository.save(socio1);
            socioRepository.save(socio2);

        };
    }

}
