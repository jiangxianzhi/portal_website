import { createRouter, createWebHistory } from "vue-router";
import ShowPage from "../Views/ShowPage.vue";
import ManagePage from "../Views/ManagePage.vue";
import uploadPage from "../Components/uploadPage.vue";
import controlPage from "../Components/controlPage.vue";
import homePage from "../Components/homePage.vue";

import contactPage from "../Components/contactPage.vue";

import LoginPage from "../Views/LoginPage.vue";
import NotFound from "../Views//NotFound.vue";
import PageComponent from "@/Components/pageComponent.vue";
const routes = [
    //Other routes...
    {
      path: "/:catchAll(.*)", // Catch-all route to handle unexpected paths
      name: "notFound",
      component: NotFound,
    },
    // :id是用来传递参数的
  {
    path: "/page/:id",
    component: PageComponent,
  },

  {
    path: "/loginPage",
    name: "LoginPage",
    component: LoginPage,
  },
  {
    path: "/contactPage",
    name: "contactPage",
    component: contactPage,
  },
 
  {
    path: "/",
    redirect: "/homePage",
  },
  {
    path: "/homePage",
    name: "HomePage",
    component: homePage,
  },
  {
    path: "/showPage",
    name: "ShowPage",
    component: ShowPage,
  },
  {
    path: "/managePage",
    name: "ManagePage",

    component: ManagePage,

    children: [
      {
        path: "uploadPage",
        name: "UploadPage",
        component: uploadPage,
      },
      {
        path: "controlPage",
        name: "ControlPage",
        component: controlPage,
      },
    ],
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;