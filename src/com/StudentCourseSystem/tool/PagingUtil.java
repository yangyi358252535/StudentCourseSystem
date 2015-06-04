package com.StudentCourseSystem.tool;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.json.annotations.JSON;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.StudentCourseSystem.bean.TMaster;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author yangyi
 * @Description 分页的共通工具类
 * @date 2013-1-28 下午8:41:49
 */
public abstract class PagingUtil<E> extends ActionSupport implements Paging,SessionAware {

	private static final long serialVersionUID = 7283490467335228263L;
	private static final Logger logger = Logger.getLogger(PagingUtil.class);
	private HibernateTemplate hibernateTemplate = null;
	//数据集合
	protected List<E> DATALIST = null;
	// 是否查询分页
	private boolean isUsePagetion=false;
	//共有多少条结果
	private long resultCount=0;
	//跳转到的页数
	private int toPageAmount=0;
	//每页数据的显示条数
	private int pageableAmount=0;
	//当前的页数
	private int currentPage=1;
	//共有的页数
	private int totalPageAmount=0;
	
	/**
	 * 
	 */
	private int pageLs=1; //左侧开始最大页码号
	private int pageRs=1; //右侧开始最大页码号 
	private int pageRows=8; //调用时可自定义变量，页码组，每组显示多少个页码编号，8默认，实际显示数会自动+1，包含当前页码
	//是否用二级缓存
	private  boolean isUseCache=true;
	//查询数据语句的字符串缓冲对象
	private StringBuffer sqlBuffer = new StringBuffer();
	//查询数据数量语句的字符串缓冲对象
	private StringBuffer sqlAmountBuffer = new StringBuffer();
	//参数集合
	protected List<Object> queryParameters=new ArrayList<Object>();
	//Session的引用
	private Map<String,Object> session=null;
	//设置排序的语句
	private String orderSql=null;
	//前台传到后台的ID集合字符串
	private String stringIdArray=null;
	
	//Jquery Easy Ui 查询用到
	private int page=1;
	private int rows=10;
	//Jquery Easy Ui 查询用到
	//private Ma
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	protected void search() {
		//查询数据
		searchDate();
		getAllPagesAmount();
	}

	protected void setSQL(String sql) {
		sqlBuffer.append(sql);
		sqlAmountBuffer.append(sql);
	}
	private String toExecuteSQL() {
		if(orderSql!=null){
			sqlBuffer.append(orderSql);
		}
		return sqlBuffer.toString();
	}

	private String toExecuteAmountSQL() {
		
		return this.sqlAmountBuffer.toString();
	}

	protected void setClass(Class<?> clazz, String nameAs) {
		//this.clazz=clazz;
		sqlBuffer.append("from " + clazz.getName() + " " + nameAs + " "
				+ " WHERE 1=1 ");
		sqlAmountBuffer.append("select count(*) from " + clazz.getName()
				+ " as " + nameAs + " WHERE 1=1 ");
	}
	@SuppressWarnings("rawtypes")
	protected void getAllPagesAmount() {
		final String sql = toExecuteAmountSQL();
		if(resultCount==0){
			List list = hibernateTemplate
					.executeFind(new HibernateCallback<List>() {
						@Override
						public List doInHibernate(
								Session session) throws HibernateException,
								SQLException {
							Query query = session.createQuery(sql);
							if(queryParameters.size()>0){
								query=setQueryParamters(query);
							}
							query.setCacheable(isUseCache);
							return query.list();
						}
					});
			if ((Long)list.get(0)>0) {
				this.resultCount = (Long) list.get(0);
			} else {
				this.resultCount = 0;
			}
			//通过总数和每页的数量计算共有多少页
			if (resultCount % pageableAmount != 0) {
				this.totalPageAmount= ((int)resultCount / pageableAmount + 1);
			} else {
				this.totalPageAmount=((int)resultCount / pageableAmount);
			}
		}
	}

	@SuppressWarnings("unchecked")
	protected void searchDate() {
		final String sql = toExecuteSQL();
		logger.info("生成的分页查询语句为：" + sql);
		 try {
		List<E> list = hibernateTemplate
				.executeFind(new HibernateCallback<List<E>>() {
					@Override
					public List<E> doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(sql);
						query.setFirstResult((currentPage - 1) * pageableAmount);//
						query.setMaxResults(pageableAmount);
						query.setCacheable(isUseCache);
						if(queryParameters.size()>0){
							query=setQueryParamters(query);
						}
						return query.list();
					}
				});
		this.DATALIST = list;
		 } catch (RuntimeException re) {
			 logger.error("find ListForPage failed", re);
	            throw re;
	        }
	}
	private Query setQueryParamters(Query query) {
		for(int index=0;index<queryParameters.size();index++){
			logger.info("queryParameters:"+queryParameters.get(index));
			query.setParameter(index, queryParameters.get(index));
		}
		return query;
	}
	@Override
	public void first() {
		currentPage=1;
	}

	@Override
	public void last() {
		currentPage=totalPageAmount;
	}

	@Override
	public void next() {
		currentPage++;
	}

	@Override
	public void page() {
		currentPage=toPageAmount;
	}

	@Override
	public void previous() {
		currentPage--;
	}
	
	@Override
	public boolean isFirstAvailiable() {
		if(currentPage>1){
			return true;
		}else{
			return false;
		}
		
	}
	@JSON(serialize=false)
	public String getOrderSql() {
		return orderSql;
	}
	public void setOrderSql(String orderSql) {
		this.orderSql = orderSql;
	}
	@Override
	public boolean isLastAvailiable() {
		if(currentPage!=totalPageAmount&&totalPageAmount!=0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean isNextAvailiable() {
		if(currentPage<totalPageAmount){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean isPreviousAvailiable() {
		if(currentPage>1){
			return true;
		}else{
			return false;
		}
	}
	/**
	* @CreateDate 2013-2-6 下午10:34:20 
	* @Description 更新session的ID集合 
	* @param   
	* @return void
	 */
	@SuppressWarnings("unchecked")
	protected void updateIdCollectionInSession(){
		logger.info("---------------进入更新Session中ID的方法--------------");
		Map<Integer, String> pageMap=(Map<Integer, String>) session.get(SystemConstant.IDS_COLLECTION);
		if(pageMap==null){
			pageMap=new HashMap<Integer, String>();
			pageMap.put(currentPage,getStringIdArray());
		}else{
			pageMap.put(currentPage,getStringIdArray());
		}
		for(Integer k:pageMap.keySet()){//TODO
			logger.info("------------------------上传的第"+k+"页的元素有："+pageMap.get(k));
		}
		session.put(SystemConstant.IDS_COLLECTION,pageMap);
		logger.info("---------------结束更新Session中ID的方法--------------");
	}
	/**
	* @CreateDate 2013-2-6 下午11:25:35 
	* @Description 取得session中的ID集合
	* @param @return  
	* @return String
	 */
	@SuppressWarnings("unchecked")
	protected String getIdCollectionFromSession(){
		logger.info("---------------进入取得Session中ID的方法--------------");
		Map<Integer, String> map=(Map<Integer, String>) session.get(SystemConstant.IDS_COLLECTION);
		if(map!=null){
			logger.info("------------------------取得的第"+currentPage+"页的元素有："+map.get(currentPage));
			return map.get(currentPage);
		}
		logger.info("---------------取得Map的-----"+map);
		logger.info("---------------结束取得Session中ID的方法--------------");
		return null;
	}
	/**
	* @CreateDate 2013-2-10 下午8:48:18 
	* @Description 取得session中所有的id并返回字符串
	* @param @return  
	* @return String id字符串
	 */
	@SuppressWarnings("unchecked")
	protected String getAllIdsFromSession(){
		logger.info("---------------进入取得所有Session中ID的方法--------------");
		Map<Integer, String> map=(Map<Integer, String>) session.get(SystemConstant.IDS_COLLECTION);
		String allIds=null;
		if(map!=null){
			for(Integer index:map.keySet()){
				String ids=map.get(index);
				if(ids!=null&&ids.length()!=0){
					if(allIds==null){
						allIds=ids;
					}else{
						allIds+=",";
						allIds+=ids;
					}
				}
				logger.info("---------------取得Map的IDS-----"+allIds);
			}
			return allIds;
		}
		return null;
	}
	@JSON(serialize=false)
	public int getTotalPageAmount() {
		return totalPageAmount;
	}

	public void setTotalPageAmount(int totalPageAmount) {
		this.totalPageAmount = totalPageAmount;
	}

	public List<E> getDATALIST() {
		return DATALIST;
	}
	@JSON(serialize=false)
	public void setDATALIST(List<E> dATALIST) {
		DATALIST = dATALIST;
	}
	@JSON(serialize=false)
	protected boolean isUseCache() {
		return isUseCache;
	}

	protected void setUseCache(boolean isUseCache) {
		this.isUseCache = isUseCache;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}
	public Map<String, Object> getSession(){
		return this.session;
	}
	public void clearSession(){
		if(session.containsKey(SystemConstant.IDS_COLLECTION)){
			logger.info("SystemConstant.IDS_COLLECTION");
			this.session.remove(SystemConstant.IDS_COLLECTION);
		}
	}
	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	@JSON(serialize=false)
	protected int getPageableAmount() {
		return pageableAmount;
	}

	protected void setPageableAmount(int pageableAmount) {
		this.pageableAmount = pageableAmount;
	}
	@JSON(serialize=false)
	public int getToPageAmount() {
		return toPageAmount;
	}

	public void setToPageAmount(int toPageAmount) {
		this.toPageAmount = toPageAmount;
	}
	@JSON(serialize=false)
	public long getResultCount() {
		return resultCount;
	}

	public void setResultCount(long resultCount) {
		this.resultCount = resultCount;
	}
	@JSON(serialize=false)
	protected boolean isUsePagetion() {
		return isUsePagetion;
	}

	protected void setUsePagetion(boolean isUsePagetion) {
		this.isUsePagetion = isUsePagetion;
	}
	@JSON(serialize=false)
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public String getStringIdArray() {
		logger.info("stringIdArray："+stringIdArray);
		return SystemUtil.clearFirstAndEndComma(stringIdArray);
	}
	public void setStringIdArray(String stringIdArray) {
		this.stringIdArray = SystemUtil.clearFirstAndEndComma(stringIdArray);
		logger.info("stringIdArray："+this.stringIdArray);
	}
	/**
	* @Description 分页要实现的Action方法
	*  toNext :负责跳转到下一页
	*  toPrevious ：负责跳转到上一页
	*  toLast ：负责跳转到最后一页
	*  toFirst : 负责跳转到第一页
	*  toPage : 负责跳转到相应的页数
	 */
	public abstract String toNext();
	public abstract String toPrevious();
	public abstract String toLast();
	public abstract String toFirst();
	public abstract String toPage();
	public abstract String toReload();
	public String uploadIdCollection(){
		return null;
	}
	public String loadIdCollection(){
		return null;
	};
	public String loadAllIdCollection() {
		return null;
	}
	public abstract String getCurrentPageIds();
	public abstract void setCurrentPageIds(String currentPageIds);
	public abstract String getCurrentAllIds();
	public abstract void setCurrentAllIds(String currentAllIds);
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getPageLs() {
		return pageLs;
	}
	public void setPageLs(int pageLs) {
		this.pageLs = pageLs;
	}
	public int getPageRs() {
		return pageRs;
	}
	public void setPageRs(int pageRs) {
		this.pageRs = pageRs;
	}
	public int getPageRows() {
		return pageRows;
	}
	public void setPageRows(int pageRows) {
		this.pageRows = pageRows;
	}
	@SuppressWarnings("unchecked")
	public List<TMaster> getMasterListByCode(final String code){
		List<TMaster> list = hibernateTemplate
				.executeFind(new HibernateCallback<List<TMaster>>() {
					public List<TMaster> doInHibernate(
							Session session) throws HibernateException,
							SQLException {
						Query query = session
								.createQuery("from TMaster t where t.code='"+code+"'");
						List<TMaster> list1 = query.list();
						return list1;
					}
				});
		return list;
	}
	@SuppressWarnings("unchecked")
	public TMaster getMasterByName(String name){
		List<TMaster> list=hibernateTemplate.find("from TMaster t where t.mastername='"+name+"'");
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public TMaster getMasterById(long id){
		List<TMaster> list=hibernateTemplate.find("from TMaster t where t.masterid="+id);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
}
