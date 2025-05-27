<template>
  <v-container>
    <v-main>
      <v-row>
        <v-col align="left">
          <v-text-field
              title="Titulo del Anuncio"
              label="Titulo del Anuncio"
              v-model="titulo"
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row no-gutters>
        <v-col cols="12" md="5" offset-md="1">
          <h2>Descripción</h2>
          <v-textarea
              name="input-descripcion"
              label="Descripción"
              auto-grow
              background-color="white"
              v-model="descripcion"
          ></v-textarea>
        </v-col>
        <v-col cols="12" md="5" offset-md="1" align-self="center">
          <v-text-field
              title="Precio por noche"
              label="$"
              v-model="precioPorNoche"
              type="number"
          ></v-text-field>
        </v-col>
        <v-row>
          <v-col cols="12" md="5" offset-md="1" align-self="center">
            <v-card>
              <v-card-title>Detalles</v-card-title>
              <v-card-text>
                <v-row>
                  <v-col>Habitaciones</v-col>
                  <v-col><v-btn @click="habitaciones_menos">-</v-btn></v-col>
                  <v-col>{{ piezas }}</v-col>
                  <v-col><v-btn @click="habitaciones_mas">+</v-btn></v-col>
                </v-row>
                <v-row>
                  <v-col>Camas</v-col>
                  <v-col><v-btn @click="camas_menos">-</v-btn></v-col>
                  <v-col>{{ camas }}</v-col>
                  <v-col><v-btn @click="camas_mas">+</v-btn></v-col>
                </v-row>
                <v-row>
                  <v-col>Baños</v-col>
                  <v-col><v-btn @click="banios_menos">-</v-btn></v-col>
                  <v-col>{{ banios }}</v-col>
                  <v-col><v-btn @click="banios_mas">+</v-btn></v-col>
                </v-row>
                <v-row>
                  <v-col>Capacidad de Huéspedes</v-col>
                  <v-col><v-btn @click="huespedes_menos">-</v-btn></v-col>
                  <v-col>{{ capacidadHuespedes }}</v-col>
                  <v-col><v-btn @click="huespedes_mas">+</v-btn></v-col>
                </v-row>
              </v-card-text>
            </v-card>
          </v-col>
          <v-col>
            <v-text-field
                title="Ubicación"
                label="Ubicación"
                prepend-icon="mdi-map-marker"
                v-model="coordenadas"
            ></v-text-field>
          </v-col>
        </v-row>
      </v-row>
      <v-row>
        <v-col>
          <v-btn to="/verMisAnuncios" elevation="2">Cancelar</v-btn>
        </v-col>
        <v-col align="right">
          <v-btn elevation="2" @click="crearAnuncio">Crear</v-btn>
        </v-col>
      </v-row>
    </v-main>
  </v-container>
</template>

<script>
import apiService from "@/services/apiService.js";

export default {
  data() {
    return {
      titulo: "",
      precioPorNoche: 0,
      descripcion: "",
      camas: 1,
      capacidadHuespedes: 1,
      banios: 1,
      piezas: 1,
      coordenadas: ""
    };
  },
  methods: {
    habitaciones_mas() { this.piezas++; },
    habitaciones_menos() { if (this.piezas > 1) this.piezas--; },
    camas_mas() { this.camas++; },
    camas_menos() { if (this.camas > 1) this.camas--; },
    banios_mas() { this.banios++; },
    banios_menos() { if (this.banios > 1) this.banios--; },
    huespedes_mas() { this.capacidadHuespedes++; },
    huespedes_menos() { if (this.capacidadHuespedes > 1) this.capacidadHuespedes--; },
    obtenerCorreo() {
      const userString = localStorage.getItem('user');
      if (!userString) return "";
      try {
        const user = JSON.parse(userString);
        return user.username;
      } catch (e) {
        return "";
      }
    },
    async crearAnuncio() {
      const correo = this.obtenerCorreo();
      if (!correo) {
        alert("Debes iniciar sesión para crear un anuncio.");
        return;
      }
      const anuncio = {
        correo,
        titulo: this.titulo,
        precioPorNoche: Number(this.precioPorNoche),
        descripcion: this.descripcion,
        camas: this.camas,
        capacidadHuespedes: this.capacidadHuespedes,
        banios: this.banios,
        piezas: this.piezas,
        coordenadas: this.coordenadas
      };
      try {
        // El slug puede ir vacío ya que tu backend no lo usa
        const response = await apiService.post("api/anuncios/crear/guardarFormulario", { params: anuncio, slug: "" });
        if (response.data === true) {
          alert("¡Anuncio creado con éxito!");
          this.$router.push("/verMisAnuncios");
        } else {
          alert("No se pudo crear el anuncio. Verifica los datos.");
        }
      } catch (error) {
        alert("Error en el servidor: " + error);
      }
    }
  }
};
</script>