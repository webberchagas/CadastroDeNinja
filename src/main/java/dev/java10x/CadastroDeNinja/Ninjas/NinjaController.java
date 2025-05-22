package dev.java10x.CadastroDeNinja.Ninjas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService){
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasvindas")
    @Operation(summary = "Mensagem de boas vindas", description = "Essa rota da uma mensagem de boas vindas, para quem acessa ela.")
    public String boasVindas(){
        return "Boas vindas a nossa aplicação SpringBoot";
    }

    // Adicionar ninja (CREATE)
    @PostMapping("/criar")
    @Operation(summary = "Cria um novo ninja", description = "Rota responsavel por criar um novo ninja no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro a criação do ninja")
    })
    public ResponseEntity<String> criaNinja(@RequestBody NinjaDTO ninja){
        NinjaDTO novoNinja= ninjaService.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja " + novoNinja.getNome() + " criado com sucesso! " + "(ID): " + novoNinja.getId());
    }

    // Mostra todos os ninjas (READ)
    @GetMapping("/listar")
    public ResponseEntity<?> MostrarTodosNinjas(){
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        return ResponseEntity.ok(ninjas);
    }

    // Mostrar ninja por id (READ)
    @GetMapping("/listar/{id}")
    @Operation(summary = "Lista o ninja por Id", description = "Rota lista um ninja pelo Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado")
    })
    public ResponseEntity<?> mostrarNinjasPorId(@PathVariable Long id){
        NinjaDTO ninja = ninjaService.listarNinjaPorId(id);
        if (ninja != null){
            return ResponseEntity.ok(ninja);
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja com o ID " + id + " não existe nos nossos registros");
        }
    }

    // Alterar dados dos ninjas (UPDATE)
    @PutMapping("/alterar/{id}")
    @Operation(summary = "Altera o ninja por Id", description = "Rota altera um ninja pelo Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja alterado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado, não foi possível alterar")
    })
    public ResponseEntity<?> alterarNinjaId(
            @Parameter(description = "Usuario mando o id no caminho da requisição")
            @PathVariable Long id,
            @Parameter(description = "Usuario mada os dados do ninja a ser atualizado no corpo da requisição")
            @RequestBody NinjaDTO ninjaDTO
    ){
        NinjaDTO ninja = ninjaService.atualizarNinja(id,ninjaDTO);

        if (ninja != null){
            return  ResponseEntity.ok(ninja);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja com o ID " + id + " não existe nos nossos registros");
        }
    }

    // Remover os ninjas (Delete);
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarNinjaPorId(@PathVariable Long id){

        if (ninjaService.listarNinjaPorId(id) != null){
            ninjaService.deletarNinjaPorId(id);
            return ResponseEntity.ok("Ninja com ID " + id + " deletado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja com o ID " + id + " não foi encotrado");
        }
    }
}
