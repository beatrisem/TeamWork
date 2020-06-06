function loadRest() {
    console.log("loading rest");
    var urlParams = new URLSearchParams(window.location.search);
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                console.log("response text: "+xhr.responseText);
                const parsedJson = JSON.parse(xhr.responseText);
                document.getElementById("name").innerHTML = parsedJson.name;
                document.getElementById("editname").innerHTML = parsedJson.name;
                document.getElementById("editaddress").innerHTML = parsedJson.address;
                document.getElementById("edithomepage").innerHTML = parsedJson.homepage;
                document.getElementById("editphone").innerHTML = parsedJson.phone;
                document.getElementById("editfreetables").innerHTML = parsedJson.freeTables;
            }
        }
    }
    xhr.open("GET", 'http://localhost:8080/restaurant/' + urlParams.get('user'), false);
    xhr.send(null);
}