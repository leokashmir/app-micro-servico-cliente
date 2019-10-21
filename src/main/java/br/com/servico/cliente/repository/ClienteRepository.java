package br.com.servico.cliente.repository;

import br.com.servico.cliente.model.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ClienteRepository extends MongoRepository<Cliente, String> {
      public  Cliente findByEmail(String email);

      @Query(value= "{ 'email': ?0, 'ativo': ?1}", fields="{ 'ativo' : 1}")
      public Cliente findByEmailAndAtivo(String email, boolean ativo);

      public String deleteByEmail(String email);
}
