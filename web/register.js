

function addUser() {

    let user = {
        username: $('#txtUsername').val(),
        password: $('#txtPassword').val(),
        id: 0
    };

    let settings = {
        "url": "http://localhost:8080/api/restaurants/register/",
        "method": "POST",
        "timeout": 1000,
        "headers": {
            "Content-Type": "application/json"
        }, "data": JSON.stringify(user)
    }

    $('#myForm').submit(function() {
        // Get all the forms elements and their values in one step
        var values = $(this).serialize();

    });

    $('#submit').click(function(e){
        $('#txtUsername').val('');
        $('#txtPassword').val('');

        $.ajax(settings).done(function (response) {
            console.log(response)

        });

        e.preventDefault();
    })

}

$(document).ready(function () {

   addUser()

});