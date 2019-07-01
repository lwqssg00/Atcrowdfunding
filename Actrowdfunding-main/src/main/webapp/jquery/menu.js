function selectBar() {

    var path = window.location.pathname;

    var project_name = "${APP_PATH}";


    var real_path =path.substr(project_name.length);

    var atar = $(".list-group-item ul li a[href*='"+real_path+"']")

    atar.css({"color":"red"});

    atar.parent().parent().parent().removeClass("tree-closed");

    atar.parent().parent().show();

}