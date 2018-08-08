let app = new Vue({
    el: "#app",
    data: {
        engs: [],
        user: {},

        //data
        tn: '',
        ei: '',
        stt: '',
        edt: '',
        disp: ''
    },
    created() {
        request200('GET', '/in/user', {}, x => {
            this.user = x;
        });
        this.update();
    },
    methods: {

        update() {
            request200('GET', '/in/eng', {nm: 0}, x => {
                this.engs = x.data;
            });
        },
        reset() {
            this.tn = '';
            this.ei = '';
            this.stt = '';
            this.edt = '';
            this.disp = '';
        },
        add() {
            var vali = false;
            for (let e of this.engs) {
                if (this.ei == e.userId) {
                    vali = true;
                    break;
                }
            }
            if (!vali) {
                alert("请输入有效的工程师编号！");
                this.update();
                return;
            }
            if (!this.stt || !this.edt) {
                alert('请选择有效的起止时间！');
                return;
            } else {
                var st = new Date(this.stt),
                    ed = new Date(this.edt);
                if (new Date('2018-01-01').getTime() >= st.getTime()) {
                    alert('请选择有效的起止时间！（起始时间要晚于2018-01-01）');
                    return;
                } else if (st.getTime() >= ed.getTime()) {
                    alert('请选择有效的起止时间！（结束时间要晚于起始时间）');
                    return;
                }
            }
            if (!this.disp) {
                alert('请输入任务描述！');
                return;
            }

            request200('POST', '/in/task/add',
                {
                    tasName: this.tn,
                    stTime: this.stt,
                    edTime: this.edt,
                    disp: this.disp,
                    userId: this.ei
                },
                x => {
                    if (x) {
                        alert("成功添加任务："+x+' '+this.tn+"！");
                        this.reset();
                    } else {
                        alert("添加任务失败！")
                    }
                    this.update();
                });
        }


    }

});