import { createRouter, createWebHistory } from "vue-router";
import Login from "@/views/Login";
import Horses from "@/views/Horses";
import History from "@/views/History";
import Bets from "@/views/Bets";

const routes = [
    {
        path: "/",
        name: "Login",
        component: Login,
    },
    {
        path: "/Horses",
        name: "Horses",
        component: Horses
    },
    {
        path: "/Bets",
        name: "Bets",
        component: Bets
    },
    {
        path: "/History",
        name: "History",
        component: History
    },
    {
        path: "/Home",
        name: "Home",
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () =>
            import(/* webpackChunkName: "about" */ "../views/Home.vue"),
    },
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes,
});

export default router;
