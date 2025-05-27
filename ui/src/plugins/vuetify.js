import '@mdi/font/css/materialdesignicons.css'
import Vue from 'vue';
import Vuetify from 'vuetify/lib';
import es from 'vuetify/es5/locale/es';
import * as components from 'vuetify/lib/components'
import * as directives from 'vuetify/lib/directives'

Vue.use(Vuetify);

export default new Vuetify({
  theme: {
      options: {
        customProperties: true,
      },
    themes: {
      light: {
        primero: '#0078f9',
        segundo: '#424242',
        tercero: '#90CDD2',
        claro: '#FFE0B2',
        highlight:'#65696e',
        accent: '#82B1FF',
        error: '#FF5252',
        info: '#2196F3',
        success: '#4CAF50',
        warning: '#FFC107'
      },
    },
  },
    lang: {
      locales: { es },
      current: 'es',
    },
  icons: {
    iconfont: 'mdi',
  },
  components,
  directives,
});
