import ApiService from "@/services/apiService";
const url = "/api/recuperarContrasenia";
export const service={
   getcontrasenia(slug){
       return ApiService.getnh(url, { slug:slug});
   },

};
export default service;