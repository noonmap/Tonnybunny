import http from "@/common/axios";
// import global from "@/common/global";
// import utils from "@/common/utils";

export default {
    /*
        SignUp
    */
    async isEmailOk() {},
    async isPwOk() {},
    async isNicknameOk() {},
    async isAuthCodeOk() {},
    async signUpClent() {},
    async signUpHelper() {},

    /*
        Login
    */
    async login(context, loginInfo) {
        console.log("async login !");
        let errorMessage;
        let { data } = await http.post(`/signin`, loginInfo).catch(() => {
            errorMessage = "이메일 혹은 패스워드가 틀립니다.";
            alert("이메일 혹은 패스워드가 틀립니다.");
        });
        errorMessage;
        try {
            console.log("async login function : ", data);

            // 로그인 성공 로직
            if (data.resultCode == "SUCCESS") {
                // 데이터 분할
                const { access_TOKEN, refresh_TOKEN, ...userInfo } = data.data;

                // 토큰 저장
                context.commit("SET_TOKENS", { access_TOKEN, refresh_TOKEN });

                // 유저 정보 저장
                context.commit("SET_USER_INFO", userInfo);
                console.log(userInfo);

                // 로그인 변경
                context.commit("SET_LOG_IN");

                // 공통코드를 저장
                switch (userInfo.userCode) {
                    // FIXME : 공통코드 수정
                    case "0010001":
                        context.commit("SET_USER_CODE", false);
                        break;
                    case "0010002":
                        context.commit("SET_USER_CODE", true);
                        break;
                }

                // http.defaults.headers.common["ACCESS_TOKEN"] = access_TOKEN;
            } else if (data.resultCode == "FAIL") {
                console.log("err.response2");
                //
            }

            return data.resultCode;
        } catch (err) {
            console.error("err.response", err.response);

            // exception
            // if (err.response.status == 403) {
            // 	alert("로그인 하세요");
            // }

            return data.resultCode;
        }
    },
    logout(context) {
        context.commit("SET_LOG_OUT");
    },
    /*
		Auth Login
	*/
    githubLogin() {},
    kakaoLogin() {},
    naverLogin() {},

    /*
        Find ID/PW
    */
    findUserId() {},
    findUserPw() {},
};
