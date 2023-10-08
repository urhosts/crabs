import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '创建日期',
    align:"center",
    dataIndex: 'createTime'
   },
   {
    title: '溶解氧',
    align:"center",
    dataIndex: 'dissolvedOxygen'
   },
   {
    title: '螃蟹只数',
    align:"center",
    dataIndex: 'crabsCount'
   },
   {
    title: '预测明日投喂量(克)',
    align:"center",
    dataIndex: 'baitPrediction'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '溶解氧',
    field: 'dissolvedOxygen',
    component: 'InputNumber',
    componentProps:{
      addonAfter:'(单位:mg/L)'
    }
  },
  {
    label: '螃蟹只数',
    field: 'crabsCount',
    component: 'InputNumber',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入螃蟹只数!'},
          ];
     },
  },
  {
    label: '预测投喂量',
    labelCol:{ style: { width: '150px' } },
    field: 'baitPrediction',
    component: 'InputNumber',
    dynamicDisabled:true,
    componentProps:{
      addonAfter:'(单位:克)'
    }
  },
	// TODO 主键隐藏字段，目前写死为ID
	{
	  label: '',
	  field: 'id',
	  component: 'Input',
	  show: false
	},
];



/**
* 流程表单调用这个方法获取formSchema
* @param param
*/
export function getBpmFormSchema(_formData): FormSchema[]{
  // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}