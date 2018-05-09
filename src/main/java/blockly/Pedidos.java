package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;

@CronapiMetaData(type = "blockly")
@CronappSecurity
public class Pedidos {

	public static final int TIMEOUT = 300;

	/**
	 */
	// Descreva esta função...
	public static void pedidos() throws Exception {
		new Callable<Var>() {

			public Var call() throws Exception {
				return Var.VAR_NULL;
			}
		}.call();
	}

}
