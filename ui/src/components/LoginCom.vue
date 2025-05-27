<template>
  <v-main>
    <div class="col-md-12">
      <div class="card card-container">
        <v-row align="center" justify="space-around">
          <h3>Login</h3>
        </v-row>
        <br>
        <img
          id="profile-img"
          src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
          class="profile-img-card"
          alt=""
        />
        <v-form
          v-model="valid"
          ref="form"
          lazy-validation
          @submit.prevent="handleLogin"
        >
          <div class="form-group">
            <v-card-actions class="justify-center">
              <label for="email">Correo</label>
            </v-card-actions>
            <v-card-actions class="justify-center">
              <v-text-field
                prepend-icon="mdi-email"
                validate-on-blur
                clearable
                dense
                outlined
                v-model="user.email"
                :rules="[reglas.reglaEmail]"
                label="Ingrese su e-mail"
                type="email"
                required
                data-cy="field-email"
              />
            </v-card-actions>
          </div>
          <div class="form-group">
            <v-card-actions class="justify-center">
              <label for="password">Contraseña</label>
            </v-card-actions>
            <v-card-actions class="justify-center">
              <v-text-field
                :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                :rules="[reglas.requerimientosContraseña, reglas.min]"
                :type="show1 ? 'text' : 'password'"
                counter
                @click:append="show1 = !show1"
                prepend-icon="mdi-lock"
                dense
                outlined
                v-model="user.password"
                label="Ingrese su contraseña"
                required
                data-cy="field-password"
              />
            </v-card-actions>
          </div>
          <div class="form-group">
            <v-card-actions class="justify-center">
              <router-link to="/recuperar_contraseña"
                >Recuperar contraseña</router-link
              >
            </v-card-actions>
          </div>
          <div class="form-group">
            <v-row align="center" justify="space-around">
              <v-btn
                color="primero"
                :disabled="loading"
                :loading="loading"
                @click="handleLogin"
                >Entrar</v-btn
              >
            </v-row>
          </div>
          <div class="form-group">
            <v-alert type="error" dense v-if="message">{{ message }}</v-alert>
          </div>
        </v-form>
      </div>
    </div>
  </v-main>
</template>

<script>
import reglas from "@/common/reglas";

export default {
    name: "login",
    data: () => ({
        valid: false,
        reglas: reglas,
        user: {},
        loading: false,
        message: "",
        show1: "",
    }),
    methods: {
        validate() {
            this.message = "";
            this.loading = true;
            if (!this.$refs.form.validate()) {
                this.loading = false;
                return false;
            }
            return true;
        },
        handleLogin() {
            if (!this.validate()) return;
            this.$store.dispatch("auth/login", this.user).then(
                () => {
                    this.$router.push("/");
                },
                (error) => {
                    this.loading = false;
                    this.message = error;
                }
            );
        },
    },
    computed: {
        loggedIn() {
            return this.$store.state.auth.status.loggedIn;
        },
    },
    created() {
        if (this.loggedIn) {
            this.$router.push("/");
        }
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
