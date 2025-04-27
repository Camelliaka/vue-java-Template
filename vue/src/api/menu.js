import request from "@/utils/request";

export  const getAllMenuService = ()=>{
   return  request.get('/menu/all')
}
