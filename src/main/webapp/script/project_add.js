let app = new Vue({
    el: "#app",
    data: {
        user: {},

        //data
        pn: '',
        stt: '',
        edt: '',
        disp: '',
        file: '',

        //ctrl
        dis: false
    },
    created() {
        request200('GET','/in/user',null,x=>{
            this.user=x;
        });
    },
    methods: {
        reset() {
            this.pn = '';
            this.stt = '';
            this.edt = '';
            this.disp = '';
            this.file = '';
            this.$refs.file.files.length = 0;
            this.$refs.file.value = '';
        },
        setfile(event) {
            this.file = event.target.files[0];
        },
        add() {
            if (!this.pn) {
                alert('请输入项目名！');
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
                alert('请输入项目描述！');
                return;
            }

            this.dis=true;
            request200('POST', '/in/pro/add',
                {
                    pn: this.pn,
                    stt: this.stt,
                    edt: this.edt,
                    dp: this.disp
                },
                x => {
                    if (x) {
                        alert('项目添加成功！' + (this.file ? '即将开始上传项目文件...' : ''));
                        if (this.file) {
                            let data = new FormData();
                            data.append('file', this.file);
                            data.append('pi', x);
                            request200('POST', '/in/engdoc/upload', data,
                                x => {
                                    alert(x ? '文件上传成功！' : '文件上传失败！（请在修改项目的页面重新上传）');
                                    this.reset();
                                    this.dis = false;
                                });
                        } else {
                            this.reset();
                            this.dis = false;
                        }
                    } else {
                        alert('项目添加失败！');
                        this.dis = false;
                    }
                });
        }
    }
});