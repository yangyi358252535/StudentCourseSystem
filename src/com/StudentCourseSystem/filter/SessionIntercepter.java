package com.StudentCourseSystem.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class SessionIntercepter extends AbstractInterceptor {
	private static final long serialVersionUID = -450173549155049245L;
	private Logger logger = Logger.getLogger(SessionIntercepter.class);

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String className = invocation.getAction().getClass().getName();
		String action = className.substring(className.lastIndexOf(".") + 1,
				className.length());
		String actionName = invocation.getProxy().getActionName();
		String result;
		String url = ServletActionContext.getRequest().getRequestURL().toString();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String type = request.getHeader("X-Requested-With");
		Object user =  ActionContext.getContext().getSession()
				.get("currentUser");
		if (user == null) {
			if(url.indexOf("toLogin.action")!=-1
					||url.contains("/OnlineAppointmentRegistration/frontPage/")){
				return invocation.invoke(); 
			}else{
				if ("XMLHttpRequest".equalsIgnoreCase(type)) {// AJAX REQUEST
					// PROCESS
					response.setHeader("sessionstatus", "timeout");
					result = null;
				} else {// NORMAL REQUEST PROCESS
					if (url.indexOf("logout.action")!=-1){   
						return invocation.invoke();   
					} 
					result = ActionSupport.LOGIN;
				}
			}
			logger.debug("SECURITY CHECKED: NEED TO LOGIN");
		} else {
			logger.debug("SECURITY CHECKED: USER HAS LOGINED");
//			boolean hanPerm = SecurityContextUtil.hasPerm(action, actionName);
			logger.debug("SECURITY CHECKED: PERMISSION---" + action + "."
					+ actionName);
			result = invocation.invoke();
		}
		return result;
	}
}
