package com.jt.dubbo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.dubbo.pojo.User;
import com.jt.dubbo.service.UserService;

@RestController 
public class UserController {
	/**
	 * @Reference dubbo的注解
	 * timeout:定义连接超时时间
	 * check=消费者启动时检查是否有服务的提供者
	 * loadbalance调整负载均衡策略:
	 * 四种策略是根据实现抽象类的四个子类而来.
	 * 	1.random:随机策略  默认的情况下
	 * 	2.roundrobin:轮询策略,需要手动设置.loadbalance="roundrobin"
	 * 	3.consistenthash:iphash策略
	 * 	4.leastactive:最小访问策略
	 */
	@Reference(timeout=3000,check=true,loadbalance = "roundrobin")
	private UserService userService;
	
	@RequestMapping("/findAll")
	public List<User> findAll(){
		//感觉调用远程服务器中数据.
		return userService.findAll();
	}
	//测试新增操作, 如果名称与对象中的属性一致,则可以使用对象直接接收
	@RequestMapping("/saveUser/{name}/{age}/{sex}")
	public String saveUser(User user) {
		
		userService.saveUser(user);
		return "用户入库成功!!!";
	}
}
