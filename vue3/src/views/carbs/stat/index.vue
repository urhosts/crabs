<template>
   
    <a-form ref="queryForm" :model="planModel" :label-col="labelCol" :wrapper-col="wrapperCol" >
      <a-row :gutter="2">
        <a-col :span="8">
            <a-form-item label="选择时间范围" v-model:value="planModel.rangeTime">
          <a-range-picker style="width:250px"  />
          <a-button type="primary" preIcon="ant-design:search-outlined" @click="searchQuery">查询</a-button>
        </a-form-item>
        </a-col>
        <a-col >
            
        </a-col>
    </a-row>  
  </a-form>
  
 <div class="p-4">
    <a-card :bordered="false" style="height: 100%">
      <a-tabs >
        <a-tab-pane key="bar" tab="摄食量可视化">
          <Bar :chartData="dataSource" height="60vh"></Bar>
        </a-tab-pane>
        
      </a-tabs>
    </a-card>
  </div>
</template>

<script lang="ts" setup>
  import { defHttp } from '/@/utils/http/axios';
  import { ref, unref, reactive } from 'vue';
  import Bar from '/@/components/chart/Bar.vue';
  const dataSource = ref([]);
  const queryForm = ref();
  const planModel = ref();
  planModel.rangeTime="";
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
  async function searchQuery(){
    console.log("查询");
    console.log(planModel);
    loadDate("/bait/remainingBait/stat");
  }
  async function loadDate(url, type, params) {
    // demoData();
    const res = await defHttp.get({ url, params }, { isTransformResponse: false, errorMessageMode: 'none' });
    if (res.success) {
      dataSource.value = res.result;
    }
    //   dataSource.value = [];
    //   switch (type) {
    //     case 'year':
    //       getYearCountSource(res.result);
    //       break;
    //     case 'month':
    //       getMonthCountSource(res.result);
    //       break;
    //     case 'category':
    //       getCategoryCountSource(res.result);
    //       break;
    //     case 'cabinet':
    //       getCabinetCountSource(res.result);
    //       break;
    //     default:
    //       break;
    //   }
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
  demoData();
</script>
