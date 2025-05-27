<template>
    <v-app-bar app clipped-left color="segundo" dark>
        <!-- Icono de prueba -->
        <v-app-bar-nav-icon></v-app-bar-nav-icon>

        <!-- Botón de inicio -->
        <v-toolbar-title>
            <v-btn to="/" text elevation="0" block>
                <span class="display-1 ml-3 mr-4 text-none">PerfectHost</span>
            </v-btn>
        </v-toolbar-title>

        <v-spacer></v-spacer>

        <!-- Barra de búsqueda con lupa -->
        <v-col sm="3">
            <v-text-field
                solo
                light
                placeholder="Buscar un anuncio"
                append-icon="mdi-magnify"
                dense
            />
        </v-col>

        <v-spacer></v-spacer>

        <!-- Corazón -->
        <v-btn icon>
            <v-icon>mdi-heart</v-icon>
        </v-btn>

    <!-- Mostrar roles del usuario logeado -->
    <div v-if="currentUser && currentUser.authorities" class="mr-4 d-flex align-center">
      <v-icon small class="mr-1">mdi-account-badge</v-icon>
      <span>
                {{ currentUser.authorities.map(a => a.authority ? a.authority : a).join(', ') }}
            </span>
    </div>

    <!-- Botones de visitante -->
    <div v-if="!currentUser">
      <v-btn
          to="/registrarse"
          class="ma-2 hidden-sm-and-down"
          small
          color="segundo"
          elevation="0"
      >Registrarse</v-btn>
      <v-btn
          to="/login"
          class="ma-2 hidden-sm-and-down"
          small
          color="segundo"
          elevation="0"
      >Entrar</v-btn>
    </div>

    <!-- Botones de usuario logeado -->
    <div v-if="currentUser">
      <v-btn
          to="/perfil"
          class="ma-2 hidden-sm-and-down"
          small
          color="segundo"
      >
        <v-icon>mdi-account</v-icon>
        {{ currentUser.username }}
      </v-btn>
      <v-btn
          @click="logOut"
          class="ma-2 hidden-sm-and-down"
          small
          color="segundo"
          elevation="0"
      >
        <v-icon>mdi-logout</v-icon>Salir
      </v-btn>
    </div>

    <!-- Menú de prueba -->
    <v-menu left bottom>
      <template v-slot:activator="{ on, attrs }">
        <v-btn icon v-bind="attrs" v-on="on">
          <v-icon>mdi-dots-vertical</v-icon>
        </v-btn>
      </template>

      <v-list>
        <!-- Solo visible si el usuario tiene el rol ROLE_ANFITRION -->
        <v-list-item
            v-if="isAnfitrion"
            :to="'/vermisanuncios'"
            link
        >
          <v-list-item-title>Mis anuncios</v-list-item-title>
        </v-list-item>
        <v-list-item v-for="n in 5" :key="n" @click="() => {}">
          <v-list-item-title>Opción {{ n }}</v-list-item-title>
          <v-list-item-title>Hola</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-menu>
  </v-app-bar>
</template>

<script>
import reglas from "@/common/reglas";
export default {
  data: () => ({
    reglas: reglas,
    correo: "",
  }),
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
    isAnfitrion() {
      // Soporta formato [{authority: "ROLE_ANFITRION"}] y también ["ROLE_ANFITRION"]
      if (!this.currentUser || !this.currentUser.authorities) return false;
      if (Array.isArray(this.currentUser.authorities)) {
        return this.currentUser.authorities.some(
            a => (a.authority || a) === "ROLE_ANFITRION"
        );
      }
      if (typeof this.currentUser.authorities === "string") {
        return this.currentUser.authorities === "ROLE_ANFITRION";
      }
      return false;
    }
  },
  methods: {
    logOut() {
      this.$store.dispatch("auth/logout");
      this.$router.push("/");
    },
  },
};
</script>