<template>
  <div class="p-4">
    <a-form  :label-col="{xs: {span: 24}, sm: {span: 6}}" :wrapper-col="{xs: {span: 24}, sm: {span: 18}}" >
            <a-row>
                <a-col :span="8">
                    <a-form-item label="日投喂量" name="title">
                        <a-input placeholder="请输入 日投喂量"/>
                    </a-form-item>
                </a-col>
                 <a-col :span="8">
                    <a-form-item label="残饵量颗数" name="title">
                        <a-input placeholder="请输入 残饵量颗数" />
                    </a-form-item>
                </a-col>
                
            </a-row>
        </a-form>
      </div>
  <div class="p-4">
    <a-card :bordered="false" style="height: 100%">
      <a-tabs v-model:activeKey="activeKey" animated @change="tabChange">
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

  const activeKey = ref('bar');
  const barType = ref('year');
  const pieType = ref('year');
  const dataSource = ref([]);
  const url = reactive({
    getYearCountInfo: '/mock/api/report/getYearCountInfo',
    getMonthCountInfo: '/mock/api/report/getMonthCountInfo',
    getCntrNoCountInfo: '/mock/api/report/getCntrNoCountInfo',
    getCabinetCountInfo: '/mock/api/report/getCabinetCountInfo',
  });

  async function loadDate(url, type, params) {
    demoData();
    // const res = await defHttp.get({ url, params }, { isTransformResponse: false, errorMessageMode: 'none' });
    // if (res.success) {
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
  function getYearCountSource(data) {
    for (let i = 0; i < data.length; i++) {
      dataSource.value.push({
        name: `${data[i].year}年`,
        value: data[i].yearcount,
      });
    }
  }
  function getMonthCountSource(data) {
    for (let i = 0; i < data.length; i++) {
      dataSource.value.push({
        name: `${data[i].month}`,
        value: data[i].monthcount,
      });
    }
  }
  function getCategoryCountSource(data) {
    for (let i = 0; i < data.length; i++) {
      dataSource.value.push({
        name: `${data[i].classifyname}`,
        value: data[i].cntrnocount,
      });
    }
  }
  function getCabinetCountSource(data) {
    for (let i = 0; i < data.length; i++) {
      dataSource.value.push({
        name: `${data[i].cabinetname}`,
        value: data[i].cabinetcocunt,
      });
    }
  }
  // 选择统计类别
  function statisticst(e) {
    if (unref(activeKey) === 'pie') {
      loadDate(getUrl(unref(pieType)), unref(pieType), {});
    } else {
      loadDate(getUrl(unref(barType)), unref(barType), {});
    }
  }

  function getUrl(type) {
    if (type === 'year') {
      return url.getYearCountInfo;
    }
    if (type === 'month') {
      return url.getMonthCountInfo;
    }
    if (type === 'category') {
      return url.getCntrNoCountInfo;
    }
    if (type === 'cabinet') {
      return url.getCabinetCountInfo;
    }
  }
  //tab切换
  function tabChange(key) {
    console.log('切换的key:', key);
  }
  // loadDate(url.getYearCountInfo, 'year', {});
  demoData();
</script>
