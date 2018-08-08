
let app = new Vue({
    el: "#app",
    data: {
        user: {},
    },
    //call on page loaded
    created() {
        request200('GET', '/in/user', null, x => {
            this.user = x;
        });
    }
});