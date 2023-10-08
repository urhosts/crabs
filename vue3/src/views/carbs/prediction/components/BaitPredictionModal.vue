 <template>
  <BasicModal v-bind="$attrs" @register="registerModal" destroyOnClose :title="title" :width="896" @ok="handleSubmit">
      <BasicForm @register="registerForm"/>
      <div style="width: 100%;text-align: center" >
          <a-button @click="handlePrediction" pre-icon="ant-design:check" type="primary">预 测</a-button>
      </div>
  </BasicModal>
</template>

<script lang="ts" setup>
    import {ref, computed, unref} from 'vue';
    import {BasicModal, useModalInner} from '/@/components/Modal';
    import {BasicForm, useForm} from '/@/components/Form/index';
    import {formSchema} from '../BaitPrediction.data';
    import {saveOrUpdate,predict} from '../BaitPrediction.api';
    // Emits声明
    const emit = defineEmits(['register','success']);
    const isUpdate = ref(true);
    //表单配置
    const [registerForm, {setProps,resetFields,getFieldsValue, setFieldsValue, validate}] = useForm({
        //labelWidth: 150,
        schemas: formSchema,
        showActionButtonGroup: false,
        baseColProps: {span: 12}
    });
    //表单赋值
    const [registerModal, {setModalProps, closeModal}] = useModalInner(async (data) => {
        //重置表单
        await resetFields();
        setModalProps({confirmLoading: false,showCancelBtn:!!data?.showFooter,showOkBtn:!!data?.showFooter});
        isUpdate.value = !!data?.isUpdate;
        if (unref(isUpdate)) {
            //表单赋值
            await setFieldsValue({
                ...data.record,
            });
        }
        // 隐藏底部时禁用整个表单
       setProps({ disabled: !data?.showFooter })
    });
    //设置标题
    const title = computed(() => (!unref(isUpdate) ? '新增' : '编辑'));
    //表单提交事件
    async function handleSubmit(v) {
        try {
            let values = await validate();
            setModalProps({confirmLoading: true});
            //提交表单
            await saveOrUpdate(values, isUpdate.value);
            //关闭弹窗
            closeModal();
            //刷新列表
            emit('success');
        } finally {
            setModalProps({confirmLoading: false});
        }
    }

    // ==== 自定义
    async function handlePrediction(v) {
      try {
          //  //提交表单
          let values = await validate();
          //   console.log(getFieldsValue);
          //   let bp = getFieldsValue("baitPic")
          //   console.log("当前图片"+JSON.stringify(bp.baitPic))
          // console.log("识别图片: "+values.baitPic);
          setModalProps({confirmLoading: true});
          await predict(values, isUpdate.value).then(function (data) {
            console.log(data);
            setFieldsValue({
                "baitPrediction":data.baitPrediction
            })
            // useForm.setFieldsValue("recognitionPic",response.RecognitionPic);
            // useForm.setFieldsValue("baitCount",2);
          });
          emit('success');
        } finally {
         setModalProps({confirmLoading: false});
        }
    }
</script>

<style lang="less" scoped>
	/** 时间和数字输入框样式 */
  :deep(.ant-input-number){
		width: 100%
	}

	:deep(.ant-calendar-picker){
		width: 100%
	}
</style>