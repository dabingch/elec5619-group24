import { createRouter, createWebHashHistory} from "vue-router";

const routes = [
    {
        path: '/',
        component: () => import('../components/LoginPage'),
    },
    {
        path: '/registerPage',
        component: () => import('../components/RegisterPage'),
    },
    {
        path: '/homePage',
        component: () => import('../components/HomePage'),
    },
    {
        path: '/adminHomePage',
        component: () => import('../components/AdminHomePage'),
    },
];

const router = createRouter({
    history: createWebHashHistory(),
    routes
});

export default router;
