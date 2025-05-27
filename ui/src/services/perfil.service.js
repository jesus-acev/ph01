import ApiService from "@/services/apiService";
const url = "/api";
export const service={
   getperfil(slug){
       return ApiService.getnh(url+"/informacionCuenta", { slug:slug});
   },
   modifcuenta(slug,params){
       return ApiService.post(url+"/modificarCuenta",{slug:slug , params:params});
   },
   getid(slug){
       return ApiService.get(url+"/idCuenta",{ slug:slug});
   },
   eliminar(slug){
       return ApiService.get(url+"/eliminarCuenta",{slug:slug})
   }
};
export default service;