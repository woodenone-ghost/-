package zhangjie.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import zhangjie.mapper.UserInformationMapper;
import zhangjie.model.UserInformation;
import zhangjie.model.UserInformationExample;
import zhangjie.util.AssertUtil;

@Repository("userInformationDAO")
public class UserInformationDAO extends BaseDAO<UserInformation, UserInformationExample, UserInformationMapper> {

	/**
	 * 增加一条记录
	 * 
	 * @param entity
	 */
	public void add(UserInformation entity) {
		AssertUtil.argIsNotNull(entity, "entity is null");
		AssertUtil.strIsNotBlank(entity.getAccount(), "account is null");
		AssertUtil.strIsNotBlank(entity.getPassword(), "password is null");
		AssertUtil.strIsNotBlank(entity.getIdentity(), "identity is null");
		this.getMapper().insert(entity);
	}

	/**
	 * 删除一条记录
	 * 
	 * @param id
	 */
	public void delete(String account) {
		AssertUtil.strIsNotBlank(account, "account is blank");
		this.getMapper().deleteByPrimaryKey(account);
	}

	/**
	 * 更新一条记录
	 * 
	 * @param entity
	 */
	public void update(UserInformation entity) {
		AssertUtil.argIsNotNull(entity, "entity is null");
		AssertUtil.argIsNotNull(entity.getId(), "id is null");
		AssertUtil.strIsNotBlank(entity.getAccount(), "account is null");
		AssertUtil.strIsNotBlank(entity.getPassword(), "password is null");
		AssertUtil.strIsNotBlank(entity.getIdentity(), "identity is null");
		this.getMapper().updateByPrimaryKeySelective(entity);
	}

	/**
	 * 根据主键查找一条记录
	 * 
	 * @param id
	 * @return
	 */
	public UserInformation selectByPrimaryKey(String account) {
		AssertUtil.strIsNotBlank(account, "account is blank");
		return this.getMapper().selectByPrimaryKey(account);
	}

	@Override
	protected UserInformationMapper getMapper() {
		// TODO Auto-generated method stub
		return super.findMapper(UserInformationMapper.class);
	}

	@Override
	protected UserInformationExample buildExample(Map<String, String> qryParams) {
		// TODO Auto-generated method stub
		return null;
	}

}
