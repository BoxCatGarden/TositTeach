var app123 = new Vue({
    el: "#app",
    data: {
        //data
        docs: [{doc:{}}],
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
        //this.update();
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
        updateByName(){
            /*var input_index = document.getElementById("sousuokey").selectedIndex;//类型
            var state;
            var can = {};
            if(input_index==1) {
              state = 1;
            }
            else if(input_index==2){
                state = 2;
            }
            else if(input_index==3){
                state = 3;
            } else state = 0;
            can = {st: (this.currPage - 1) * this.pageSize, nm: this.pageSize,s:state};
            request200('GET', '/in/pro', can, x => {
                this.totalPage = this.pageSize * 1 && Math.ceil(x.total / this.pageSize);
                if (this.currPage > this.totalPage) this.currPage = this.totalPage || 1;
                this.docs = x.data;
            });*/
        },

        jump(i) {
            this.currPage=i;
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
        detail(i) {
            //jump to new page with parameters
            sessionStorage.setItem('param_pro_adetail_proId', this.docs[i].proId);
            window.location = '/project_adetail.html';

        }
    }
});