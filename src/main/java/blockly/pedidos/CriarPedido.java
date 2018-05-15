package blockly.pedidos;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;

@CronapiMetaData(type = "blockly")
@CronappSecurity
public class CriarPedido {

	public static final int TIMEOUT = 300;

	/**
	 *
	 * @param context
	 */
	// Descreva esta função...
	public static void criar(Var context) throws Exception {
		new Callable<Var>() {

			private Var idPedido = Var.VAR_NULL;
			private Var valorPedido = Var.VAR_NULL;

			public Var call() throws Exception {
				idPedido = cronapi.util.Operations.generateUUID();
				valorPedido = Var.valueOf(0);
				cronapi.database.Operations.insert(Var.valueOf("app.entity.Pedido"),
						Var.valueOf("data", cronapi.dateTime.Operations.getNow()), Var.valueOf("valor", valorPedido),
						Var.valueOf("id", idPedido), Var.valueOf("quantidade", Var.valueOf(0)));
				cronapi.json.Operations.setJsonOrMapField(context, Var.valueOf("idPedido"),
						cronapi.json.Operations.toJson(idPedido));
				cronapi.json.Operations.setJsonOrMapField(context, Var.valueOf("valorPedido"),
						cronapi.json.Operations.toJson(valorPedido));
				cronapi.json.Operations.setJsonOrMapField(context, Var.valueOf("numItem"), Var.valueOf(0));
				System.out.println(Var.valueOf(Var.valueOf("Contexto criarPedido: ").toString() + context.toString())
						.getObjectAsString());
				return Var.VAR_NULL;
			}
		}.call();
	}

	/**
	 *
	 * @return Var
	 */
	// Descreva esta função...
	public static Var idPedido() throws Exception {
		return new Callable<Var>() {

			private Var idPedido = Var.VAR_NULL;
			private Var valorPedido = Var.VAR_NULL;
			private Var context = Var.VAR_NULL;

			public Var call() throws Exception {
				return Var.valueOf(cronapi.util.Operations.generateUUID());
			}
		}.call();
	}

}
