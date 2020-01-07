window.blockly = window.blockly || {};
window.blockly.js = window.blockly.js || {};
window.blockly.js.blockly = window.blockly.js.blockly || {};
window.blockly.js.blockly.LoginFormas = window.blockly.js.blockly.LoginFormas || {};

/**
 * Descreva esta função...
 */
window.blockly.js.blockly.LoginFormas.LoginAnonimo = function() {
 var item, IdDispositivo, registro, password, login, options;
  IdDispositivo = this.cronapi.cordova.device.getDeviceInfo('uuid');
  this.cronapi.util.callServerBlocklyAsynchronous('blockly.Login:CadastroTemporario', function(sender_registro) {
      registro = sender_registro;
    if (registro) {
      this.blockly.js.blockly.LoginFormas.Logar(String('Anonimo_') + String(IdDispositivo), 'SemCadastro', null);
    } else {
      this.cronapi.screen.notify('error','Não foi possivel fazer a entrada sem autenticação.');
    }
  }.bind(this), IdDispositivo);
}

/**
 * Descreva esta função...
 */
window.blockly.js.blockly.LoginFormas.LoginFacebook = function() {
 var item, IdDispositivo, registro, password, login, options;
  this.cronapi.social.sociaLogin('facebook', 'false');
}

/**
 * Descreva esta função...
 */
window.blockly.js.blockly.LoginFormas.LoginGoogle = function() {
 var item, IdDispositivo, registro, password, login, options;
  this.cronapi.social.sociaLogin('google');
}

/**
 * Descreva esta função...
 */
window.blockly.js.blockly.LoginFormas.LoginLinkedIn = function() {
 var item, IdDispositivo, registro, password, login, options;
  this.cronapi.social.sociaLogin('linkedin');
}

/**
 * Descreva esta função...
 */
window.blockly.js.blockly.LoginFormas.Logar = function(login, password, options) {
 var item, IdDispositivo, registro;
  this.cronapi.screen.showLoading();
  if (!this.cronapi.logic.isNullOrEmpty(this.cronapi.screen.getHostapp())) {
    this.cronapi.util.getURLFromOthers('POST', 'application/x-www-form-urlencoded', String(this.cronapi.screen.getHostapp()) + String('auth'), this.cronapi.object.createObjectFromString(['{ \"username\": \"',login,'\" , \"password\": \"',password,'\" } '].join('')), null, function(sender_item) {
        item = sender_item;
      this.cronapi.screen.hide();
      this.cronapi.util.setLocalStorage('_u', this.cronapi.object.serializeObject(item));
      this.cronapi.screen.changeView("#/app/logged/home",[  ]);
    }.bind(this), function(sender_item) {
        item = sender_item;
      if (this.cronapi.object.getProperty(item, 'status') == '403' || this.cronapi.object.getProperty(item, 'status') == '401') {
        this.cronapi.screen.notify('error',this.cronapi.i18n.translate("LoginViewInvalidpassword",[  ]));
      } else {
        this.cronapi.screen.notify('error',this.cronapi.object.getProperty(item, 'responseJSON.message'));
      }
      this.cronapi.screen.hide();
    }.bind(this));
  } else {
    this.cronapi.screen.hide();
    this.cronapi.screen.notify('error','HostApp is Required');
  }
}
