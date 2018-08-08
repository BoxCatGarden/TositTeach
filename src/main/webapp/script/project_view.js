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
        input: '',
        type: 1
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
            var param = {st: (this.currPage - 1) * this.pageSize, nm: this.pageSize};
            if (this.input) {
                if (this.type == 1) {
                    param.pn = this.input;
                } else if (this.type == 2) {
                    param.en = this.input;
                }
            }
            request200('GET', '/in/pro', param, x => {
                this.totalPage = this.pageSize * 1 && Math.ceil(x.total / this.pageSize);
                if (this.currPage > this.totalPage) this.currPage = this.totalPage || 1;
                this.docs = x.data;
            });
            this.jumpPage = '';
        },

        jump() {
            var jump = this.jumpPage*1;
            if (jump && 0 < jump && jump <= this.totalPage) {
                this.currPage=jump;
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
            sessionStorage.setItem('param_pro_detail_proId', this.docs[i].proId);
            window.location = '/project_detail.html';
            /* alert('jump to /document_detail.html with docId '+this.docs[i].proId);*/
        }
    }
});