let app = new Vue({
    el:"#app",
    data:{
            user:{}
    },
    created(){
        request200('GET', '/in/user', {}, x => {
            this.user = x;
        });
    },
    methods:{
        verify() {
            var np1 = document.getElementById("ncode").value;
            var op = document.getElementById("pcode").value;
            var npc = document.getElementById("ncodec").value;

            if(np1==npc){
                request200('POST', '/in/user/chgpwd', {p:op,np:np1}, x => {
                    if(x==1){
                        alert("修改密码成功！");
                    }
                    else{
                        alert("修改密码失败！");
                    }
                    window.location = "/home.html";
                });
            }
            else{
                alert("两次密码不一致！");
            }
        }
    }

});
