var restaurantGridOptions = {};
const timeout = 3000;
const baseApiUrl = 'http://localhost:8080/api/';

function selectionChanged() {
    let selectedRows = restaurantGridOptions.api.getSelectedRows();
    if (selectedRows.length > 0) {
        enableDisableEditButton(true);
    } else {
        enableDisableEditButton(false);
    }
}


function createRestaurantGrid() {
    let columnDefs = [
        // {headerName: "Id", field: "id", sortable: true, filter: true},
        {headerName: "Name", field: "name", sortable: true, filter: true},
        {headerName: "City", field: "city", sortable: true, filter: true},
        {headerName: "Address", field: "address", sortable: true, filter: true},
        {headerName: "District", field: "district", sortable: true, filter: true},
        {headerName: "Free tables", field: "freeTables", sortable: true, filter: true},
        {headerName: "Max free tables", field: "maxTables", sortable: true, filter: true},
        {headerName: "Latitude", field: "lat", sortable: true, filter: true},
        {headerName: "Longitude", field: "lng", sortable: true, filter: true},
        {headerName: "Username", field: "username", sortable: true, filter: true}
    ];

    restaurantGridOptions = {
        columnDefs: columnDefs,
        rowSelection: 'single',
        onSelectionChanged: selectionChanged
    };
    enableDisableEditButton(false);
}

function showLoader(show) {
    if (show) {
        $('#loader').css("visibility", "visible");
    } else {
        $('#loader').css("visibility", "hidden");
    }
}

function initForm() {
    $("#frmAddRest").validate( {
        rules: {
            txtName: "required",
            txtCity: "required"
        },
        messages: {
            txtName: "Name cannot be empty!",
            txtCity: "City cannot be empty!"
        },
        errorElement: "em",
        errorPlacement: function ( error, element ) {
            // Add the `help-block` class to the error element
            error.addClass( "help-block" );

            if ( element.prop( "type" ) === "checkbox" ) {
                error.insertAfter( element.parent( "label" ) );
            } else {
                error.insertAfter( element );
            }
        },
        highlight: function ( element, errorClass, validClass ) {
            $( element ).addClass( "is-invalid" ).removeClass( "is-valid" );
        },
        unhighlight: function (element, errorClass, validClass) {
            $( element ).addClass( "is-valid" ).removeClass( "is-invalid" );
        },
        submitHandler : function () {
            addOrUpdateRestaurant();
        }
    } );
}


function addOrUpdateRestaurant() {

    let restaurant = {
        id: $('#hdId').val(),
        name: $('#txtName').val(),
        city: $('#txtCity').val(),
        address: $('#txtAddress').val(),
        district: $('#txtDistrict').val(),
        freeTables: $('#txtFreeTables').val(),
        maxTables: $('#txtMaxFreeTables').val(),
        lat: $('#txtLat').val(),
        lng: $('#txtLng').val(),
        username: $('#txtUsername').val(),

    };

    let url = baseApiUrl + 'restaurants/';
    let method = 'POST';

    if (restaurant.id !== '0') {
        url += restaurant.id;
        method = 'PUT';
    }

    let settings = {
        "url": url,
        "method": method,
        "timeout": timeout,
        "headers": {
            "Content-Type": "application/json"
        },
        "data": JSON.stringify(restaurant),
    };

    showLoader(true);

    $.ajax(settings).done(function (response) {
        console.log(response);
        showLoader(false);
        loadRestaurants();
        enableDisableEditButton(false);


        if (response.valid) {
            $('#hdId').val('');
            $('#txtName').val(''),
                $('#txtCity').val(''),
                $('#txtAddress').val(''),
                $('#txtDistrict').val(''),
                $('#txtFreeTables').val(''),
                $('#txtMaxFreeTables').val(''),
                $('#txtLat').val(''),
                $('#txtLng').val(''),
                $('#txtUsername').val('')

            $('#addEditDialog').modal('hide');

            showAlert(response.valid, response.errors);
        }else{
            showModalError(true, response.errors);
        }


    }).fail(function (response) {
        alert(response);
    });
}

function showModalError(show, errors) {
    let errorAlert = $('#modal-error');
    if(show) {
        errorAlert.css('display', 'block');
        $('#modal-error > p').text('Error: '+ errors);
    } else {
        errorAlert.css('display', 'none');
    }
}

function showAlert(isSuccess, errors) {
    let alert = $('#alertBox');
    alert.css('display', 'block');
    if (isSuccess) {
        alert.addClass('alert-success');
        alert.removeClass('alert-danger');
        $('#alertBox > p').text('Record changed!');
    } else {
        alert.removeClass('alert-success');
        alert.addClass('alert-danger');
        $('#alertBox > p').text("Some errors occured: " + errors);
    }

    setTimeout(function () {
        alert.css('display', 'none');
    }, 5000);
}

function loadRestaurants() {
    showLoader(true);

    agGrid.simpleHttpRequest({url: baseApiUrl + 'restaurants/'}).then(function (data) {
        restaurantGridOptions.api.setRowData(data);
        showLoader(false);
    });
}

function showAddDialog() {
    showModalError(false, null);
    $('#addEditDialog').modal('show');
    $('#hdId').val(0);

}

function showEditDialog() {
    showModalError(false, null);
    let selectedRows = restaurantGridOptions.api.getSelectedRows();

    if (selectedRows.length === 0) {
        return;
    }

    let id = selectedRows[0].id;
    $('#hdId').val(id);

    var settings = {
        "url": baseApiUrl + "restaurants/" + id,
        "method": "GET",
        "timeout": timeout,
    };

    $.ajax(settings).done(function (response) {
        $('#txtName').val(response.name);
        $('#txtAddress').val(response.address);
        $('#txtDistrict').val(response.district);
        $('#txtFreeTables').val(response.freeTables);
        $('#txtMaxFreeTables').val(response.maxTables);
        $('#txtLat').val(response.lat);
        $('#txtLng').val(response.lng);
        $('#txtUsername').val(response.lng);

        showLoader(false);
        $('#addEditDialog').modal('show')

    });


}

function enableDisableEditButton(enable) {
    if (enable) {
        $('#btnEdit').prop('disabled', false);
    } else {
        $('#btnEdit').prop('disabled', true);
    }
}


$(document).ready(function () {

    initForm();

    //createDemoGrid();
    createRestaurantGrid();

    // lookup the container we want the Grid to use
    // let eGridDiv = document.querySelector('#result');
    let restGridDiv = document.querySelector('#restResult');

    // create the grid passing in the div to use together with the columns & data we want to use
    // new agGrid.Grid(eGridDiv, gridOptions);
    new agGrid.Grid(restGridDiv, restaurantGridOptions);

    loadRestaurants();

});