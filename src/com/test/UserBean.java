package com.test;

import java.util.List;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.transaction.annotation.Transactional;

public class UserBean {
	  /** 由Spring注入 */
	  private RuntimeService runtimeService;
	  private TaskService taskService;

	  

	@Transactional
	  public void hello() {
	        //这里，你可以在你们的领域模型中做一些事物处理。
	        //当在调用Activiti RuntimeService的startProcessInstanceByKey方法时，
	        //它将会结合到同一个事物中。
		  runtimeService.startProcessInstanceByKey("Interview").getId();
		 // Task task = taskService.createTaskQuery().singleResult();
		  //System.out.println("---------->"+task.getName());
		  //taskService.complete(task.getId());
		  
		 TaskQuery tq= taskService.createTaskQuery();
		 
		 List<Task> ta=tq.list();
		 
		 for (Task ta1 :ta){
			 	System.out.println("...getOwner>"+ta1.getOwner());
			 	System.out.println("...getAssignee>"+ta1.getAssignee());
			 	System.out.println("...getExecutionId>"+ta1.getExecutionId());
		 }
		  
		    List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("开发组").list(); 
		    
		    System.out.println(tasks.size());
		    for (Task task : tasks) {  
		        System.out.println("开发组的任务：name:"+task.getName()+",id:"+task.getId());  
		        taskService.claim(task.getId(), "张三");  
		    }  
		  
		  
	  }
        
	
	  public void setRuntimeService(RuntimeService runtimeService) {
	    this.runtimeService = runtimeService;
	  }
	  
	  public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	  }
}
