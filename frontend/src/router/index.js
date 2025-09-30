import { createRouter, createWebHistory } from "vue-router";
import Login from "../components/Login.vue";

const routes = [
  { path: "/", component: Login },
  
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

// Simple Auth Guard
router.beforeEach((to, from, next) => {
  const token = localStorage.getItem("token");
  if (to.path !== "/" && !token) {
    next("/");
  } else {
    next();
  }
});

export default router;
