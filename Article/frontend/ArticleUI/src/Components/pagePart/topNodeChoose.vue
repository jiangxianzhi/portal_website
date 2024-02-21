<template>
  <el-dropdown trigger="click" @command="handleCommand">
    <span class="el-dropdown-link">
      选择父页面
      <el-icon class="el-icon--right">
        <arrow-down />
      </el-icon>
    </span>
    <template #dropdown>
      <el-dropdown-menu>
        <el-dropdown-item :command="1"> 1</el-dropdown-item>
        <el-dropdown-item
          v-for="item in items"
          :key="item.id"
          :command="item.id"
        >
          {{ item.id }}</el-dropdown-item
        >
      </el-dropdown-menu>
    </template>
  </el-dropdown>
</template>

<script lang="ts" setup>
import { ref, watch, onUpdated,onMounted } from "vue";
import { ArrowDown } from "@element-plus/icons-vue";
import { useStore } from "vuex";

const store = useStore();
const items = ref(store.state.pageData);
const props = defineProps({
  myselfId: {
    type: Number,
    required: true,
  },
});

watch(
  () => store.state.pageData,
  (newVal) => {
    items.value = newVal;
    tempItems.value = newVal;
  }
);
// 暂存items
const tempItems = ref(store.state.pageData);
// 点击第一个edit触发
onMounted(() => {
  // console.log("myselfId is " + props.myselfId);
if(props.myselfId){
items.value = tempItems.value.filter(item=>item.id!==props.myselfId)
}}
  )
  // 点击第二次之后的edit触发
onUpdated(() => {
  // console.log("myselfId is " + props.myselfId);
  if(props.myselfId){
items.value = tempItems.value.filter(item=>item.id!==props.myselfId)
}

});

const handleCommand = (command) => {
  // console.log('Selected item:', command);

  // You can perform further actions with the selected item here
  store.commit("changeHeadNode", command);
};
</script>
<style scoped>
.example-showcase .el-dropdown-link {
  cursor: pointer;
  color: var(--el-color-primary);
  display: flex;
  align-items: center;
}
</style>
