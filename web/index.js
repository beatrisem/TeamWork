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
        {headerName: "Id", field: "id", sortable: true, filter: true},
        {headerName: "Name", field: "name", sortable: true, filter: true},
        {headerName: "City", field: "city", sortable: true, filter: true},
        {headerName: "Address", field: "address", sortable: true, filter: true},
        {headerName: "District", field: "district", sortable: true, filter: true},
        {headerName: "Free tables", field: "freeTables", sortable: true, filter: true},
        {headerName: "Max free tables", field: "maxTables", sortable: true, filter: true},
        {headerName: "Latitude", field: "lat", sortable: true, filter: true},
        {headerName: "Longitude", field: "lng", sortable: true, filter: true},
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


function addRestaurant() {
    let restaurant = {
        id: 0,
        name: $('#txtName').val(),
        city: $('#txtCity').val(),
        address: $('#txtAddress').val(),
        district: $('#txtDistrict').val(),
        freeTables: $('#txtFreeTables').val(),
        maxTables: $('#txtMaxFreeTables').val(),
        lat: $('#txtLat').val(),
        lng: $('#txtLng').val(),

    };

    let settings = {
        "url": baseApiUrl + "restaurants/",
        "method": "POST",
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

        $('#txtName').val(''),
            $('#txtCity').val(''),
            $('#txtAddress').val(''),
            $('#txtDistrict').val(''),
            $('#txtFreeTables').val(''),
            $('#txtMaxFreeTables').val(''),
            $('#txtLat').val(''),
            $('#txtLng').val(''),

        $('#addEditDialog').modal('hide')


    });
}

function loadRestaurants() {
    showLoader(true);

    agGrid.simpleHttpRequest({url: baseApiUrl + 'restaurants/'}).then(function (data) {
        restaurantGridOptions.api.setRowData(data);
        showLoader(false);
    });
}

function showAddDialog() {
    $('#addEditDialog').modal('show')
}

function showEditDialog() {

    let selectedRows = restaurantGridOptions.api.getSelectedRows();
    if (selectedRows.length === 0) {
        return;
    }
    let id = selectedRows[0].id;

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