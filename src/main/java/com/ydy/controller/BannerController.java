/**
 * 
 */
package com.ydy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ydy.annotation.AdminToken;
import com.ydy.annotation.CheckFormRepeat;
import com.ydy.annotation.CtrlParam;
import com.ydy.model.Banner;
import com.ydy.service.banner.BannerService;
import com.ydy.utils.StringUtils;
import com.ydy.vo.other.BaseVo;
import com.ydy.vo.other.PageVo;

/**
 * @author xuzhaojie
 *
 *         2019年1月18日 上午9:17:29
 */
@Controller
@RequestMapping("banner")
public class BannerController {

	@Autowired
	private BannerService bannerService;

	@GetMapping("list")
	@ResponseBody
	public ResponseEntity<PageVo<Banner>> list(Banner banner, Integer page, Integer size) {
		StringUtils.setParamEmptyToNull(banner);
		PageVo<Banner> vo = bannerService.list(banner, page, size);
		return ResponseEntity.ok(vo);
	}

	@AdminToken
	@GetMapping("select")
	@ResponseBody
	public ResponseEntity<PageVo<Banner>> select(Banner banner, Integer page, Integer size) {
		StringUtils.setParamEmptyToNull(banner);
		PageVo<Banner> vo = bannerService.select(banner, page, size);
		return ResponseEntity.ok(vo);
	}

	@AdminToken
	@CheckFormRepeat
	@PostMapping("save")
	@ResponseBody
	public ResponseEntity<Banner> save(Banner banner) {
		return ResponseEntity.ok(bannerService.saveOrUpdate(banner));
	}

	@AdminToken
	@CheckFormRepeat
	@PostMapping("status")
	@ResponseBody
	public ResponseEntity<BaseVo> status(@CtrlParam("id") Long id) {
		return ResponseEntity.ok(bannerService.status(id));
	}

	@AdminToken
	@CheckFormRepeat
	@DeleteMapping("delete")
	@ResponseBody
	public ResponseEntity<BaseVo> delete(@CtrlParam("id") Long id) {
		return ResponseEntity.ok(bannerService.delete(id));
	}

	@AdminToken
	@GetMapping("selectById")
	@ResponseBody
	public ResponseEntity<Banner> selectById(@CtrlParam("id") Long id) {
		return ResponseEntity.ok(bannerService.selectById(id));
	}
}