<template>
  <div>
    <el-menu
      :default-active="activeIndex"
      class="el-menu-popper-demo"
      mode="horizontal"
      :ellipsis="false"
    >
      <el-menu-item index="0"
      @click="() => gotoPage('homePage')">
        <img
          style="width: 100px"
          src="../../static/logo/element-plus-logo.svg"
          alt="Element logo"
        />
      </el-menu-item>
      <div class="flex-grow" />

      <el-menu-item
        class="el-listMenu"
        index="/homePage"
        @click="() => gotoPage('homePage')"
        >home</el-menu-item
      >
      <el-menu-item
        class="el-listMenu"
        index="/showPage"
        @click="() => gotoPage('showPage')"
        >blog</el-menu-item
      >

   
      <!--there are 3 layers structure , and if you want,you can also use recursive iterator but el-menu has ugly style when it comes to 6 or 7 layer You must write style by your own -->
      <template v-for="(value, key) in tree.children">
        <template v-if="value.id && value.children.length > 0">
          <el-sub-menu class="el-listMenu" :index="value.title" :key="value.id">
            <template #title>{{ value.title }}</template>
         
            <template v-for="(subValue, subKey) in value.children">
              <template
                v-if="subValue.children && subValue.children.length > 0"
              >
                <el-sub-menu
                  class="el-listMenu"
                  :index="subValue.id"
                  :key="subValue.id"
                >
                  <template #title>{{ subKey.title }}</template>
                  <el-menu-item
                    class="el-listMenu"
                    v-for="(subSubValue, subSubKey) in subValue"
                    :index="subSubValue.id"
                    :key="subSubValue.id"
                    @click="gotoPage(subSubValue)"
                    >{{ subSubValue.title }}</el-menu-item
                  >
                </el-sub-menu>
              </template>
              <template v-else>
                <el-menu-item
                  class="el-listMenu"
                  :index="subValue.id"
                  :key="subValue.id"
                  @click="gotoPage(subValue)"
                  >{{ subValue.title }}</el-menu-item
                >
              </template>
            </template>
          </el-sub-menu>
        </template>
        <template v-else>
          <el-menu-item
            class="el-listMenu"
            :index="value.id"
            :key="value.id"
            @click="gotoPage(value)"
            >{{ value.title }}</el-menu-item
          >
        </template>
      </template>
      <el-menu-item
        class="el-listMenu"
        index="/contactPage"
        @click="() => gotoPage('contactPage')"
        >contact</el-menu-item
      >
      <!-- width 小于多少时 -->
      <el-sub-menu class="el-menu-collapse">
        <template #title>
          <el-icon><Operation /></el-icon>
       
        </template>

        <el-menu-item index="/homePage" @click="() => gotoPage('homePage')"
          >home</el-menu-item
        >
        <el-menu-item index="/showPage" @click="() => gotoPage('showPage')"
          >blog</el-menu-item
        >

  
        <!--there are 3 layers structure , and if you want,you can also use recursive iterator but el-menu has ugly style when it comes to 6 or 7 layer You must write style by your own -->
        <template v-for="(value, key) in tree.children">
          <template v-if="value.id && value.children.length > 0">
            <el-sub-menu :index="value.title" :key="value.id">
              <template #title>{{ value.title }}</template>
             
              <template v-for="(subValue, subKey) in value.children">
                <template
                  v-if="subValue.children && subValue.children.length > 0"
                >
                  <el-sub-menu :index="subValue.id" :key="subValue.id">
                    <template #title>{{ subKey.title }}</template>
                    <el-menu-item
                      v-for="(subSubValue, subSubKey) in subValue"
                      :index="subSubValue.id"
                      :key="subSubValue.id"
                      @click="gotoPage(subSubValue)"
                      >{{ subSubValue.title }}</el-menu-item
                    >
                  </el-sub-menu>
                </template>
                <template v-else>
                  <el-menu-item
                    :index="subValue.id"
                    :key="subValue.id"
                    @click="gotoPage(subValue)"
                    >{{ subValue.title }}</el-menu-item
                  >
                </template>
              </template>
            </el-sub-menu>
          </template>
          <template v-else>
            <el-menu-item
              :index="value.id"
              :key="value.id"
              @click="gotoPage(value)"
              >{{ value.title }}</el-menu-item
            >
          </template>
        </template>
        
        <el-menu-item
          index="/contactPage"
          @click="() => gotoPage('contactPage')"
          >contact</el-menu-item
        >
      </el-sub-menu>
    </el-menu>
  </div>
</template>

<script lang="ts" setup>
// 通过 route编程式跳转
import { ref, watch, onBeforeMount } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";

const router = useRouter();
const store = useStore();
const tree = ref({ id: 1, children: [] });
onBeforeMount(() => {
  if (localStorage.getItem("pageTree")) {
    tree.value = JSON.parse(localStorage.getItem("pageTree"));
  } else {
    tree.value = JSON.parse(JSON.stringify(store.state.pageTree));
  }
});
watch(
  () => store.state.pageTree,
  (newVal) => {
    tree.value = newVal;
    localStorage.setItem("localTree", JSON.stringify(newVal));
    console.log("tree value children");
    console.log(tree.value.children);
  }
);
const activeIndex = ref("1");
// 页面跳转
function gotoPage(value) {
  // console.log(value);
  if (typeof value === "string") {
    // made,this area if not have / ,there will take pre path( /page/aboutPage)
    router.push({ path: "/" + value });
  } else {
    // when value is a object
    router.push({ path: "/page/" + value.id });
  }
}
</script>

<style scoped>
.flex-grow {
  flex-grow: 1;
}
.el-menu-collapse {
  display: none;
}

@media (max-width: 768px) {
  .el-listMenu {
    display: none !important; /* Hide the menu items */
  }
  .el-menu-collapse {
    display: block; /* Display the three-line icon */
  }
}
</style>
