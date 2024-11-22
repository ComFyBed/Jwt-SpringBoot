
$(document).ready(function (){
    $("body").hide().slideDown(500)
    $("#register").one("click", function (){
        window.location.href = "/register"
    })

    $("#login").one("click", function (){
        window.location.href = "/login"
    })
})