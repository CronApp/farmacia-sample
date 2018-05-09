package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;

@CronapiMetaData(type = "blockly")
@CronappSecurity
public class ValorTotal {

	public static final int TIMEOUT = 300;

	/**
	 *
	 * @param Entidade
	 * @return Var
	 */
	// valorTotal
	public static Var calcular(Var Entidade) throws Exception {
		return new Callable<Var>() {

			private Var precoVenda = Var.VAR_NULL;
			private Var quantidade = Var.VAR_NULL;
			private Var precoTotal = Var.VAR_NULL;

			public Var call() throws Exception {
				precoVenda = cronapi.database.Operations.getField(Entidade, Var.valueOf("produto.preco_venda"));
				quantidade = cronapi.database.Operations.getField(Entidade, Var.valueOf("quantidade"));
				precoTotal = cronapi.math.Operations.multiply(precoVenda, quantidade);
				return precoTotal;
			}
		}.call();
	}

}
