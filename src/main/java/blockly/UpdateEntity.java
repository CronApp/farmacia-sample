package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;

@CronapiMetaData(type = "blockly")
@CronappSecurity
public class UpdateEntity {

	public static final int TIMEOUT = 300;

	/**
	 */
	// Descreva esta função...
	public static void updateRemedio() throws Exception {
		new Callable<Var>() {

			private Var consultaDb = Var.VAR_NULL;

			public Var call() throws Exception {
				consultaDb = cronapi.database.Operations.query(Var.valueOf("app.entity.Produto"),
						Var.valueOf("select p.nome from Produto p where p.nome = :nome"), Var.valueOf("nome",
								cronapi.screen.Operations.getValueOfField(Var.valueOf("Produto.active.nome"))));
				return Var.VAR_NULL;
			}
		}.call();
	}

}
