let app = new Vue({
    el: "#app",
    data: {
        user: {},
        //data
        info: {}
    },
    //call on page loaded
    created() {
        request200('GET', '/in/user', null, x => {
            this.user = x;
        });
        request200('GET', '/in/user/info', null, x => {
            this.info = x;
        });
    },
    methods: {
        signOut() {
            if (confirm('确定离开系统吗？')) {
                request200('POST','/in/user/signout',{}, x=>{
                    if (x) {
                        window.location='/';
                    } else {
                        alert('退出失败！请稍候尝试');
                    }
                })
            }
        }
    }
});