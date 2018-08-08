let app = new Vue({
    el: '#app',
    data: {
        ui: '',
        p: '',

        signInFlag: 0,
        dis: false
    },
    methods: {
        reset() {
            this.ui = '';
            this.p = '';
        },
        signIn() {
            if (!this.ui) {
                alert('请输入帐号！');
                return;
            }

            this.dis = true;
            this.signInFlag = 1;
            request200('POST', '/in/user/signin', {ui: this.ui, p: this.p},
                x => {
                    if (x) {
                        window.location = '/mainpage.html';
                    } else {
                        alert('登录失败！');
                        this.reset();
                        this.signInFlag = 0;
                        this.dis = false;
                    }
                });
        }
    }
});