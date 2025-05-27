import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);
export const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component:  () => import('./views/PaginaPrincipal.vue')
    },
    {
      path: '/login',
      name: 'login',
      component:  () => import('./views/Login.vue')
    },
    {
      path: '/registrarse',
      name: 'registrarse',
      component:  () => import('./views/Registrarse.vue')
    },
    {
      path: '/recuperar_contraseña',
      name: 'recuperar contraseña',
      component:  () => import('./views/RecuperarContraseña.vue')
    },
    {
      path: '/perfil',
      name: 'perfil',
      component:  () => import('./views/Perfil.vue')
    },
    {
      path: '/modificar_perfil',
      name: 'modificar perfil',
      component:  () => import('./views/PerfilMod.vue')
    },

    {
      path: '/verMisAnuncios',
      name: 'ver mis Anuncios',
      component:  () => import('./views/VerMisAnuncios.vue')
    },

    {
      path: '/editarAnuncio',
      name: 'Editar Anuncio',
      component:  () => import('./views/EditarAnuncio.vue')
    },

    {
      path: '/crearAnuncio',
      name: 'Crear Anuncio',
      component:  () => import('./views/CrearAnuncio.vue')
    },
  ]
});
export default router