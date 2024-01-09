<template>
    <div>

        <div style="height: 400px; border: 1px solid black;margin-left: 10px;margin-right: 10px;">
          <vue3-tree-org
          :data="pageTree" :props="treeProps"
            center
            :horizontal="horizontal"
            :collapsable="collapsable"
            :label-style="style"
            :only-one-node="onlyOneNode"
            :clone-node-drag="cloneNodeDrag"
            :before-drag-end="beforeDragEnd"
            @on-node-drag="nodeDragMove"
            @on-node-drag-end="nodeDragEnd"
            @on-contextmenu="onMenus"
            @on-expand="onExpand"
            @on-node-dblclick="onNodeDblclick"
            @on-node-click="onNodeClick"
          />
        </div>
    </div>
  </template>
  <script>
  import { ElSwitch, ElColorPicker, ElMessage } from 'element-plus'
  import { ref,watch,onBeforeMount } from 'vue'
  import { useStore } from 'vuex';
  export default {
    name: "baseTree",
    components: {
      ElSwitch,
      ElColorPicker
    },
    setup() {
      const cloneNodeDrag = ref(true)
      const store = useStore();
    const pageTree = ref({});
    // 初始化加载 pageTree
    onBeforeMount(() => {
      pageTree.value = store.state.pageTree;
    });

    const treeProps = {
      label: 'title', // Replace with the property name for the node label in your pageTree data
      children: 'children' // Replace with the property name for the children nodes in your pageTree data
    };
    watch(
      () => store.state.pageTree,
      (newVal) => {
        pageTree.value = newVal;
      }
    );
      return {
        cloneNodeDrag,
        pageTree,
        treeProps
      }
    },


    data() {
      return {
     
        horizontal: true,
        collapsable: false,
        onlyOneNode: false,
        expandAll: true,
        disaled: false,
        style: {
          background: "#fff",
          color: "#5e6d82",
        },
      };
    },
    created() {
      // this.toggleExpand(this.data, this.expandAll);
    },
    methods: {
      onMenus({ node, command }) {
        console.log(node, command);
      },
      onExpand(e, data) {
        console.log(e, data);
      },
      onExpandAll(b) {
        console.log(b)
      },
      nodeDragMove(data) {
        console.log(data);
      },
      beforeDragEnd(node, targetNode) {
        return new Promise((resolve, reject) => {
          if (!targetNode) reject()
          if (node.id === targetNode.id) {
            reject()
          } else {
            resolve()
          }
        })
      },
      nodeDragEnd(data, isSelf) {
        console.log(data, isSelf);
      },
      onNodeDblclick() {
        console.log('onNodeDblclick')
      },
      onNodeClick(e, data) {
        // 出现编辑+删除两个功能，
      if(data.children.length){
        console.log(data);
      }
      },
      expandChange() {
        // this.toggleExpand(this.data, this.expandAll);
      },
      toggleExpand(data, val) {
        if (Array.isArray(data)) {
          data.forEach((item) => {
            item.expand = val
            if (item.children) {
              this.toggleExpand(item.children, val);
            }
          });
        } else {
          data.expand = val
          if (data.children) {
            this.toggleExpand(data.children, val);
          }
        }
      },
    },
  };
  </script>
  