package app.dao;

import app.entity.*;
import java.util.*;
import org.springframework.stereotype.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.*;
import org.springframework.transaction.annotation.*; 


/**
 * Realiza operação de Create, Read, Update e Delete no banco de dados.
 * Os métodos de create, edit, delete e outros estão abstraídos no JpaRepository
 * 
 * @see org.springframework.data.jpa.repository.JpaRepository
 * 
 * @generated
 */
@Repository("ClienteVendaDAO")
@Transactional(transactionManager="app-TransactionManager")
public interface ClienteVendaDAO extends JpaRepository<ClienteVenda, java.lang.String> {

  /**
   * Obtém a instância de ClienteVenda utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Instância relacionada com o filtro indicado
   * @generated
   */    
  @Query("SELECT entity FROM ClienteVenda entity WHERE entity.id = :id")
  public ClienteVenda findOne(@Param(value="id") java.lang.String id);

  /**
   * Remove a instância de ClienteVenda utilizando os identificadores
   * 
   * @param id
   *          Identificador 
   * @return Quantidade de modificações efetuadas
   * @generated
   */    
  @Modifying
  @Query("DELETE FROM ClienteVenda entity WHERE entity.id = :id")
  public void delete(@Param(value="id") java.lang.String id);



  /**
   * Foreign Key cliente
   * @generated
   */
  @Query("SELECT entity FROM ClienteVenda entity WHERE entity.cliente.id = :id")
  public Page<ClienteVenda> findClienteVendasByCliente(@Param(value="id") java.lang.String id, Pageable pageable);

  /**
   * Foreign Key venda
   * @generated
   */
  @Query("SELECT entity FROM ClienteVenda entity WHERE entity.venda.id = :id")
  public Page<ClienteVenda> findClienteVendasByVenda(@Param(value="id") java.lang.String id, Pageable pageable);

}
