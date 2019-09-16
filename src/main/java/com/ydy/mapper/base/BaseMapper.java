/**
 * 
 */
package com.ydy.mapper.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author xuzhaojie
 *
 *         2018年8月1日 下午3:46:06
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
