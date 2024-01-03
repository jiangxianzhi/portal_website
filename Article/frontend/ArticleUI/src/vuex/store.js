import { createStore } from "vuex";
import axios from "axios";
import axiosInstance from "@/axiosAbstract/axiosAPI.js";

const store = createStore({
  state: {
    //    页面加载相关的数据
    pageData: [],
    //创造时选择的头节点id,默认给1
    choosedHeadNode: 1,
    // 树状结构的pageData
    pageTree: {id:1,children:[]},
  },
  mutations: {
    // Your mutations
    // 改变头节点
    changeHeadNode(state, item) {
      state.choosedHeadNode = item;
      // console.log(state.choosedHeadNode)
    },
    // 通过watch pageData得到 pageTree
    setPageTree(state, newTree) {
      // console.log('newTree iss')
      // console.log(newTree)
      state.pageTree = newTree;
    },
    // 从action得到页面内容
    setPageData(state, pages) {
      state.pageData = pages;
    },

    // 得到树状结构pageTitle
  },
  actions: {
    // Your actions
    // 得到所有页面
    async fetchPages({ commit }) {
      try {
        const response = await axios.get("http://localhost:8080/page");
        // console.log(response.data);
        commit("setPageData", response.data);
      } catch (error) {
        console.error("Error fetching pages:", error);
      }
    },

    deletePage({ commit, state }, id) {
      function deleteNodes(id) {
        // Find and remove the node with the given id
        let arr = JSON.parse(JSON.stringify(state.pageData));
        let index = arr.findIndex((node) => node.id === id);
        if (index !== -1) {
        
          arr.splice(index, 1);
         
          deleteSinglePage(arr,id);
        }

        // Find children of the node with the given id and delete them
        
        
        let children = arr.filter((node) => node.parentId === id);
     
        for (let child of children) {
          deleteNodes( child.id);
        }

    
      }

      function deleteSinglePage(arr,id) {
        // 通过id删除page，两点，本地删除+调用接口删除（就不用重新加载了）
        axiosInstance
          .get("http://localhost:8080/page/" + id)
          .then((response) => {
            if (response.data === 1) {
              commit("setPageData", arr);
            } else {
            }
          })
          .catch((error) => {
            console.log(error);
          });
      }

      deleteNodes(id);
    },
  },
  getters: {
    // Your getters
  },
});

// 监听pageData来改变pageTree
store.watch(
  (state) => state.pageData,
  (newVal) => {
    // Logic to update pageTree based on pageData
    const arr = newVal;
    // console.log("newvalu is")
    // console.log(newVal);
    const map = {};
    const root = { id: 1, title: "root", children: [] };
    arr.forEach((item) => {
      map[item.id] = { ...item, children: [] };
      // console.log(map[item.id]);
    });

    arr.forEach((item) => {
      if (item.parentId !== 1) {
        map[item.parentId].children.push(map[item.id]);
      } else {
        root.children.push(map[item.id]);
      }
    });
    // console.log(root)
    store.commit("setPageTree", root);
  }
);

export default store;
