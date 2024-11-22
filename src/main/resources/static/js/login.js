$(document).ready(function (){
    $("body").hide().slideDown(500)
    $("#register").one('click', function (){
        window.location.href = "/register"
    })
    $("#form").one('submit', function (event){
        event.preventDefault()
        const data = {
            email: $("#email").val(),
            password: $("#password").val()
        }

        const url = '/api/auth'

        sendSavingInfos(url, data)

    })
})

function sendSavingInfos(url, data) {
    $.ajax({
        url: url,
        data: JSON.stringify(data),
        dataType: 'json',
        contentType: 'application/json',
        method: 'POST',
        success: function (data) {
            console.log(data)
            sessionStorage.setItem('token', data.token);
            $("#flag").hide().text("Connected successfully !").slideDown(300).css('color', 'green')

            setTimeout(function (){
                $("#flag").hide().text("Redirecting to your profile ...").slideDown(300)
                setTimeout(function (){
                    // fetch function
                    sendRequest('/user/profile')
                }, 2000)
            }, 1000)
        },
        error: function (error) {
            console.log("Incorrect username, or Password !")
            $("#flag").hide().text("Incorrect username, or Password !").slideDown(300).css('color', 'red')
            setTimeout(function (){
                $("#flag").hide().text("Refreshing the page ...").slideDown(300)
                setTimeout(function (){
                    // fetch function
                    window.location.href = "/login"
                }, 2000)
            }, 1000)
        }
    })
}

/*function sendRequest(url){
    fetch(url, {
        method: 'GET',
        headers: {'Authorization':'Bearer ' + sessionStorage.getItem('token') }
    })
        .then(response => response.text())
        .then(data => {
            document.open()

        });
}*/

function sendRequest(url){
    $.ajax({
        url: url,
        method: 'GET',
        beforeSend: function(request) {
            request.setRequestHeader('Authorization', 'Bearer ' + sessionStorage.getItem('token'));
        },
        dataType: 'text',
        contentType: 'application/text',
        success: function (data) {
            document.open()
            document.write(data)
            document.close()
         }
    })
}
