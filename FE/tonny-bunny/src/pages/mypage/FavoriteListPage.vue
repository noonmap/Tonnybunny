<template>
    <title-banner title="즐겨찾기" text="즐겨찾기한 헬퍼의 목록입니다." />

    <div class="container w-75">
        <div v-if="followCnt != 0">
            <div v-for="follow in followList" :key="follow.seq">
                <UserProfileItem
                    btnText="언팔로우"
                    :helperInfo="follow"
                    @clickBtn="deleteFollow(follow.seq)" />
            </div>
        </div>
        <div v-else>
            <br />
            <title-text type="h2" title="즐겨찾기 한 유저가 없습니다🐾" class="text-center" />
        </div>
        <br />
        <div class="w-50 mx-auto">
            <router-link :to="{ name: 'MyPage' }">
                <smallBtn color="carrot" class="w-100" text="확인"></smallBtn>
            </router-link>
        </div>
    </div>
</template>

<script>
import UserProfileItem from "@/components/mypage/UserProfileItem.vue";
import TitleText from "@/components/common/TitleText.vue";
import TitleBanner from "@/components/common/TitleBanner";
import SmallBtn from "@/components/common/button/SmallBtn.vue";
import { mapGetters } from "vuex";
import http from "@/common/axios";
export default {
    name: "FavoriteListPage",
    data() {
        return {
            followList: [],
            followCnt: 0,
        };
    },

    components: {
        UserProfileItem,
        TitleText,
        TitleBanner,
        SmallBtn,
    },
    methods: {
        async getFollowList(seq) {
            try {
                let res = await http.get(`/mypage/${seq}/follow`);
                if (res.data.resultCode == "SUCCESS") {
                    this.followList = res.data.data;
                    this.followCnt = res.data.data.length;
                }
            } catch (error) {
                console.log(error);
            }
        },

        async deleteFollow(helperSeq) {
            const userSeq = this.userInfo.seq;
            try {
                let res = await http.delete(`mypage/${userSeq}/follow/${helperSeq}`);

                if (res.data.resultCode == "SUCCESS") {
                    this.followCnt -= 1;
                }
            } catch (error) {
                console.log(error);
            }
        },
    },
    computed: {
        ...mapGetters({
            userInfo: "getUserInfo",
        }),
    },
    created() {
        const userSeq = this.userInfo.seq;
        this.getFollowList(userSeq);
    },
};
</script>

<style></style>
