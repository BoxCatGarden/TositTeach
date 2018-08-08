let app = new Vue({
    el: "#app",
    data: {
        //data
        docs: [],
        user: {},

        //paging
        currPage: 1,
        totalPage: 0,
        pageSize: 4,
        jumpPage: '',

        //action
        type: 0
    },
    //call on page loaded
    created() {
        request200('GET', '/in/user', null, x => {
            this.user = x;
        });
        this.update();
    },
    methods: {
        //paging
        update() {
            request200('GET', '/in/pro',
                {st: (this.currPage - 1) * this.pageSize, nm: this.pageSize, s: this.type},
                x => {
                    this.totalPage = this.pageSize * 1 && Math.ceil(x.total / this.pageSize);
                    if (this.currPage > this.totalPage) this.currPage = this.totalPage || 1;
                    this.docs = x.data;
                });
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
        del(i) {
            var pro = this.docs[i];
            if (confirm(`确认删除项目（${pro.proId},${pro.proName}）吗？`)) {
                request200('POST', '/in/pro/del', {pi: pro.proId}, x => {
                    alert(x ? '删除成功' : '删除失败');
                    this.update();
                });
            }
        },
        detail(i) {
            //jump to new page with parameters
            sessionStorage.setItem('param_pro_adetail_proId', this.docs[i].proId);
            window.location = '/project_adetail.html';
        }
    }
});