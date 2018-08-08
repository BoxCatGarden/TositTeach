let app = new Vue({
    el: "#app",
    data: {
        user: {},
        //data
        clas: [],
        stus: [],
        pros: [],

        //paging
        currPage: 1,
        totalPage: 0,
        pageSize: 10,
        jumpPage: '',

        //action
        type: 'n',
        stuIds: [],
        sall: false,

        ci: '',
        gn: '',
        pi: ''
    },
    //call on page loaded
    created() {
        request200('GET', '/in/user', {}, x => {
            this.user = x;
        });
        request200('GET', '/in/cla', {nm: 0}, x => {
            this.clas = x.data;
            this.ci = this.clas[0] ? this.clas[0].claId : '';
        });
        this.update();
    },
    watch: {
        stuIds() {
            if (this.stuIds.length == this.stus.length) this.sall = true;
            else if (this.sall) this.sall = false;
        }
    },
    //other functions
    methods: {
        //paging
        update() {
            //所有的班级
            request200('GET', '/in/stu', {
                st: (this.currPage - 1) * this.pageSize,
                nm: this.pageSize,
                ci: this.type || undefined
            }, x => {
                this.totalPage = this.pageSize * 1 && Math.ceil(x.total / this.pageSize);
                if (this.currPage > this.totalPage) this.currPage = this.totalPage || 1;
                for (let stu of x.data) if (!stu.gp) stu.gp = {};
                this.reset();
                this.stus = x.data;
            });
        },
        onupdate() {
            this.currPage = 1;
            if (this.type && this.type != 'n') this.updatePro();
            this.update();
        },
        reset() {
            this.stuIds.length = 0;
            this.sall = false;
            this.gn = '';
        },

        jump() {
            var jump = this.jumpPage * 1;
            if (jump && 0 < jump && jump <= this.totalPage) {
                this.currPage = jump;
                this.update();
            }
            this.jumpPage = '';
        },
        prev() {
            if (this.currPage > 1) {
                --this.currPage;
                this.update();
            }
        },
        next() {
            if (this.currPage < this.totalPage) {
                ++this.currPage;
                this.update();
            }
        },

        //action
        selectAll() {
            this.stuIds.length = 0;
            if (this.sall) {
                for (let s of this.stus) {
                    this.stuIds.push(s.userId);
                }
            }
        },

        claAdd() {
            if (!this.stuIds.length) {
                alert('请选择至少1个学生！');
                return;
            }
            var ci = this.ci;
            request200('POST', '/in/cla/addstu', [ci, ...this.stuIds], x => {
                if (x) {
                    alert('成功添加' + x + '个学生到班级' + ci + '！');
                    this.update();
                } else {
                    alert('添加失败！');
                }
            });
        },

        updatePro() {
            request200('GET', '/in/pro', {nm: 0, hg: 0}, x => {
                this.pros = x.data;
                this.pi = this.pros[0] ? this.pros[0].proId : '';
            });
        },
        makeGp() {
            if (!this.stuIds.length) {
                alert('请选择至少1个学生！');
                return;
            }
            if (!this.gn) {
                alert('请输入小组名称！');
                return;
            }
            if (!this.pi) {
                alert('必须要给小组选择一个项目！');
                return;
            }

            var ci = this.type;
            request200('POST', '/in/gp/group',
                [this.gn, ci, this.pi, ...this.stuIds],
                x => {
                    if (x) {
                        alert('成功将' + x + '个学生分入新的小组！');
                        this.updatePro();
                        this.update();
                    } else {
                        alert('分组失败！');
                        this.updatePro();
                    }
                });
        }
    }
});