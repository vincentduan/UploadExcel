package test.upload.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import test.upload.model.User;
import test.upload.service.IUserService;


/**
 */
@Controller
@RequestMapping("/index")
public class IndexController {
	@Autowired
	private IUserService userService;
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);


	/**
	 * 列表
	 * 
	 * @param request
	 * @return
	 * @author duandingyang
	 */
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String index(HttpServletRequest request) {
		List<User> users = userService.getUserList();
		request.setAttribute("users", users);
		return "index";
	}
	@ResponseBody
	@RequestMapping(value = "/allUsers", method = RequestMethod.POST)
	public List<User> getAllUsers(HttpServletRequest request) {
		String flag = request.getParameter("flag");
		logger.debug(flag);
		List<User> users = userService.getUserList();
		request.setAttribute("users", users);
		return users;
	}

	/**
	 * 参数绑定，查询一条数据
	 * 
	 * @param userId
	 * @param request
	 * @return
	 * @author duandingyang
	 */
	@RequestMapping(value = "/showUser/{userId}", method = RequestMethod.GET)
	public String getUserById(@PathVariable("userId") Integer userId, HttpServletRequest request) {
		User user = userService.getUserById(userId);
		request.setAttribute("user", user);
		return "index";
	}

	@RequestMapping(value = "/delUser/{userId}", method = RequestMethod.GET)
	public String delUserById(@PathVariable("userId") Integer userId, HttpServletRequest request) {
		userService.delUserById(userId);
		request.setAttribute("success", "success");
		return "index";
	}
}
