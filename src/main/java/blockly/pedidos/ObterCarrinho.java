package blockly.pedidos;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.Iterator;
import java.util.concurrent.Callable;


@CronapiMetaData(type = "blockly")
@CronappSecurity
public class ObterCarrinho {

public static final int TIMEOUT = 300;

/**
 */
// Descreva esta função...
public static void inserir() throws Exception {
  new Callable<Var>() {

   private Var idVenda = Var.VAR_NULL;
   private Var carrinho = Var.VAR_NULL;
   private Var j = Var.VAR_NULL;

   public Var call() throws Exception {
    idVenda = cronapi.util.Operations.generateUUID();
    carrinho = cronapi.json.Operations.toJson(cronapi.screen.Operations.getValueOfField(Var.valueOf("vars.Lista")));
    cronapi.database.Operations.insert(Var.valueOf("app.entity.Venda"),Var.valueOf("valor_venda",cronapi.screen.Operations.getValueOfField(Var.valueOf("vars.totalVenda"))),Var.valueOf("data",cronapi.dateTime.Operations.getNow()),Var.valueOf("id",idVenda));
    for (Iterator it_j = carrinho.iterator(); it_j.hasNext();) {
        j = Var.valueOf(it_j.next());
        cronapi.database.Operations.insert(Var.valueOf("app.entity.Pedido"),Var.valueOf("venda",idVenda),Var.valueOf("produto",cronapi.object.Operations.getObjectField(j, Var.valueOf("produto.id"))),Var.valueOf("quantidade",cronapi.object.Operations.getObjectField(j, Var.valueOf("quantidade"))));
    } // end for
    cronapi.util.Operations.callClientFunction(Var.valueOf("cronapi.screen.changeView"), Var.valueOf("#/home/logged/pedido"));
   return Var.VAR_NULL;
   }
 }.call();
}

}

