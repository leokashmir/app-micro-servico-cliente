package br.com.servico.cliente.service;

import br.com.servico.cliente.exceptions.UniqueException;
import br.com.servico.cliente.model.Cliente;
import br.com.servico.cliente.repository.ClienteRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@RunWith(MockitoJUnitRunner.class)
public class ClienteServiceImplTest {

    @InjectMocks
    private ClienteServiceImpl service;

    @Mock
    private ClienteRepository repo;


    @Test
    public void saveErroCliente() {

        Cliente cli =  this.getCliente();
        doReturn(null).when(repo).save(cli);
        Cliente retorno = repo.save(cli);
        assertNull(retorno);

    }
    @Test
    public void saveCliente() {

        Cliente cli =  this.getCliente();
        doReturn(cli).when(repo).save(cli);
        Cliente retorno = repo.save(cli);
        assertNotNull(retorno);

    }

    @Test
    public void removerCliente() {
        Cliente cli =  this.getCliente();
        doReturn("1").when(repo).deleteByEmail(cli.getEmail());
        String retorno  = repo.deleteByEmail("Teste01@email.com");
        assertEquals("1", retorno);
    }


    @Test
    public void findByEmail() {

        Cliente cli =  this.getCliente();
        doReturn(cli).when(repo).findByEmail(cli.getEmail());
        Cliente retorno = repo.findByEmail("Teste01@email.com");
        assertNotNull(retorno);

    }


    @Test
    public void isCadastradoTrue() {
        Cliente cli =  this.getCliente();
        doReturn(cli).when(repo).findByEmailAndAtivo (cli.getEmail(), true );
        Cliente retorno = repo.findByEmailAndAtivo("Teste01@email.com", true);
        assertNotNull(retorno);

    }

    @Test
    public void isCadastradoFalse() {
        Cliente cli =  this.getCliente();
        doReturn(null).when(repo).findByEmailAndAtivo (cli.getEmail(), false );
        Cliente retorno = repo.findByEmailAndAtivo("Teste01@email.com", false);
        assertNull(retorno);

    }

    private Cliente getCliente(){
        Cliente cli = new Cliente();

        cli.setNome("Teste01");
        cli.setEmail("Teste01@email.com");
        cli.setRua("Rua oito");
        cli.setNumero("45");
        cli.setBairro("Centro");
        cli.setCep("34567890");
        cli.setCidade("SP");
        cli.setEstado("SP");
        cli.setTelefone("1198762345");
       return cli;
    }
}