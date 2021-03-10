function login()
{
    var user = document.getElementById("current-user").innerHTML;
    if(user!="" && user!="Login")
        alert("이미 로그인 되어있습니다.");
    else
        location.href = "./login";
}
function design()
{
    var user = document.getElementById("current-user").innerHTML;
    if(user=="" || user=="Login")
        alert("로그인이 되어있지 않습니다.");
    else
        location.href = "./design";
}
function register()
{
    var user = document.getElementById("current-user").innerHTML;
    if(user!="" && user!="Login")
        alert("이미 로그인 되어있습니다.");
    else
        location.href = "./register";
}