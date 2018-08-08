let app = new Vue({
    el: "#app",
    data: {
        //data
        task: {eng: {}},
        id: '',
        user: {},

        //mod
        plan: '',
        modFlag: 0

    },
    //call on page loaded
    created() {
        this.id = sessionStorage.getItem('param_task_detail_docId');
        request200('GET', '/in/user', {}, x => {
            this.user = x;
        });
        this.update();
    },
    //other functions
    methods: {
        update() {
            request200('GET', '/in/task/get', {ti: this.id}, x => {
                this.task = x;
                if (!this.task.eng) this.task.eng = {};
                this.reset();
            });
        },
        reset() {
            this.plan = this.task.plan;
        },
        onreset() {
            if (confirm('确认重置吗？'))
                this.reset();
        },
        save() {
            this.modFlag = 0;
            var plan = this.plan;
            if (plan == this.task.plan) alert("未做任何修改");
            else if (confirm("确认修改吗?")) request200('POST', '/in/task/mod', {tasId: this.id, plan: plan}, x => {
                if (x == 1) {
                    alert("修改成功！");
                }
                else {
                    alert("修改失败！");
                }
                this.update();
            });
            else this.update();
        }

    }
});