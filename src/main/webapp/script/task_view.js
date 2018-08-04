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

        //action
        currDoc:0
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
            request200('GET', '/in/task', {st: (this.currPage - 1) * this.pageSize, nm: this.pageSize}, x => {
                this.totalPage = this.pageSize * 1 && Math.ceil(x.total / this.pageSize);
                if (this.currPage > this.totalPage) this.currPage = this.totalPage || 1;
                this.docs = x.data;
            });
        },

        jump(i) {
            this.currPage=i;
            update();
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
        detail(i) {
            //jump to new page with parameters
            sessionStorage.setItem('param_task_detail_docId',this.docs[i].tasId);
            window.location = '/task_detail.html';
            /*alert('jump to /document_detail.html with docId '+this.docs[i].docId);*/
        }
    }
});