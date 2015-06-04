package com.StudentCourseSystem.tool;

import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author yangyi
 * @date 2013-2-6 下午11:37:00
 */
public abstract class BaseProcessAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 5071556207291554499L;

	private Map<String,Object> session=null;
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}
	public Map<String,Object> getSession(){
		return this.session;
	}
	
	protected void clearSession(){
		if(session.containsKey(SystemConstant.IDS_COLLECTION)){
			this.session.remove(SystemConstant.IDS_COLLECTION);
		}
	}
	protected String clearSessionForSearch(){
		return null;
	}
	protected String checkUsing() {
		return null;
	}
	protected String modifyVersion(){
		return null;
	}
	protected Object getCurrentUser(){
		return session.get(SystemConstant.CURRENTUSER);
	}
	protected void setCurrentUser(Object user){
		session.put(SystemConstant.CURRENTUSER,user);
	}
	public abstract String getAllIds();
	public abstract void setAllIds(String allIds);
}
