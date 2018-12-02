package blockly.pedidos;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;

@CronapiMetaData(type = "blockly")
@CronappSecurity
public class AddItemPedido {

	public static final int TIMEOUT = 300;

	/**
	 *
	 * @param context
	 * @param watsonMsg
	 */
	// Descreva esta função...
	public static void adicionar(Var context, Var watsonMsg) throws Exception {
		new Callable<Var>() {

			private Var idPedido = Var.VAR_NULL;
			private Var valorPedido = Var.VAR_NULL;
			private Var numItem = Var.VAR_NULL;
			private Var entityValue = Var.VAR_NULL;
			private Var consultaDb = Var.VAR_NULL;

			public Var call() throws Exception {
				idPedido = cronapi.json.Operations.getJsonOrMapField(context, Var.valueOf("idPedido"));
				valorPedido = cronapi.json.Operations.getJsonOrMapField(context, Var.valueOf("valorPedido"));
				numItem = cronapi.json.Operations.getJsonOrMapField(context, Var.valueOf("numItem"));
				entityValue = cronapi.json.Operations.getJsonOrMapField(watsonMsg, Var.valueOf("$.entities[0].value"));
				consultaDb = cronapi.database.Operations.query(Var.valueOf("app.entity.Produto"),
						Var.valueOf("select p.nome, p.preco_venda, p.id from Produto p where upper(p.nome) = :upper"),
						Var.valueOf("upper", Var.valueOf(entityValue.getObjectAsString().toUpperCase())));
				cronapi.json.Operations.setJsonOrMapField(context, Var.valueOf("produtoAtual"),
						cronapi.database.Operations.getField(consultaDb, Var.valueOf("this[0]")));
				cronapi.json.Operations.setJsonOrMapField(context, Var.valueOf("valorPedido"), cronapi.math.Operations
						.sum(valorPedido, cronapi.database.Operations.getField(consultaDb, Var.valueOf("this[1]"))));
				cronapi.json.Operations.setJsonOrMapField(context, Var.valueOf("numItem"),
						cronapi.math.Operations.sum(numItem, Var.valueOf(1)));
				if (Var.valueOf(cronapi.json.Operations.getJsonOrMapField(context, Var.valueOf("numItem"))
						.compareTo(Var.valueOf(1)) > 0).getObjectAsBoolean()) {
					cronapi.json.Operations.setJsonOrMapField(context, Var.valueOf("spItens"), Var.valueOf("Itens"));
				} else {
					cronapi.json.Operations.setJsonOrMapField(context, Var.valueOf("spItens"), Var.valueOf("Item"));
				}
				cronapi.database.Operations.insert(Var.valueOf("app.entity.Venda"),
						Var.valueOf("produto",
								cronapi.database.Operations.getField(consultaDb, Var.valueOf("this[2]"))),
						Var.valueOf("data", cronapi.dateTime.Operations.getNow()), Var.valueOf("pedido", idPedido),
						Var.valueOf("quantidade", Var.valueOf(1)));
				cronapi.database.Operations.execute(Var.valueOf("app.entity.Pedido"),
						Var.valueOf("update Pedido set quantidade = :quantidade, valor = :valor where id = :id"),
						Var.valueOf("quantidade",
								Var.valueOf(
										cronapi.json.Operations.getJsonOrMapField(context, Var.valueOf("numItem")))),
						Var.valueOf("valor", Var.valueOf(
								cronapi.json.Operations.getJsonOrMapField(context, Var.valueOf("valorPedido")))),
						Var.valueOf("id", idPedido));
				System.out.println(Var.valueOf(Var.valueOf("Context addItemPedido: ").toString() + context.toString())
						.getObjectAsString());
				System.out
						.println(Var.valueOf(Var.valueOf("WatsonMsg addItemPedido: ").toString() + watsonMsg.toString())
								.getObjectAsString());
				return Var.VAR_NULL;
			}
		}.call();
	}

}
