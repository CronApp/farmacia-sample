package blockly.pedidos;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;

@CronapiMetaData(type = "blockly")
@CronappSecurity
public class GerarBoleto {

	public static final int TIMEOUT = 300;

	/**
	 *
	 * @param pedidoId
	 * @return Var
	 */
	// gerarBoleto
	public static Var gerar(Var pedidoId) throws Exception {
		return new Callable<Var>() {

			private Var consultaDB = Var.VAR_NULL;
			private Var jsonBoletos = Var.VAR_NULL;
			private Var jsonBeneficiario = Var.VAR_NULL;
			private Var link = Var.VAR_NULL;
			private Var boleto = Var.VAR_NULL;

			public Var call() throws Exception {
				System.out.println(pedidoId.getObjectAsString());
				consultaDB = cronapi.database.Operations.query(Var.valueOf("app.entity.Venda"),
						Var.valueOf(
								"select v.data, v.pedido.id, SUM(v.quantidade), SUM(v.produto.preco_venda) from Venda v where v.pedido.id = :pedidoId group by v.pedido.id"),
						Var.valueOf("pedidoId", pedidoId));
				jsonBoletos = Var
						.valueOf(
								br.com.mabilis.cronapi.boleto.Operations
										.criarBoleto(Var.valueOf("false"), Var.valueOf("101"), Var.valueOf("773784"),
												Var.valueOf("463474"),
												Var.valueOf(Var.valueOf(cronapi.dateTime.Operations.getNowNoHour())
														.toString()),
												Var.valueOf(
														"DUPLICATA_MERCANTIL"),
												Var.valueOf(Var.valueOf(cronapi.dateTime.Operations.incDay(
														Var.valueOf(cronapi.dateTime.Operations.getNowNoHour()),
														Var.valueOf(5))).toString()),
												Var.valueOf("626551"),
												Var.valueOf(
														"Não receber após o vencimento. Caso seja necessário prorrogar o boleto entre em contato com a nossa central de atendimento"),
												Var.valueOf("1"), Var.valueOf("REGISTRAR"),
												Var.valueOf(Var.valueOf(cronapi.dateTime.Operations.getNowNoHour())
														.toString()),
												Var.VAR_NULL, Var.VAR_NULL, Var.VAR_NULL, Var.VAR_NULL, Var.VAR_NULL,
												Var.VAR_NULL, Var.VAR_NULL, Var.VAR_NULL, Var.VAR_NULL, Var.VAR_NULL,
												Var.VAR_NULL, Var.VAR_NULL, Var.VAR_NULL, Var.VAR_NULL,
												Var.VAR_NULL, Var
														.valueOf(
																Var.valueOf(
																		"Boleto de demonstração, o valor real seria: ")
																		.toString()
																		+ cronapi.math.Operations
																				.multiply(
																						cronapi.database.Operations
																								.getField(consultaDB,
																										Var.valueOf(
																												"this[3]")),
																						cronapi.database.Operations
																								.getField(consultaDB,
																										Var.valueOf(
																												"this[2]")))
																				.toString()),
												Var.VAR_NULL,
												Var.valueOf(Var.valueOf(cronapi.dateTime.Operations.getNowNoHour())
														.toString()),
												Var.VAR_NULL, Var.VAR_NULL, Var.VAR_NULL, Var.VAR_NULL, Var.VAR_NULL));
				jsonBeneficiario = Var.valueOf(br.com.mabilis.cronapi.boleto.Operations.criarBeneficiario(
						Var.valueOf("SANTANDER"), Var.valueOf("3822"), Var.valueOf("0"), Var.valueOf("13005126"),
						Var.valueOf("3"), Var.valueOf("JURIDICA"), Var.VAR_NULL, Var.valueOf("Cronapp"),
						Var.valueOf("Salvador"), Var.valueOf("Bahia"), Var.valueOf("41730101"), Var.valueOf("Itaigara"),
						Var.valueOf("Alto do Parque"), Var.valueOf("20"), Var.valueOf("Itaigara"), Var.VAR_NULL,
						Var.valueOf("101"), Var.valueOf("5163285"), Var.valueOf("38220516328501300512")));
				link = Var.valueOf(Var.valueOf(cronapi.io.Operations.fileAppReclycleDir()).toString()
						+ Var.valueOf(cronapi.io.Operations.fileSeparator()).toString()
						+ Var.valueOf("boleto.pdf").toString());
				boleto = br.com.mabilis.cronapi.boleto.Operations.gerarBoletoPDF(link, jsonBeneficiario,
						cronapi.list.Operations.newList(jsonBoletos), Var.valueOf("BOLETO_NORMAL"));
				cronapi.io.Operations.fileDownload(link);
				return Var.VAR_NULL;
			}
		}.call();
	}

}
