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
            request200('GET', '/in/studoc', {st: (this.currPage - 1) * this.pageSize, nm: this.pageSize}, x => {
                this.totalPage = this.pageSize * 1 && Math.ceil(x.total / this.pageSize);
                if (this.currPage > this.totalPage) this.currPage = this.totalPage || 1;
                this.docs = x.data;
            });
        },
        updateByName(){
            var input_sousuo = document.getElementById("sousuo").value;//s输入
            var input_index = document.getElementById("sousuokey").selectedIndex;//类型
            var can = {};
            if(input_index==1) {
                can = {st: (this.currPage - 1) * this.pageSize, nm: this.pageSize,dn: input_sousuo};
            }
            else if(input_index==2){
                can = {st: (this.currPage - 1) * this.pageSize, nm: this.pageSize,pn:input_sousuo};
            }
            else if(input_index==3){
                can = {st: (this.currPage - 1) * this.pageSize, nm: this.pageSize,gn:input_sousuo};
            }
            request200('GET', '/in/pro', can, x => {
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
        del() {
            //delete currDoc;
           /* alert('delete '+this.currDoc);*/
            request200('POST', '/in/pro/del', {di:docs[this.currDoc].docId}, x => {
                if(x==1){
                    alert("删除成功！");
                }
                else{
                    alert("删除失败！");
                }
            });
            this.update();
            this.$refs.altBoxClose.click();
        },

        detail(i) {
            //jump to new page with parameters
             sessionStorage.setItem('param_doc_detail_docId',this.docs[i].docId);
             window.location = '/document_detail.html';
            /*alert('jump to /document_detail.html with docId '+this.docs[i].docId);*/
        }
    }
});