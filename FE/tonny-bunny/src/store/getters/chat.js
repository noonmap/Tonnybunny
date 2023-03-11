export default {
    /*
        chat
    */
    getChatRoomInfo(state) {
        return state.chat.chatRoomInfo;
    },
    getChatUserSeq(state) {
        return state.chat.chatUserSeq;
    },
    getChatRoomList(state) {
        return state.chat.chatRoomList;
    },
    getChatStompSocket(state) {
        return state.chat.chatStompSocket;
    },
    getChatSocket(state) {
        return state.chat.chatSocket;
    },
    getSendUrlMessage(state) {
        return state.chat.sendUrlMessage;
    },
    getUrlMessage(state) {
        return state.chat.urlMessage;
    },
};
