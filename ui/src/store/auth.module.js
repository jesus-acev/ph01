import AuthService from '../services/auth.service';

const user = JSON.parse(localStorage.getItem('user'));
const initialState = user
  ? { status: { loggedIn: true }, user }
  : { status: {}, user: null };

export const auth = {
  namespaced: true,
  state: initialState,
  actions: {
    login({ commit }, usuario) {
      return AuthService.login(usuario).then(
        response => {
          if (response.authorities) {
            localStorage.setItem('user', JSON.stringify(response));
            commit('loginSuccess', response);
            return Promise.resolve(response);
          }
          else {
            commit('loginFailure');
            return Promise.reject("Su cuenta no tiene permisos");
          }
        },
        error => {
          commit('loginFailure');
          return Promise.reject(error);
        }
      );
    },
    logout({ commit }) {
      AuthService.logout();
      commit('logout');
    },
    register({ commit },  usuario) {
      return AuthService.register(usuario).then(
        response => {
          commit('registerSuccess');
          return Promise.resolve(response.data);
        },
        error => {
          commit('registerFailure');
          return Promise.reject(error.response.data);
        }
      );
    }
  },
  mutations: {
    loginSuccess(state,  usuario) {
      state.status = { loggedIn: true };
      state.user =  usuario;
    },
    loginFailure(state) {
      state.status = {};
      state.user = null;
    },
    logout(state) {
      state.status = {};
      state.user = null;
    },
    registerSuccess(state) {
      state.status = {};
    },
    registerFailure(state) {
      state.status = {};
    }
  },
  getters: {
    user: (state) => { return state.user},
  }
};
