let app = new Vue({
    el: "#app",
    data: {
        user: {},

        //data
        project: {eng: {}, doc: {}},
        id: '',

        pn: '',
        edt: '',
        disp: '',
        file: '',

        //ctrl
        dis: true
    },
    //call on page loaded
    created() {
        this.id = sessionStorage.getItem('param_pro_detail_proId');
        request200('GET', '/in/user', {}, x => {
            this.user = x;
        });
        this.update();
    },
    //other functions
    methods: {
        update() {
            request200('GET', '/in/pro/get', {pi: this.id}, x => {
                this.project = x;
                if (!x.doc) x.doc = {};
                if (!x.eng) x.eng = {};
                this.reset();
            });
        },
        reset() {
            this.pn = this.project.proName;
            this.edt = this.project.edTime;
            this.disp = this.project.disp;
        },
        setfile(event) {
            this.file = event.target.files[0];
        },

        startMod() {
            this.file = '';
            this.$refs.file.files.length = 0;
            this.$refs.file.value = '';
            this.$refs.file.style.width = '400px';
            this.dis = false;
        },
        mod() {
            if (!this.pn) {
                alert('请输入项目名！');
                return;
            }
            if (!this.edt) {
                alert('请选择有效的起止时间！');
                return;
            } else {
                var st = new Date(this.project.stTime),
                    ed = new Date(this.edt);
                if (st.getTime() >= ed.getTime()) {
                    alert('请选择有效的起止时间！（结束时间要晚于起始时间）');
                    return;
                }
            }
            if (!this.disp) {
                alert('请输入项目描述！');
                return;
            }


            if (!confirm('确认要修改吗？')) return;
            this.dis = true;
            if (this.pn == this.project.proName
                && this.edt == this.project.edTime
                && this.disp == this.project.disp) {
                alert('项目信息没有修改！' + (this.file ? '即将开始上传项目文档...' : ''));
                if (this.file)
                    this.upload();
                else this.$refs.file.style.width = 0;
            } else request200('POST', '/in/pro/mod',
                {
                    pi: this.id,
                    pn: this.pn,
                    edt: this.edt,
                    dp: this.disp
                },
                x => {
                    if (x) {
                        alert('项目修改成功！' + (this.file ? '即将开始上传项目文档...' : ''));
                        if (this.file) {
                            this.upload();
                        } else {
                            this.$refs.file.style.width = 0;
                            this.update();
                        }
                    } else {
                        alert('项目修改失败！');
                        this.dis = false;
                    }
                });
        },
        upload() {
            this.$refs.file.style.width = 0;
            var data = new FormData(),
                url = this.project.doc.docId ? '/in/engdoc/reupload' : '/in/engdoc/upload';
            data.append('file', this.file);
            if (this.project.doc.docId)
                data.append('di', this.project.doc.docId);
            else data.append('pi', this.id);
            request200('POST', url, data,
                x => {
                    this.update();
                    if (x) {
                        alert('文件上传成功！');
                    } else {
                        alert('文件上传失败！（请尝试重新上传）');
                        this.dis = false;
                        this.$refs.file.style.width = '400px';
                    }
                });
        },
        onreset() {
            if (confirm('确认重置吗？')) {
                this.reset();
                this.file = '';
                this.$refs.file.files.length = 0;
                this.$refs.file.value = '';
            }
        }
    }
});