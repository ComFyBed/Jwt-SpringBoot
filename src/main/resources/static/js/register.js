$(document).ready(function (){
    $("body").hide().slideDown(500)
    $("#home").one("click", function (){
        window.location.href = "/"
    })

    $("#form").one("submit", function (event){
        event.preventDefault()
        const data = {
            firstname: $("#firstname").val(),
            lastname: $("#lastname").val(),
            age : $("#age").val(),
            email: $("#email").val(),
            password: $("#password").val(),
            gender: $("#gender").val()
        }

        const url = "/api/register"

        sendSavingInfos(url, data)
    })


})

function sendSavingInfos(url, data){

    $.ajax({
        url: url,
        data: JSON.stringify(data),
        dataType: 'json',
        contentType: 'application/json',
        method: 'POST',
        success: function (data){
            console.log(data)
            $("#flag").hide().text("Saved successfully !").slideDown(300).css('color', 'green')

            setTimeout(function (){
                $("#flag").hide().text("Redirecting to home page ...").slideDown(300)
                setTimeout(function (){
                    window.location.href= "/"
                }, 2000)
            }, 1000)
        },  
        error: function (data){
            console.error(data)
        }

    })
}