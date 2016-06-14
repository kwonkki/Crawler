package cebu.dao.service;

import java.util.ArrayList;

import cebu.dao.factory.DaoFactoryTicket;
import cebu.dao.interfaces.IDaoTicket;
import cebu.model.Ticket;

/**
 * Ticket数据库相关的服务层
 * 使用该层是为了使功能和具体的dao层实现分离
 * 如果更换了dao层实现（如使用spring，hibernate），只需要更改daoconfig.properties
 * 不需要更改其他service/impl/factory的代码
 * 
 * 直接调用静态方法实现数据库相关操作
 * 
 * @author Administrator
 *
 */

public class TicketService {
	
	// 从daoFactory获取dao的实现
	private static IDaoTicket daoTicket = DaoFactoryTicket.getDao(); 
	
	/**
	 * 插入一个ticket
	 * @param ticket
	 * @return
	 */
	public static  int insert(Ticket ticket) {
		return daoTicket.insert(ticket);
	}
	
	/**
	 * 插入ticket列表
	 * @param tickets
	 * @return
	 */
	public static  int insert(ArrayList<Ticket> tickets) {
		return daoTicket.insert(tickets);
	}
	
	/**
	 * 查询ticket
	 * @param ticket
	 * @return 返回查询结果列表，如果只有一个，则列表中只有一个对象
	 */
	public static  ArrayList<Ticket> query(Ticket ticket) {
		return daoTicket.query(ticket);
	}
	
	/**
	 * 更新
	 * @param ticket
	 * @return
	 */
	public static  int update(Ticket ticket) {
		return daoTicket.update(ticket);
	}
	
}
