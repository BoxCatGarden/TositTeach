let app = new Vue({
    el: "#app",
    data: {
        user: {},

        //data
        dn: '',
        dp: '',
        file: '',

        //ctrl
        dis: false
    },
    created() {
        request200('GET', '/in/user', {}, x => {
            this.user = x;
        });
    },
    methods: {
        reset() {
            this.dn = '';
            this.dp = '';
            this.file = '';
            this.$refs.file.length = 0;
            this.$refs.file.value = '';
        },
        setfile(event) {
            this.file = event.target.files[0];
        },
        add() {
            if (!this.dn) {
                alert('请输入文件名！');
                return;
            }
            if (!this.file) {
                alert('请选择一个文件！');
                return;
            }

            this.dis = true;
            var data = new FormData();
            data.append('file', this.file);
            data.append('dn', this.dn);
            data.append('dp', this.dp);
            request200('POST', '/in/studoc/add', data, x => {
                if (x) {
                    alert('添加成功！');
                    this.reset();
                    this.dis = false;
                } else {
                    alert('添加失败！');
                    this.dis = false;
                }
            });
        }
    }
});