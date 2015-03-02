package com.test;
import org.activiti.engine.RepositoryService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import com.test.UserBean;

@ContextConfiguration("spring/ApplicationContext.xml")
public class Test {

    //本流程模拟的是某公司2012年实习生招聘流程
    //张三来参加该招聘流程，公司相关负责人负责处理流程
    public static void main(String[] args) {
        //加载配置文件
    	ClassPathXmlApplicationContext applicationContext =
    		    new ClassPathXmlApplicationContext("spring/ApplicationContext.xml");
    	
    	/*RepositoryService repositoryService = (RepositoryService) applicationContext.getBean("repositoryService");
        repositoryService.createDeployment()
            .addClasspathResource("helloProcess.bpmn")
            .deploy();*/
        
        UserBean userBean = (UserBean) applicationContext.getBean("userBean");
        userBean.hello();
    }
}