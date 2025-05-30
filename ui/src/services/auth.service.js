import axios from 'axios';
import { API_URL } from "@/common/config";

const AUTH_URL = API_URL;

class AuthService {
  login(user) {
    return axios
      .post(`${AUTH_URL}/api/login?username=${user.email}&password=${user.password}`)
      .then(response => {
        if (response.data.token) {
          return { username: response.data.user.username, token: response.data.token, authorities: response.data.user.authorities };

        }
        return null;
      }, error => {
        if (error.response.status === 401) {
          this.logout();
          return Promise.reject(error.response.data.mensaje);
        }
        return Promise.reject("Error desconocido");
      });
  }

  logout() {
    localStorage.removeItem('user');
  }

  register(user) {
    return axios.post(AUTH_URL + '/users', {
      username: user.username,
      email: user.email,
      password: user.password
    });
  }

}

export default new AuthService();
