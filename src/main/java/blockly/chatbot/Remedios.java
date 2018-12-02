package blockly.chatbot;

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
						Var.valueOf("select p.nome from Produto p"));
				System.out
						.println(cronapi.text.Operations
								.getLettersFromStartToFromStart(remedios,
										Var.valueOf(2), (cronapi.math.Operations
												.subtract(Var.valueOf(remedios.length()), Var.valueOf(1))))
								.getObjectAsString());
				cronapi.json.Operations.setJsonOrMapField(context, Var.valueOf("remedios"),
						cronapi.text.Operations.getLettersFromStartToFromStart(remedios, Var.valueOf(2),
								(cronapi.math.Operations.subtract(Var.valueOf(remedios.length()), Var.valueOf(1)))));
				return Var.VAR_NULL;
			}
		}.call();
	}

}
