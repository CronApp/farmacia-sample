window.blockly = window.blockly || {};
window.blockly.js = window.blockly.js || {};
window.blockly.js.blockly = window.blockly.js.blockly || {};
window.blockly.js.blockly.OperacoesVariavelEscopo = window.blockly.js.blockly.OperacoesVariavelEscopo || {};

/**
 * Descreva esta função...
 */
window.blockly.js.blockly.OperacoesVariavelEscopo.inserir = function(produto, quantidade) {
 var itensLinha;
  itensLinha = this.cronapi.object.createObjectFromString('{\"pedido\":\"\",\"quantidade\":0, \"produto\":{\"id\":\"\",\"nome\":\"\",\"preco_venda\":0}}');
  this.cronapi.object.setProperty(itensLinha, 'quantidade', quantidade);
  this.cronapi.object.setProperty(itensLinha, 'produto.nome', this.cronapi.object.getProperty(produto, 'nome'));
  this.cronapi.object.setProperty(itensLinha, 'produto.id', this.cronapi.object.getProperty(produto, 'id'));
  this.cronapi.object.setProperty(itensLinha, 'produto.preco_venda', this.cronapi.object.getProperty(produto, 'preco_venda'));
  this.cronapi.screen.getScopeVariable('Lista').push(itensLinha);
  this.cronapi.screen.changeValueOfField("vars.Produto", '');
  this.cronapi.screen.changeValueOfField("vars.input6044", '');
  this.cronapi.screen.changeValueOfField("vars.totalVenda", this.cronapi.screen.getValueOfField("vars.totalVenda") + this.cronapi.object.getProperty(produto, 'preco_venda') * quantidade);
}

/**
 * Descreva esta função...
 */
window.blockly.js.blockly.OperacoesVariavelEscopo.criar = function() {
 var quantidade, produto, itensLinha;
  this.cronapi.screen.createScopeVariable('Lista', []);
}
