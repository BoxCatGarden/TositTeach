let app = new Vue({
    el: "#app",
    data: {
        //data
        clas: [],
        stus:[],
        pros:[],
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
            //所有的班级
            request200('GET', '/in/cla', {st: 0, nm: 0}, x => {
                this.clas = x;
            });
            request200('GET', '/in/pro', {st: 0, nm: 0}, x => {
                this.pros = x;
            });
            request200('GET', '/in/stu', {st: (this.currPage - 1) * this.pageSize, nm: this.pageSize}, x => {
                this.totalPage = this.pageSize * 1 && Math.ceil(x.total / this.pageSize);
                if (this.currPage > this.totalPage) this.currPage = this.totalPage || 1;
                this.stus = x;
            });
        },
        jump(i) {
            this.currPage = i;
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
        pan() {
            var type = document.getElementById("sousuokey").selectedIndex;
            var ban = document.getElementById("suosou");
            var index = ban.selectedIndex;
            var claid = ban.options[index].value;
            var can = {};
            if (type == 1) {//分班
                can = {st: (this.currPage - 1) * this.pageSize, nm: this.pageSize, ci: 'n'};

            }
            else if (type == 2) {//分组
                can = {st: (this.currPage - 1) * this.pageSize, nm: this.pageSize, ci: claid}
            }
            request200('GET', '/in/stu', can, x => {
                this.totalPage = this.pageSize * 1 && Math.ceil(x.total / this.pageSize);
                if (this.currPage > this.totalPage) this.currPage = this.totalPage || 1;
                this.stus = x;
            });
        },
        showDiv() {
            var type = document.getElementById("sousuokey").selectedIndex;
            var ban = document.getElementById("suosou");
            var index = ban.selectedIndex;
            var claid = ban.options[index].value;
            var arr = [];//所有学生id
            arr.push(claid);
            $("input[type='checkbox']:checked").each(
                function () {
                    arr.push($(this).val());
                }
            );
            if (type == 1) {
                request200('POST', '/in/cla/addstu', {ids:arr}, x => {
                    if(x==1){
                        alert("添加成功！");
                    }
                    else{
                        alert("添加失败！");
                    }
                    return;
                });

            }
            else if(type==2){//显示弹框
                var x = document.getElementById("groupbox");
                x.style.width = "600px";
                x.style.background="gainsboro";
                x.style.display = "block";
                x.style.float = "left";
                x.style.position = "absolute";
                x.style.left = "400px";
                x.style.top = "400px";
                x.style.border = "solid 1px gainsboro";
                x.style.borderRadius = "10%";

            }
        },
        hiddenDiv() {
            var x = document.getElementById("groupbox");
            x.style.display = "none";
        },
        fg(){
                var name = document.getElementById("gpname");
                var pro =  document.getElementById("fpro");
                var index = pro.selectedIndex;
                var proid = pro.options[index].value;
                var arr = [];//所有学生id
                arr.push(name);
                arr.push(claid);
                arr.push(proid);
                $("input[type='checkbox']:checked").each(
                    function () {
                        arr.push($(this).val());
                    }
                );
                request200('POST', '/in/gp/group', {ids:arr}, x => {
                    if(x==1){
                        alert("添加成功！");
                    }
                    else{
                        alert("添加失败！");
                    }
                    return;
                });
        }

    }
});