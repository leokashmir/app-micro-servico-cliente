package br.com.servico.cliente.controller;


import br.com.servico.cliente.dto.ClienteDTO;
import br.com.servico.cliente.exceptions.UniqueException;
import br.com.servico.cliente.exceptions.ValidaCamposException;
import br.com.servico.cliente.model.Cliente;
import br.com.servico.cliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping
public class ClienteController {


    @Autowired
    private ClienteService clienteService;


    @PutMapping("/cliente/add")
    @ResponseBody
    public ResponseEntity<Cliente> addCliente(@RequestBody ClienteDTO cliente) throws IllegalArgumentException, ValidaCamposException {
        Cliente userOk = clienteService.saveCliente(cliente);
        return ResponseEntity.status(HttpStatus.OK).body(userOk);
    }

    @PostMapping("/cliente/update")
    @ResponseBody
    public ResponseEntity<Cliente> updateCliente(@RequestBody Cliente cliente) throws IllegalArgumentException, ValidaCamposException {
        Cliente userOk = clienteService.updateCliente(cliente);
        return ResponseEntity.status(HttpStatus.OK).body(userOk);
    }


    @DeleteMapping("/cliente/remove/{email}")
    public ResponseEntity<String> removeCliente(@PathVariable String email) throws UniqueException {
         clienteService.removerCliente(email);
         return  ResponseEntity.ok("Cliente Excluido com Sucesso!");
    }

    @GetMapping("/cliente/search/{email}")
    public ResponseEntity<Cliente> searchCliente(@PathVariable String email) throws IllegalArgumentException, UniqueException {
        Cliente userOk = clienteService.findByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(userOk);
    }

    @GetMapping("/cliente/ativar/{email}")
    public ResponseEntity<String> ativarCliente(@PathVariable String email) throws IllegalArgumentException, UniqueException {
        clienteService.confirmaCadastro(email);
        return  ResponseEntity.ok("Ativação Feita com sucesso!");
    }
    
    @RequestMapping(value="/", method= RequestMethod.GET)
	 public RedirectView conusltaAPI() {
	    return new RedirectView("https://app-micro-servico-cliente.herokuapp.com/swagger-ui.html");
	}

}
