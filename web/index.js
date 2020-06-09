const baseApiUrl = 'http://localhost:8080/api/';



function initTypeahead() {

    console.log("inside");

    $.typeahead({
        input: '.js-typeahead-car_v1',
        minLength: 1,
        order: "asc",
        offset: true,
        hint: true,
        dynamic: true,
        delay: 300,
        template: '<span>' +
            '<br><span class="name">{{name}}</span></br>' +
            '<span class="city">{{city}},&nbsp;</span>' +
            '<span class="address">{{address}}' +
            '</span>',
        source: {
            restaurants: {
                display: ["name", "city", "address"],
                ajax: function (query) {
                    return {
                        type: "GET",
                        url: baseApiUrl + "restaurants/filtered",
                        data: {
                            query: "{{query}}"
                        },

                    }
                }

            }

        },
        callback: {
            onClick: function (node, a, item, event) {
                document.getElementById("match-name").innerHTML = item.name;
                document.getElementById("match-city").innerHTML = item.city;
                document.getElementById("match-address").style.visibility = 'visible';
                document.getElementById("match-address").innerHTML += item.address;
                document.getElementById("match-tables").style.visibility = 'visible';
                document.getElementById("match-tables").innerHTML += item.freeTables;
            },
        }
    });
}

$(document).ready(function () {


    initTypeahead();

});