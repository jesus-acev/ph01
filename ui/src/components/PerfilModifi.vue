<template>
  <v-main>
    <div class="col-md-12">
      <div class="card card-container" v-if="currentUser.user">
        <v-row align="center" justify="space-around">
          <img
            id="profile-img"
            src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
            class="profile-img-card"
            alt=""
          />
          <v-btn color="tercero">subir imagen</v-btn>
        </v-row>
        <div class="form-group">
          <v-row align="center" justify="space-around">
            <label for="NombreUsuario">Nombre de Usuario:</label>
            <v-text-field
              prepend-icon="mdi-account"
              validate-on-blur
              clearable
              dense
              outlined
              :rules="[reglas.campoVacio]"
              label="Ingrese su nombre de usuario"
              required
              v-model="cuenta.cueNombreUsuario"
            />
          </v-row>
        </div>
        <div class="form-group">
          <v-row align="center" justify="space-around">
            <label>Correo:</label>
            <v-text-field
              prepend-icon="mdi-email"
              validate-on-blur
              clearable
              dense
              outlined
              :rules="[reglas.reglaEmail]"
              label="Ingrese su e-mail"
              type="correo"
              v-model="cuenta.cueCorreo"
            />
          </v-row>
        </div>
        <div class="form-group">
          <v-row align="center" justify="space-around">
            <label>Telefono:</label>
            <v-text-field
              prepend-icon="mdi-phone"
              validate-on-blur
              clearable
              dense
              outlined
              :rules="[reglas.largoTelefono]"
              label="Ingrese su telefono"
              type="telefono"
              v-model="cuenta.cueTelefono"
            />
          </v-row>
        </div>
        <div class="form-group">
          <v-row align="center" justify="space-around">
            <label>Cuenta Bancaria:</label>
            <v-text-field
              prepend-icon="mdi-wallet"
              validate-on-blur
              clearable
              dense
              outlined
              :rules="[reglas.largoNumerodeCuenta]"
              label="Ingrese su numero de cuenta bancaria"
              type="cuenta bancaria"
              v-model="cuenta.cueIdBanco"
            />
          </v-row>
        </div>
        <div class="form-group">
          <v-card-actions class="justify-center">
            <v-btn color="primero" to="/perfil">Cancelar</v-btn>
            <v-btn color="primero" @click="modificar()">Confirmar</v-btn>
          </v-card-actions>
        </div>
        <v-row justify="center">
          <v-dialog v-model="dialog" persistent max-width="600">
            <template v-slot:activator="{ on, attrs }">
              <v-btn color="error" dark v-bind="attrs" v-on="on">
                Eliminar Cuenta
              </v-btn>
            </template>
            <v-card>
              <v-card-title class="text-h5">
                ¿Realmente desea eliminar su cuenta?
              </v-card-title>
              <v-card-text
                >Si es así presione en la palabra eliminar, de lo contrario
                presione cancelar</v-card-text
              >
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="primero" text @click="dialog = false">
                  Cancelar
                </v-btn>
                <v-btn color="error" text @click="eliminar()">
                  Eliminar
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-row>
        <div class="form-group" v-if="message">
          <v-alert type="error" dense v-if="message">{{ message }}</v-alert>
        </div>
      </div>
    </div>

    <div class="card card-container" v-if="!currentUser.user">
      <div class="form-group">
        <v-card-actions class="justify-center">
          <h3>
            Usted no está logueado, por favor loguee primero para acceder a esta información
          </h3>
        </v-card-actions>
        <v-card-actions class="justify-center">
          <v-btn color="primero" to="/login">salir</v-btn>
        </v-card-actions>
      </div>
    </div>
  </v-main>
</template>
<script>
import reglas from "@/common/reglas";
import perfil from "@/services/perfil.service";
export default {
  name: "PerfilCom",
  data: () => ({
    dialog: false,
    id: null,
    reglas: reglas,
    cuenta: {},
    message: "",
  }),
  computed: {
    currentUser() {
      return this.$store.state.auth;
    },
  },
  methods: {
    getcuenta: function() {
      perfil.getperfil(this.currentUser.user.username).then((response1) => {
        this.cuenta = response1.data;
      });
    },
    modificar() {
      perfil
        .getid(this.currentUser.user.username)
        .then((response2) => {
          this.id = response2.data;
          perfil.modifcuenta(this.id, this.cuenta).then(response3=>{
             this.$router.push("/perfil");
          }).catch((error) => {
          this.message = error.response.data;
        })

        })
    },
    eliminar(){
        perfil.eliminar(this.currentUser.user.username).then((response) => {
             this.$store.dispatch("auth/logout");
            this.$router.push("/");}).catch((error) => {
          this.message = error.response.data;
        })
    }
  },
  mounted: function() {
    this.getcuenta();
  },
};
</script>
<style>
label {
  display: block;
  margin-top: 0px;
}

.card-container.card {
  max-width: 600px !important;
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
  width: 120px;
  height: 120px;
  margin: 15px;
  display: block;
  -moz-border-radius: 40%;
  -webkit-border-radius: 40%;
  border-radius: 40%;
}
</style>
