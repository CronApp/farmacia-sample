package blockly.pedidos;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;

@CronapiMetaData(type = "blockly")
@CronappSecurity
public class GerarRelatorio {

	public static final int TIMEOUT = 300;

	/**
	 *
	 * @return Var
	 */
	// gerarRelatorio
	public static Var gerar() throws Exception {
		return new Callable<Var>() {

			public Var call() throws Exception {
				cronapi.util.Operations.callClientFunction(Var.valueOf("cronapi.util.openReport"),
						Var.valueOf("reports/relatorioVendas.jrxml"), cronapi.list.Operations.newList());
				return Var.VAR_NULL;
			}
		}.call();
	}

}
