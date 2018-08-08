let app = new Vue({
    el: "#app",
    data: {
        user: {},
        //data
        clas: [],
        //paging
        currPage: 1,
        totalPage: 0,
        pageSize: 10,
        jumpPage: '',

        //action
        claIds: [],
        sall: false
    },
    //call on page loaded
    created() {
        request200('GET', '/in/user', null, x => {
            this.user = x;
        });
        this.update();
    },
    watch: {
        claIds() {
            if (this.claIds.length == this.clas.length) this.sall = true;
            else if (this.sall) this.sall = false;
        }
    },
    //other functions
    methods: {
        //paging
        update() {
            request200('GET', '/in/cla', {st: (this.currPage - 1) * this.pageSize, nm: this.pageSize}, x => {
                this.totalPage = this.pageSize * 1 && Math.ceil(x.total / this.pageSize);
                if (this.currPage > this.totalPage) this.currPage = this.totalPage || 1;
                for (let cla of x.data) if (!cla.eng) cla.eng={};
                this.reset();
                this.clas = x.data;
            });
        },
        reset() {
            this.claIds.length = 0;
            this.sall = false;
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
            this.claIds.length = 0;
            if (this.sall) {
                for (let s of this.clas) {
                    this.claIds.push(s.claId);
                }
            }
        },

        del() {
            if (!this.claIds.length) {
                alert('请选择至少1个班级！');
                return;
            }

            if (!confirm('确认删除这'+this.claIds.length+'个班级吗？')) return;
            request200('POST','/in/cla/del',[...this.claIds],x=>{
                if (x) {
                    alert('成功删除'+x+'个班级！');
                    this.update();
                } else {
                    alert('删除失败！');
                }
            });
        }
    }
});