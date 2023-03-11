import { createStore } from "vuex";

import getters from "./getters";
import mutations from "./mutations";
import actions from "./actions";

import temp from "./state/temp";
import account from "./state/account";
import board from "./state/board";
import jtonny from "./state/jtonny";
import ytonny from "./state/ytonny";
import bunny from "./state/bunny";
import live from "./state/live";
import common from "./state/common";
import alert from "./state/alert";
import mypage from "./state/mypage";
import chat from "./state/chat";
import schedule from "./state/schedule";
import review from "./state/review";
import createPersistedState from "vuex-persistedstate";

export default createStore({
    plugins: [createPersistedState()],

    state: {
        // 공통 state
        common,

        // 각 파일 만들어서 모듈화 사용
        temp,
        account,
        board,
        jtonny,
        ytonny,
        live,
        bunny,
        alert,
        mypage,
        chat,
        schedule,
        review,
    },

    getters,
    mutations,
    actions,
});
