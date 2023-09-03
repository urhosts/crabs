import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '当日投喂量',
    align:"center",
    dataIndex: 'feeding'
   },
   {
    title: '残饵量',
    align:"center",
    dataIndex: 'remaining'
   },
   {
    title: '柱状图',
    align:"center",
    dataIndex: 'barChart'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '当日投喂量',
    field: 'feeding',
    component: 'InputNumber',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入当日投喂量!'},
          ];
     },
  },
  {
    label: '残饵量',
    field: 'remaining',
    component: 'InputNumber',
  },
  {
    label: '柱状图',
    field: 'barChart',
    component: 'Input',
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