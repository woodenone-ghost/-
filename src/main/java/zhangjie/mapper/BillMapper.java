package zhangjie.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import zhangjie.entity.BarChart;
import zhangjie.entity.LineChart;
import zhangjie.entity.PieGraph;
import zhangjie.model.Bill;
import zhangjie.model.BillExample;

public interface BillMapper {
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table bill
	 *
	 * @mbg.generated Sat Apr 27 14:50:54 CST 2019
	 */
	long countByExample(BillExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table bill
	 *
	 * @mbg.generated Sat Apr 27 14:50:54 CST 2019
	 */
	int deleteByExample(BillExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table bill
	 *
	 * @mbg.generated Sat Apr 27 14:50:54 CST 2019
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table bill
	 *
	 * @mbg.generated Sat Apr 27 14:50:54 CST 2019
	 */
	int insert(Bill record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table bill
	 *
	 * @mbg.generated Sat Apr 27 14:50:54 CST 2019
	 */
	int insertSelective(Bill record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table bill
	 *
	 * @mbg.generated Sat Apr 27 14:50:54 CST 2019
	 */
	List<Bill> selectByExample(BillExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table bill
	 *
	 * @mbg.generated Sat Apr 27 14:50:54 CST 2019
	 */
	Bill selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table bill
	 *
	 * @mbg.generated Sat Apr 27 14:50:54 CST 2019
	 */
	int updateByExampleSelective(@Param("record") Bill record, @Param("example") BillExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table bill
	 *
	 * @mbg.generated Sat Apr 27 14:50:54 CST 2019
	 */
	int updateByExample(@Param("record") Bill record, @Param("example") BillExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table bill
	 *
	 * @mbg.generated Sat Apr 27 14:50:54 CST 2019
	 */
	int updateByPrimaryKeySelective(Bill record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to
	 * the database table bill
	 *
	 * @mbg.generated Sat Apr 27 14:50:54 CST 2019
	 */
	int updateByPrimaryKey(Bill record);

	List<LineChart> selectDateAndSalesVolumeById(Integer idCommdity);

	List<LineChart> selectGoodEvaluation(Integer idCommdity);

	List<LineChart> selectBadEvaluation(Integer idCommdity);

	List<PieGraph> selectSex(Integer idCommdity);

	List<PieGraph> selectAge(Integer idCommdity);

	List<PieGraph> selectEvaluation(Integer idCommdity);

	List<BarChart> selectAgeForBarChart(Integer idCommdity);
}