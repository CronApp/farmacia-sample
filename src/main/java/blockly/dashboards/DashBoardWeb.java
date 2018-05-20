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
	// dashBoard
	public static Var vendas() throws Exception {
		return new Callable<Var>() {

			private Var vendasQuantidade = Var.VAR_NULL;

			public Var call() throws Exception {
				vendasQuantidade = cronapi.database.Operations.query(Var.valueOf("app.entity.Venda"),
						Var.valueOf("select v.produto.nome, SUM(v.quantidade) from Venda v  group by v.produto.nome"));
				cronapi.chart.Operations.createChart(Var.valueOf("grafico1"), Var.valueOf("bar"),
						cronapi.database.Operations.getColumn(vendasQuantidade, Var.valueOf("this[0]")), Var.VAR_NULL,
						cronapi.database.Operations.getColumn(vendasQuantidade, Var.valueOf("this[1]")));
				return Var.VAR_NULL;
			}
		}.call();
	}

}
