// common > global.js
// 모든 컴포넌트가 공유할 수 있는 글로벌 영역 입니다.

export default {
    setDate(time) {
        const options = {
            year: "numeric",
            month: "short",
            day: "numeric",
        };

        const currentTime = Date.now();
        const oldTime = new Date(time);
        const pastTime = new Date(currentTime - oldTime);
        const sec = Math.floor(pastTime / 1000);

        let temp;
        if (sec >= 86400) {
            temp = oldTime.toLocaleDateString("ko-KR", options);
        } else if (sec >= 3600) {
            temp = Math.floor(sec / 3600) + "시간 전";
        } else {
            temp = Math.floor(sec / 60) + "분 전";
        }
        return temp;
    },

    // 2023-02-07T12:00:00 -> date 추출
    dateSplit(dateTime) {
        let split = dateTime.split("T")[0].split("-");

        let date = {
            year: split[0],
            month: split[1],
            day: split[2],
        };

        return date;
    },

    // 2023-02-07T12:00:00 -> time 추출
    dateTimeSplit(dateTime) {
        let split = dateTime.split("T")[1].split(":");

        let time = {
            hour: split[0],
            minute: split[1],
            second: split[2],
        };

        return time;
    },

    // 12:00:00 -> time 추출
    timeSplit(timeStr) {
        let split = timeStr.split(":");

        let time = {
            hour: split[0],
            minute: split[1],
            second: split[2],
        };

        return time;
    },
};
