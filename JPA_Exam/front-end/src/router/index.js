import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: "/",
    alias: "/dept",
    name: "dept",
    component: () => import("@/components/dept/DeptList")
  },
  // 부서 라우팅
  {
    path: "/dept/:dno",
    name: "dept-details",
    component: () => import("@/components/dept/DeptDetail")
  },
  {
    path: "/add/dept",
    name: "add-dept",
    component: () => import("@/components/dept/AddDept")
  },  
  // 사원 라우팅
  {
    path: "/emp",
    name: "emp",
    component: () => import("@/components/emp/EmpList")
  },
  {
    path: "/emp/:eno",
    name: "emp-details",
    component: () => import("@/components/emp/EmpDetail")
  },
  {
    path: "/add/emp",
    name: "add-emp",
    component: () => import("@/components/emp/AddEmp")
  }, 
  // 사원 라우팅
  {
    path: "/faq",
    name: "faq",
    component: () => import("@/components/faq/FaqList")
  },
  {
    path: "/faq/:no",
    name: "faq-details",
    component: () => import("@/components/faq/FaqDetail")
  },
  {
    path: "/add/faq",
    name: "add-faq",
    component: () => import("@/components/faq/AddFaq")
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
