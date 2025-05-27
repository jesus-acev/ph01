<template>
  <v-main>
    <div class="col-md-12">

        <div class="card card-container">
          <v-row align="center" justify="space-around">
            <img
              id="profile-img"
              src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
              class="profile-img-card"
              alt=""
          />
        </v-row>
        <div class="form-group">
          <v-row align="center" justify="space-around">
            <label>Nombre de Usuario:</label>
            <label>{{ userData.cueNombreUsuario }}</label>
          </v-row>
        </div>
        <div class="form-group">
          <v-row align="center" justify="space-around">
            <label>Correo:</label>
            <label>{{ userData.cueCorreo }}</label>
          </v-row>
        </div>
        <div class="form-group">
          <v-row align="center" justify="space-around">
            <label>Teléfono:</label>
            <label>{{ userData.cueTelefono }}</label>
          </v-row>
        </div>
        <div class="form-group">
          <v-row align="center" justify="space-around">
            <label>Rol:</label>
            <label>{{ rolLimpio }}</label>
          </v-row>
        </div>
        <div class="form-group">
          <v-row align="center" justify="space-around">
            <label>Saldo:</label>
            <label>{{ userData.saldo !== undefined && userData.saldo !== null ? userData.saldo : '-' }}</label>
          </v-row>
        </div>
        <div class="form-group">
          <v-card-actions class="justify-center">
            <v-btn color="primero" to="/">Cancelar</v-btn>
            <v-btn color="primero" to="/modificar_perfil">Modificar perfil</v-btn>
          </v-card-actions>
        </div>
      </div>
    </div>
  </v-main>
</template>

<script>
import perfil from "@/services/perfil.service";
export default {
  name: "PerfilCom",
  data: () => ({
    userData: {}
  }),
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
    rolLimpio() {
      if (!this.userData.roles || !this.userData.roles.length) return "-";
      return (this.userData.roles[0].roleName || "")
          .replace("ROLE_", "")
          .toUpperCase();
    }
  },
  methods: {
    cargarPerfil() {
      const correo = this.currentUser.username || this.currentUser.cueCorreo;
      perfil.getperfil(correo).then(response => {
        this.userData = response.data;
      });
    }
  },
  mounted() {
    this.cargarPerfil();
  }
};
</script>