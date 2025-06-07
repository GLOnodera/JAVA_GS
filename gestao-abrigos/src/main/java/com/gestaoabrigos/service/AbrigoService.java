package com.gestaoabrigos.service;

import com.gestaoabrigos.dto.AbrigoDTO;
import com.gestaoabrigos.exception.AbrigoNotFoundException;
import com.gestaoabrigos.model.Abrigo;
import com.gestaoabrigos.repository.AbrigoRepository;
import com.gestaoabrigos.repository.DistribuicaoRecursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AbrigoService {

    private final AbrigoRepository abrigoRepository;
    private final DistribuicaoRecursoRepository distribuicaoRepository;

    @Transactional(readOnly = true)
    public List<Abrigo> listarTodos() {
        return abrigoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Abrigo buscarPorId(Long id) {
        return abrigoRepository.findById(id)
                .orElseThrow();
    }

    @Transactional
    public Abrigo criarAbrigo(AbrigoDTO abrigoDTO) {
        Abrigo abrigo = new Abrigo();
        abrigo.setNome(abrigoDTO.getNome());
        abrigo.setCapacidade(abrigoDTO.getCapacidade());
        abrigo.setLocalizacao(abrigoDTO.getLocalizacao());
        abrigo.setLatitude(abrigoDTO.getLatitude());
        abrigo.setLongitude(abrigoDTO.getLongitude());
        return abrigoRepository.save(abrigo);
    }

    @Transactional
    public Abrigo atualizarAbrigo(Long id, AbrigoDTO abrigoDTO) {
        Abrigo abrigo = buscarPorId(id);
        abrigo.setNome(abrigoDTO.getNome());
        abrigo.setCapacidade(abrigoDTO.getCapacidade());
        abrigo.setLocalizacao(abrigoDTO.getLocalizacao());
        abrigo.setLatitude(abrigoDTO.getLatitude());
        abrigo.setLongitude(abrigoDTO.getLongitude());
        return abrigoRepository.save(abrigo);
    }

    @Transactional
    public void excluirAbrigo(Long id) {
        Abrigo abrigo = buscarPorId(id);
        distribuicaoRepository.deleteByAbrigoId(abrigo.getId());
        abrigoRepository.delete(abrigo);
    }

    @Transactional(readOnly = true)
    public List<Abrigo> buscarPorLocalizacao(String localizacao) {
        return abrigoRepository.findByLocalizacaoContainingIgnoreCase(localizacao);
    }

    @Transactional(readOnly = true)
    public Integer calcularCapacidadeDisponivel(Long abrigoId) {
        Abrigo abrigo = buscarPorId(abrigoId);
        Integer totalDistribuido = distribuicaoRepository.sumQuantidadeByAbrigoId(abrigoId);
        return abrigo.getCapacidade() - (totalDistribuido != null ? totalDistribuido : 0);
    }

    public Abrigo salvar(Abrigo abrigo) {
        return abrigo;
    }

    public List<Abrigo> listarComCapacidadeDisponivel() {
        return null;
    }
}