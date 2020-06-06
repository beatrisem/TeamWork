function authenticateUser(username, password) {
    console.log("authenticating as " + username + ", " + password);
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                if(username==="admin") {
                    window.open('admin.html');
                } else {
                    window.open('user.html?user='+username);
                }
            } else alert("Invalid username and password.")
        }
    }
    xhr.open("POST", 'http://localhost:8080/api/login', false);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send("username=" + username + "&password=" + password);
}