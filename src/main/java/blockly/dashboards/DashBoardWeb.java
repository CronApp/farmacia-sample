package blockly.dashboards;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;


@CronapiMetaData(type = "blockly")
@CronappSecurity
public class DashBoardWeb {

public static final int TIMEOUT = 300;

/**
 *
 * @return Var
 */
// dashBoardWeb
public static Var vendas() throws Exception {
 return new Callable<Var>() {

   private Var produtosVendidos = Var.VAR_NULL;
   private Var faturamentoDia = Var.VAR_NULL;

   public Var call() throws Exception {
    produtosVendidos = cronapi.database.Operations.query(Var.valueOf("app.entity.Pedido"),Var.valueOf("select p.produto.nome, SUM(p.quantidade) from Pedido p  group by p.produto.nome"));
    faturamentoDia = cronapi.database.Operations.query(Var.valueOf("app.entity.Venda"),Var.valueOf("select v.data, SUM(v.valor_venda) from Venda v  group by v.data"));
    cronapi.chart.Operations.createChart(Var.valueOf("grafico1"), Var.valueOf("bar"), cronapi.database.Operations.getColumn(produtosVendidos, Var.valueOf("this[0]")), Var.VAR_NULL, cronapi.database.Operations.getColumn(produtosVendidos, Var.valueOf("this[1]")));
    cronapi.chart.Operations.createChart(Var.valueOf("grafico2"), Var.valueOf("line"), cronapi.database.Operations.getColumn(faturamentoDia, Var.valueOf("this[0]")), Var.VAR_NULL, cronapi.database.Operations.getColumn(faturamentoDia, Var.valueOf("this[1]")));
    return Var.VAR_NULL;
   }
 }.call();
}

}

