<template>
  <v-main>
    <div class="col-md-12">
      <div class="card card-container">
        <v-row align="center" justify="space-around">
          <h3>Creacion de cuenta</h3>
        </v-row>
        <br />
        <img
          id="profile-img"
          src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
          class="profile-img-card"
          alt=""
        />
        <div class="form-group">
          <v-row align="center" justify="space-around">
            <label for="nombreUsuario">Nombre de Usuario</label>
            <v-text-field
              prepend-icon="mdi-account"
              validate-on-blur
              clearable
              dense
              outlined
              v-model="usuario.cueNombreUsuario"
              :rules="[reglas.campoVacio]"
              label="Ingrese su nombre de usuario"
              type="nombreUsuario"
              required
              data
              cy="field-nombreUsuario"
            />
          </v-row>
        </div>
        <div class="form-group">
          <v-row align="center" justify="space-around">
            <label for="correo">Correo</label>
            <v-text-field
              prepend-icon="mdi-email"
              validate-on-blur
              clearable
              dense
              outlined
              v-model="usuario.cueCorreo"
              :rules="[reglas.reglaEmail]"
              label="Ingrese su e-mail"
              type="correo"
              required
              data-cy="field-correo"
            />
          </v-row>
        </div>
        <div class="form-group">
          <v-row align="center" justify="space-around">
            <label for="contraseña">Contraseña</label>
            <v-text-field
              :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
              :rules="[reglas.requerimientosContraseña, reglas.min]"
              :type="show1 ? 'text' : 'contraseña'"
              counter
              @click:append="show1 = !show1"
              prepend-icon="mdi-lock"
              dense
              outlined
              v-model="usuario.cueClave"
              label="Ingrese su contraseña"
              required
              data-cy="field-contraseña"
            />
          </v-row>
        </div>
        <div class="form-group">
          <v-row align="center" justify="space-around">
            <label for="registrarseComo">Registrarse como:</label>
            <v-select
              dense
              outlined
              :items="items"
              v-model="selectedItem"
              item-text="name"
              label="Seleccione"
              single-line
              return-object
            />
          </v-row>
        </div>
        <div class="form-group">
          <v-row align="center" justify="space-around">
            <v-btn color="primero" @click="cancelar()">Cancelar</v-btn>
            <v-btn color="primero" @click="registrarse()">Registrarse</v-btn>
          </v-row>
        </div>
        <div class="form-group">
            <v-alert type="error" dense v-if="message">{{ message }}</v-alert>
        </div>
      </div>
    </div>
  </v-main>
</template>
<script>
import reglas from "@/common/reglas";
import registrarseService from "@/services/registrarse.service.js";
export default {
  data() {

    return {
      items: ['Anfitrion', 'Huesped'],
      reglas: reglas,
      selectedItem:"",
      usuario: {
        cueCorreo: "",
        cueClave: "",
        cueNombreUsuario: "",
      },
      message: "",
      show1: "",
      
    };
    
  },
  

  methods: {
    registrarse() {
       registrarseService.create(this.selectedItem,this.usuario).then(response=>{
        this.$router.push("login");}).catch((error) => {
          if (error.response.status=='403'){
            this.message="Seleccione el tipo de cuenta que desea";
          }else{
          this.message = error.response.data;
          }
        })
    },
    cancelar() {
      this.$router.push({
        name: "home",
      });
    },
  },
};
</script>
<style scoped>
label {
  display: block;
  margin-top: 10px;
}

.card-container.card {
  max-width: 350px !important;
  padding: 40px 40px;
}

.card {
  background-color: #eeeeee;
  padding: 20px 25px 30px;
  margin: 0 auto 25px;
  margin-top: 50px;
  -moz-border-radius: 2px;
  -webkit-border-radius: 2px;
  border-radius: 2px;
  -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
}

.profile-img-card {
  width: 96px;
  height: 96px;
  margin: 0 auto 10px;
  display: block;
  -moz-border-radius: 50%;
  -webkit-border-radius: 50%;
  border-radius: 50%;
}
</style>
