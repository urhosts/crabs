package org.jeecg.modules.crabs.bait.controller;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.config.JeecgBaseConfig;
import org.jeecg.config.vo.Path;
import org.jeecg.modules.crabs.bait.entity.RemainingBait;
import org.jeecg.modules.crabs.bait.service.IRemainingBaitService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;

 /**
 * @Description: 残饵计数识别
 * @Author: jeecg-boot
 * @Date:   2023-09-03
 * @Version: V1.0
 */
@Api(tags="残饵计数识别")
@RestController
@RequestMapping("/bait/remainingBait")
@Slf4j
public class RemainingBaitController extends JeecgController<RemainingBait, IRemainingBaitService> {
	@Autowired
	private IRemainingBaitService remainingBaitService;
	 @Autowired
	 private JeecgBaseConfig jeecgBaseConfig;
	/**
	 * 分页列表查询
	 *
	 * @param remainingBait
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "残饵计数识别-分页列表查询")
	@ApiOperation(value="残饵计数识别-分页列表查询", notes="残饵计数识别-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<RemainingBait>> queryPageList(RemainingBait remainingBait,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<RemainingBait> queryWrapper = QueryGenerator.initQueryWrapper(remainingBait, req.getParameterMap());
		Page<RemainingBait> page = new Page<RemainingBait>(pageNo, pageSize);
		IPage<RemainingBait> pageList = remainingBaitService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param remainingBait
	 * @return
	 */
	@AutoLog(value = "残饵计数识别-添加")
	@ApiOperation(value="残饵计数识别-添加", notes="残饵计数识别-添加")
	@RequiresPermissions("bait:remaining_bait:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody RemainingBait remainingBait) {
		remainingBaitService.save(remainingBait);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param remainingBait
	 * @return
	 */
	@AutoLog(value = "残饵计数识别-编辑")
	@ApiOperation(value="残饵计数识别-编辑", notes="残饵计数识别-编辑")
	@RequiresPermissions("bait:remaining_bait:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody RemainingBait remainingBait) {
		remainingBaitService.updateById(remainingBait);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "残饵计数识别-通过id删除")
	@ApiOperation(value="残饵计数识别-通过id删除", notes="残饵计数识别-通过id删除")
	@RequiresPermissions("bait:remaining_bait:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		remainingBaitService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "残饵计数识别-批量删除")
	@ApiOperation(value="残饵计数识别-批量删除", notes="残饵计数识别-批量删除")
	@RequiresPermissions("bait:remaining_bait:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.remainingBaitService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "残饵计数识别-通过id查询")
	@ApiOperation(value="残饵计数识别-通过id查询", notes="残饵计数识别-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<RemainingBait> queryById(@RequestParam(name="id",required=true) String id) {
		RemainingBait remainingBait = remainingBaitService.getById(id);
		if(remainingBait==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(remainingBait);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param remainingBait
    */
    @RequiresPermissions("bait:remaining_bait:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, RemainingBait remainingBait) {
        return super.exportXls(request, remainingBait, RemainingBait.class, "残饵计数识别");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("bait:remaining_bait:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, RemainingBait.class);
    }

	 /**
	  * 识别
	  *
	  * @param remainingBait
	  * @param response
	  * @return
	  */
	 // @RequiresPermissions("bait:remaining_bait:importExcel")
	 @RequestMapping(value = "/recognition", method = RequestMethod.POST)
	 public Result<?> recognition(@RequestBody RemainingBait remainingBait, HttpServletResponse response) {
		 String baitPic = remainingBait.getBaitPic();
		 String basPath = jeecgBaseConfig.getPath().getUpload();
		 String absPath = basPath+ File.separator+baitPic;
		 String url = "http://localhost:5005/index";
		 String result = new String();
		 try {
			 RestTemplate template = new RestTemplate();
			 // 封装参数，千万不要替换为Map与HashMap，否则参数无法传递
			 MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
			 paramMap.add("img_name", absPath);
			 //String result 1、使用postForObject请求接口
			 result = template.postForObject(url, paramMap, String.class);
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 // 创建一个Map来存放键值对
		 Map<String, String> keyValueMap = new HashMap<>();

		 // 使用逗号分割字符串
		 String[] keyValuePairs = result.split(",");

		 // 遍历键值对并添加到Map中
		 for (String pair : keyValuePairs) {
			 String[] keyValue = pair.split("=");
			 if (keyValue.length == 2) {
				 String key = keyValue[0];
				 String value = keyValue[1];
				 keyValueMap.put(key, value);
			 }
		 }

		 log.debug("需要识别的图片路径为:{}",keyValueMap.get("img_result"));
		 String result_pic = keyValueMap.get("img_result");
		 String result_count = keyValueMap.get("img_count");
		 Integer count = Integer.valueOf(result_count);
		 String regexPattern = "upFiles(.*)"; // 匹配 "result" 之后的任何字符
		 Pattern pattern = Pattern.compile(regexPattern);
		 Matcher matcher = pattern.matcher(result_pic);
		 String absResult = new String();
		 if (matcher.find()) {
			 absResult = matcher.group(1);
		 }

//		 String replacedString = resutl_pic.replace("\\", "\\\\");
		 remainingBait.setRecognitionPic(absResult);
		 remainingBait.setBaitCount(count);
		 // 调用 图片识别 返回结果
		 return Result.ok(remainingBait);
	 }

}
