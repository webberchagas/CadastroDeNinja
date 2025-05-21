package dev.java10x.CadastroDeNinja.Ninjas;

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
    public String boasVindas(){
        return "Boas vindas a nossa aplicação SpringBoot";
    }

    // Adicionar ninja (CREATE)
    @PostMapping("/criar")
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
    public ResponseEntity<?> alterarNinjaId(@PathVariable Long id,@RequestBody NinjaDTO ninjaDTO){
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
