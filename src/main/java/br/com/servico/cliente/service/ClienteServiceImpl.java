package br.com.servico.cliente.service;

import br.com.servico.cliente.dto.ClienteDTO;
import br.com.servico.cliente.exceptions.UniqueException;
import br.com.servico.cliente.model.Cliente;
import br.com.servico.cliente.repository.ClienteRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository repo;

    private static final Logger log = LogManager.getLogger(ClienteServiceImpl.class);

    /**
     * @param clienteDto
     * @return Cliente
     */
    public Cliente saveCliente(ClienteDTO clienteDto) {
        log.info("$$$$$$ Start Save Cliente");
        ModelMapper modelMapper = new ModelMapper();
        Cliente cliente = modelMapper.map(clienteDto, Cliente.class);
        cliente.setAtivo(false);
        return repo.save(cliente);
    }

    /**
     * @param email
     */
    public void confirmaCadastro(String email) throws UniqueException {
        log.info("$$$$$$ Start Confirma Cadastro");
        Cliente cliente = repo.findByEmail(email);

        if( this.isCadastrado(email,false)){
            log.info("$$$$$$  Cliente Ja Ativado");
            throw new UniqueException("Cliente Já Ativo","Cliente Ativo", HttpStatus.OK);
        }else{
            cliente.setAtivo(true);
            repo.save(cliente);
        }
      }

    /**
     * @param cliente
     * @return
     */
    public Cliente updateCliente(Cliente cliente) {
        return  repo.save(cliente);
    }


    /**
     * Excluli um Clinete da base de dados
     * @param email
     * @retunr void
     */
    public void removerCliente(String email) throws UniqueException {
        log.info("$$$$$$ Remove Save Cliente");
        if( this.isCadastrado(email,false)){
            log.error("$$$$$$ Erro ao Remover Cliente "+ email);
            throw new UniqueException("Cliente não pode ser alterado","Cliente Ativo", HttpStatus.SEE_OTHER);
         }else{
            repo.deleteByEmail(email);
        }
     }

    /**
     * Verifica se o email ja esta cadastrado na base.
     * @param email
     * @return Boolean
     */
    private Boolean verificaEmailCadastrado(String email) {
        return ( repo.findByEmail(email) !=null ) ? true : false;
    }

    /**
     * Lista todos os clientes da base.
     * @return List<Cliente>
     */
    public List<Cliente> findAll() {
        return repo.findAll();
    }

    /**
     * Pesquisa por Email do cliente
     * @param email
     * @return
     */
    public Cliente findByEmail(String email) throws UniqueException {
        log.info("$$$$$$ Start Buscar Cliente por Email");

          if( this.isCadastrado(email,false)){
              log.error("$$$$$$ Cliente não pode ser alterado.");
               throw new UniqueException("Cliente não pode ser alterado","Cliente Ativo", HttpStatus.SEE_OTHER);
           }else{
               return repo.findByEmail(email);
           }
    }

    /**
     * Metodo que verifica se o Cliente ja esta com cadastro ativo
     * @param email
     * @param ativo
     * @return
     */
    public Boolean isCadastrado(String email, boolean ativo) {
        return repo.findByEmailAndAtivo(email,ativo).getAtivo();
    }
}
