package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;

@CronapiMetaData(type = "blockly")
@CronappSecurity
public class Remedios {

	public static final int TIMEOUT = 300;

	/**
	 *
	 * @param entities
	 * @param intents
	 * @param context
	 */
	// Descreva esta função...
	public static void remedios(Var entities, Var intents, Var context) throws Exception {
		new Callable<Var>() {

			private Var remedios = Var.VAR_NULL;

			public Var call() throws Exception {
				remedios = cronapi.database.Operations.query(Var.valueOf("app.entity.Produto"),
						Var.valueOf("select p.nome, p.preco_venda from Produto p"));
				cronapi.json.Operations.setJsonOrMapField(context, Var.valueOf("remedios"),
						cronapi.json.Operations.toJson(remedios));
				System.out.println(context.getObjectAsString());
				return Var.VAR_NULL;
			}
		}.call();
	}

}
