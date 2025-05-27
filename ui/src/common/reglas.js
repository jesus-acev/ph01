const reglas = {
  reglaEmail: value => {
    const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return pattern.test(value) || "Su correo debe seguir el patrón usuario@correo.com";
  },

  campoVacio: value => !!value || "Campo no debe quedar vacío",
  min: v =>
      (!!v && v.length >= 8) || "Tu contraseña debe contener al menos 8 carácteres",
  requerimientosContraseña: value => !!value || "Una contraseña es requerida",

  largoTelefono: value => {
    const pattern = /^(\+|\d)[0-9]{7,13}$/;
    return pattern.test(value) || "deben ser digitos maximo 14 y se acepta el +";
  },

  
  largoNumerodeCuenta: value => {
    const pattern = /^(\d){0,16}$/;
    return pattern.test(value) || "deben ser digitos maximo 16";
  }
};

export default reglas;