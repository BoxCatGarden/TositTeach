let app = new Vue({
    el: "#app",
    data: {
        user: {},

        //data
        engs: [],

        cn: '',
        rm: '',
        ei: '',

        //ctrl
        dis: false
    },
    //call on page loaded
    created() {
        request200('GET', '/in/user', {}, x => {
            this.user = x;
        });
        this.update();
    },
    //other functions
    methods: {
        //paging
        update() {
            request200('GET', '/in/eng', {nm: 0}, x => {
                this.engs = x.data;
            });
        },
        reset() {
            this.cn = '';
            this.rm = '';
            this.ei = '';
        },

        add() {
            if (!this.rm) {
                alert('请输入班级的教室！');
                return;
            }
            var vali = false, ui = this.ei;
            for (let e of this.engs) {
                if (ui == e.userId) {
                    vali = true;
                    break;
                }
            }
            if (!vali) {
                alert('请输入有效的工程师编号！');
                return;
            }

            this.dis = true;
            request200('POST', '/in/cla/add', {cn: this.cn, rm: this.rm, ui: ui},
                x => {
                    if (x) {
                        alert('添加成功！');
                        this.reset();
                    } else {
                        alert('添加失败！');
                    }
                    this.update();
                    this.dis = false;
                });
        }
    }
});