package test.upload.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import test.upload.model.User;
import test.upload.service.IUserService;
import test.upload.utils.ExcelUtils;

/**
 * 
 * @author duandingyang
 *
 */
@Controller
@RequestMapping("/index")
public class IndexController {
	@Autowired
	private IUserService userService;
	private static final Logger logger = LoggerFactory
			.getLogger(IndexController.class);

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
		return "user_manager";
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
	public String getUserById(@PathVariable("userId") Integer userId,
			HttpServletRequest request) {
		User user = userService.getUserById(userId);
		request.setAttribute("user", user);
		return "user_manager";
	}

	@RequestMapping(value = "/delUser/{userId}", method = RequestMethod.GET)
	public String delUserById(@PathVariable("userId") Integer userId,
			HttpServletRequest request) {
		userService.delUserById(userId);
		request.setAttribute("success", "success");
		return "user_manager";
	}

	@RequestMapping(value = "/downloadUserData", method = RequestMethod.GET)
	public void downloadUserData(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("application/x-excel");
		response.setHeader("content-disposition", "attachment;filename="
				+ "UserData.xls");
		response.setCharacterEncoding("UTF-8");
		List<User> users = userService.getUserList();
		Map<String, ExcelUtils.ExcelTitle> titleMap = this.getExcelTitleMap();
		OutputStream ouputStream = null;
		try {
			HSSFWorkbook workBook = ExcelUtils.generateExcel(User.class, users,
					titleMap);
			ouputStream = response.getOutputStream();
			workBook.write(ouputStream);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ouputStream.flush();
				ouputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private Map<String, ExcelUtils.ExcelTitle> getExcelTitleMap() {
		Map<String, ExcelUtils.ExcelTitle> titleMap = new HashMap<String, ExcelUtils.ExcelTitle>();

		ExcelUtils.ExcelTitle excelTitle0 = new ExcelUtils.ExcelTitle(0,
				String.class, "序号");
		titleMap.put("id", excelTitle0);

		ExcelUtils.ExcelTitle excelTitle1 = new ExcelUtils.ExcelTitle(1,
				String.class, "姓名");
		titleMap.put("name", excelTitle1);

		ExcelUtils.ExcelTitle excelTitle2 = new ExcelUtils.ExcelTitle(2,
				String.class, "电话");
		titleMap.put("phone", excelTitle2);

		ExcelUtils.ExcelTitle excelTitle3 = new ExcelUtils.ExcelTitle(2,
				String.class, "地址");
		titleMap.put("address", excelTitle3);

		return titleMap;
	}
}
