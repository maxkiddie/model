/**
 * 
 */
package com.ydy.service.banner.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ydy.constant.SystemConstant;
import com.ydy.exception.BusinessException;
import com.ydy.exception.DataNotFoundException;
import com.ydy.exception.ValidateException;
import com.ydy.ienum.EnumBanner;
import com.ydy.mapper.BannerMapper;
import com.ydy.model.Banner;
import com.ydy.service.banner.BannerService;
import com.ydy.utils.ValidateUtil;
import com.ydy.vo.other.BaseVo;
import com.ydy.vo.other.PageVo;
import com.ydy.vo.other.ResultVo;

import tk.mybatis.mapper.entity.Example;

/**
 * @author xuzhaojie
 *
 *         2019年5月29日 上午8:35:26
 */
@Service
@Transactional
public class BannerServiceImpl implements BannerService {
	private final static Logger log = LoggerFactory.getLogger(BannerServiceImpl.class);
	@Autowired
	private BannerMapper bannerMapper;

	@Override
	public PageVo<Banner> select(Banner banner, Integer page, Integer size) {
		PageVo<Banner> vo = new PageVo<Banner>(page, size);
		Page<Banner> pageBean = PageHelper.startPage(vo.getPage(), vo.getSize(), "id desc");
		List<Banner> list = bannerMapper.select(banner);
		vo.setTotal(pageBean.getTotal());
		vo.setList(list);
		return vo;
	}

	@Override
	public PageVo<Banner> list(Banner banner, Integer page, Integer size) {
		PageVo<Banner> vo = new PageVo<Banner>(page, size);
		Page<Banner> pageBean = PageHelper.startPage(vo.getPage(), vo.getSize(), "inx asc,id desc");
		Date now = new Date();
		Example example = new Example(Banner.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("useStatus", SystemConstant.USE_STATUS_ON);
		criteria.andLessThan("startTime", now);
		criteria.andGreaterThan("endTime", now);
		List<Banner> list = bannerMapper.selectByExample(example);
		vo.setTotal(pageBean.getTotal());
		vo.setList(list);
		return vo;
	}

	@Override
	public Banner saveOrUpdate(Banner banner) {
		if (banner == null) {
			throw new NullPointerException();
		}
		// 校验数据有效
		Map<String, String> validateInfo = ValidateUtil.validateEntity(banner);
		if (!validateInfo.isEmpty()) {
			throw new ValidateException(validateInfo);
		}
		// 新增信息
		if (banner.getId() == null) {
			banner.setUseStatus(SystemConstant.USE_STATUS_OFF);
			banner.setCreateTime(new Date());
			banner.setInx(0);// 先填个默认
			bannerMapper.insertSelective(banner);
			log.info("新增banner成功：" + banner.getId());
		} else {// 根据id更新信息
			Banner temp = bannerMapper.selectByPrimaryKey(banner.getId());
			if (temp == null) {
				log.info("找不到banner信息：" + banner.getId());
				throw new DataNotFoundException(EnumBanner.DATA_NOT_FOUND);
			}
			bannerMapper.updateByPrimaryKeySelective(banner);
			log.info("更新banner成功：" + banner.getId());
		}
		return banner;
	}

	@Override
	public BaseVo delete(Long id) {
		if (id == null) {
			throw new NullPointerException();
		}
		Banner temp = bannerMapper.selectByPrimaryKey(id);
		if (temp == null) {
			log.info("找不到banner信息：" + id);
			throw new DataNotFoundException(EnumBanner.DATA_NOT_FOUND);
		}
		bannerMapper.deleteByPrimaryKey(id);
		log.info("删除banner成功：" + id);
		return new ResultVo();
	}

	@Override
	public BaseVo status(Long id) {
		if (id == null) {
			throw new NullPointerException();
		}
		Banner temp = bannerMapper.selectByPrimaryKey(id);
		if (temp == null) {
			log.info("找不到banner信息：" + id);
			throw new DataNotFoundException(EnumBanner.DATA_NOT_FOUND);
		}
		Banner banner = new Banner();
		banner.setId(id);
		StringBuilder builder = new StringBuilder();
		if (Objects.equals(SystemConstant.USE_STATUS_ON, temp.getUseStatus())) {
			banner.setUseStatus(SystemConstant.USE_STATUS_OFF);
			builder.append("Banner下架成功:").append(id);
		} else {
			banner.setUseStatus(SystemConstant.USE_STATUS_ON);
			builder.append("Banner上架成功:").append(id);
		}
		bannerMapper.updateByPrimaryKeySelective(banner);
		log.info(builder.toString());
		return new ResultVo();
	}

	@Override
	public Banner selectById(Long id) {
		if (id == null) {
			throw new NullPointerException();
		}
		Banner temp = bannerMapper.selectByPrimaryKey(id);
		if (temp == null) {
			throw new BusinessException(EnumBanner.DATA_NOT_FOUND);
		}
		return temp;
	}

}
