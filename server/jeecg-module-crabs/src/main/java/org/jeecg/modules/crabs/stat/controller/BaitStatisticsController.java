package org.jeecg.modules.crabs.stat.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.crabs.stat.entity.BaitStatistics;
import org.jeecg.modules.crabs.stat.service.IBaitStatisticsService;

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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;

 /**
 * @Description: 摄食量可视化
 * @Author: jeecg-boot
 * @Date:   2023-09-03
 * @Version: V1.0
 */
@Api(tags="摄食量可视化")
@RestController
@RequestMapping("/stat/baitStatistics")
@Slf4j
public class BaitStatisticsController extends JeecgController<BaitStatistics, IBaitStatisticsService> {
	@Autowired
	private IBaitStatisticsService baitStatisticsService;
	
	/**
	 * 分页列表查询
	 *
	 * @param baitStatistics
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "摄食量可视化-分页列表查询")
	@ApiOperation(value="摄食量可视化-分页列表查询", notes="摄食量可视化-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<BaitStatistics>> queryPageList(BaitStatistics baitStatistics,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<BaitStatistics> queryWrapper = QueryGenerator.initQueryWrapper(baitStatistics, req.getParameterMap());
		Page<BaitStatistics> page = new Page<BaitStatistics>(pageNo, pageSize);
		IPage<BaitStatistics> pageList = baitStatisticsService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param baitStatistics
	 * @return
	 */
	@AutoLog(value = "摄食量可视化-添加")
	@ApiOperation(value="摄食量可视化-添加", notes="摄食量可视化-添加")
	@RequiresPermissions("stat:bait_statistics:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody BaitStatistics baitStatistics) {
		baitStatisticsService.save(baitStatistics);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param baitStatistics
	 * @return
	 */
	@AutoLog(value = "摄食量可视化-编辑")
	@ApiOperation(value="摄食量可视化-编辑", notes="摄食量可视化-编辑")
	@RequiresPermissions("stat:bait_statistics:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody BaitStatistics baitStatistics) {
		baitStatisticsService.updateById(baitStatistics);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "摄食量可视化-通过id删除")
	@ApiOperation(value="摄食量可视化-通过id删除", notes="摄食量可视化-通过id删除")
	@RequiresPermissions("stat:bait_statistics:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		baitStatisticsService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "摄食量可视化-批量删除")
	@ApiOperation(value="摄食量可视化-批量删除", notes="摄食量可视化-批量删除")
	@RequiresPermissions("stat:bait_statistics:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.baitStatisticsService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "摄食量可视化-通过id查询")
	@ApiOperation(value="摄食量可视化-通过id查询", notes="摄食量可视化-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<BaitStatistics> queryById(@RequestParam(name="id",required=true) String id) {
		BaitStatistics baitStatistics = baitStatisticsService.getById(id);
		if(baitStatistics==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(baitStatistics);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param baitStatistics
    */
    @RequiresPermissions("stat:bait_statistics:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, BaitStatistics baitStatistics) {
        return super.exportXls(request, baitStatistics, BaitStatistics.class, "摄食量可视化");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("stat:bait_statistics:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, BaitStatistics.class);
    }

}
