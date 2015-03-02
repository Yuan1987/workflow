package com.fh.controller.system.workflow;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.entity.system.Menu;
import com.fh.entity.system.Role;
import com.fh.service.system.menu.MenuService;
import com.fh.service.system.role.RoleService;
import com.fh.util.Const;
import com.fh.util.PageData;
import com.fh.util.RightsHelper;
import com.fh.util.Tools;
/** 
 * 类名称：RoleController
 * 创建人：FH 
 * 创建时间：2014年6月30日
 * @version
 */
@Controller
@RequestMapping(value="/workflow")
public class WorkflowController extends BaseController {
	
	@Resource(name="menuService")
	private MenuService menuService;
	@Resource(name="roleService")
	private RoleService roleService;
	
	
	/**
	 * 列表
	 */
	@RequestMapping
	public ModelAndView list(HttpSession session, Page page)throws Exception{
		
			pd = this.getPageData();
			
			String roleId = pd.getString("ROLE_ID");
			System.out.println(roleId+"----------->");
			if(roleId == null || "".equals(roleId)){
				pd.put("ROLE_ID", "1");
			}
			List<Role> roleList = roleService.listAllRoles();				//列出所有部门
			List<Role> roleList_z = roleService.listAllRolesByPId(pd);		//列出此部门的所有下级
			
			List<PageData> kefuqxlist = roleService.listAllkefu(pd);		//管理权限列表
			List<PageData> gysqxlist = roleService.listAllGysQX(pd);		//用户权限列表
			
			
			pd = roleService.findObjectById(pd);							//取得点击部门
			
			mv.addObject("pd", pd);
			mv.addObject("kefuqxlist", kefuqxlist);
			mv.addObject("gysqxlist", gysqxlist);
			mv.addObject("roleList", roleList);
			mv.addObject("roleList_z", roleList_z);
			mv.setViewName("system/workflow/workflow_list");
		
		return mv;
	}
	
	/**
	 * 部署
	 */
	@RequestMapping(value="/toAdd")
	public ModelAndView toAdd(Page page){
		
		try{
			
			pd = this.getPageData();
			
			mv.setViewName("system/role/role_add");
			mv.addObject("pd", pd);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	
}
