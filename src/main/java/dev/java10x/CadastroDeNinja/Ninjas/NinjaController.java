package dev.java10x.CadastroDeNinja.Ninjas;

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
    public NinjaModel criaNinja(@RequestBody NinjaModel ninja){
        return ninjaService.criarNinja(ninja);
    }

    // Mostra todos os ninjas (READ)
    @GetMapping("/listar")
    public List<NinjaModel> MostrarTodosNinjas(){
        return ninjaService.listarNinjas();
    }

    // Mostrar ninja por id (READ)
    @GetMapping("/listar/{id}")
    public NinjaModel mostrarNinjasPorId(@PathVariable Long id){
        return ninjaService.listarNinjaPorId(id);
    }

    // Alterar dados dos ninjas (UPDATE)
    @PutMapping("/alterarid")
    public String alterarNinjaId(){
        return "Alterar Ninja por id";
    }

    // Remover os ninjas (Delete);
    @DeleteMapping("/deletarid")
    public String removerNinjaId(){
        return "Removendo Ninja por id";
    }
}
