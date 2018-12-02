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

			public Var call() throws Exception {
				System.out
						.println(Var
								.valueOf(cronapi.watson.conversation.ConversationOperations
										.createValue(Var.valueOf("2018-02-16").getTypedObject(java.lang.String.class),
												Var.valueOf("131f7036-21b9-4e60-89de-4fd8baad44ab\n\n").getTypedObject(
														java.lang.String.class),
												Var.valueOf("4ibq5pkzpFbr").getTypedObject(java.lang.String.class),
												Var.VAR_NULL.getTypedObject(java.lang.String.class),
												Var.VAR_NULL.getTypedObject(
														java.util.Map.class),
												cronapi.object.Operations
														.newObject(
																Var.valueOf(
																		"com.ibm.watson.developer_cloud.conversation.v1.model.CreateValueOptions"),
																Var.valueOf("workspaceId", Var
																		.valueOf("13830c02-870a-4ca8-8beb-2275d2c20a9e")
																		.getTypedObject(java.lang.String.class)),
																Var.valueOf("entity",
																		Var.valueOf("remedios").getTypedObject(
																				java.lang.String.class)),
																Var.valueOf("value", cronapi.screen.Operations
																		.getValueOfField(
																				Var.valueOf("Produto.active.nome"))
																		.getTypedObject(java.lang.String.class)))
														.getTypedObject(
																com.ibm.watson.developer_cloud.conversation.v1.model.CreateValueOptions.class)))
								.getObjectAsString());
				return Var.VAR_NULL;
			}
		}.call();
	}

}
