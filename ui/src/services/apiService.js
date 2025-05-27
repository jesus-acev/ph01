import axios from "axios";
import { API_URL } from '@/common/config.js'
var swisApi = axios.create({ baseURL: API_URL });

const apiService = {
  setHeader() {
    const user = JSON.parse(localStorage.getItem('user'));
    swisApi.defaults.headers.common["Authorization"]
      = `Bearer ${user.token}`;
  },
  post(route, { params = " ", slug = "" }) {
    this.setHeader();
    return swisApi.post(`${route}/${slug}`, params);
  },
  postnh(route, { params = " ", slug = "" }) {
    return swisApi.post(`${route}${slug}`, params);
  },
  update(route, { params = " ", slug = "" }) {
    this.setHeader();
    return swisApi.put(`${route}/${slug}`, params);
  },
  get(route, { slug = "" }) {
    this.setHeader();
    return swisApi.get(`${route}/${slug}`);
  },
  getnh(route, {slug = "" }) {
    return swisApi.get(`${route}/${slug}`);
  },
  delete(route, { slug = "" }) {
    this.setHeader();
    return swisApi.delete(`${route}/${slug}`)

  }
}
export default apiService;
