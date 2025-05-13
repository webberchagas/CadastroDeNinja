package dev.java10x.CadastroDeNinja.Missoes;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    @GetMapping("/listar")
    public String listarMissao(){
        return "Missoes listadas com sucesso";
    }

    @PostMapping("/criar")
    public String criarMissao(){
        return "Missão Criada com sucesso";
    }

    @PutMapping("/altera")
    public String alterarMissao(){
        return "Missão alterada com sucesso";
    }

    public String deletarMissao(){
        return "Missão deletada";
    }
}
