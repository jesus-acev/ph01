import ApiService from "@/services/api.service";
const url = "/api/anuncios/crear/guardar";
export const service={
   create(direccion,params){
       return ApiService.postnh(url, { params : params });
   },

};
export default service;