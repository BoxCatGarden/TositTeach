let app = new Vue({
    el: "#app",
    data: {
        //data
        clas: [],
        user:{},
        //paging
        currPage: 1,
        totalPage: 0,
        pageSize: 10,

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
                 request200('GET', '/in/cla', {st: (this.currPage - 1) * this.pageSize, nm: this.pageSize}, x => {
                this.totalPage = this.pageSize * 1 && Math.ceil(x.total / this.pageSize);
                if (this.currPage > this.totalPage) this.currPage = this.totalPage || 1;
                this.clas = x;
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
            var arr = [];
            $("input[type='checkbox']:checked").each(
                function () {
                    arr.push($(this).val());
                }
            );
            if(arr==[]){
                alert("请选择删除的班级！");
                return;
            }
            request200('POST', '/in/cla/del', {ids:[arr]}, x => {
                if(x==1){
                    alert("删除成功！");
                }
                else{
                    alert("删除失败！");
                }
            });
            this.update();
        },

    }
});