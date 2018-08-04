let app = new Vue({
    el:"#app",
    data:{
        engs:[],
        user:{}
    },
    create(){
        request200('GET', '/in/user', {}, x => {
            this.user = x;
        });
        this.update();
    },
    method:{

        update(){
            request200('GET', '/in/eng',  {st:0,nm:0}, x => {
                this.engs = x;
            });
        },
        addt(){
            var input_tname = document.getElementById("tname").value;
            var input_index = document.getElementById("eng").index;
            var input_engId = this.engs[input_index].eng.id;
            var input_st = document.getElementById("st-ptime").value;
            var input_ed = document.getElementById("ed_ptime").value;
            var input_disp = document.getElementById("disp").value;
            request200('POST', '/in/eng/add',  {tasName:input_tname,stTime:input_st,edTime:input_ed,disp:input_disp,userId:input_engId}, x => {
                if(x==1){
                    alert("添加任务成功！");
                }
                else{
                    alert("添加任务失败！")
                }
            });
        }


    }

});