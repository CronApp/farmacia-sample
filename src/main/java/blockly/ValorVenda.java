package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;

@CronapiMetaData(type = "blockly")
@CronappSecurity
public class ValorVenda {

	public static final int TIMEOUT = 300;

	/**
	 *
	 * @param Entidade
	 * @return Var
	 */
	// valorVenda
	public static Var calcular(Var Entidade) throws Exception {
		return new Callable<Var>() {

			private Var valor_total = Var.VAR_NULL;

			public Var call() throws Exception {
				valor_total = cronapi.math.Operations.sum(
						cronapi.database.Operations.getField(Entidade, Var.valueOf("produto.preco_venda")),
						cronapi.database.Operations.getField(Entidade, Var.valueOf("quantidade")));
				return Var.VAR_NULL;
			}
		}.call();
	}

}
