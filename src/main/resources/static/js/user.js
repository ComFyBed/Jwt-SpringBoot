$(document).ready(function (){
    $("body").hide().slideDown(500)
    $("#table").hide()
    $("#logout").one('click', function (){
        sessionStorage.clear()
        window.location.href = "/"
    })

    sendRequest('/api/user')

    $("#all").on('click', function (){
        $("#table tbody").empty()
        $("#table").show()
        showAll('api/all')

    })

})

function sendRequest(url) {
    $.ajax({
        url: url,
        method: 'POST',
        beforeSend: function (request) {
            request.setRequestHeader('Authorization', 'Bearer ' + sessionStorage.getItem('token'))
        },
        data: JSON.stringify({
            token: sessionStorage.getItem('token')
        }),
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            $("#title").text("Hello Mr. " + data.lastname + " " + data.firstname);
        },
        error: function (error) {
            console.error(error)
        }
    })
}
/*
    fetch(url, {
        method: 'POST',
        headers: {
            'Authorization': 'Bearer ' + sessionStorage.getItem('token'),
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            token: sessionStorage.getItem('token') // Use 'body' instead of 'data'
        })
    })
        .then(response => response.json())
        .then(data => {
            // Update the DOM with the returned data
            $("#title").text("Hello Mr. " + data.lastname + " " + data.firstname);
        })
        .catch(error => {
            console.error('Error:', error); // Error handling
        });*/


function showAll(url) {
    $.ajax({
        url: url,
        method: 'GET',
        beforeSend: function (request) {
            request.setRequestHeader('Authorization', 'Bearer ' + sessionStorage.getItem('token'))
        },
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            data.forEach(function (element) {
                const rows = '<tr><td>' + element.firstname + '</td>' +
                    '<td>' + element.lastname + '</td>' +
                    '<td>' + element.email + '' + '</td>' +
                    '<td>' + element.gender + '' + '</td>' +
                    '<td>' + element.age + '</td>' +
                    '</tr>'
                $("#table tbody").hide().slideDown(300).append(rows)
            })
        },
        error: function (error) {
            console.error(error)
        }
    })
}
    /*fetch(url, {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer ' + sessionStorage.getItem('token'),
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            // Update the DOM with the returned data
            data.forEach(function (element){

                const rows = '<tr><td>'+element.firstname+'</td>' +
                    '<td>'+element.lastname+'</td>' +
                    '<td>'+element.email+'' + '</td>' +
                    '<td>'+element.gender+'' + '</td>' +
                    '<td>'+element.age+'</td>' +
                    '</tr>'
                $("#table tbody").hide().slideDown(300).append(rows)
            })

        })
        .catch(error => {
            console.error('Error:', error); // Error handling
        });*/
