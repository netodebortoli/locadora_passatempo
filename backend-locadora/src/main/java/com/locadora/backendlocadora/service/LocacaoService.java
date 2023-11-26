package com.locadora.backendlocadora.service;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locadora.backendlocadora.domain.Locacao;
import com.locadora.backendlocadora.domain.entity.LocacaoEntity;
import com.locadora.backendlocadora.domain.mapper.LocacaoMapper;
import com.locadora.backendlocadora.repository.LocacaoRepository;
import com.locadora.backendlocadora.service.exception.NegocioException;
import com.locadora.backendlocadora.service.exception.RegistroNaoEncontradoException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
public class LocacaoService extends GenericService<Locacao, Long, LocacaoRepository, LocacaoEntity, LocacaoMapper> {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ItemService itemService;

    public LocacaoService(LocacaoRepository repository, LocacaoMapper classeMapper) {
        super(repository, classeMapper);
        this.setHumanReadableName("Locação");
    }

    @Override
    public Locacao salvar(@Valid @NotNull Locacao model) throws RegistroNaoEncontradoException, NegocioException {

        validarSave(model);

        return mapper.toModel(this.repository.saveAndFlush(mapper.toEntity(model)));
    }

    @Override
    public void validarSave(@NotNull @Valid Locacao model) throws NegocioException {

        itemService.buscarPorId(model.item().id());

        clienteService.buscarPorId(model.cliente().getId());

        if (model.id() == null && this.repository.isClienteEmDebito(model.cliente().getId())) {
            throw new NegocioException("O cliente está em débito e não é possível realizar a locação.");
        }

        if (model.dataDevolucaoEfetiva() != null) {
            if (model.dataDevolucaoEfetiva().compareTo(model.dataLocacao()) < 0) {
                throw new NegocioException("A data de devolução efetiva precisa ser maior ou igual a data de locação.");
            }
            if (model.dataDevolucaoPrevista().before(model.dataLocacao())) {
                throw new NegocioException("A data de devoulução prevista precisa ser posterior a data de locação.");
            }
            if (model.dataDevolucaoEfetiva().after(model.dataDevolucaoPrevista()) && model.multaCobrada() == null) {
                throw new NegocioException("O valor da multa é obrigatório pois o cliente atrasou a devolução.");
            }
        }

        Locacao locacoesFromDB = this.mapper.toModel(
                this.repository.isItemDisponivel(model.dataLocacao(), model.item().id()));

        if (locacoesFromDB != null && model.id() != locacoesFromDB.id()) {

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String dtDevolucaoPrevistaFormatada = sdf.format(locacoesFromDB.dataDevolucaoPrevista());

            throw new NegocioException(String.format(
                    "O item %s não está disponível na data de locação informada. Provavelmente o item estará disponível na data %s",
                    locacoesFromDB.item().id(), dtDevolucaoPrevistaFormatada));
        }
    }

    @Override
    public void deletar(@Valid @NotNull Long id) throws RegistroNaoEncontradoException, NegocioException {

        Locacao locacaoFromDB = this.buscarPorId(id);

        if (locacaoFromDB.dataDevolucaoEfetiva() != null) {
            throw new NegocioException("Não é possível cancelar uma locação concluída.");
        }

        super.deletar(id);

    }
}
