package br.com.servico.cliente.service;

import br.com.servico.cliente.dto.ClienteDTO;
import br.com.servico.cliente.exceptions.UniqueException;
import br.com.servico.cliente.model.Cliente;

import java.util.List;

public interface ClienteService {

    public Cliente saveCliente(ClienteDTO cliente);

    public void confirmaCadastro(String email) throws UniqueException;

    public Cliente updateCliente(Cliente cliente);

    public void removerCliente(String email) throws UniqueException;

    public List<Cliente> findAll ();

    public Cliente findByEmail(String email) throws UniqueException;

    public Boolean isCadastrado(String email, boolean ativo);



}
