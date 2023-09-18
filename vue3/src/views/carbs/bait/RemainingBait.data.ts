import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: '创建时间',
    align:"center",
    dataIndex: 'createTime'
  },
  {
    title: '原始图片',
    align:"center",
    dataIndex: 'baitPic',
    customRender:render.renderImage,
  },
  {
    title: '识别结果',
    align:"center",
    dataIndex: 'recognitionPic',
    customRender:render.renderImage,
  },
  {
    title: '残饵计数',
    align:"center",
    dataIndex: 'baitCount'
  },
  {
    title: '投喂量',
    align:"center",
    dataIndex: 'baitInput'
  },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '原始图片',
    field: 'baitPic',
    component: 'JImageUpload',
    componentProps:{
      fileMax : 1
    },
    dynamicRules: ({model,schema}) => {
      return [
        { required: true, message: '请输入原始图片!'},
      ];
    },
  },
  {
    label: '识别结果',
    field: 'recognitionPic',
    component: 'JImageUpload',
    componentProps:{
      text:"",
      listType:"picture-card"
    },
    dynamicDisabled:true
  },
  {
    label: '残饵计数',
    field: 'baitCount',
    component: 'InputNumber',
    dynamicDisabled:true
  },
  {
    label: '投喂量',
    field: 'baitInput',
    component: 'InputNumber',
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
