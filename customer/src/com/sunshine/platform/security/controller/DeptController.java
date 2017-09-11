/**
 * <html>
 * <body>
 *  <P> Copyright 2017 阳光康众</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 2017年7月9日</p>
 *  <p> Created by 于策/yu.ce@foxmail.com</p>
 *  </body>
 * </html>
 */
package com.sunshine.platform.security.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sunshine.common.GlobalConstant;
import com.sunshine.common.controller.BasePlatformController;
import com.sunshine.framework.common.spring.ext.SpringContextHolder;
import com.sunshine.framework.mvc.controller.RespBody;
import com.sunshine.framework.mvc.controller.RespBody.StatusEnum;
import com.sunshine.framework.mvc.mysql.service.BaseSQLService;
import com.sunshine.platform.security.entity.Dept;
import com.sunshine.platform.security.service.DeptService;

/**
 * @Project ChuFangLiuZhuan_PlatForm
 * @Package com.sunshine.platform.security.controller
 * @ClassName DeptController.java
 * @Description
 * @JDK version used 1.8
 * @Author 于策/yu.ce@foxmail.com
 * @Create Date 2017年7月9日
 * @modify By
 * @modify Date
 * @Why&What is modify
 * @Version 1.0
 */
@Controller
@RequestMapping("/platform/dept")
public class DeptController extends BasePlatformController<Dept, String> {
	private static Logger logger = LoggerFactory.getLogger(DeptController.class);
	private DeptService deptService = SpringContextHolder.getBean(DeptService.class);

	@Override
	protected BaseSQLService<Dept, String> getService() {
		// TODO Auto-generated method stub
		return deptService;
	}

	@RequestMapping(value = "/toAdd", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView toAdd(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("/platform/security/dept/editDept");
		Dept dept = new Dept();
		modelAndView.addObject("dept", dept);

		return modelAndView;
	}

	@RequestMapping(value = "/toEdit", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView toEdit(HttpServletRequest request, String id) {
		Dept dept = null;
		ModelAndView modelAndView = null;
		if (StringUtils.isNotBlank(id)) {
			dept = deptService.findById(id);
			dept.setOldName(dept.getName());
			modelAndView = new ModelAndView("/platform/security/dept/editDept");
			modelAndView.addObject("dept", dept);
		} else {
			modelAndView = new ModelAndView("/platform/security/dept/deptList");
			modelAndView.addObject("error", "找不到对应的记录");
		}
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/toDelete", method = RequestMethod.POST)
	public RespBody toDelete(HttpServletRequest request, String ids) {
		Map<String, Object> resMap = new HashMap<>();
		if (StringUtils.isNotBlank(ids)) {
			List<String> idList = Arrays.asList(ids.split(GlobalConstant.STRING_SPLIT_CHAR));
			deptService.deleteByIds(idList);
			resMap.put(GlobalConstant.MOTHED_INVOKE_RES_IS_SUCCESS, true);
			resMap.put(GlobalConstant.MOTHED_INVOKE_RES_MSG, "删除成功");
		} else {
			resMap.put(GlobalConstant.MOTHED_INVOKE_RES_IS_SUCCESS, false);
			resMap.put(GlobalConstant.MOTHED_INVOKE_RES_MSG, "删除失败");
		}

		return new RespBody(StatusEnum.OK, resMap);
	}

	@ResponseBody
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	public RespBody saveOrUpdate(Dept entity, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Map<String, Object> resMap = new HashMap<>();
		StringBuffer msg = new StringBuffer();
		boolean isUniqueName = deptService.isUniqueDeptName(entity);
		if (!isUniqueName) {
			resMap.put(GlobalConstant.MOTHED_INVOKE_RES_IS_SUCCESS, false);
			msg.append("保存部门失败，部门名称").append(entity.getName()).append("已经存在！");
			resMap.put(GlobalConstant.MOTHED_INVOKE_RES_MSG, msg.toString());
		} else {
			boolean isUniqueCode = deptService.isUniqueDeptCode(entity);
			if (!isUniqueCode) {
				resMap.put(GlobalConstant.MOTHED_INVOKE_RES_IS_SUCCESS, false);
				msg.append("保存部门失败，部门编码").append(entity.getCode()).append("已经存在！");
				resMap.put(GlobalConstant.MOTHED_INVOKE_RES_MSG, msg.toString());
			} else {
				try {
					if (StringUtils.isNotBlank(entity.getId())) {
						deptService.update(entity);
					} else {
						deptService.insert(entity);
					}
					resMap.put(GlobalConstant.MOTHED_INVOKE_RES_IS_SUCCESS, true);
					resMap.put(GlobalConstant.MOTHED_INVOKE_RES_MSG, "操作成功");
				} catch (Exception e) {
					// TODO: handle exception
					resMap.put(GlobalConstant.MOTHED_INVOKE_RES_IS_SUCCESS, false);
					resMap.put(GlobalConstant.MOTHED_INVOKE_RES_MSG, "操作失败");
					logger.error("happenTime:{},opAccount:{},event:{}", GlobalConstant.formatYYYYMMDDHHMMSS(new Date()),
							getPlatformUser(request).getAccount(), e.getMessage());
				}
			}
		}
		return new RespBody(StatusEnum.OK, resMap);
	}

	@RequestMapping(value = "/list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("/platform/security/dept/deptList");
	}

}
