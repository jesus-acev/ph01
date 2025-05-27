<template>
    <v-app id="app">
        <div :style="{ 'background-image': 'linear-gradient(#dee3cc, #7bbbab, #a29a85, #2b5458)' }">
            <cabecera />
            <router-view />
            <abajo />
        </div>
    </v-app>
</template>

<script>
import cabecera from "@/components/Header.vue";
import abajo from "@/components/Footer.vue";

export default {
    name: "App",
    data: () => ({
        //
    }),
    components: {
        cabecera,
        abajo,
    },
};
</script>
