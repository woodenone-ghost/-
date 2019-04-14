package zhangjie.dao;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import zhangjie.mapper.SellerMapper;
import zhangjie.model.Seller;
import zhangjie.model.SellerExample;
import zhangjie.model.SellerExample.Criteria;
import zhangjie.util.AssertUtil;

@Repository("sellerDAO")
public class SellerDAO extends BaseDAO<Seller, SellerExample, SellerMapper> {

	/**
	 * 增加一条记录
	 * 
	 * @param entity
	 */
	public void add(Seller entity) {
		AssertUtil.argIsNotNull(entity, "entity is null");
		AssertUtil.strIsNotBlank(entity.getNameSeller(), "name is null");
		AssertUtil.strIsNotBlank(entity.getAddressSeller(), "address is null");
		this.getMapper().insert(entity);
	}

	/**
	 * 删除一条记录
	 * 
	 * @param idSeller
	 */
	public void delete(Integer idSeller) {
		AssertUtil.argIsNotNull(idSeller, "idSeller is null");
		this.getMapper().deleteByPrimaryKey(idSeller);
	}

	/**
	 * 更新一条记录
	 * 
	 * @param entity
	 */
	public void update(Seller entity) {
		AssertUtil.argIsNotNull(entity, "entity is null");
		AssertUtil.argIsNotNull(entity.getIdSeller(), "idSeller is null");
		AssertUtil.strIsNotBlank(entity.getNameSeller(), "name is null");
		AssertUtil.strIsNotBlank(entity.getAddressSeller(), "address is null");
		AssertUtil.argIsNotNull(entity.getGoodSeller(), "good is null");
		AssertUtil.argIsNotNull(entity.getBadSeller(), "bad is null");
		this.getMapper().updateByPrimaryKeySelective(entity);
	}

	/**
	 * 
	 * 根据主键查询一条记录
	 * 
	 * @param idSeller
	 * @return
	 */
	public Seller selectByPrimaryKey(Integer idSeller) {
		AssertUtil.argIsNotNull(idSeller, "idSeller is null");
		return this.getMapper().selectByPrimaryKey(idSeller);
	}

	@Override
	protected SellerMapper getMapper() {
		// TODO Auto-generated method stub
		return super.findMapper(SellerMapper.class);
	}

	@Override
	/**
	 * 构建搜索条件
	 */
	protected SellerExample buildExample(Map<String, String> qryParams) {
		// TODO Auto-generated method stub
		SellerExample example=new SellerExample();
		Criteria criteria=example.createCriteria();
		String idSeller=qryParams.get("idSeller");
		if(StringUtils.isNotBlank(idSeller)) {
			criteria.andIdSellerEqualTo(Integer.valueOf(idSeller));
		}
		String nameSeller=qryParams.get("nameSeller");
		if(StringUtils.isNotBlank(nameSeller)) {
			criteria.andNameSellerLike("%"+nameSeller+"%");
		}
		return example;
	}

}
