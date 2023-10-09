<template>

<!--  <a-form ref="queryForm" :model="planModel" :label-col="labelCol" :wrapper-col="wrapperCol" >-->
<!--    <a-row :gutter="2">-->
<!--      <a-col :span="8">-->
<!--        <a-form-item label="选择时间范围" >-->
<!--          <a-range-picker style="width:250px"  :value="dateData" @change="ondateChange"/>-->
<!--          <a-button type="primary" preIcon="ant-design:search-outlined" @click="searchQuery">查询</a-button>-->
<!--        </a-form-item>-->
<!--      </a-col>-->
<!--      <a-col >-->

<!--      </a-col>-->
<!--    </a-row>-->
<!--  </a-form>-->

  <div class="p-4">
    <a-card :bordered="false" style="height: 100%">
      <a-tabs >
        <a-tab-pane key="bar" tab="摄食量可视化">
<!--          <Bar :chartData="dataSource" height="60vh"></Bar>-->
          <Bar ref="bar" :chartData="option" height="60vh" :option = option></Bar>
        </a-tab-pane>
      </a-tabs>
    </a-card>
  </div>
</template>

<script lang="ts" setup>
import { defHttp } from '/@/utils/http/axios';
import { ref, unref, reactive } from 'vue';
import Bar from '/@/components/chart/Bar.vue';
import { cloneDeep } from 'lodash-es';
const dataSource = ref([]);
const bar = ref();
let option = ref({});
const queryForm = ref();
const planModel = ref();
const dateData = ref();
const labelCol = reactive({
  xs: {span: 24},
  sm: {span: 6},
})
const wrapperCol = reactive({
  xs: {span: 24},
  sm: {span: 16},
})

/**
 * 表单提交事件
 * */
async function handleSubmit() {
  console.log("查询,handleSubmit");
}
  
// 通过日期查找对应的条目数据,方便构造鼠标移上去显示标签的构造
function getItemData(itemDate){
  if(!dataCache || dataCache.result<1){
    return {};
  }
  let len = dataCache.result.length;
  for (var i=0; i<len; i++){
    let info = dataCache.result[i];
    if(info.createTime==itemDate){
      return info;
    }
  }
}
function getDefaultOpt(){
  return {
    title: {
      text: '蟹群日摄食量可视化',
      left: 'center'
    },
   tooltip: {
          trigger: 'item',
          show:true,
          formatter: function(params){
            let itemData = getItemData(params.name);
            if(params.seriesIndex==0){
              let lab = params.name +"</br>";
              lab =lab+ "日摄食量: "+ itemData.baitIntake +"克</br>";
              lab =lab+ "溶解氧: "+ itemData.recoDissolvedOxygen +"mg/L</br>";
              lab =lab+ "投喂量: "+ itemData.baitInput +"克</br>";
              lab =lab+ "螃蟹只数: "+ itemData.recoCrabsCount +"只</br>";
              return lab;  
            }else{
              let lab = params.name +"</br>";
              lab =lab+ "螃蟹只数: "+ itemData.antiCrabsCount +"只</br>";
              lab =lab+ "溶解氧: "+ itemData.antiDissolvedOxygen +"mg/L</br>";
              lab =lab+ "预测明日投喂量: "+ itemData.baitPrediction +"克</br>";
              
              return lab;  
            }
            
          }
        },
    legend: {
      data: ['今日摄食量估算', '明日投喂量预测'],
      orient: 'horizontal', // 水平布局
      left: 'center',       // 图例位于图表中央
      bottom: 10            // 图例位于图表底部，可以根据需要调整距离底部的距离

    },
    yAxis: {
      name: '克',
      type: 'value',
      axisLabel: {
        formatter: '{value} 克'
      }
    },
    xAxis: {
      type: 'category',
      data: []
    },
    series: [
      {
        name: '今日摄食量估算',
        type: 'bar',
        label: {
          show: true,          // 显示标签
          position: 'top',     // 标签位置（上方）
          formatter: '{c} 克',
        },
        data: [],
      },
      {
        name: '明日投喂量预测',
        type: 'bar',
        label: {
          show: true,          // 显示标签
          position: 'top',     // 标签位置（上方）
          formatter: '{c} 克',
        },
        data: [],
      }
    ],
  }
}  
/* 
陈老板，这是后端返回的json
{
    "success": true,
    "message": "",
    "code": 200,
    "result": [
        {
            "createTime": "2023-10-04",
            "baitCount": 16,
            "baitInput": 50,
            "baitIntake": 49.424,
            "recoDissolvedOxygen": 0.0,
            "antiDissolvedOxygen": 0.0,
            "recoCrabsCount": 0,
            "antiCrabsCount": 0,
            "baitPrediction": 0.0,
            "name": "2023-10-04",
            "value": 49.424
        }
    ],
    "timestamp": 1696824443177
}
*/
/* 郭老板， 这就是数据结构了
1. 增加了鼠标一放上去的标签显示
2. 增加了两个柱状图的颜色区分
3. 同时显示2个柱子
let option = {
  tooltip: {
        trigger: 'item',
        show:true,
        formatter: function(params){
          if(params.seriesIndex==0){
            let lab = params.name +"</br>";
            lab =lab+ "日摄食量: "+ params.data +"克</br>";
            lab =lab+ "溶解氧: "+ params.data +"mg/L</br>";
            lab =lab+ "投喂量: "+ params.data +"克</br>";
            lab =lab+ "螃蟹只数: "+ params.data +"只</br>";
            return lab;  
          }else{
            let lab = params.name +"</br>";
            lab =lab+ "螃蟹只数: "+ params.data +"只</br>";
            lab =lab+ "溶解氧: "+ params.data +"mg/L</br>";
            lab =lab+ "预测明日投喂量: "+ params.data +"克</br>";
            
            return lab;  
          }
          
        }
      },
  legend: {
    data: ['今日摄食量估算', '明日投喂量预测']
  },
  xAxis: {
    type: 'category',
    data: ['2023-09-08', '2023-09-09', '2023-09-10', '2023-09-11', '2023-09-12', '2023-09-13', '2023-09-14']
  },
  yAxis: {
    type: 'value'
  },
  series: [
    {
      name: '今日摄食量估算',
      data: [120, 200, 150, 80, 70, 110, 130],
      type: 'bar'
      
    },
    {
      name: '明日投喂量预测',
      data: [20, 20, 15, 80, 70, 10, 13],
      type: 'bar'
    }
  ]
};


*/
let dataCache=null;
async function newLoadDate(url, type, params) {
  // demoData();
  const res = await defHttp.get({ url, params }, { isTransformResponse: false, errorMessageMode: 'none' });
  dataSource.value=[];
  dataCache = res;
  let baitIntakeArray = [];
  let predictArray = [];
  let x_date = [];
  for (let i = 0; i < res.result.length; i++) {
    let intake = res.result[i].baitIntake || 0;
    let baitPre = res.result[i].baitPrediction || 0 ;
    let xdate = res.result[i].createTime;
    x_date.push(xdate);
    baitIntakeArray.push(intake);
    predictArray.push(baitPre);

  }
  let opt = getDefaultOpt();
  opt.xAxis.data = x_date;
  opt.series[0].data = baitIntakeArray;
  opt.series[1].data = predictArray;  
  Object.assign(option.value, cloneDeep(opt));
  // Bar.props.option.value = opt;
  // console.log(Bar.props.option)
  // bar.value.setOptions(opt)
  // dataSource.value.push(opt)
}

async function loadDate(url, type, params) {
  // demoData();
  const res = await defHttp.get({ url, params }, { isTransformResponse: false, errorMessageMode: 'none' });
  dataCache = res;
  for (let i = 0; i < res.result.length; i++) {
    dataSource.value.push({
      name: `${res.result[i].createTime}`,
      value: res.result[i].baitIntake,
    });
  }

  // if (res.success) {
  //   dataSource.value = res.result;
  // }
}
function demoData() {
  let data = [
    {
      "name": "8-1日",
      "value": 320
    },
    {
      "name": "8-2日",
      "value": 457
    },
    {
      "name": "8-3日",
      "value": 182
    }
  ]
  dataSource.value=data;
}
//tab切换
function tabChange(key) {
  console.log('切换的key:', key);
}
// loadDate(url.getYearCountInfo, 'year', {});
// demoData();
// loadDate("/bait/remainingBait/stat");
newLoadDate("/bait/remainingBait/stat");
</script>
