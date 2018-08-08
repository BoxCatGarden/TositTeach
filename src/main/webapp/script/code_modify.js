let app = new Vue({
    el: "#app",
    data: {
        user: {},

        op: '',
        np: '',
        anp: ''
    },
    created() {
        request200('GET', '/in/user', {}, x => {
            this.user = x;
        });
    },
    methods: {
        reset() {
            this.op='';
            this.np='';
            this.anp='';
        },
        change() {
            if (this.np != this.anp) {
                alert('请确认两次输入的新密码相同！');
                return;
            }

            if (confirm(this.np ? '确认修改密码吗？' : '确认将密码设置为空值吗？')) {
                request200('POST', '/in/user/chgpwd', {p: this.op, np: this.np},
                    x => {
                        if (x) {
                            alert('修改成功！');
                            window.location = 'home.html';
                        } else {
                            alert('修改失败！');
                            this.reset();
                        }
                    });
            }
        }
    }
});
