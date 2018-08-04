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

        //action
        currDoc: 0,
        input:'',
        type:1
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
            request200('GET', '/in/pro', {st: (this.currPage - 1) * this.pageSize, nm: this.pageSize}, x => {
                this.totalPage = this.pageSize * 1 && Math.ceil(x.total / this.pageSize);
                if (this.currPage > this.totalPage) this.currPage = this.totalPage || 1;
                this.docs = x.data;
            });
        },
        updateByName() {

            var can = {st: (this.currPage - 1) * this.pageSize, nm: this.pageSize};
            if (this.type == 1) {
                can.pn = this.input;
            }
            else if (this.type == 2) {
                can.en = this.input;
            }
            request200('GET', '/in/pro', can, x => {
                this.totalPage = this.pageSize * 1 && Math.ceil(x.total / this.pageSize);
                if (this.currPage > this.totalPage) this.currPage = this.totalPage || 1;
                this.docs = x.data;
            });
        },

        jump(i) {
            this.currPage = i;
            this.update();
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
        select(i) {
            this.currDoc = i;
        },
        del() {
            //delete currDoc;
            /*alert('delete '+this.currDoc);*/
            request200('POST', '/in/pro/del', {pi: this.docs[this.currDoc].proId}, x => {
                if (x == 1) {
                    alert("删除成功！");
                }
                else {
                    alert("删除失败！");
                }
                this.update();
                this.$refs.altBoxClose.click();
            });
        },
        detail(i) {
            //jump to new page with parameters
            sessionStorage.setItem('param_pro_detail_proId', this.docs[i].proId);
            window.location = '/project_detail.html';
            /* alert('jump to /document_detail.html with docId '+this.docs[i].proId);*/
        }
    }
});