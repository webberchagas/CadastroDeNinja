package dev.java10x.CadastroDeNinja.Ninjas;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    @GetMapping("/boasvindas")
    public String boasVindas(){
        return "Boas vindas a nossa aplicação SpringBoot";
    }

    // Adicionar ninja (CREATE)
    @PostMapping("/criar")
    public String criaNinja(){
        return "Ninja Criado";
    }

    // Mostra todos os ninjas (READ)
    @GetMapping("/listar")
    public String MostrarTodosNinjas(){
        return "Mostrando todos os ninjas";
    }

    // Mostrar ninja por id (READ)
    @PostMapping("/listarid")
    public String mostrarNinjaId(){
        return "mostrar ninja por ID";
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
