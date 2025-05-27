import ApiService from "@/services/apiService";
const url = "/api/registrarse";
export const service={
   create(direccion,params){
       return ApiService.postnh(url+direccion, { params : params });
   },

};
export default service;