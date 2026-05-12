package com.bootdo.blog.controller;

import com.bootdo.blog.domain.ContentDO;
import com.bootdo.blog.service.ContentService;
import com.bootdo.common.utils.DateUtils;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bootdo 1992lcg@163.com
 */
@RequestMapping("/blog")
@Controller
public class BlogController {
	@Autowired
    ContentService bContentService;

	@GetMapping()
	String blog() {
		return "blog/index/main";
	}

	@ResponseBody
	@GetMapping("/open/list")
	public PageUtils opentList(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<ContentDO> bContentList = bContentService.list(query);
		int total = bContentService.count(query);
		PageUtils pageUtils = new PageUtils(bContentList, total);
		return pageUtils;
	}

	@GetMapping("/open/post/{cid}")
		String post(@PathVariable("cid") Long cid, Model model) {
			ContentDO bContentDO = bContentService.get(cid);

			Integer hits = bContentDO.getHits();
			if (hits == null) {
				hits = 0;
			}
			bContentDO.setHits(hits + 1);
			bContentService.update(bContentDO);

			model.addAttribute("bContent", bContentDO);
			model.addAttribute("gtmModified", DateUtils.format(bContentDO.getGtmModified()));
			return "blog/index/post";
		}
	@ResponseBody
	@PostMapping("/open/post/{cid}/like")
	public Map<String, Object> like(@PathVariable("cid") Long cid) {
		Map<String, Object> result = new HashMap<>(16);

		ContentDO content = bContentService.get(cid);
		if (content == null) {
			result.put("code", 1);
			result.put("msg", "文章不存在");
			return result;
		}

		Integer likes = content.getLikes();
		if (likes == null) {
			likes = 0;
		}

		content.setLikes(likes + 1);
		bContentService.update(content);

		result.put("code", 0);
		result.put("likes", content.getLikes());
		return result;
	}
	@GetMapping("/open/page/{categories}")
	String about(@PathVariable("categories") String categories, Model model) {
		Map<String, Object> map = new HashMap<>(16);
		map.put("categories", categories);
		ContentDO bContentDO =null;
		if(bContentService.list(map).size()>0){
			 bContentDO = bContentService.list(map).get(0);
		}
		model.addAttribute("bContent", bContentDO);
		return "blog/index/post";
	}
}
