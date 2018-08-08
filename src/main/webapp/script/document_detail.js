
let app = new Vue({
    el: "#app",
    data: {
        user:{},

        //data
        doc:{gp:{},pro:{}},
        id:'',

        score:-1,
        file:'',

        //ctrl
        scFlag:0,
        dis:false
    },
    //call on page loaded
    created() {
        this.id = sessionStorage.getItem('param_doc_detail_docId');
        request200('GET', '/in/user', null, x => {
            this.user = x;
        });
        this.update();
    },
    //other functions
    methods: {
        update(){
            request200('GET', '/in/studoc/get', {di:this.id}, x => {
                if (!x.gp) x.gp={};
                if (!x.pro) x.pro={};
                this.doc = x;
                this.reset();
            });
        },
        reset() {
            this.score = this.doc.score==-1?'':this.doc.score+'';
        },

        //score
        doscore(){
            var sc = this.score*1;
            if (!this.score || (!sc&&sc!=0) || sc < 0 || 100 < sc || Math.floor(sc)<sc) {
                alert('请输入有效的分数！（0~100的整数）');
                this.reset();
                return;
            }

            if (sc == this.doc.score) {
                alert('评分没有改变！');
                this.scFlag=0;
                return;
            }
            this.dis = true;
            request200('POST', '/in/studoc/score', {di:this.id,sc:sc}, x => {
                if(x){
                    alert("评分成功！");
                    this.dis=false;
                    this.scFlag=0;
                    this.update();
                } else {
                    alert("评分失败！");
                    this.dis=false;
                }
            });
        },
        onreset() {
            if (confirm('确认重置吗？')) {
                this.reset();
            }
        },

        //file
        setfile(event) {
            this.file = event.target.files[0];
        },
        reupload() {
            if (this.file) {
                var data = new FormData();
                data.append('file',this.file);
                data.append('di',this.doc.docId);
                request200('POST','/in/studoc/mod',data, x=>{
                    if (x) {
                        alert('重新上传成功！');
                        window.location=window.location.pathname;
                    } else alert('上传失败！');
                });
            } else alert('未选择文件！');
        }

    }
});