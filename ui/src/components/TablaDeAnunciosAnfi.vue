<template>
  <v-container>
    <v-main>
      <v-row>
        <v-col align="right">
          <v-btn color="primero" to="/crearAnuncio" elevation="2">Crear nuevo Anuncio</v-btn>
        </v-col>
      </v-row>
      <v-row> <br></v-row>
      <v-row no-gutters>
        <v-col cols="12" md="8" offset-md="2">
          <v-alert type="info" v-if="sinAnuncios" dense>
            {{ mensajeError }}
          </v-alert>
          <v-simple-table v-else>
            <template v-slot:default>
              <thead>
              <tr>
                <th class="text-left">Id</th>
                <th class="text-left">Título</th>
                <th>Descripción</th>
                <th>Administrar</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="item in anuncios" :key="item.id">
                <td>{{ item.id }}</td>
                <td>{{ item.titulo }}</td>
                <td>{{ item.descripcion }}</td>
                <td>
                  <v-icon @click="editar(item)">mdi-pencil</v-icon>
                  <v-icon @click="ocultar(item)">mdi-eye-off</v-icon>
                  <v-icon @click="eliminar(item)">mdi-trash-can</v-icon>
                </td>
              </tr>
              </tbody>
            </template>
          </v-simple-table>
        </v-col>
      </v-row>
    </v-main>
  </v-container>
</template>

<script>
import apiService from "@/services/apiService.js";
import axios from 'axios';

export default {
  data () {
    return {
      anuncios: [],
      sinAnuncios: false,
      mensajeError: ''
    }
  },
  methods: {
    editar(item){
      // Puedes pasar el id como query param
      this.$router.push({ path: '/editarAnuncio', query: { id: item.id } });
    },
    ocultar(item) {
      confirm('¿Seguro que desea ocultar este anuncio?');
      // Aquí puedes agregar lógica para ocultar usando un endpoint si lo tienes
    },
    eliminar(item) {
      confirm('¿Seguro que desea eliminar este anuncio?');
      // Aquí puedes agregar lógica para eliminar usando un endpoint si lo tienes
    },
    obtenerUsername() {
      // Lee el username desde localStorage
      const userString = localStorage.getItem('user');
      if (!userString) return null;
      try {
        const user = JSON.parse(userString);
        return user.username;
      } catch (e) {
        return null;
      }
    },
    cargarAnuncios() {
      const username = this.obtenerUsername();
      if (!username) return;
      apiService.get('api/buscador/user', { slug: username })
          .then(response => {
            this.anuncios = response.data;
            if (this.anuncios.length === 0) {
              this.sinAnuncios = true;
              this.mensajeError = 'No hay anuncios disponibles para mostrar';
            } else {
              this.sinAnuncios = false;
              this.mensajeError = '';
            }
          })
          .catch(error => {
            if (error.response && error.response.status === 404) {
              this.sinAnuncios = true;
              this.mensajeError = 'No hay anuncios disponibles para mostrar';
              this.anuncios = [];
            } else {
              this.sinAnuncios = true;
              this.mensajeError = 'Error inesperado al cargar anuncios';
              console.error('Error al cargar anuncios', error);
              this.anuncios = [];
            }
          });
    }
  },
  mounted() {
    this.cargarAnuncios();
  }
}
</script>

<style lang="sass" scoped>
</style>