window.blockly = window.blockly || {};
window.blockly.js = window.blockly.js || {};
window.blockly.js.CriarCarrinho = window.blockly.js.CriarCarrinho || {};

/**
 * Descreva esta função...
 */
window.blockly.js.CriarCarrinho.inserir = function(nomeProduto, valorProduto,
		idProduto) {

	var nomeProduto, itensLinha, valorProduto, idProduto;
	itensLinha = this.cronapi.object
			.createObjectFromString('{\"pedido\":\"\",\"quantidade\":0, \"produto\":{\"nome\":\"\",\"valor_venda\":0}}');
	this.cronapi.object.setProperty(itensLinha, 'pedido', this.cronapi.screen
			.getScopeVariable('Pedido'));
	this.cronapi.object.setProperty(itensLinha, 'quantidade', 1);
	this.cronapi.object.setProperty(itensLinha, 'produto.nome', nomeProduto);
	this.cronapi.object.setProperty(itensLinha, 'produto.valor_venda',
			valorProduto);
	this.cronapi.object.setProperty(itensLinha, 'produto.id', idProduto);
	window.alert(String('Id produto ') + String(idProduto));
	window.alert(String('Nome Produto') + String(nomeProduto));
	window.alert(String('Valor Produto') + String(valorProduto));
	this.cronapi.screen.getScopeVariable('Lista').push(itensLinha);
	this.cronapi.screen.showComponent("crn-button-272456");
	this.cronapi.screen.notify('success', String(nomeProduto)
			+ String(' Adicionado com sucesso.'));
}

/**
 * Descreva esta função...
 */
window.blockly.js.CriarCarrinho.criar = function() {

	var nomeProduto, itensLinha, valorProduto, idProduto;
	this.cronapi.screen.hideComponent("crn-button-272456");
	this.cronapi.screen.createScopeVariable('Pedido', this.cronapi.util
			.callServerBlockly('blockly.CriarPedido:idPedido'));
	this.cronapi.screen.createScopeVariable('Lista', []);
}
