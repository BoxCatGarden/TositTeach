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

        //ctrl
        dis: false
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
        }
    }
});