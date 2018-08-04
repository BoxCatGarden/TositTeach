let app = new Vue({
    el: "#app",
    data: {
        //data
        user: {},
        info: {}
    },
    //call on page loaded
    created() {
        request200('GET', '/in/user', {}, x => {//接口
            this.user = x;
        });
        request200('GET','/in/user/info',null,x=>{
            this.info=x;
        });
    }
});