import request from "@/utils/request";

/*
*  使用菜单id获取此id下所有版块数据
*  @pram menuId 菜单id
* @return List<section>
* */
export const getSectionListById =(menuId)=>{
    return  request.get('/section/getMenuId/'+menuId)
}
