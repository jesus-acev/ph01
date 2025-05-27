<template>
<v-container>
<v-main>
  <v-row>
    <v-col align="left">
  <v-text-field title="Titulo del Anuncio" label="Titulo del Anuncio">
</v-text-field>
    </v-col>
    <v-col></v-col>
  </v-row>
  <v-row no-gutters>
    
    <v-col cols="12"
        md="5"
        offset-md="1">
 <h2>Descripción</h2>
  <v-textarea
      name="input-descripcion"
      label="Descripción"
      auto-grow
       background-color="white"
    ></v-textarea>
    </v-col>
    <v-col cols="12"
        md="5"
        offset-md="1"
        align-self="center">
      <v-text-field title="Precio por noche" label="$">
</v-text-field>
    </v-col>
    <v-row>
      <v-col cols="12"
        md="5"
        offset-md="1"
        align-self="center">
      <v-card>
        <v-card-title>Detalles</v-card-title>
        <v-card-text>
        <v-row> <v-col>Habitaciones</v-col>
        <v-col> <v-btn @click="habitaciones_menos">-</v-btn></v-col>
        <v-col> {{habitaciones_val}}</v-col>
        <v-col><v-btn @click="habitaciones_mas">+</v-btn></v-col></v-row>
        <v-row> <v-col>Camas</v-col>
        <v-col> <v-btn>-</v-btn></v-col>
        <v-col> {{camas_val}}</v-col>
        <v-col><v-btn>+</v-btn></v-col></v-row>
        <v-row> <v-col>Baños</v-col>
        <v-col> <v-btn>-</v-btn></v-col>
        <v-col> {{baños_val}}</v-col>
        <v-col><v-btn>+</v-btn></v-col></v-row>
        <v-row> <v-col>Capacidad de Huéspedes</v-col>
        <v-col> <v-btn>-</v-btn></v-col>
        <v-col> {{huespedes_val}}</v-col>
        <v-col><v-btn>+</v-btn></v-col></v-row>
        
        </v-card-text>
      </v-card>
      </v-col>

    </v-row>
  </v-row>
  <v-row>
    <v-col>
      <v-btn  to="/verMisAnuncios"
  elevation="2" 
>Cancelar</v-btn>
    </v-col>
    <v-col align="right">
      <v-btn  to="/verMisAnuncios"
  elevation="2" 
>Crear</v-btn>
    </v-col>
  </v-row>
</v-main>
</v-container>
</template>

<script>
  export default {
    
    methods: {
     
      habitaciones_mas () {
        this.habitaciones_val++
      },
      habitaciones_menos () {
        if(this.habitaciones_val!=1)
        this.habitaciones_val--
      },
     camas_mas () {
        this.camas_val++
      },
      camas_menos () {
        if(this.camas_val!=1)
        this.camas_val--
      },
      baños_mas () {
        this.baños_val++
      },
      baños_menos () {
        if(this.baños_val!=1)
        this.baños_val--
      },
      huespedes_mas () {
        this.huespedes_val++
      },
      huespedes_menos () {
        if(this.huespedes_val!=1)
        this.huespedes_val--
      },
    },

    data () {
      return {
        habitaciones_val:1,
        camas_val:1,
        baños_val:1,
        huespedes_val:1
      }
    },
  }
</script>
<style>
</style>

