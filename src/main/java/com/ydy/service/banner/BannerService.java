/**
 * 
 */
package com.ydy.service.banner;

import com.ydy.model.Banner;
import com.ydy.vo.other.BaseVo;
import com.ydy.vo.other.PageVo;

/**
 * @author xuzhaojie
 *
 *         2019年5月27日 下午7:11:11
 */
public interface BannerService {

	PageVo<Banner> select(Banner banner, Integer page, Integer size);

	PageVo<Banner> list(Banner banner, Integer page, Integer size);

	Banner saveOrUpdate(Banner banner);

	BaseVo status(Long id);

	BaseVo delete(Long id);

	Banner selectById(Long id);
}
