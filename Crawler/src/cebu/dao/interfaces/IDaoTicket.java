package cebu.dao.interfaces;

import java.util.ArrayList;

import cebu.model.Ticket;

/**
 * Ticket的Dao接口
 * 定义了该dao需要实现的数据库操作
 * @author Administrator
 *
 */

public interface IDaoTicket {
	
	/**
	 * 插入一个ticket
	 * @param ticket
	 * @return
	 */
	public int insert(Ticket ticket);
	
	/**
	 * 插入ticket列表
	 * @param tickets
	 * @return
	 */
	public int insert(ArrayList<Ticket> tickets);
	
	/**
	 * 查询ticket
	 * @param ticket
	 * @return 返回查询结果列表，如果只有一个，则列表中只有一个对象
	 */
	public ArrayList<Ticket> query(Ticket ticket);
	
	
	/**
	 * 更新
	 * @param ticket
	 * @return
	 */
	public int update(Ticket ticket);
	
	
}
