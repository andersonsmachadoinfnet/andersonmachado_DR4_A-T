package br.anderson.infnet.dr4at;

import br.anderson.infnet.dr4at.model.domain.Veiculo;
import br.anderson.infnet.dr4at.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class VeiculoService {
    @Autowired
    private VeiculoRepository _rps;

    public VeiculoService() {}

    public Veiculo incluir(Veiculo v) {
        v.setId(null);
        return _rps.save(v);
    }

    public Optional<Veiculo> ler(Integer key) {
        return _rps.findById(key);
    }

    public void excluir(Integer key) {
        _rps.deleteById(key);
    }

    public Collection<Veiculo> obterLista() {
        return (Collection<Veiculo>) _rps.findAll();
    }

    public Veiculo salvar(Veiculo v) {
        return _rps.save(v);
    }
}
