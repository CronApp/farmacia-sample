package blockly.chatbot;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;

@CronapiMetaData(type = "blockly")
@CronappSecurity
public class Chat {

	public static final int TIMEOUT = 300;

	/**
	 *
	 * @param context
	 * @param watsonMsg
	 */
	// Descreva esta função...
	public static void adicionarItemPedido(Var context, Var watsonMsg) throws Exception {
		new Callable<Var>() {

			public Var call() throws Exception {
				blockly.pedidos.AddItemPedido.adicionar(context, watsonMsg);
				return Var.VAR_NULL;
			}
		}.call();
	}

	/**
	 *
	 * @param context
	 */
	// Descreva esta função...
	public static void chamarCriarPedido(Var context) throws Exception {
		new Callable<Var>() {

			public Var call() throws Exception {
				blockly.pedidos.CriarPedido.criar(context);
				return Var.VAR_NULL;
			}
		}.call();
	}

	/**
	 *
	 * @param entities
	 * @param intents
	 * @param context
	 */
	// Descreva esta função...
	public static void chamarRemedios(Var entities, Var intents, Var context) throws Exception {
		new Callable<Var>() {

			public Var call() throws Exception {
				blockly.chatbot.Remedios.remedios(entities, intents, context);
				return Var.VAR_NULL;
			}
		}.call();
	}

	/**
	 *
	 * @return Var
	 */
	// Descreva esta função...
	public static Var iniciar_watson() throws Exception {
		return new Callable<Var>() {

			private Var watsonMsg = Var.VAR_NULL;

			public Var call() throws Exception {
				watsonMsg = Var.valueOf(cronapi.watson.conversation.ConversationOperations.message(
						Var.valueOf("2018-02-16").getTypedObject(java.lang.String.class),
						Var.valueOf("131f7036-21b9-4e60-89de-4fd8baad44ab\n\n").getTypedObject(java.lang.String.class),
						Var.valueOf("4ibq5pkzpFbr").getTypedObject(java.lang.String.class),
						Var.VAR_NULL.getTypedObject(java.lang.String.class),
						Var.VAR_NULL.getTypedObject(java.util.Map.class),
						cronapi.object.Operations
								.newObject(Var.valueOf(
										"com.ibm.watson.developer_cloud.conversation.v1.model.MessageOptions"),
										Var.valueOf("workspaceId",
												Var.valueOf("13830c02-870a-4ca8-8beb-2275d2c20a9e")))
								.getTypedObject(
										com.ibm.watson.developer_cloud.conversation.v1.model.MessageOptions.class)));
				return cronapi.map.Operations
						.createObjectMapWith(
								Var.valueOf("text",
										cronapi.object.Operations.getObjectField(watsonMsg,
												Var.valueOf("$.output.text[0]"))),
								Var.valueOf("context",
										cronapi.object.Operations.getObjectField(watsonMsg, Var.valueOf("$.context"))));
			}
		}.call();
	}

	/**
	 *
	 * @return Var
	 */
	// Descreva esta função...
	public static Var obter_login() throws Exception {
		return new Callable<Var>() {

			private Var context = Var.VAR_NULL;
			private Var watsonMsg = Var.VAR_NULL;
			private Var mensagem = Var.VAR_NULL;
			private Var entities = Var.VAR_NULL;
			private Var intents = Var.VAR_NULL;
			private Var blockly = Var.VAR_NULL;

			public Var call() throws Exception {
				return Var.valueOf(cronapi.text.Operations.titleCase((cronapi.util.Operations.getCurrentUserName())));
			}
		}.call();
	}

	/**
	 *
	 * @param mensagem
	 * @param param_context
	 * @return Var
	 */
	// Descreva esta função...
	public static Var recebe_mensagem(Var mensagem, Var param_context) throws Exception {
		return new Callable<Var>() {

			// param
			private Var context = param_context;
			// end
			private Var watsonMsg = Var.VAR_NULL;
			private Var entities = Var.VAR_NULL;
			private Var intents = Var.VAR_NULL;
			private Var blockly = Var.VAR_NULL;

			public Var call() throws Exception {
				cronapi.map.Operations.setMapField(context, Var.valueOf("userLogin"),
						cronapi.util.Operations.getCurrentUserName());
				watsonMsg = Var.valueOf(cronapi.watson.conversation.ConversationOperations.message(
						Var.valueOf("2018-02-16").getTypedObject(java.lang.String.class),
						Var.valueOf("131f7036-21b9-4e60-89de-4fd8baad44ab\n\n").getTypedObject(java.lang.String.class),
						Var.valueOf("4ibq5pkzpFbr").getTypedObject(java.lang.String.class),
						Var.VAR_NULL.getTypedObject(java.lang.String.class),
						Var.VAR_NULL.getTypedObject(java.util.Map.class),
						cronapi.object.Operations
								.newObject(
										Var.valueOf(
												"com.ibm.watson.developer_cloud.conversation.v1.model.MessageOptions"),
										Var.valueOf("workspaceId", Var.valueOf("13830c02-870a-4ca8-8beb-2275d2c20a9e")),
										Var.valueOf("input",
												cronapi.object.Operations.newObject(
														Var.valueOf(
																"com.ibm.watson.developer_cloud.conversation.v1.model.InputData"),
														Var.valueOf("text", mensagem))),
										Var.valueOf("context", context))
								.getTypedObject(
										com.ibm.watson.developer_cloud.conversation.v1.model.MessageOptions.class)));
				context = cronapi.object.Operations.getObjectField(watsonMsg, Var.valueOf("$.context"));
				entities = cronapi.object.Operations.getObjectField(watsonMsg, Var.valueOf("$.entities"));
				intents = cronapi.object.Operations.getObjectField(watsonMsg, Var.valueOf("$.intents"));
				blockly = cronapi.json.Operations.getJsonOrMapField(context, Var.valueOf("blockly"));
				if (cronapi.logic.Operations.isNullOrEmpty(blockly).negate().getObjectAsBoolean()) {
					if (Var.valueOf(blockly.equals(Var.valueOf("blockly.Remedios"))).getObjectAsBoolean()) {
						chamarRemedios(entities, intents, context);
					} else if (Var.valueOf(blockly.equals(Var.valueOf("blockly.CriarPedido"))).getObjectAsBoolean()) {
						chamarCriarPedido(context);
					} else if (Var.valueOf(blockly.equals(Var.valueOf("blockly.AddItemPedido"))).getObjectAsBoolean()) {
						adicionarItemPedido(context, watsonMsg);
					}
					watsonMsg = Var
							.valueOf(cronapi.watson.conversation.ConversationOperations
									.message(Var.valueOf("2018-02-16").getTypedObject(java.lang.String.class),
											Var.valueOf("131f7036-21b9-4e60-89de-4fd8baad44ab\n\n").getTypedObject(
													java.lang.String.class),
											Var.valueOf("4ibq5pkzpFbr").getTypedObject(java.lang.String.class),
											Var.VAR_NULL.getTypedObject(java.lang.String.class),
											Var.VAR_NULL
													.getTypedObject(java.util.Map.class),
											cronapi.object.Operations
													.newObject(
															Var.valueOf(
																	"com.ibm.watson.developer_cloud.conversation.v1.model.MessageOptions"),
															Var.valueOf("workspaceId",
																	Var.valueOf(
																			"13830c02-870a-4ca8-8beb-2275d2c20a9e")),
															Var.valueOf("input", cronapi.object.Operations.newObject(
																	Var.valueOf(
																			"com.ibm.watson.developer_cloud.conversation.v1.model.InputData"),
																	Var.valueOf("text",
																			Var.valueOf(blockly.toString() + Var
																					.valueOf(".Done").toString())))),
															Var.valueOf("context", context))
													.getTypedObject(
															com.ibm.watson.developer_cloud.conversation.v1.model.MessageOptions.class)));
				}
				return cronapi.map.Operations
						.createObjectMapWith(
								Var.valueOf("text",
										cronapi.object.Operations.getObjectField(watsonMsg,
												Var.valueOf("$.output.text"))),
								Var.valueOf("context",
										cronapi.object.Operations.getObjectField(watsonMsg, Var.valueOf("$.context"))));
			}
		}.call();
	}

}
