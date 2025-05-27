<template>
    <v-main>
        <div class="col-md-12">
            <div class="card card-container">
                <v-row align="center" justify="space-around">
                    <h1>&#127968; Bienvenido a PerfectHost &#127968;</h1>
                </v-row>
                <br />

                <p>
                    PerfectHost es un mercado online de hospedajes, donde las
                    personas pueden:
                </p>

                <ul>
                    <li>Buscar alojamientos</li>
                    <li>Publicar alojamientos</li>
                    <li><del>Reservar alojamientos</del> (próximamente)</li>
                    <li><del>Calificar alojamientos</del> (próximamente)</li>
                </ul>

                <br />
                <p>
                    PerfectHost nace como una plataforma con el objetivo de
                    satisfacer la demanda de alojamientos necesarios debido al
                    aumento de viajes en el futuro, debido al contexto actual de
                    pandemia. Su principal objetivo es ofrecer un servicio
                    seguro y confiable.
                </p>

                <br />

                <v-row align="center" justify="space-around">
                    <h1>&#10024; ¡Descubre viviendas increíbles! &#10024;</h1>
                </v-row>

                <br />

                <!-- Imágenes de muestra -->
                <img width="50%" src="@/assets/imagen1.png" alt="Una pieza de un alojamiento, con una cama, un estante para poner libros, una ventana por la que entra la luz del sol acompañada de una mesa y una silla de escritorio."/>
                <img width="50%" src="@/assets/imagen2.png" alt="Un salón de un alojamiento con cuatro sillones de color café, cojines de múltiples colores, una mesa central pequeña y una gran ventana que cubre toda la pared y contiene una puerta."/>
                <img width="50%" src="@/assets/imagen3.png" alt="Una sala de estar de un alojamiento, con tres sillones de distintos colores, dos ventanas, una televisión encima de un mueble y una lámpara en una esquina."/>
                <img width="50%" src="@/assets/imagen4.png" alt="Una sala de estar de un alojamiento, que contiene un sillón blanco, una mesa con una lámpara, acompañada de un cuadro pintado en la pared. En la esquina también se puede ver una mesa redonda con tres sillas, un espejo y un estante para colocar libros."/>

                <br />
                <br />

                <!-- No mostrar si está logeado -->
                <div v-if="!currentUser.user">
                    <v-row align="center" justify="space-around">
                        <h1>&#9992; ¡Empieza tu viaje! &#9992;</h1>
                    </v-row>

                    <br />
                    <br />

                    <v-row align="center" justify="space-around">
                        <v-btn
                            light
                            to="/registrarse"
                            x-large
                            color="tercero"
                            block
                            >Registrate</v-btn
                        >

                        <br />

                        o

                        <br />
                        <br />

                        <v-btn light to="/login" x-large color="tercero" block
                            >Inicia sesión</v-btn
                        >
                    </v-row>
                </div>
            </div>
        </div>
    </v-main>
</template>

<script>
export default {
    name: "Principal",
    computed: {
        currentUser() {
            return this.$store.state.auth;
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
    max-width: 75% !important;
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
</style>
