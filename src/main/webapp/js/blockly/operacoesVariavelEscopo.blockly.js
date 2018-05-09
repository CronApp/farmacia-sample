window.blockly = window.blockly || {};
window.blockly.js = window.blockly.js || {};
window.blockly.js.blockly = window.blockly.js.blockly || {};
window.blockly.js.blockly.OperacoesVariavelEscopo = window.blockly.js.blockly.OperacoesVariavelEscopo
		|| {};

/**
 * Descreva esta função...
 */
window.blockly.js.blockly.OperacoesVariavelEscopo.inserir = function(produto,
		quantidade) {

	var itensLinha, produto, quantidade;
	itensLinha = this.cronapi.object
			.createObjectFromString('{\"quantidade\":0, \"produto\":{\"nome\":\"\",\"valor_venda\":0}}');
	this.cronapi.object.setProperty(itensLinha, 'quantidade', quantidade);
	this.cronapi.object.setProperty(itensLinha, 'produto', produto);
	this.cronapi.screen.getScopeVariable('Lista').push(itensLinha);
	this.cronapi.screen.changeValueOfField("vars.Produto", '');
	this.cronapi.screen.changeValueOfField("vars.input6044", '');
}

/**
 * Descreva esta função...
 */
window.blockly.js.blockly.OperacoesVariavelEscopo.criar = function() {

	var itensLinha, produto, quantidade;
	this.cronapi.screen.createScopeVariable('Lista', []);
}
