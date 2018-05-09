package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;

@CronapiMetaData(type = "blockly")
@CronappSecurity
public class DashBoard {

	public static final int TIMEOUT = 300;

	/**
	 *
	 * @return Var
	 */
	// dashBoard
	public static Var vendas() throws Exception {
		return new Callable<Var>() {

			private Var vendasData = Var.VAR_NULL;
			private Var vendasQuantidade = Var.VAR_NULL;
			private Var vendasFaturamento = Var.VAR_NULL;

			public Var call() throws Exception {
				vendasData = cronapi.database.Operations.query(Var.valueOf("app.entity.Venda"),
						Var.valueOf("select COUNT(v), v.data from Venda v  group by v.data"));
				vendasQuantidade = cronapi.database.Operations.query(Var.valueOf("app.entity.Venda"),
						Var.valueOf("select v.produto.nome, SUM(v.quantidade) from Venda v  group by v.produto.nome"));
				vendasFaturamento = cronapi.database.Operations.query(Var.valueOf("app.entity.Venda"), Var.valueOf(
						"select v.produto.nome, SUM(v.quantidade * v.produto.preco_venda) from Venda v  group by v.produto.nome"));
				cronapi.chart.Operations.createChart(Var.valueOf("graficoMaior"), Var.valueOf("polarArea"),
						cronapi.database.Operations.getColumn(vendasData, Var.valueOf("this[1]")), Var.VAR_NULL,
						cronapi.database.Operations.getColumn(vendasData, Var.valueOf("this[0]")));
				cronapi.chart.Operations.createChart(Var.valueOf("grafico1"), Var.valueOf("doughnut"),
						cronapi.database.Operations.getColumn(vendasQuantidade, Var.valueOf("this[0]")), Var.VAR_NULL,
						cronapi.database.Operations.getColumn(vendasQuantidade, Var.valueOf("this[1]")));
				cronapi.chart.Operations.createChart(Var.valueOf("grafico2"), Var.valueOf("doughnut"),
						cronapi.database.Operations.getColumn(vendasFaturamento, Var.valueOf("this[0]")), Var.VAR_NULL,
						cronapi.database.Operations.getColumn(vendasFaturamento, Var.valueOf("this[1]")));
				return Var.VAR_NULL;
			}
		}.call();
	}

}
