package zhangjie.dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import zhangjie.entity.BarChart;
import zhangjie.entity.LineChart;
import zhangjie.entity.PieGraph;
import zhangjie.mapper.BillMapper;
import zhangjie.model.Bill;
import zhangjie.model.BillExample;
import zhangjie.model.BillExample.Criteria;
import zhangjie.util.AssertUtil;

@Repository("billDAO")
public class BillDAO extends BaseDAO<Bill, BillExample, BillMapper> {

	/**
	 * 增加一条记录
	 * 
	 * @param entity
	 */
	public void add(Bill entity) {
		AssertUtil.argIsNotNull(entity, "entity is null");
		AssertUtil.argIsNotNull(entity.getAccountBuyer(), "accountBuyer is null");
		AssertUtil.argIsNotNull(entity.getIdCommodity(), "idCommodity is null");
		AssertUtil.argIsNotNull(entity.getAccountSeller(), "accountSeller is null");
		AssertUtil.argIsNotNull(entity.getQuantity(), "quantity is null");
		AssertUtil.strIsNotBlank(entity.getPrice(), "price is null");
		AssertUtil.argIsNotNull(entity.getTime(), "time is null");
		AssertUtil.strIsNotBlank(entity.getState(), "state is null");
		entity.setEvaluation(" ");
		entity.setEvaluationPrice(" ");
		entity.setEvaluationService(" ");
		entity.setEvaluationWords(" ");
		this.getMapper().insert(entity);
	}

	/**
	 * 删除一条记录
	 * 
	 * @param idBuyer
	 */
	public void delete(Integer id) {
		AssertUtil.argIsNotNull(id, "id is null");
		this.getMapper().deleteByPrimaryKey(id);
	}

	/**
	 * 更新一条记录
	 * 
	 * @param entity
	 */
	public void update(Bill entity) {
		AssertUtil.argIsNotNull(entity, "entity is null");
		AssertUtil.argIsNotNull(entity.getId(), "id is null");
		AssertUtil.argIsNotNull(entity.getAccountBuyer(), "accountBuyer is null");
		AssertUtil.argIsNotNull(entity.getIdCommodity(), "idCommodity is null");
		AssertUtil.argIsNotNull(entity.getAccountSeller(), "accountSeller is null");
		AssertUtil.argIsNotNull(entity.getQuantity(), "quantity is null");
		AssertUtil.strIsNotBlank(entity.getPrice(), "price is null");
		AssertUtil.argIsNotNull(entity.getTime(), "time is null");
		AssertUtil.strIsNotBlank(entity.getState(), "state is null");
		this.getMapper().updateByPrimaryKeySelective(entity);
	}

	/**
	 * 根据主键选择一条记录
	 * 
	 * @param idBuyer
	 * @return
	 */
	public Bill selectByPrimaryKey(Integer id) {
		AssertUtil.argIsNotNull(id, "id is null");
		return this.getMapper().selectByPrimaryKey(id);
	}

	public List<LineChart> selectDateAndSalesVolumeById(Integer idCommodity) {
		AssertUtil.argIsNotNull(idCommodity, "idCommodity is null");
		return this.getMapper().selectDateAndSalesVolumeById(idCommodity);
	}

	public List<LineChart> selectGoodEvaluation(Integer idCommdity) {
		AssertUtil.argIsNotNull(idCommdity, "idCommodity is null");
		return this.getMapper().selectGoodEvaluation(idCommdity);
	}

	public List<LineChart> selectBadEvaluation(Integer idCommdity) {
		AssertUtil.argIsNotNull(idCommdity, "idCommodity is null");
		return this.getMapper().selectBadEvaluation(idCommdity);
	}

	public List<PieGraph> selectSex(Integer idCommdity) {
		AssertUtil.argIsNotNull(idCommdity, "idCommodity is null");
		return this.getMapper().selectSex(idCommdity);
	}

	public List<PieGraph> selectAge(Integer idCommdity) {
		AssertUtil.argIsNotNull(idCommdity, "idCommodity is null");
		return this.getMapper().selectAge(idCommdity);
	}

	public List<PieGraph> selectEvaluation(Integer idCommdity) {
		AssertUtil.argIsNotNull(idCommdity, "idCommodity is null");
		return this.getMapper().selectEvaluation(idCommdity);
	}

	public List<BarChart> selectAgeForBarChart(Integer idCommdity) {
		AssertUtil.argIsNotNull(idCommdity, "idCommodity is null");
		return this.getMapper().selectAgeForBarChart(idCommdity);
	}

	public List<BarChart> selectLastMonthSalesVolume(Integer idCommdity, LocalDate firstDay, LocalDate finalDay) {
		AssertUtil.argIsNotNull(idCommdity, "idCommodity is null");
		AssertUtil.argIsNotNull(firstDay, "firstDay is null");
		AssertUtil.argIsNotNull(finalDay, "finalDay is null");
		return this.getMapper().selectLastMonthSalesVolume(idCommdity, firstDay, finalDay);
	}

	@Override
	protected BillMapper getMapper() {
		// TODO Auto-generated method stub
		return super.findMapper(BillMapper.class);
	}

	@Override
	/**
	 * 构筑搜索的条件
	 */
	protected BillExample buildExample(Map<String, String> qryParams) {
		// TODO Auto-generated method stub
		BillExample example = new BillExample();
		Criteria criteria = example.createCriteria();
		String id = qryParams.get("id");
		if (StringUtils.isNotBlank(id)) {
			criteria.andIdEqualTo(Integer.valueOf(id));
		}
		String accountBuyer = qryParams.get("accountBuyer");
		if (StringUtils.isNotBlank(accountBuyer)) {
			criteria.andAccountBuyerLike("%" + accountBuyer + "%");
		}
		String idCommodity = qryParams.get("idCommodity");
		if (StringUtils.isNotBlank(idCommodity)) {
			criteria.andIdCommodityEqualTo(Integer.valueOf(idCommodity));
		}
		String accountSeller = qryParams.get("accountSeller");
		if (StringUtils.isNotBlank(accountSeller)) {
			criteria.andAccountSellerLike("%" + accountSeller + "%");
		}
		String accountSellerZ = qryParams.get("accountSellerZ");// 专门用于检查用户自己的数据，所以必须完全正确
		if (StringUtils.isNotBlank(accountSellerZ)) {
			criteria.andAccountSellerEqualTo(accountSellerZ);
		}
		String accountBuyerZ = qryParams.get("accountBuyerZ");// 专门用于检查用户自己的数据，所以必须完全正确
		if (StringUtils.isNotBlank(accountBuyerZ)) {
			criteria.andAccountBuyerEqualTo(accountBuyerZ);
		}
		String state = qryParams.get("state");
		if (StringUtils.isNotBlank(state)) {
			criteria.andStateLike("%" + state + "%");
		}
		String evaluation = qryParams.get("evaluation");
		if (StringUtils.isNotBlank(evaluation)) {
			criteria.andEvaluationEqualTo(evaluation);
		}
		String time = qryParams.get("time");
		if (StringUtils.isNotBlank(time)) {
			Date now = new Date();
			criteria.andTimeEqualTo(now);
		}
		return example;
	}
}
