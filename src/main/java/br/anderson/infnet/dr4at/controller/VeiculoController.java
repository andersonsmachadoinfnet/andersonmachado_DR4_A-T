package br.anderson.infnet.dr4at.controller;

import br.anderson.infnet.dr4at.VeiculoService;
import br.anderson.infnet.dr4at.model.domain.Veiculo;
import br.anderson.infnet.dr4at.repository.VeiculoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/")
public class VeiculoController {
    @Autowired
    private VeiculoService _sc;

    @GetMapping(value = "")
    @Operation(summary = "Lista todos os veiculos cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de veiculos",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Veiculo.class))}
            )
    })
    public Iterable<Veiculo> getList(){
        return _sc.obterLista();
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Retorna o veiculo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veiculo",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Veiculo.class))}
            )
    })
    public Optional<Veiculo> getVeiculo(@PathVariable Integer id) {
        return _sc.ler(id);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deleta o cadastro de um veículo.")
    public boolean deletarVeiculo(@PathVariable Integer id) {
        _sc.excluir(id);
        return true;
    }

    @PutMapping("")
    @Operation(summary = "Altera o cadastro de um veiculo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "veiculo",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Veiculo.class))}
            )
    })
    ResponseEntity<Optional<Veiculo>> setVeiculo(@RequestBody Veiculo veiculo) {
        Optional<Veiculo> lTmp;
        lTmp = _sc.ler(veiculo.getId());
        if (lTmp.isEmpty()) {
            return new ResponseEntity<Optional<Veiculo>>(lTmp, HttpStatus.NOT_FOUND);
        }
        else {
            lTmp = Optional.ofNullable(_sc.salvar(veiculo));
            return new ResponseEntity<Optional<Veiculo>>(lTmp, HttpStatus.OK);
        }
    }

    @PostMapping("")
    @Operation(summary = "Cadastra um novo veiculo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veiculo",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Veiculo.class))}
            )
    })
    Veiculo novo(@RequestBody Veiculo item) {
        _sc.incluir(item);
        return item;
    }

}
