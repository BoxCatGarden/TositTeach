
let app = new Vue({
    el: "#app",
    data: {
        //data
        user: {},
    },
    //call on page loaded
    created() {

        request200('GET', '/in/user', null, x => {//接口
            this.user = x;
        });
    }
});