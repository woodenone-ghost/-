package zhangjie.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import zhangjie.model.Bill;
import zhangjie.model.BillExample;

public interface BillMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill
     *
     * @mbg.generated Wed Apr 17 10:37:38 CST 2019
     */
    long countByExample(BillExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill
     *
     * @mbg.generated Wed Apr 17 10:37:38 CST 2019
     */
    int deleteByExample(BillExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill
     *
     * @mbg.generated Wed Apr 17 10:37:38 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill
     *
     * @mbg.generated Wed Apr 17 10:37:38 CST 2019
     */
    int insert(Bill record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill
     *
     * @mbg.generated Wed Apr 17 10:37:38 CST 2019
     */
    int insertSelective(Bill record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill
     *
     * @mbg.generated Wed Apr 17 10:37:38 CST 2019
     */
    List<Bill> selectByExample(BillExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill
     *
     * @mbg.generated Wed Apr 17 10:37:38 CST 2019
     */
    Bill selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill
     *
     * @mbg.generated Wed Apr 17 10:37:38 CST 2019
     */
    int updateByExampleSelective(@Param("record") Bill record, @Param("example") BillExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill
     *
     * @mbg.generated Wed Apr 17 10:37:38 CST 2019
     */
    int updateByExample(@Param("record") Bill record, @Param("example") BillExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill
     *
     * @mbg.generated Wed Apr 17 10:37:38 CST 2019
     */
    int updateByPrimaryKeySelective(Bill record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill
     *
     * @mbg.generated Wed Apr 17 10:37:38 CST 2019
     */
    int updateByPrimaryKey(Bill record);
}