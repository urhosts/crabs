<template>
  <div ref="chartRef" :style="{ height, width }"></div>
</template>
<script lang="ts">
  import { defineComponent, PropType, ref, Ref, reactive, watchEffect } from 'vue';
  import { useECharts } from '/@/hooks/web/useECharts';
  import { cloneDeep } from 'lodash-es';
 
  export default defineComponent({
    name: 'bar',
    props: {
      chartData: {
        type: Array,
        default: () => [],
      },
      option: {
        type: Object,
        default: () => ({}),
      },
      width: {
        type: String as PropType<string>,
        default: '100%',
      },
      height: {
        type: String as PropType<string>,
        default: 'calc(100vh - 78px)',
      },
      setOptionsRef:function(opt){
        // setOptions(opt);
      }
    },

    setup(props) {
       const chartRef = ref<HTMLDivElement | null>(null);
      const { setOptions, echarts } = useECharts(chartRef as Ref<HTMLDivElement>);
      const option = reactive({
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow',
            label: {
              show: true,
              backgroundColor: '#333',
            },
          },
        },
        xAxis: {
          type: 'category',
          data: [],
        },
        yAxis: {
          type: 'value',
        },
        series: [
          {
            name: 'bar',
            type: 'bar',
            data: [],
          },
        ],
      });
      
      watchEffect(() => {
        props.chartData && initCharts();
      });
      
      
      function initCharts() {
        
        if (props.option) {
          Object.assign(option, cloneDeep(props.option));
        }
         /* 直接使用echart原始参数
        let seriesData = props.chartData.map((item) => {
          return item.value;
        });
        let xAxisData = props.chartData.map((item) => {
          return item.name;
        });
        option.series[0].data = seriesData;
        option.xAxis.data = xAxisData;
        */        
        // option.series = props.option.series;
        // Object.assign(option, cloneDeep(props.option));
        setOptions(option);
        // props.setOptionsRef = setOptions;
      }
      return { chartRef };
    },
    
  });

</script>
