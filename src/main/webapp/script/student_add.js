let app = new Vue({
    el: "#app",
    data: {
        user: {},
        //data
        n: '',
        sx: 0,
        g: 0,
        sh: '',
        id: '',

        file: '',
        //ctrl
        dis: false,
        prog: false,
        intv:''
    },
    created() {
        request200('GET', '/in/user', {}, x => {
            this.user = x;
        });
    },
    methods: {
        reset() {
            this.n = '';
            this.sx = 0;
            this.g = 0;
            this.sh = '';
            this.id = '';
        },
        add() {
            if (!this.n) {
                alert('请输入学生姓名！');
                return;
            }
            if (!this.sh) {
                alert('请输入学生所在学校！');
                return;
            }
            if (!this.id) {
                alert('请输入学生的学号！');
                return;
            } else if (!/^[0-9]+$/.test(this.id)) {
                alert('请输入有效学号！（只包含数字0-9）');
                return;
            }

            this.dis = true;
            request200('POST', '/in/stu/add',
                {
                    school: this.sh,
                    id: this.id,
                    name: this.n,
                    sex: this.sx,
                    grade: this.g
                },
                x => {
                    if (x) {
                        alert('添加成功！');
                        this.reset();
                        this.dis = false;
                    } else {
                        alert('添加失败！');
                        this.dis = false;
                    }
                });
        },

        setfile(event) {
            this.file = event.target.files[0];
        },
        batch() {
            if (this.file && /\.xls$|\.xlsx$/.test(this.file.name)) {
                this.dis = true;
                var data = new FormData();
                data.append('file', this.file);
                this.startProg();
                request200('POST', '/in/stu/bat', data, x => {
                    this.stopProg();
                    if (x) {
                        alert('成功导入'+x+'个学生！');
                        window.location = window.location.pathname;
                    } else {
                        alert('导入失败！');
                        this.dis = false;
                    }
                });
            } else alert('请选择Excel表格文件！');
        },

        startProg() {
            var e = this.$refs.innerbar.style,
                x = 0;
            this.intv = setInterval(()=>{
                e["margin-left"] = x+'%';
                x+=0.2;
                if (x>100) x=-10;
            }, 10);
            this.prog = true;
        },
        stopProg() {
            clearInterval(this.intv);
            this.prog=false;
        }
    }
});