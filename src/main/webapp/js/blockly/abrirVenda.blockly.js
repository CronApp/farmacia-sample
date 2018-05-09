window.blockly = window.blockly || {};
window.blockly.js = window.blockly.js || {};
window.blockly.js.blockly = window.blockly.js.blockly || {};
window.blockly.js.blockly.AbrirVenda = window.blockly.js.blockly.AbrirVenda
		|| {};

/**
 * abrirVenda
 */
window.blockly.js.blockly.AbrirVenda.abrir = function() {
	this.cronapi.screen.openUrl('/webapp/#/home/logged/novaVenda', false, 800,
			600);
}
