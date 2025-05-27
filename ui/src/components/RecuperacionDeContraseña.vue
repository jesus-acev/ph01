<template>
  <v-main>
    <div class="col-md-12">
      <div class="card card-container">
        <h2>Recuperar contraseña</h2>
        <div class="form-group">
          <v-card-actions class="justify-center">
            <label for="correo"
              >Ingrese el correo electronico de su cuenta.</label
            >
          </v-card-actions>
          <v-card-actions class="justify-center">
            <v-text-field
              prepend-icon="mdi-email"
              validate-on-blur
              clearable
              dense
              outlined
              v-model="correo"
              :rules="[reglas.reglaEmail]"
              label="Ingrese su e-mail"
              type="email"
              required
              data-cy="field-email"
            />
          </v-card-actions>
        </div>
        <div class="form-group">
          <v-row align="center" justify="space-around">
            <v-btn color="tercero" to="/login">Cancelar</v-btn>
            <v-btn color="tercero" @click="recuperarContrasenia()"
              >Buscar</v-btn
            >
          </v-row>
        </div>
        <div class="form-group" v-if="message">
          <v-alert type="error" dense v-if="message">{{ message }}</v-alert>
        </div>
      </div>
    </div>
  </v-main>
</template>

<script>
import reglas from "@/common/reglas";
import recuperar from "@/services/recuperarcontraseña.service";
export default {
  data: () => ({
    reglas: reglas,
    correo: "",
    message: "",
  }),
  methods: {
    recuperarContrasenia() {
      recuperar.getcontrasenia(this.correo).then(
        (response) => {
          this.$router.push("login");
        },).catch((error) => {
          this.message = error.response.data;
        })
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
