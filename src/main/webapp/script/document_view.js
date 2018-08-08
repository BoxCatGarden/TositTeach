let app = new Vue({
    el: "#app",
    data: {
        //data
        docs: [],
        user:{},
        //paging
        currPage: 1,
        totalPage: 0,
        pageSize: 4,
        jumpPage: '',

        //action
        type: 0,
        input:''
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
            if (this.type==0) {
                param.dn = this.input;
            } else if (this.type==1) {
                param.pn = this.input;
            } else {
                param.gn = this.input;
            }
            request200('GET', '/in/studoc', param, x => {
                this.totalPage = this.pageSize * 1 && Math.ceil(x.total / this.pageSize);
                if (this.currPage > this.totalPage) this.currPage = this.totalPage || 1;
                this.docs = x.data;
            });
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
            var doc = this.docs[i];
            if (confirm(`确认删除文档（${doc.docId},${doc.docName}）吗？`)) {
                request200('POST', '/in/studoc/del', {di: doc.docId}, x => {
                    alert(x ? '删除成功' : '删除失败');
                    this.update();
                });
            }
        },
        detail(i) {
            //jump to new page with parameters
             sessionStorage.setItem('param_doc_detail_docId',this.docs[i].docId);
             window.location = '/document_detail.html';
            /*alert('jump to /document_detail.html with docId '+this.docs[i].docId);*/
        }
    }
});