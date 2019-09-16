/**
 * 
 */
package com.ydy.vo.other;

import com.ydy.ienum.base.IErrorEnum;

/**
 * @author xuzhaojie
 *
 *         2019年5月6日 上午11:59:30
 */
public class ResultVo extends BaseVo {
	public ResultVo() {

	}

	public ResultVo(IErrorEnum e) {
		this.setErrorEnum(e);
	}
}
