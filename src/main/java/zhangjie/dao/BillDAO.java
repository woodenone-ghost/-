package zhangjie.dao;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

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
		AssertUtil.argIsNotNull(entity.getNameSeller(), "nameSeller is null");
		AssertUtil.argIsNotNull(entity.getQuantity(), "quantity is null");
		AssertUtil.argIsNotNull(entity.getPrice(), "price is null");
		AssertUtil.argIsNotNull(entity.getTime(), "time is null");
		AssertUtil.strIsNotBlank(entity.getState(), "state is null");
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
		AssertUtil.argIsNotNull(entity.getNameSeller(), "nameSeller is null");
		AssertUtil.argIsNotNull(entity.getQuantity(), "quantity is null");
		AssertUtil.argIsNotNull(entity.getPrice(), "price is null");
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
		BillExample example=new BillExample();
		Criteria criteria=example.createCriteria();
		String id=qryParams.get("id");
		if(StringUtils.isNotBlank(id)) {
			criteria.andIdEqualTo(Integer.valueOf(id));
		}
		String accountBuyer=qryParams.get("accountBuyer");
		if(StringUtils.isNotBlank(accountBuyer)) {
			criteria.andAccountBuyerLike("%"+accountBuyer+"%");
		}
		String idCommodity=qryParams.get("idCommodity");
		if(StringUtils.isNotBlank(idCommodity)) {
			criteria.andIdCommodityEqualTo(Integer.valueOf(idCommodity));
		}
		String nameSeller=qryParams.get("nameSeller");
		if(StringUtils.isNotBlank(nameSeller)) {
			criteria.andNameSellerLike("%"+nameSeller+"%");
		}
		String time=qryParams.get("time");
		if(StringUtils.isNotBlank(time)) {
			Date now=new Date();
			criteria.andTimeEqualTo(now);		
		}
		return example;
	}
}
