package org.jeecg.modules.crabs.prediction.controller;

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
import org.jeecg.modules.crabs.bait.entity.RemainingBait;
import org.jeecg.modules.crabs.prediction.entity.BaitPrediction;
import org.jeecg.modules.crabs.prediction.service.IBaitPredictionService;

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
 * @Description: 投喂量预测
 * @Author: jeecg-boot
 * @Date:   2023-10-08
 * @Version: V1.0
 */
@Api(tags="投喂量预测")
@RestController
@RequestMapping("/prediction/baitPrediction")
@Slf4j
public class BaitPredictionController extends JeecgController<BaitPrediction, IBaitPredictionService> {
	@Autowired
	private IBaitPredictionService baitPredictionService;
	
	/**
	 * 分页列表查询
	 *
	 * @param baitPrediction
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "投喂量预测-分页列表查询")
	@ApiOperation(value="投喂量预测-分页列表查询", notes="投喂量预测-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<BaitPrediction>> queryPageList(BaitPrediction baitPrediction,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<BaitPrediction> queryWrapper = QueryGenerator.initQueryWrapper(baitPrediction, req.getParameterMap());
		Page<BaitPrediction> page = new Page<BaitPrediction>(pageNo, pageSize);
		IPage<BaitPrediction> pageList = baitPredictionService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param baitPrediction
	 * @return
	 */
	@AutoLog(value = "投喂量预测-添加")
	// @ApiOperation(value="投喂量预测-添加", notes="投喂量预测-添加")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody BaitPrediction baitPrediction) {
		baitPredictionService.save(baitPrediction);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param baitPrediction
	 * @return
	 */
	@AutoLog(value = "投喂量预测-编辑")
	@ApiOperation(value="投喂量预测-编辑", notes="投喂量预测-编辑")
	// @RequiresPermissions("prediction:bait_prediction:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody BaitPrediction baitPrediction) {
		baitPredictionService.updateById(baitPrediction);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "投喂量预测-通过id删除")
	@ApiOperation(value="投喂量预测-通过id删除", notes="投喂量预测-通过id删除")
	// @RequiresPermissions("prediction:bait_prediction:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		baitPredictionService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "投喂量预测-批量删除")
	@ApiOperation(value="投喂量预测-批量删除", notes="投喂量预测-批量删除")
	// @RequiresPermissions("prediction:bait_prediction:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.baitPredictionService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "投喂量预测-通过id查询")
	@ApiOperation(value="投喂量预测-通过id查询", notes="投喂量预测-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<BaitPrediction> queryById(@RequestParam(name="id",required=true) String id) {
		BaitPrediction baitPrediction = baitPredictionService.getById(id);
		if(baitPrediction==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(baitPrediction);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param baitPrediction
    */
   //  @RequiresPermissions("prediction:bait_prediction:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, BaitPrediction baitPrediction) {
        return super.exportXls(request, baitPrediction, BaitPrediction.class, "投喂量预测");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
   // @RequiresPermissions("prediction:bait_prediction:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, BaitPrediction.class);
    }
	 /**
	  * 通过输出参数预测明日数量
	  *
	  * @param baitPrediction
	  * @param response
	  * @return
	  */
	 // @RequiresPermissions("prediction:bait_prediction:importExcel")
	 @RequestMapping(value = "/predict", method = RequestMethod.POST)
	 public Result<?> predict(@RequestBody BaitPrediction baitPrediction, HttpServletResponse response) {
		 // BaitPrediction baitPrediction = baitPredictionService.getById("1711030661658628097");
		 if(baitPrediction==null) {
			 return Result.error("未找到对应数据");
		 }
		 // baitPrediction 就是页面提交的上来的参数
		 // 简单的将 螃蟹只数 乘以了 12.4(随便写的)作为预测值返回了
		 baitPrediction.setBaitPrediction(baitPrediction.getCrabsCount()* 12.4);
		 return Result.OK(baitPrediction);
	 }

}
